package cn.edu.pku.telephonebook.contact;

import java.util.HashMap;

public class Group {
	private HashMap<String, String> id;
	private HashMap<String, String> title;

	public Group(){
		
	}
	
	public void setId(HashMap<String, String> _id){
		id = new HashMap<String, String>(_id);
	}
	
	public void setTitle(HashMap<String, String> _title){
		title = new HashMap<String, String>(_title);
	}
	
	public HashMap<String, String> getId(){
		return id;
	}
	
	public HashMap<String, String> getTitle(){
		return title;
	}

}
