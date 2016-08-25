package darts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Application {
	
	public static void main(String[] args) {
		DoubleArrayTrie doubleArrayTrie = new DoubleArrayTrie();	
		NormalFilter normalFilter = new NormalFilter();
		
		Long keysBefore = (new Date()).getTime();
		List<String> keysList = getKeys();
		Long keysAfter = (new Date()).getTime();
		
		Long buildBefore = (new Date()).getTime();
		doubleArrayTrie.build(keysList);
		Long buildAfter = (new Date()).getTime();
		
		Long serchBefore = (new Date()).getTime();
		String testStr = "国民";
		int keyWordIndex = doubleArrayTrie.exactMatchSearch(testStr);
		if(keyWordIndex>0){
			System.out.println("***search result: "+keysList.get(keyWordIndex));
		} else {
			System.out.println("***search result: "+" no match");
		}
		Long serchAfter = (new Date()).getTime();
		
		Long commonPrefixBefore = (new Date()).getTime();
		List<Integer> commonPrefixIndexList = doubleArrayTrie.commonPrefixSearch(testStr);
		for(Integer index : commonPrefixIndexList){
			System.out.println("***common prefix: "+index+","+keysList.get(index));
		}
		Long commonPrefixAfter = (new Date()).getTime();
		
		Long normalBefore = (new Date()).getTime();
		System.out.println("normal equals: "+normalFilter.isKeyWords(testStr, keysList));
		Long normalAfter = (new Date()).getTime();
		
		System.out.println("read and sort time:"+(keysAfter-keysBefore));
		System.out.println("build time:"+(buildAfter-buildBefore));
		System.out.println("search time:"+(serchAfter-serchBefore));
		System.out.println("common prefix time:"+(commonPrefixAfter-commonPrefixBefore));
		System.out.println("total trie time:"+(commonPrefixAfter-keysBefore));
		System.out.println("normal time:" + (normalAfter - normalBefore));
		
		
		String longText = "自动的广告监测测试，蒋中国不知道国民关键字国民党之类的能不能打出来推翻";
		List<String> resultKeys = doubleArrayTrie.getAllKeysInContent(longText);	
		if(resultKeys!=null && resultKeys.isEmpty()==false){
			for(String k : resultKeys){
				System.out.println("get all: " + k + keysList.get(Integer.parseInt(k)));
			}
		} else {
			System.out.println("get null for all");
		}
		
		System.out.println("get the filtered text: "+ doubleArrayTrie.getFilteredContent(longText));
	}
	
	
	private static List<String> getKeys(){
		List<String> keysList = new ArrayList<String>();
		File file;
		try {
			file = new File("C:\\Users\\Administrator\\Desktop\\datas\\keywords.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while(null!=(tempStr=reader.readLine())){
				keysList.add(tempStr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(keysList);
		return keysList;
	}
	

}
