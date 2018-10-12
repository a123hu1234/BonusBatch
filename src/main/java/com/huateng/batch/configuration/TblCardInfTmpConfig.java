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

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.Processor.CardTmpItemProcessor;
import com.huateng.batch.listener.CardJobListener;
import com.huateng.batch.listener.CsvJobListener;
import com.huateng.batch.model.TblCardInfTmp;
import com.huateng.batch.model.TblCustInf;
import com.huateng.batch.validator.CardBeanValidator;
import com.huateng.batch.validator.CsvBeanValidator;
import com.huateng.util.Util;

/***
 * 导入数据到card临时表
 * 
 * @author data
 *
 */
@Configuration
@EnableBatchProcessing
public class TblCardInfTmpConfig {

	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Bean
	public ItemProcessor<TblCardInfTmp, TblCardInfTmp> processor() {
		CardTmpItemProcessor processor = new CardTmpItemProcessor();
	//	processor.setValidator(CardBeanValidator());
		return processor;
	}



	@Bean
	public Job importCardTmpJob(JobBuilderFactory jobs,  @Qualifier("importCardInfTmpStep")Step importCardInfTmpStep,@Qualifier("cardInfTmpToCardInf")Step cardInfTmpToCardInf) {
		return jobs.get("importCardTmpJob").incrementer(new RunIdIncrementer()).listener(cardJobListener()).flow(importCardInfTmpStep).next(cardInfTmpToCardInf).end()
				.build();
	}
	//导入流程
	@Bean
	public Step importCardInfTmpStep(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<TblCardInfTmp> cardTmpFileReader,
			@Qualifier("cardSave")ItemWriter<TblCardInfTmp> cardSave, ItemProcessor<TblCardInfTmp, TblCardInfTmp> processor) {
		return stepBuilderFactory.get("importCardInfTmp").<TblCardInfTmp, TblCardInfTmp>chunk(65000).reader(cardTmpFileReader)
				.processor(processor).writer(cardSave).build();
	}
	
	/*
	 * 从临时表到主表
	 */
	@Bean
	public Step cardInfTmpToCardInf(StepBuilderFactory stepBuilderFactory, ItemReader<TblCardInfTmp> cardTmpDataBaseReader,
			@Qualifier("tblCardInftItemWriter")ItemWriter<TblCardInfTmp> tblCardInftItemWriter, ItemProcessor<TblCardInfTmp, TblCardInfTmp> cardProcessor) {
		return stepBuilderFactory.get("cardInfTmpToCardInf").<TblCardInfTmp, TblCardInfTmp>chunk(65000).reader(cardTmpDataBaseReader).writer(tblCardInftItemWriter).build();
	}


	@Bean
	public CardJobListener cardJobListener() {
		return new CardJobListener();
	}

	@Bean
	public Validator<TblCardInfTmp> csvBeanValidator() {
		return new CardBeanValidator<TblCardInfTmp>();
	}
	
	
	//数据处理 暂不处理数据
	@Bean
	@StepScope
	public ItemProcessor<TblCardInfTmp, TblCardInfTmp> cardProcessor(ItemReader<TblCardInfTmp> cardTmpDataBaseReader) {
		CardTmpItemProcessor processor = new CardTmpItemProcessor();
		processor.setValidator(csvBeanValidator());
		return processor;
	}
	
/*	@Bean
	public JdbcBatchItemWriter<TblCardInfTmp> cardTmpDeleteOrUpdate(DataSource dataSource) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql ="delete from  tbl_card_inf a  where  exists (select 1 from tbl_card_inf_tmp b  where  b.card_id =a.card_id) ";
		//删除主表数据
		PreparedStatement deletePs =conn.prepareStatement(sql);
		deletePs.execute();
		//将临时表数据导入主表

		JdbcBatchItemWriter<TblCardInfTmp> writer = new JdbcBatchItemWriter<TblCardInfTmp>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<TblCardInfTmp>());
	//	String insertSql = "insert into " + TblCardInfTmp.getTblCardInfTableClum() + "values(" + TblCardInfTmp.getBeanClum() + ")";
		String insertSql = BeanPropertyRowMapper.getInsertSql(TblCardInfTmp.class);
		writer.setSql(insertSql); // 3
		writer.setDataSource(dataSource);
		return writer;
	}*/
	
	
	
}
