package cn.edu.pku.telephonebook.contact;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class NameLetters
{
	private String letters;
	
	public NameLetters(String chinese_words){
		letters = "";
		getLetters(chinese_words);
	}
	
	public String getLetters(){
		return letters;
	}
	
	private void getLetters(String chinese_words){
		
        char[] chars = chinese_words.toCharArray();   
        try{
            for (int i = 0; i < chars.length; i++) {   
                String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(chars[i], getDefaultOutputFormat());   
                if (pinyin != null)    
                	letters += pinyin[0];   
                else   
                	letters += chars[i];   
            }   
        }
        catch(BadHanyuPinyinOutputFormatCombination e){
        	e.printStackTrace();
        }
	}
	
	private HanyuPinyinOutputFormat getDefaultOutputFormat() 
	{   
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();   
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);   
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
        return format;   
    }   
}
