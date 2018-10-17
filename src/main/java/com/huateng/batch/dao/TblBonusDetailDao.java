package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblBonusDetail;

public interface TblBonusDetailDao {
	public int[] saveList(List<? extends TblBonusDetail> list);
}
