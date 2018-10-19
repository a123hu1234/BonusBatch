
package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblCardInf;

public interface TblCardInfDaoI {
	
	/* public void save(List<TblCardInf> items) ;

	public void delete(List<TblCardInf> items);*/
	
	 public void save(List<? extends TblCardInf> items) ;

		public void delete(List<? extends TblCardInf> items);

}

