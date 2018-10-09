package  org.springframework.batch.item.file.transform;
import java.util.List;

import org.springframework.batch.item.file.transform.RegexLineTokenizer;

import com.huateng.batch.model.TblCustInfTmp;

public class RegexLineTokenizerTest{
	public static void main(String[] args) {
		RegexLineTokenizer tokenizer = new RegexLineTokenizer();
		tokenizer.setRegex("[,]+");
		tokenizer.setNames(TblCustInfTmp.toArray());
		//List<String> list = tokenizer.doTokenize("18371103800330902195901290924平亚芳219590129030720180611");
		List<String> list = tokenizer.doTokenize("1837110380,0,330902195901290924,平亚芳,2,,,,,19590129,0307,20180611");
		System.out.println(list.size());
		
	}
}
