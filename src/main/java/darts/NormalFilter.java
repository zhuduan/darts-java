package darts;

import java.util.List;

public class NormalFilter {

	public boolean isKeyWords(String inputStr,List<String> keyLists){
		for(String key : keyLists){
			if(key.equals(inputStr)){
				return true;
			}
		}
		return false;
	}
	
}
