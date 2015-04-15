package cn.edu.pku.telephonebook.contact;

import java.util.Locale;

public class StructuredName{
	private String display_name;//(Alias)DISPLAY_NAME
	private String letters;
	
	public StructuredName(){
		
	}
	
	public StructuredName(String _display_name){
		display_name = _display_name;
		setLetters();
	}
	
	public void setDisplayName(String _display_name){
		display_name = _display_name;
	}
	
	public String getDisplayName(){
		return display_name;
	}
	
	
	public String getLetters(){
		return letters;
	}
	
	private void setLetters(){
		letters = new NameLetters(display_name).getLetters().toUpperCase(Locale.ENGLISH);
	}

}
