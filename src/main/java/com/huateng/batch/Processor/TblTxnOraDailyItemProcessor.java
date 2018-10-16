package com.huateng.batch.Processor;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.huateng.batch.model.TblTxnOraDaily;
import com.huateng.util.Util;

public class TblTxnOraDailyItemProcessor extends ValidatingItemProcessor<TblTxnOraDaily>{
	private int count =1;
	private String taskId="ImportTxn";

    @Override
    public  TblTxnOraDaily process( TblTxnOraDaily item) throws ValidationException {
    	
        super.process(item); //需要执行super.process(item)才会调用自定义校验器
        item.setTaskSeq(count);
        item.setTaskId(taskId);
        item.setTaskDate(Util.getCurrDate());
        count++;
       
        return item;
    }

}



