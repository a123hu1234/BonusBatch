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

import com.huateng.batch.ItemWriter.CardTempItemWriter;
import com.huateng.batch.Processor.CardTmpItemProcessor;
import com.huateng.batch.listener.CardJobListener;
import com.huateng.batch.model.TblCardInf;
import com.huateng.batch.model.TblCardInfTmp;
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
public class TblCardtInfTmpConfig {

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	@Qualifier("cardTmpFileReader")
	FlatFileItemReader cardTmpFileReader;
	
	/*@Autowired
	@Qualifier("cardTmpProcessor")
	CardTmpItemProcessor cardTmpProcessor;*/
	@Autowired
	@Qualifier("cardTempItemWriter")
	CardTempItemWriter cardTempItemWriter;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("cardItemWriter")
	ItemWriter cardItemWriter;

	@Autowired
	@Qualifier("cardTmpDataBaseReader")
	JdbcPagingItemReader cardTmpDataBaseReader;
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JobBuilderFactory jobs;
	
	@Bean
	public ItemProcessor<TblCardInfTmp, TblCardInfTmp> processor() {
		CardTmpItemProcessor processor = new CardTmpItemProcessor();
		processor.setValidator(cardBeanValidator());
		return processor;
	}



	/*
	 * 1.importCardTmpJob 任务入口 
	 * 
	 */
	
	@Bean
	public Job importCardTmpJob(JobBuilderFactory jobs,  @Qualifier("importCardInfTmpStep")Step importCardInfTmpStep,@Qualifier("cardInfTmpToCardInf")Step cardInfTmpToCardInf) {
		return jobs.get("importCardTmpJob").incrementer(new RunIdIncrementer()).listener(cardJobListener()).flow(importCardInfTmpStep).next(cardInfTmpToCardInf).end()
				.build();
	}
	//导入流程  文件到临时表
	@Bean
	public Step importCardInfTmpStep() {
		return stepBuilderFactory.get("importCardInfTmp").<TblCardInfTmp, TblCardInfTmp>chunk(65000).reader(cardTmpFileReader).writer(cardTempItemWriter).build();
	}
	
	/*
	 * 从临时表到主表
	 */
	@Bean
	public Step cardInfTmpToCardInf() {
		return stepBuilderFactory.get("cardInfTmpToCardInf").<TblCardInf, TblCardInf>chunk(65000).reader(cardTmpDataBaseReader).writer(cardItemWriter).build();
	}


	@Bean
	public CardJobListener cardJobListener() {
		return new CardJobListener();
	}

	@Bean
	public Validator<TblCardInfTmp> cardBeanValidator() {
		return new CsvBeanValidator<TblCardInfTmp>();
	}
	
	
	
	//数据处理 暂不处理数据
	@Bean
	@StepScope
	public ItemProcessor<TblCardInfTmp, TblCardInfTmp> cardProcessor(ItemReader<TblCardInfTmp> cardTmpDataBaseReader) {
		CardTmpItemProcessor processor = new CardTmpItemProcessor();
		processor.setValidator(cardBeanValidator());
		return processor;
	}
	
	
	
	
	
}
