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

import com.huateng.batch.ItemReader.IntoAcctReader;
import com.huateng.batch.ItemWriter.BonusBookingWriter;
import com.huateng.batch.ItemWriter.IntoAcctWriter;
import com.huateng.batch.ItemWriter.PlanDetailWriter;
import com.huateng.batch.ItemWriter.TblBonusPlanWriter;
import com.huateng.batch.Processor.IntoAcctItemProcessor;
import com.huateng.batch.listener.ComputeBonusJobListener;
import com.huateng.batch.model.IntoAcctBean;
import com.huateng.batch.model.TblBonusAccItf;
import com.huateng.batch.model.TblBonusPlan;
import com.huateng.batch.model.TblBonusPlanDetail;
import com.huateng.batch.validator.IntoAcctBeanValidator;

@Configuration
@EnableBatchProcessing
public class IntoAcctConfig {

	private Logger logger = LoggerFactory.getLogger(ComputeBonusConfig.class);
	private String task_id = "1005";

	@Autowired
	@Qualifier("intoAcctWriter")
	private IntoAcctWriter intoAcctWriter;
	
	@Autowired
	@Qualifier("tblBonusPlanWriter")
	private TblBonusPlanWriter tblBonusPlanWriter;
	@Autowired
	@Qualifier("planDetailWriter")
	private PlanDetailWriter planDetailWriter;
	
	@Autowired
	@Qualifier("bonusBookingWriter")
	private BonusBookingWriter bonusBookingWriter;

	// @Autowired
	// @Qualifier("intoAcctReader")
	// private IntoAcctReader intoAcctReader;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JobBuilderFactory jobs;

	@Bean
	@StepScope
	public ItemProcessor<IntoAcctBean, IntoAcctBean> intoAcctprocessor() {
		IntoAcctItemProcessor processor = new IntoAcctItemProcessor();
		processor.setValidator(intoAcctValidator());
		return processor;
	}

	// @formatter:off
	@Bean
	public Job intoAcctJob() throws Exception {
		return jobs.get("computeBonusJob")
				.incrementer(new RunIdIncrementer())
				.listener(computeBonusJobListener())
				.flow(insertNotExistsBonusPlanStep())
				.next(insertNotExistsBonusPlanDetailStep())
				.next(bonusBookingStep())
				.end().build();
	}
	// @formatter:on

	// Step 1 插入已经产生待入账积分的客户，但是还没有对应的tbl_bonus_plan记录的，
	// 此方法将极大提供入账效率，
	@Bean
	public Step insertNotExistsBonusPlanStep() {
		Step step = null;
		try {
			step = stepBuilderFactory.get("insertNotExistsBonusPlanStep")//
					.<TblBonusPlan, TblBonusPlan>chunk(1000)//
					.reader(insertNotExistsBonusPlanReader())//
					//.processor(tblBonusPlanProcessor())//
					.writer(tblBonusPlanWriter).build();//
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
		}
		return step;
	}
	
	@Bean
	public ItemReader<TblBonusPlan> insertNotExistsBonusPlanReader() throws Exception {
		ItemReader<TblBonusPlan> reader = new IntoAcctReader() {
			{
				setDataSource(dataSource);
			}
		}.getTblBonusPlanReader();

		return reader;

	}


	// Step 2 插入已经产生待入账积分的客户，但是还没有对应的tbl_bonus_plan_detail记录的，
	// 此方法将极大提供入账效率，
	@Bean
	public Step insertNotExistsBonusPlanDetailStep() {
		Step step = null;
		try {
			step = stepBuilderFactory.get("insertNotExistsBonusPlanDetailStep")//
					.<TblBonusPlanDetail, TblBonusPlanDetail>chunk(1000)//
					.reader(insertNotExistsBonusPlanDetailReader())//
					//.processor(intoAcctprocessor())//
					.writer(planDetailWriter).build();//
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
		}
		return step;
	}
	@Bean
	public ItemReader<TblBonusPlanDetail> insertNotExistsBonusPlanDetailReader() throws Exception {
		ItemReader<TblBonusPlanDetail> reader = new IntoAcctReader() {
			{
				setDataSource(dataSource);
			}
		}.getTblBonusPlanDetailReader();

		return reader;

	}
	
	//Step 3 积分入账
	@Bean
	public Step bonusBookingStep() {
		Step step = null;
		try {
			step = stepBuilderFactory.get("bonusBookingStep")//
					.<TblBonusAccItf, TblBonusAccItf>chunk(1000)//
					.reader(bonusBookingReader())//
					//.processor(intoAcctprocessor())//
					.writer(bonusBookingWriter).build();//
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
		}
		return step;
	}
	
	@Bean
	public ItemReader<TblBonusAccItf> bonusBookingReader() throws Exception {
		ItemReader<TblBonusAccItf> reader = new IntoAcctReader() {
			{
				setDataSource(dataSource);
			}
		}.getbonusBookingReader();

		return reader;

	}
	@Bean
	public ComputeBonusJobListener computeBonusJobListener() {
		return new ComputeBonusJobListener();
	}

	@Bean
	public Validator<IntoAcctBean> intoAcctValidator() {
		return new IntoAcctBeanValidator<IntoAcctBean>();
	}

}
