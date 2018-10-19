<<<<<<< HEAD

package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblAccountInfTmp;

public interface TblAccountInfTempDaoI {
	
	/* public void save(List<TblAccountInf> items) ;

	public void delete(List<TblAccountInf> items);*/
	
	 public void save(List<? extends TblAccountInfTmp> items) ;

		public void delete(List<? extends TblAccountInfTmp> items);

}
=======
package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblAccountInf;
import com.huateng.batch.model.TblAccountInfTmp;

public interface TblAccountInfTempDaoI {
	
	/* public void save(List<TblAccountInf> items) ;

	public void delete(List<TblAccountInf> items);*/
	
	 public void save(List<? extends TblAccountInfTmp> items) ;

		public void delete(List<? extends TblAccountInfTmp> items);

}
>>>>>>> 40b4d4adb8788e3f95106901134864cf7924b2a5
