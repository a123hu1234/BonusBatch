package org.springframework.batch.item.file.transform;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.huateng.batch.BeanRowMapperTool.BeanRowMapper;
import com.huateng.batch.model.TblCardInfTmp;
import com.huateng.util.Util;

public class DelimitedLineTokenizerTest {
	public static void main(String[] args) {
		DelimitedLineTokenizer tokenizer = null;
		try {
			tokenizer = new DelimitedLineTokenizer(Util.asciiToStrings("0x03"));
			BeanRowMapper<TblCardInfTmp> beanPropertyRowMapper = new BeanRowMapper<TblCardInfTmp>(TblCardInfTmp.class);
			// tokenizer.setRegex("[|]+");
			tokenizer.setNames(beanPropertyRowMapper.getMappedProperties().toArray(new String[beanPropertyRowMapper.getMappedProperties().size()]));
			List<String> list = tokenizer
					.doTokenize("﻿18371134386217261576009884773丁永青0215201806112012183711343800028121212121212121212121212");
			// List<String> list =
			// tokenizer.doTokenize("1837110380|0|330902195901290924|平亚芳|2|||||19590129|0307|20180611");
			System.out.println(list.size());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
