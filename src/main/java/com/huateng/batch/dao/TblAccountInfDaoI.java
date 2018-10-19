
package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblAccountInf;

public interface TblAccountInfDaoI {
	
	/* public void save(List<TblAccountInf> items) ;

	public void delete(List<TblAccountInf> items);*/
	
	 public void save(List<? extends TblAccountInf> items) ;

		public void delete(List<? extends TblAccountInf> items);

}

