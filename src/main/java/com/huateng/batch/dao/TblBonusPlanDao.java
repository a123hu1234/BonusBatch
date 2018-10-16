package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.TblBonusPlan;

/***
 * 积分账户表数据操作
 * @author data
 *
 */
public interface TblBonusPlanDao {
	public int[] insertPlan(List<? extends TblBonusPlan>  list);
}
