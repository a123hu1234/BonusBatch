package com.huateng.batch.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;

import com.huateng.batch.Processor.TblTxnOraDailyItemProcessor;
import com.huateng.batch.listener.TblTxnOraDailyJobListener;
import com.huateng.batch.model.TblTxnOraDaily;
import com.huateng.batch.validator.CsvBeanValidator;
import com.huateng.util.Util;

/***
 * 加载交易数据入交易详情表
 * @author 11299
 *
 */
@Configuration
@EnableBatchProcessing
public class TblTxnOraDailyConfig {

	
	
	@Bean
	@StepScope
	public FlatFileItemReader<TblTxnOraDaily> dailyReader(@Value("#{jobParameters['input.file.name']}") String pathToFile)
			throws Exception {
		FlatFileItemReader<TblTxnOraDaily> reader = new FlatFileItemReader<TblTxnOraDaily>(); //
		reader.setResource(new FileUrlResource(pathToFile)); //
		reader.setLineMapper(new DefaultLineMapper<TblTxnOraDaily>() {
			{ //
				setLineTokenizer(new DelimitedLineTokenizer(Util.asciiToStrings("0x03")) {
					{
						setNames(TblTxnOraDaily.toArray());
						
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<TblTxnOraDaily>() {
					{
						setTargetType(TblTxnOraDaily.class);
					}
				});
			}
		});
		
		return reader;
	}

	@Bean
	public ItemProcessor<TblTxnOraDaily, TblTxnOraDaily> dailyProcessor() {
		TblTxnOraDailyItemProcessor processor = new TblTxnOraDailyItemProcessor();
		processor.setValidator(tblTxnOraDailyValidator());
		
		return processor;
	}

	@Bean
	public ItemWriter<TblTxnOraDaily> dailyWriter(DataSource dataSource) {
		JdbcBatchItemWriter<TblTxnOraDaily> writer = new JdbcBatchItemWriter<TblTxnOraDaily>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<TblTxnOraDaily>());
		String sql = "insert into " + TblTxnOraDaily.getTableClum() + "values(" + TblTxnOraDaily.getBeanClum() + ")";
		writer.setSql(sql); // 3
		writer.setDataSource(dataSource);
		return writer;
	}


	/*
	 * 1.importCustTmpJob 任务入口 
	 * 
	 */
	
	@Bean
	public Job importTblTxnOraDailyJob(JobBuilderFactory jobs,  @Qualifier("importTblTxnOraDailyStep")Step importTblTxnOraDailyStep) {
		return jobs.get("importCustTmpJob").incrementer(new RunIdIncrementer()).listener(tblTxnOraDailyListener()).flow(importTblTxnOraDailyStep).end()
				.build();
	}
	//step1.导入交易数据
	@Bean
	public Step importTblTxnOraDailyStep(StepBuilderFactory stepBuilderFactory,  FlatFileItemReader<TblTxnOraDaily> dailyReader,DataSource dataSource) {
		return stepBuilderFactory.get("importCustInfTmp").<TblTxnOraDaily, TblTxnOraDaily>chunk(65000).reader(dailyReader)
				.processor(dailyProcessor()).writer(dailyWriter(dataSource)).build();
	}
	
	

	@Bean
	public TblTxnOraDailyJobListener tblTxnOraDailyListener() {
		return new TblTxnOraDailyJobListener();
	}

	@Bean
	public Validator<TblTxnOraDaily> tblTxnOraDailyValidator() {
		return new CsvBeanValidator<TblTxnOraDaily>();
	}
	
	
	
	

	


}
