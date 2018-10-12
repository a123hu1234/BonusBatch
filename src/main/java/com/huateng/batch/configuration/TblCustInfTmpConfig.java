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

import com.huateng.batch.Processor.CustTmpItemProcessor;
import com.huateng.batch.listener.CsvJobListener;
import com.huateng.batch.model.TblCustInfTmp;
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
public class TblCustInfTmpConfig {

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Bean
	@StepScope
	public FlatFileItemReader<TblCustInfTmp> custTmpReader(@Value("#{jobParameters['input.file.name']}") String pathToFile)
			throws Exception {
		FlatFileItemReader<TblCustInfTmp> reader = new FlatFileItemReader<TblCustInfTmp>(); //
		reader.setResource(new FileUrlResource(pathToFile)); //
		reader.setLineMapper(new DefaultLineMapper<TblCustInfTmp>() {
			{ //
				setLineTokenizer(new DelimitedLineTokenizer(Util.asciiToStrings("0x03")) {
					{
						setNames(TblCustInfTmp.toArray());
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<TblCustInfTmp>() {
					{
						setTargetType(TblCustInfTmp.class);
					}
				});
			}
		});

		return reader;
	}

	@Bean
	public ItemProcessor<TblCustInfTmp, TblCustInfTmp> processor() {
		CustTmpItemProcessor processor = new CustTmpItemProcessor();
		processor.setValidator(csvBeanValidator());
		return processor;
	}

	@Bean
	public ItemWriter<TblCustInfTmp> custTmpWriter(DataSource dataSource) {
		JdbcBatchItemWriter<TblCustInfTmp> writer = new JdbcBatchItemWriter<TblCustInfTmp>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<TblCustInfTmp>());
		String sql = "insert into " + TblCustInfTmp.getTableClum() + "values(" + TblCustInfTmp.getBeanClum() + ")";
		writer.setSql(sql); // 3
		writer.setDataSource(dataSource);
		return writer;
	}


	/*
	 * 1.importCustTmpJob 任务入口 
	 * 
	 */
	
	@Bean
	public Job importCustTmpJob(JobBuilderFactory jobs,  @Qualifier("importCustInfTmpStep")Step importCustInfTmpStep,@Qualifier("custInfTmpToCustInf")Step custInfTmpToCustInf) {
		return jobs.get("importCustTmpJob").incrementer(new RunIdIncrementer()).listener(csvJobListener()).flow(importCustInfTmpStep).next(custInfTmpToCustInf).end()
				.build();
	}
	//导入流程
	@Bean
	public Step importCustInfTmpStep(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<TblCustInfTmp> reader,
			ItemWriter<TblCustInfTmp> cardSave, ItemProcessor<TblCustInfTmp, TblCustInfTmp> processor) {
		return stepBuilderFactory.get("importCustInfTmp").<TblCustInfTmp, TblCustInfTmp>chunk(65000).reader(reader)
				.processor(processor).writer(cardSave).build();
	}
	
	/*
	 * 从临时表到主表
	 */
	@Bean
	public Step custInfTmpToCustInf(StepBuilderFactory stepBuilderFactory, ItemReader<TblCustInfTmp> custTmpDataBaseReader,
			ItemWriter<TblCustInfTmp> custItemWriter, ItemProcessor<TblCustInfTmp, TblCustInfTmp> custProcessor) {
		return stepBuilderFactory.get("custInfTmpToCustInf").<TblCustInfTmp, TblCustInfTmp>chunk(65000).reader(custTmpDataBaseReader).writer(custItemWriter).build();
	}


	@Bean
	public CsvJobListener csvJobListener() {
		return new CsvJobListener();
	}

	@Bean
	public Validator<TblCustInfTmp> csvBeanValidator() {
		return new CsvBeanValidator<TblCustInfTmp>();
	}
	
	
	
	//数据处理 暂不处理数据
	@Bean
	@StepScope
	public ItemProcessor<TblCustInfTmp, TblCustInfTmp> custProcessor(ItemReader<TblCustInfTmp> custTmpDataBaseReader) {
		CustTmpItemProcessor processor = new CustTmpItemProcessor();
		processor.setValidator(csvBeanValidator());
		return processor;
	}
	
	@Bean
	public JdbcBatchItemWriter<TblCustInfTmp> custTmpDeleteOrUpdate(DataSource dataSource) throws SQLException {
	
		Connection conn = dataSource.getConnection();
		String sql ="delete from  tbl_cust_inf a  where  exists (select 1 from tbl_cust_inf_tmp b  where  b.cust_id =a.cust_id) ";
		//删除主表数据
		PreparedStatement deletePs =conn.prepareStatement(sql);
		deletePs.execute();
		//将临时表数据导入主表

		JdbcBatchItemWriter<TblCustInfTmp> writer = new JdbcBatchItemWriter<TblCustInfTmp>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<TblCustInfTmp>());
		String insertSql = "insert into " + TblCustInfTmp.getTblCustInfTableClum() + "values(" + TblCustInfTmp.getBeanClum() + ")";
		writer.setSql(insertSql); // 3
		writer.setDataSource(dataSource);
		return writer;
	
		
		
		
		
	}
	
	@Bean
	public ItemWriter<TblCustInfTmp> custSave(DataSource dataSource,List<TblCustInfTmp> listTblCustInfTmp) {
		JdbcBatchItemWriter<TblCustInfTmp> insertWriter = new JdbcBatchItemWriter<TblCustInfTmp>();
		insertWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<TblCustInfTmp>());
			String sql = "insert into " + TblCustInfTmp.getTableClum() + "values(" + TblCustInfTmp.getBeanClum() + ")";
			insertWriter.setSql(sql); // 3
		//deleteWriter.setSql("delete from  tbl_cust_inf where cust_id='“”'"); // 3
		insertWriter.setDataSource(dataSource);
		
		return insertWriter;
	}
	
	
}
