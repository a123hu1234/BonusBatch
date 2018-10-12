package com.huateng.batch.configuration;


import javax.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.Processor.AccountTmpItemProcessor;
import com.huateng.batch.listener.AccountJobListener;
import com.huateng.batch.listener.CsvJobListener;
import com.huateng.batch.model.TblAccountInfTmp;
import com.huateng.batch.model.TblCustInf;
import com.huateng.batch.validator.AccountBeanValidator;
import com.huateng.batch.validator.CsvBeanValidator;
import com.huateng.util.Util;

/***
 * 导入数据到Account临时表
 * 
 * @author data
 *
 */
@Configuration
@EnableBatchProcessing
public class TblAccountInfTmpConfig {

	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Bean
	public ItemProcessor<TblAccountInfTmp, TblAccountInfTmp> processor() {
		AccountTmpItemProcessor processor = new AccountTmpItemProcessor();
	//	processor.setValidator(AccountBeanValidator());
		return processor;
	}



	@Bean
	public Job importAccountTmpJob(JobBuilderFactory jobs,  @Qualifier("importAccountInfTmpStep")Step importAccountInfTmpStep,@Qualifier("accountInfTmpToAccountInf")Step accountInfTmpToAccountInf) {
		return jobs.get("importAccountTmpJob").incrementer(new RunIdIncrementer()).listener(accountJobListener()).flow(importAccountInfTmpStep).next(accountInfTmpToAccountInf).end()
				.build();
	}
	//步骤一 文件入临时表
	@Bean
	public Step importAccountInfTmpStep(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<TblAccountInfTmp> accountTmpFileReader,
			@Qualifier("accountSave")ItemWriter<TblAccountInfTmp> accountSave, ItemProcessor<TblAccountInfTmp, TblAccountInfTmp> processor) {
		return stepBuilderFactory.get("importAccountInfTmp").<TblAccountInfTmp, TblAccountInfTmp>chunk(65000).reader(accountTmpFileReader)
				.processor(processor).writer(accountSave).build();
	}
	
	/*
	 *  步骤二 从临时表到主表
	 */
	@Bean
	public Step accountInfTmpToAccountInf(StepBuilderFactory stepBuilderFactory, ItemReader<TblAccountInfTmp> accountTmpDataBaseReader,
			@Qualifier("tblAccountInftItemWriter")ItemWriter<TblAccountInfTmp> tblAccountInftItemWriter, ItemProcessor<TblAccountInfTmp, TblAccountInfTmp> accountProcessor) {
		return stepBuilderFactory.get("accountInfTmpToAccountInf").<TblAccountInfTmp, TblAccountInfTmp>chunk(65000).reader(accountTmpDataBaseReader).writer(tblAccountInftItemWriter).build();
	}


	@Bean
	public AccountJobListener accountJobListener() {
		return new AccountJobListener();
	}

	@Bean
	public Validator<TblAccountInfTmp> csvBeanValidator() {
		return new AccountBeanValidator<TblAccountInfTmp>();
	}
	
	
	//数据处理 暂不处理数据
	@Bean
	@StepScope
	public ItemProcessor<TblAccountInfTmp, TblAccountInfTmp> accountProcessor(ItemReader<TblAccountInfTmp> accountTmpDataBaseReader) {
		AccountTmpItemProcessor processor = new AccountTmpItemProcessor();
		processor.setValidator(csvBeanValidator());
		return processor;
	}
	
	
	
	
}
