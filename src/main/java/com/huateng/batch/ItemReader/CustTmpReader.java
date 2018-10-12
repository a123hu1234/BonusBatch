package com.huateng.batch.ItemReader;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.model.TblCustInfTmp;
import com.huateng.util.Util;

/*
 * cust信息阅读器
 * by ygq
 */
@Configuration
@EnableBatchProcessing
public class CustTmpReader {
	BeanRowMapper<TblCustInfTmp> BeanPropertyRowMapper = new BeanRowMapper<TblCustInfTmp>(TblCustInfTmp.class);
	
	@Bean
	@StepScope
	public FlatFileItemReader<TblCustInfTmp> custTmpFileReader(@Value("#{jobParameters['input.file.name']}") String pathToFile)
			throws Exception {
		
		FlatFileItemReader<TblCustInfTmp> reader = new FlatFileItemReader<TblCustInfTmp>(); //
		reader.setResource(new FileUrlResource(pathToFile)); //
		reader.setLineMapper(new DefaultLineMapper<TblCustInfTmp>() {
			{ //
				setLineTokenizer(new DelimitedLineTokenizer(Util.asciiToStrings("0x03")) {
					{
						String[] s =BeanPropertyRowMapper.getMappedProperties().toArray(new String[BeanPropertyRowMapper.getMappedProperties().size()]);
						for(int i=0;i<s.length;i++) {
							System.out.println("=============" + s[i]);
						}
						
						setNames(s);
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
	public JdbcPagingItemReader<TblCustInfTmp> custTmpDataBaseReader(DataSource dataSource)
			throws Exception {
		JdbcPagingItemReader<TblCustInfTmp> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);               //设置数据源
        reader.setFetchSize(2);                         //FetchSize设置为2，表示每次从数据库中,2条数据
        reader.setRowMapper(new BeanPropertyRowMapper<TblCustInfTmp>(TblCustInfTmp.class));       //把数据库表中每条数据映射到对象中
        OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
        queryProvider.setSelectClause("*");            //设置查询的列
        queryProvider.setFromClause("from Tbl_Cust_Inf_Tmp");     //设置查询的表
        Map<String, Order> sortKeys = new HashMap<>(); //定义一个map，用于存放排序列
        sortKeys.put("Cust_ID", Order.ASCENDING);           //按id列升序排列
       // sortKeys.put("age", Order.DESCENDING);         //按age的降序排列
        queryProvider.setSortKeys(sortKeys);           //设置排序列
        reader.setQueryProvider(queryProvider);
        return reader;

	}
	

	

}
