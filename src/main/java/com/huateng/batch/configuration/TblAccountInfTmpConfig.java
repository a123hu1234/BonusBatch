package com.huateng.batch.configuration;

import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

import com.huateng.batch.ItemReader.AccountTmpReader;
import com.huateng.batch.ItemWriter.AccountTempItemWriter;
import com.huateng.batch.Processor.AccountTmpItemProcessor;
import com.huateng.batch.listener.AccountJobListener;
import com.huateng.batch.model.TblAccountInf;
import com.huateng.batch.model.TblAccountInfTmp;
import com.huateng.batch.validator.CsvBeanValidator;
import com.huateng.util.Util;

/***
 * 导入数据到客户临时表
 * 
 * @author data
 *
 */
@Configuration
@EnableBatchProcessing
public class TblAccountInfTmpConfig {

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	@Qualifier("accountTmpFileReader")
	FlatFileItemReader accountTmpFileReader;
	
	/*@Autowired
	@Qualifier("accountTmpProcessor")
	AccountTmpItemProcessor accountTmpProcessor;*/
	@Autowired
	@Qualifier("accountTempItemWriter")
	AccountTempItemWriter accountTempItemWriter;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("accountItemWriter")
	ItemWriter accountItemWriter;

	@Autowired
	@Qualifier("accountTmpDataBaseReader")
	JdbcPagingItemReader accountTmpDataBaseReader;
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JobBuilderFactory jobs;
	
	@Bean
	public ItemProcessor<TblAccountInfTmp, TblAccountInfTmp> processor() {
		AccountTmpItemProcessor processor = new AccountTmpItemProcessor();
		processor.setValidator(accountBeanValidator());
		return processor;
	}



	/*
	 * 1.importAccountTmpJob 任务入口 
	 * 
	 */
	
	@Bean
	public Job importAccountTmpJob(JobBuilderFactory jobs,  @Qualifier("importAccountInfTmpStep")Step importAccountInfTmpStep,@Qualifier("accountInfTmpToAccountInf")Step accountInfTmpToAccountInf) {
		return jobs.get("importAccountTmpJob").incrementer(new RunIdIncrementer()).listener(accountJobListener()).flow(importAccountInfTmpStep).next(accountInfTmpToAccountInf).end()
				.build();
	}
	//导入流程  文件到临时表
	@Bean
	public Step importAccountInfTmpStep() {
		return stepBuilderFactory.get("importAccountInfTmp").<TblAccountInfTmp, TblAccountInfTmp>chunk(65000).reader(accountTmpFileReader).writer(accountTempItemWriter).build();
	}
	
	/*
	 * 从临时表到主表
	 */
	@Bean
	public Step accountInfTmpToAccountInf() {
		return stepBuilderFactory.get("accountInfTmpToAccountInf").<TblAccountInf, TblAccountInf>chunk(65000).reader(accountTmpDataBaseReader).writer(accountItemWriter).build();
	}


	@Bean
	public AccountJobListener accountJobListener() {
		return new AccountJobListener();
	}

	@Bean
	public Validator<TblAccountInfTmp> accountBeanValidator() {
		return new CsvBeanValidator<TblAccountInfTmp>();
	}
	
	
	
	//数据处理 暂不处理数据
	@Bean
	@StepScope
	public ItemProcessor<TblAccountInfTmp, TblAccountInfTmp> accountProcessor(ItemReader<TblAccountInfTmp> accountTmpDataBaseReader) {
		AccountTmpItemProcessor processor = new AccountTmpItemProcessor();
		processor.setValidator(accountBeanValidator());
		return processor;
	}
	
	//从文件加载帐户数据
	@Bean
	@StepScope
	public FlatFileItemReader<TblAccountInfTmp> accountTmpFileReader(@Value("#{jobParameters['input.file.name']}") String pathToFile)
			throws Exception {
		
		FlatFileItemReader<TblAccountInfTmp> reader = new AccountTmpReader().
				accountTmpFileReader(pathToFile);

		return reader;
	}
	
	//从临时表加载账户数据
	@Bean
	public JdbcPagingItemReader<TblAccountInfTmp> accountTmpDataBaseReader(DataSource dataSource)
			throws Exception {
		JdbcPagingItemReader<TblAccountInfTmp> reader = new AccountTmpReader().
				accountTmpDataBaseReader(dataSource);
		return reader;

	}
	
	
}
