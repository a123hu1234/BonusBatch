package com.huateng.util;

/** @(#)
*
*
* Modify Information:
* =============================================================================
*   Author             Date           Description
*   ------------      ----------     --------------------------------------------
*   zhanyaokang   		2012-09-24     description
*
*
* Copyright Notice:
* =============================================================================
*       Copyright 2012 Huateng Software, Inc. All rights reserved.
*
*       This software is the confidential and proprietary information of
*       Shanghai HUATENG Software Co., Ltd. ("Confidential Information").
*       You shall not disclose such Confidential Information and shall use it
*       only in accordance with the terms of the license agreement you entered
*       into with Huateng.
*
* Warning:
* =============================================================================
*
*/


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @Company:上海华腾软件系统有限公司
* @description: 读取配置文件属性
* @author <a href="mailto:zhan_yaokang@huateng.com">湛耀康</a>
* @version 1.0
* Copyright 2010, Shanghai Huateng Software Systems Co., Ltd. 
* All right reserved.
*/
public class ConfigProperties {
	private final static Logger logger = LoggerFactory.getLogger(ConfigProperties.class);

	private static Properties props = null;
	// 高速缓存
	private static Map cashePropsMap = new HashMap();
	private static String projectHomePath;
	private static String projectNASHomePath;
	private static String[] module;

	/**
	 * 取得属性
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperties(String key) {
		if (props == null)
			systemInit();
		String propsValue = (String) cashePropsMap.get(key);
		if (propsValue == null) {
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return propsValue;
	}
	
	/**
	 * 
	 *<p><strong>Description:</strong> 取得int类型属性  </p>
	 * @param key
	 * @return
	 * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
	 * @update 日期: 2012-10-11
	 */
	public static int getIntProperties(String key) {
		if (props == null)
			systemInit();
		String propsValue = (String) cashePropsMap.get(key);
		if (propsValue == null) {
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return Integer.parseInt(propsValue);
	}
	
	
	/**
	 * 取得项目相关文件基路径
	 * 
	 * @return
	 */
	public static String getProjectHomePath() {
		if (props == null)
			systemInit();
		return projectHomePath;
	}

	/**
	 * 取得配置全路径（基路径+配置定义路径）
	 * 
	 * @param key
	 * @return
	 */
	public static String getFullPathProperties(String key) {
		if (props == null)
			systemInit();
		String propsValue = (String) cashePropsMap.get(key);
		if (propsValue == null) {
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return projectHomePath + propsValue;
	}

	/**
	 * 取得配置NAS全路径（基路径+配置定义路径）
	 * 
	 * @param key
	 * @return
	 */
	public static String getFullNASPathProperties(String key) {
		if (props == null)
			systemInit();
		String propsValue = (String) cashePropsMap.get(key);
		if (propsValue == null) {
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		return  projectNASHomePath+ propsValue;
	}
	
	private static void loadInitSystemProperties() {
		props = new Properties();
		InputStream in = null;
		try {
			in = ConfigProperties.class.getClassLoader().getResourceAsStream(
					"config.properties");
			if (in != null) {
				props.load(in);
			} else {
				throw new RuntimeException("无法加载配置文件");
			}

			projectHomePath = props.getProperty("project.home.path");
			projectNASHomePath = props.getProperty("project.nas.path");
		} catch (Exception ioe) {
			logger.error(ioe.getMessage(), ioe);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ioe) {
				logger.error("初始化配置文件异常" + ioe.getMessage(), ioe);
			}
		}
	}

	/**
	 * 清除高速缓存
	 */
	public static void cleanCashe() {
		cashePropsMap.clear();
	}

	public static void systemInit() {
		loadInitSystemProperties();
	}

	/**
	 * 取得属性
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperties(String key, Map<String, String> regexMap) {
		if (props == null)
			systemInit();
		String propsValue = (String) cashePropsMap.get(key);
		if (propsValue == null) {
			propsValue = props.getProperty(key);
			cashePropsMap.put(key, propsValue);
		}
		if (regexMap != null && regexMap.size() > 0) {
			Set<String> keySet = regexMap.keySet();
			for (String regexKey : keySet) {
				propsValue = propsValue.replaceAll("\\$\\{" + regexKey + "}",
						regexMap.get(regexKey));
			}
		}
		return propsValue;
	}

	/**
	 * 取得配置全路径（基路径+配置定义路径）
	 * 
	 * @param key
	 * @return
	 */
	public static String getFullPathProperties(String key,
			Map<String, String> regexMap) {
		if (props == null) {
			systemInit();
		}
		return projectHomePath + getProperties(key, regexMap);
	}
	
	/**
	 * 取得配置全路径（基路径+配置定义路径）
	 * 
	 * @param key
	 * @return
	 */
	public static String getFullNASPathProperties(String key,
			Map<String, String> regexMap) {
		if (props == null) {
			systemInit();
		}
		return projectNASHomePath + getProperties(key, regexMap);
	}
	

	public static void main(String[] arg) {
		Map<String, String> taskDateRegexMap = new HashMap<String, String>();
		taskDateRegexMap.put("yyyyMMdd", "20120909");
		String modules = getProperties("file.location.ods.untar",taskDateRegexMap);
		System.out.println(modules);
	}

}

