package com.gzw.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="关于调整上海金牛经济发展有限公司\r\n" + 
				"法定代表人、执行董事的通知\r\n" + 
				"\r\n" + 
				"上海金牛经济发展有限公司：\r\n" + 
				"经研究决定：\r\n" + 
				"拟推荐王力维同志担任你公司法定代表人、执行董事。\r\n" + 
				"拟推荐王维同志担任你公司法定代表人、执行董事。\r\n" + 
				"郑国跃同志不再担任你公司法定代表人、执行董事。\r\n" + 
				"郑国同志不再担任你公司法定代表人、执行董事。\r\n" + 
				"请你单位收文后按相关程序办理工商变更登记。\r\n" + 
				"特此通知。\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"2019-02-13    \r\n" + 
				"\r\n" + 
				"";
		String s2="拟推荐王力维同志担任你公司法定代表人、执行董事。\r\n";
		String rgex = "(.*?)。\r\n";
		String rgex2="拟推荐(.*?)同志担任";
		 //System.out.println(PatternTest.getAllMatchedStr(css,rgex));
		 //System.out.println(PatternTest.getSubUtilSimple(s2,rgex2));
	}

	 /** 
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样 
     * @param soap 
     * @param rgex 
     * @return 
     */  
    public static String getSubUtilSimple(String soap,String rgex){  
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
        Matcher m = pattern.matcher(soap);  
        while(m.find()){  
            return m.group(1);  
        }  
        return "";  
    } 
	
	 /** 
     * 返回所有匹配的字符串（一个或者多个）
     * 
     * @param soap 
     * @param rgex 
     * @return 
     */  
    public static List<String> getAllMatchedStr(String soap,String rgex){  
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
        Matcher m = pattern.matcher(soap);  
        List<String> data = new ArrayList<String>();
        while(m.find())
            data.add(m.group());
        return data;
    } 
    

}
