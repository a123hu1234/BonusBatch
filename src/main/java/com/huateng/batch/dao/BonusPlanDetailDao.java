package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblBonusPlanDetail;

public interface BonusPlanDetailDao {
	 public int[] saveList(List<TblBonusPlanDetail> list);
}
