
package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblCardInfTmp;

public interface TblCardInfTempDaoI {
	
	/* public void save(List<TblCardInf> items) ;

	public void delete(List<TblCardInf> items);*/
	
	 public void save(List<? extends TblCardInfTmp> items) ;

		public void delete(List<? extends TblCardInfTmp> items);

}
