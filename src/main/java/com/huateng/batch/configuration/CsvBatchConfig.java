package com.huateng.batch.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
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
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.cbhu.entry.Person;
import com.huateng.batch.Processor.CsvItemProcessor;
import com.huateng.batch.listener.CsvJobListener;
import com.huateng.batch.validator.CsvBeanValidator;

//@Configuration
//@EnableBatchProcessing
public class CsvBatchConfig {

    @Bean
    public ItemReader<Person> reader() throws Exception {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>(); //使用FlatFileItemReader读取文件
        reader.setResource(new ClassPathResource("people.csv")); //使用FlatFileItemReader的setResource方法设置CSV文件的路径
            reader.setLineMapper(new DefaultLineMapper<Person>() {{ //在此处对CVS文件的数据和领域模型类做对应映射
                setLineTokenizer(new DelimitedLineTokenizer() {{
                    setNames(new String[] { "name","age", "nation" ,"address"});
                }});
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }});
            }});
            return reader;
    }
    
    @Bean
    public ItemProcessor<Person, Person> processor() {
        CsvItemProcessor processor = new CsvItemProcessor(); //使用自定义的ItemProcessor的实现
        processor.setValidator(csvBeanValidator()); //为Processor指定校验器
        return processor;
    }
    
    

    @Bean
    public ItemWriter<Person> writer(DataSource dataSource) {//Spring能让容器中已有的Bean以参数的形式注入，Spring boot已经定义了DataSource
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>(); //使用JDBC批处理的JdbcBatchItemWriter来写数据到数据库
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        String sql = "insert into person " + "(id,name,age,nation,address) "
                + "values(hibernate_sequence.nextval, :name, :age, :nation,:address)";
        writer.setSql(sql); //在此设置要执行批处理的sql语句
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("oracle");
        jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importJob2")
                .incrementer(new RunIdIncrementer())
                .flow(s1) //指定step
                .end()
                .listener(csvJobListener()) //绑定监听器
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer,
            ItemProcessor<Person,Person> processor) {
        return stepBuilderFactory
                .get("step1")
                .<Person, Person>chunk(65000) //批处理每次提交65000条数据
                .reader(reader) //给step绑定reader
                .processor(processor) //给step绑定Processor
                .writer(writer) //给step绑定writer
                .build();
    }



    @Bean
    public CsvJobListener csvJobListener() {
        return new CsvJobListener();
    }

    @Bean
    public Validator<Person> csvBeanValidator() {
        return new CsvBeanValidator<Person>();
    }
    

}
