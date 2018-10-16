/** @(#)
 *
 *
 * Modify Information:
 * =============================================================================
 *   Author             Date           Description
 *   ------------      ----------     --------------------------------------------
 *   zhanyaokang   		2012-10-18     description
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
package com.huateng.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Company:上海华腾软件系统有限公司
 * @description: 字符工具类
 * @author <a href="mailto:zhan_yaokang@huateng.com">湛耀康</a>
 * @version 1.0
 * Copyright 2010, Shanghai Huateng Software Systems Co., Ltd. 
 * All right reserved.
 */
public class StringUtil {

    /**
     * Pads the supplied String with 0's to the specified length and returns
     * the result as a new String. For example, if the initial String is
     * "9999" and the desired length is 8, the result would be "00009999".
     * This type of padding is useful for creating numerical values that need
     * to be stored and sorted as character data. Note: the current
     * implementation of this method allows for a maximum <tt>length</tt> of
     * 16.
     *
     * @param string the original String to pad.
     * @param length the desired length of the new padded String.
     * @return a new String padded with the required number of 0's.
     */
    
    public static final String zeroPadString(String string, int length) {
        if (string == null || string.length() > length) {
            return string;
        }
         char[] zeroArray = "0000000000000000".toCharArray();
        StringBuffer buf = new StringBuffer(length);
        buf.append(zeroArray, 0, length - string.length()).append(string);
        return buf.toString();
    }
	
	/**
	 * 检查字符串是为空，为null，或者长度大于0
	 * @param str
	 * @return 非空：false，空：true
	 */
	public static boolean checkNull(String str){
		if(str!=null&&str.length()>0)
			return false;
		
		return true;
	}
	

    /**
     * 
     *<p><strong>Description:</strong>字符串转换成Double，如果传入空或者""返回null   </p>
     * @param source
     * @return
     * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
     * @update 日期: 2012-10-18
     */
    public static Double convertDoubleReturnNull(String source){
        if(source!=null&&source.length()>0){
            return new Double(source);
        }
        return null;
    }
	
	/**
	 * 
	 *<p><strong>Description:</strong>字符串转换成Double，如果传入空或者""返回0   </p>
	 * @param source
	 * @return
	 * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
	 * @update 日期: 2012-10-18
	 */
	public static Double convertDouble(String source){
		if(source!=null&&source.length()>0){
			return new Double(source);
		}
		return new Double(0);
	}
    /**
     * 
     *<p><strong>Description:</strong>字符串转换成Integer，如果传入空或者""返回null   </p>
     * @param source
     * @return
     * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
     * @update 日期: 2012-10-18
     */
    public static Integer convertIntegerRetrunNull(String source){
        if(source!=null&&source.length()>0){
            return new Integer(source);
        }
        return null;
    }
	/**
	 * 
	 *<p><strong>Description:</strong>字符串转换成Integer，如果传入空或者""返回0   </p>
	 * @param source
	 * @return
	 * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
	 * @update 日期: 2012-10-18
	 */
	public static Integer convertInteger(String source){
		if(source!=null&&source.length()>0){
			return new Integer(source);
		}
		return new Integer(0);
	}
	/**
	 * 
	 *<p><strong>Description:</strong>字符串转换成Long，如果传入空或者""返回0   </p>
	 * @param source
	 * @return
	 * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
	 * @update 日期: 2012-10-18
	 */
	public static Long convertLong(String source){
		if(source!=null&&source.length()>0){
			return new Long(source);
		}
		return new Long(0);
	}
	
	/**
	 * 将应用网关的Double格式字符串（小数点前10位+小数点后2位，12字符串，没小数点），转换为double类型
	 * @param srt
	 * @return
	 * @throws Exception
	 */
	public static double ccEnvelopeStringToDouble(String srt) throws Exception{
		if(srt==null||srt.length()!=12){
			throw new Exception("Format Error");
		}
		String convertStr = srt.substring(0,10)+"."+srt.substring(10);
		return Double.parseDouble(convertStr);
	}
	/**
	 * 将double类型，转换为应用网关的Double格式字符串（小数点前10位+小数点后2位，12字符串，没小数点）,
	 * 本系统已约定double类型只有小数点后2为
	 * @param dou
	 * @return
	 */
	public static String ccEnvelopeDoubleToString(double dou){
		
		String str = dou+"";
		if(str.substring(str.indexOf(".")+1).length()<2){
			str+="0";
		}
		
		str = str.replace(".", "");
		if(str.length()<12){
			str = "000000000000".substring(str.length())+str;
		}
		return str;
	}
	
	/**
	 * 将数字转化为指定长度的字符串，数字前面补0，长度不能超过10位
	 * @param number
	 * @param length
	 * @return
	 */
	public static String intToString(int number,int length){
		String numString = number+"";
		if(numString.length()>length||numString.length()>10||length>10)
			return numString;
		String temp = "0000000000".substring(10-length);
		return temp.substring(numString.length())+numString;
	}
	/**
	 * 将数字转化为指定长度的字符串，数字前面补0，长度不能超过10位
	 * @param number
	 * @param length
	 * @return
	 */
	public static String intToString(Integer number,int length){
		if(number==null)
			number = new Integer(0);
		return intToString(number.intValue(), length);
	}
	/**
	 * 将数字转化为指定长度的字符串，数字前面补0，长度不能超过10位
	 * @param number
	 * @param length
	 * @return
	 */
	public static String intToString(Long number,int length){
		if(number==null)
			number = new Long(0);
		return intToString(number.intValue(), length);
	}
	/**
	 * 将数字转化为制定长度的字符串，不足长度前补0
	 * @param number
	 * @param length
	 * @return
	 */
	public static String intToString2(Long number, int length){
		if(number==null)
			number = new Long(0);
		return String.format("%0"+length+"d", number);
	}


    /**
     * 清除对象的左右空格
     * @param str
     * @return:返回清除左右空格后的字符串
     */
    public static String ObjectTrim(Object str) {
        if (null == str || "null".equalsIgnoreCase(str.toString()) ) {
            return "";
        }

        return str.toString().trim();
    }
	
	/**
	 * 清除字符串的左右空格
	 * @param str
	 * @return:返回清除左右空格后的字符串
	 */
	public static String trim(String str) {
		if (null == str || "null".equalsIgnoreCase(str) ) {
			return "";
		}

		return str.trim();
	}
	/**
	 * 判断字符串是否为空串（null或者内容为空）
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if ((null == str) || (str.trim().length() <= 0)) {
			return true;
		}

		return false;
	}
	/**
	 * 如果字符串是空串, 返回""
	 * @param str
	 * @return
	 */
	public static String reverseEmpty(String str){
		return isEmpty(str) ? "" : str;
	}
	/**
	 * 把空字符串转换成指定字符串
	 * @param str
	 * @param defaultStr
	 * @return
	 */
	public static String reverseEmptyToString(String str, String defaultStr){
		return isEmpty(str) ? defaultStr : str.trim();
	}
	/**
	 * 将字符串按指定的符号分割成数组（数组内容不包括指定符号）
	 * @param source
	 * @param splitRegex
	 * @return
	 */
	public static String[] splitString(String source, String splitRegex) {
		if(source!=null&&source.endsWith(" ")){
			source = source.trim();
		}
		return source.split(splitRegex, -1);
	}
	
	
	public static String[] splitInd(String s, String delimiter) {
		if (s == null) {
			return null;
		}
		int delimiterLength;
		int stringLength = s.length();
		if (delimiter == null || (delimiterLength = delimiter.length()) == 0) {
			return new String[] { s };
		}

		// a two pass solution is used because a one pass solution would
		// require the possible resizing and copying of memory structures
		// In the worst case it would have to be resized n times with each
		// resize having a O(n) copy leading to an O(n^2) algorithm.

		int count;
		int start;
		int end;

		// Scan s and count the tokens.
		count = 0;
		start = 0;
		while ((end = s.indexOf(delimiter, start)) != -1) {
			count++;
			start = end + delimiterLength;
		}
		count++;

		// allocate an array to return the tokens,
		// we now know how big it should be
		String[] result = new String[count];

		// Scan s again, but this time pick out the tokens
		count = 0;
		start = 0;
		while ((end = s.indexOf(delimiter, start)) != -1) {
			result[count] = (s.substring(start, end));
			count++;
			start = end + delimiterLength;
		}
		end = stringLength;
		result[count] = s.substring(start, end);
		return (result);
	}
	
	
	
	
	/**
	 * 如果传入null返回如果是数字类型返回0，非数字返回""，否则返回source.trim()
	 * @param source
	 * @param isNum
	 * @return
	 */
	public static String trimNull(String source,boolean isNum){
		if(source==null){
			if(isNum){
				return "0";
			}
			return "";
		}
		return source.trim();
	}
	
	public static String replaceString(String source,Map<String, String>regex){
		if(source==null||regex==null||regex.size()==0){
			return source;
		}
		Set<String> keySet = regex.keySet();
		for(String key:keySet){
			source = source.replace("${"+key+"}", regex.get(key));
		}
		return source;
	}
	
	
	/**
	 * 把数组数据转乘字符型
	 * @param datas	
	 * @return
	 */
	public static List<Object[]>  converObjToStr(List<Object[]> objLis){
		if(objLis != null && objLis.size() > 0){
			for(Object[] objs:objLis){
				for(int i = 0;i< objs.length;i++){
					objs[i] = StringUtil.trim(objs[i]+"");
				}
			}
		}
		return objLis;
	}
	
	public static String formBlankString(int iCount) {
		String temp = "";
		for (int i = 0; i < iCount; i++) {

			temp = temp + "0";

		}
		return temp;

	}
	//删除所有空格
		public static String chkNullTrim(Object obj) {
			if (obj == null) {
				return "";
			}
			if (((String) obj).equalsIgnoreCase("null")) {
				return "";
			}
			if("".equals(((String)obj).trim())){
				return "";
			}
			return replace(((String) obj).trim()," ","");
		}
		/**
		 * Replaces all instances of oldString with newString in line.
		 *
		 * @param line the String to search to perform replacements on
		 * @param oldString the String that should be replaced by newString
		 * @param newString the String that will replace all instances of oldString
		 *
		 * @return a String will all instances of oldString replaced by newString
		 */
		public static final String replace(String line, String oldString, String newString) {
			if (line == null) {
				return null;
			}
			int i = 0;
			if ((i = line.indexOf(oldString, i)) >= 0) {
				char[] line2 = line.toCharArray();
				char[] newString2 = newString.toCharArray();
				int oLength = oldString.length();
				StringBuffer buf = new StringBuffer(line2.length);
				buf.append(line2, 0, i).append(newString2);
				i += oLength;
				int j = i;
				while ((i = line.indexOf(oldString, i)) > 0) {
					buf.append(line2, j, i - j).append(newString2);
					i += oLength;
					j = i;
				}
				buf.append(line2, j, line2.length - j);
				return buf.toString();
			}
			return line;
		}
	
	public static void main(String[] args) {
		String source = "aa_${T1}_${T2}_${T3}_${T1}";
		Map<String, String>regex = new HashMap<String, String>();
		regex.put("T1", "好");
		regex.put("T2", "AR");
		System.out.println(replaceString(source, regex));
		
		System.out.println(intToString(80, 10));
	}
}
