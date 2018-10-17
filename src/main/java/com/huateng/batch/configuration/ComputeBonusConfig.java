package com.huateng.batch.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huateng.batch.ItemReader.BonusBeanReader;
import com.huateng.batch.ItemWriter.BonusAcctInfWriter;
import com.huateng.batch.Processor.ComputeBonusItemProcessor;
import com.huateng.batch.listener.ComputeBonusJobListener;
import com.huateng.batch.model.BonusBean;
import com.huateng.batch.validator.BonusBeanValidator;
@Configuration
@EnableBatchProcessing
public class ComputeBonusConfig {
	private Logger logger= LoggerFactory.getLogger(ComputeBonusConfig.class);
	private String task_id="1004";
	
	@Autowired
	@Qualifier("bonusAcctInfWriter")
	private BonusAcctInfWriter bonusAcctInfWriter;

	@Bean
	@StepScope
	public ItemProcessor<BonusBean, BonusBean> processor() {
		ComputeBonusItemProcessor processor = new ComputeBonusItemProcessor();
		processor.setValidator(bonusBeanValidator());
		return processor;
	}

	@Bean
	public Job computeBonusJob(JobBuilderFactory jobs,StepBuilderFactory stepBuilderFactory,DataSource dataSource
			) throws Exception {
		return jobs.get("computeBonusJob").incrementer(new RunIdIncrementer()).listener(computeBonusJobListener())
				.flow(computeBonusStep(stepBuilderFactory,dataSource)).end().build();
	}

	// 步骤一 文件入临时表
	@Bean
	public Step computeBonusStep(StepBuilderFactory stepBuilderFactory,DataSource dataSource) {
		Step step = null;
		try {
			 step = stepBuilderFactory.get("importAccountInfTmp").<BonusBean, BonusBean>chunk(5000)
			.reader(bonusBeanReader(dataSource)).processor(processor()).writer(bonusAcctInfWriter).build();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return step;
	}

	@Bean
	public ItemReader<BonusBean> bonusBeanReader(DataSource dataSource) throws Exception {
		ItemReader<BonusBean> reader = new BonusBeanReader() {
			{
				setDataSource(dataSource);
			}
		}.getReader2();
		
		return reader;

	}
	
	

	@Bean
	public ComputeBonusJobListener computeBonusJobListener() {
		return new ComputeBonusJobListener();
	}

	@Bean
	public Validator<BonusBean> bonusBeanValidator() {
		return new BonusBeanValidator<BonusBean>();
	}

	

}
