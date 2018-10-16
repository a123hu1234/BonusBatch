package com.huateng.drool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.toprules.adapter.TopRules;
import com.huateng.util.ConfigProperties;


public class DroolIntance {
	
	private  Logger logger = LoggerFactory.getLogger(DroolIntance.class);
	private   TopRules topRules = new TopRules();
	
	
	
	
	private static class Intance{
		private static final DroolIntance INTANCE  = new DroolIntance();
	}
	
	public static  DroolIntance getIntance() {
		return Intance.INTANCE;
				
	}
	
	public void initRuleFile(String type) throws Exception {
		String loca = ConfigProperties.getFullNASPathProperties("rule.locale");
		String fileName = ConfigProperties.getProperties("file.name.rule."
				+ type);

		// String loca = "E:" + File.separator + "home" + File.separator +
		// "wasadmin" + File.separator
		// + "data" + File.separator + "RuleFile" + File.separator + "rules" +
		// File.separator;

		String ruleFullName = loca + fileName + ".pkg";
		File file = new File(ruleFullName);
		// logger.info("file.isFile()：" + file.isFile());
		if (!file.isFile()) {
			logger.info("找不到规则文件：" + ruleFullName);
			throw new Exception("找不到规则文件");
		}
		logger.info("加载规则文件：" + ruleFullName);
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] bytes = new byte[4096];
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			int len = in.read(bytes);
			while (len != -1) {
				stream.write(bytes, 0, len);
				len = in.read(bytes);
			}
			// 检查是否存在目录 (Check whether there are directories)
			
			topRules.releaseFromDrl(stream);

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
	
	public TopRules getTopRules() {
		return topRules;
	}


}
