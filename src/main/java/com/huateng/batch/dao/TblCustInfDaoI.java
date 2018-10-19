<<<<<<< HEAD

package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblCustInf;

public interface TblCustInfDaoI {
	
	/* public void save(List<TblCustInf> items) ;

	public void delete(List<TblCustInf> items);*/
	
	 public void save(List<? extends TblCustInf> items) ;

		public void delete(List<? extends TblCustInf> items);

}

=======
package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblCustInf;
import com.huateng.batch.model.TblCustInfTmp;

public interface TblCustInfDaoI {
	
	/* public void save(List<TblCustInf> items) ;

	public void delete(List<TblCustInf> items);*/
	
	 public void save(List<? extends TblCustInf> items) ;

		public void delete(List<? extends TblCustInf> items);

}
>>>>>>> 40b4d4adb8788e3f95106901134864cf7924b2a5
