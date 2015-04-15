package cn.edu.pku.telephonebook.contact;

import java.util.HashMap;

public class Event {
	private HashMap<String, String> birthday;//TYPE_BIRTHDAY
	private HashMap<String, String> anniversary;//TYPE_ANNIVERSARY
	private HashMap<String, String> other;//TYPE_OTHER;
	private HashMap<String, String> custom;//TYPE_CUSTOM
	
	public Event(){
		
	}
	
	public Event(HashMap<String, String> _birthday, HashMap<String, String> _anniversary,
			HashMap<String, String> _other, HashMap<String, String> _custom){
		birthday = new HashMap<String, String>(_birthday);
		anniversary = new HashMap<String, String>(_anniversary);
		other = new HashMap<String, String>(_other);
		custom = new HashMap<String, String>(_custom);
	}
	
	public void setBirthday(HashMap<String, String> _birthday){
		birthday = new HashMap<String, String>(_birthday);
	}
	
	public void setAnniversary(HashMap<String, String> _anniversary){
		anniversary = new HashMap<String, String>(_anniversary);
	}
	
	public void setOther(HashMap<String, String> _other){
		other = new HashMap<String, String>(_other);
	}
	
	public void setCustom(HashMap<String, String> _custom){
		custom = new HashMap<String, String>(_custom);
	}
			
	public HashMap<String, String> getBirthday(){
		return birthday;
	}
	
	public HashMap<String, String> getAnniversary(){
		return anniversary;
	}
	
	public HashMap<String, String> getOther(){
		return other;
	}
	
	public HashMap<String, String> getCustom(){
		return custom;
	}	
	
}
