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
import org.springframework.batch.item.ItemReader;
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



	@Bean
	public Job importCustTmpJob(JobBuilderFactory jobs,  @Qualifier("importCustInfTmpStep")Step importCustInfTmp) {
		return jobs.get("importCustTmpJob").incrementer(new RunIdIncrementer()).flow(importCustInfTmp).end()
				.listener(csvJobListener()).build();
	}

	@Bean
	public Step importCustInfTmpStep(StepBuilderFactory stepBuilderFactory, ItemReader<TblCustInfTmp> reader,
			ItemWriter<TblCustInfTmp> custTmpWriter, ItemProcessor<TblCustInfTmp, TblCustInfTmp> processor) {
		return stepBuilderFactory.get("importCustInfTmp").<TblCustInfTmp, TblCustInfTmp>chunk(65000).reader(reader)
				.processor(processor).writer(custTmpWriter).build();
	}

	@Bean
	public CsvJobListener csvJobListener() {
		return new CsvJobListener();
	}

	@Bean
	public Validator<TblCustInfTmp> csvBeanValidator() {
		return new CsvBeanValidator<TblCustInfTmp>();
	}

}
