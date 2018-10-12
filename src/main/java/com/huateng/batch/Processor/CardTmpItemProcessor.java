package com.huateng.batch.Processor;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.huateng.batch.model.TblCardInfTmp;
import com.huateng.batch.model.TblCustInf;
import com.huateng.batch.model.TblCustInfTmp;

/***
 * 客户自定义信息校验器
 * @author data
 *
 */
public class CardTmpItemProcessor extends ValidatingItemProcessor<TblCardInfTmp>{

    @Override
    public TblCardInfTmp process(TblCardInfTmp item) throws ValidationException {
        super.process(item); //需要执行super.process(item)才会调用自定义校验器

       
        return item;
    }

}
