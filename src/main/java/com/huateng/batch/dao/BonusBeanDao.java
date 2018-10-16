package com.huateng.batch.dao;

import java.util.List;

import com.huateng.batch.model.BonusBean;

public interface BonusBeanDao {
	public void save(List<? extends BonusBean> items );
}
