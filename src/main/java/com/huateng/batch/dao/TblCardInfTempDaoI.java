<<<<<<< HEAD

package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblCardInfTmp;

public interface TblCardInfTempDaoI {
	
	/* public void save(List<TblCardInf> items) ;

	public void delete(List<TblCardInf> items);*/
	
	 public void save(List<? extends TblCardInfTmp> items) ;

		public void delete(List<? extends TblCardInfTmp> items);

}
=======
package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblCardInf;
import com.huateng.batch.model.TblCardInfTmp;

public interface TblCardInfTempDaoI {
	
	/* public void save(List<TblCardInf> items) ;

	public void delete(List<TblCardInf> items);*/
	
	 public void save(List<? extends TblCardInfTmp> items) ;

		public void delete(List<? extends TblCardInfTmp> items);

}
>>>>>>> 40b4d4adb8788e3f95106901134864cf7924b2a5
