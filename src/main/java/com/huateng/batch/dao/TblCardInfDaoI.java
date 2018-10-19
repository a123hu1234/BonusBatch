<<<<<<< HEAD

package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblCardInf;

public interface TblCardInfDaoI {
	
	/* public void save(List<TblCardInf> items) ;

	public void delete(List<TblCardInf> items);*/
	
	 public void save(List<? extends TblCardInf> items) ;

		public void delete(List<? extends TblCardInf> items);

}

=======
package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblCardInf;
import com.huateng.batch.model.TblCardInfTmp;

public interface TblCardInfDaoI {
	
	/* public void save(List<TblCardInf> items) ;

	public void delete(List<TblCardInf> items);*/
	
	 public void save(List<? extends TblCardInf> items) ;

		public void delete(List<? extends TblCardInf> items);

}
>>>>>>> 40b4d4adb8788e3f95106901134864cf7924b2a5
