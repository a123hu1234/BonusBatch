package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblTxnDetail;

public interface TblTxnDetailDao {

	public int[] saveList(List<TblTxnDetail> txnDetailList);
	
}
