package com.huateng.util;

public class SSNUtil {
	private static final long MAX_SSN = 999999999l;
	
//	private SSNUtil util;
	private long ssn = 0;
	
	private  static class Instance{
		public static final SSNUtil UTIL = new SSNUtil();
	}
	
	public static SSNUtil getInstance() {
		return Instance.UTIL;
	}
	
	public String getSSN() {
		synchronized (SSNUtil.class) {
			ssn++;
			if(ssn>MAX_SSN) {
				ssn = 0;
			}
		}
		
		String sReturn = Util.getCurrDate() +"1"+ String.format("%09d", ssn);
		
		return sReturn;
	}
	
	public static void main(String[] args) {
		System.out.println(SSNUtil.getInstance().getSSN());
	}
}
