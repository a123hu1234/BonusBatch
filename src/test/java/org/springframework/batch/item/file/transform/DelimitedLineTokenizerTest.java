package org.springframework.batch.item.file.transform;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.huateng.batch.model.TblCustInfTmp;
import com.huateng.util.Util;

public class DelimitedLineTokenizerTest {
	public static void main(String[] args) {
		DelimitedLineTokenizer tokenizer = null;
		try {
			tokenizer = new DelimitedLineTokenizer(Util.asciiToStrings("0x03"));

			// tokenizer.setRegex("[|]+");
			tokenizer.setNames(TblCustInfTmp.toArray());
			List<String> list = tokenizer
					.doTokenize("18371103800330902195901290924平亚芳219590129030720180611");
			// List<String> list =
			// tokenizer.doTokenize("1837110380|0|330902195901290924|平亚芳|2|||||19590129|0307|20180611");
			System.out.println(list.size());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
