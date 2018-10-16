package com.huateng.batch.Processor;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.huateng.batch.model.IntoAcctBean;
import com.huateng.util.Util;

public class IntoAcctItemProcessor extends ValidatingItemProcessor<IntoAcctBean>{

    @Override
    public IntoAcctBean process(IntoAcctBean item) throws ValidationException {
        super.process(item); //需要执行super.process(item)才会调用自定义校验器
       
        return item;
    }


}
