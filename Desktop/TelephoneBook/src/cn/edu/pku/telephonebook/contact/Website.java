package cn.edu.pku.telephonebook.contact;

import java.util.HashMap;

public class Website {
	private HashMap<String, String> homepage;//TYPE_HOMEPAGE 个人
	private HashMap<String, String> work;//TYPE_WORK 公司
	private HashMap<String, String> other;//TYPE_OTHER 其他
	
	public Website(){
		
	}
	
	public Website(HashMap<String, String> _homepage, HashMap<String, String> _work, HashMap<String, String> _other){
		homepage = new HashMap<String, String>(_homepage);
		work = new HashMap<String, String>(_work);
		other = new HashMap<String, String>(_other);
	}
	
	public void setHomePage(HashMap<String, String> _homepage){
		homepage = new HashMap<String, String>(_homepage);
	}
	
	public void setWork(HashMap<String, String> _work){
		work = new HashMap<String, String>(_work);
	}
	
	public void setOther(HashMap<String, String> _other){
		other = new HashMap<String, String>(_other);
	}
	
	public HashMap<String, String> getHomePage(){
		return homepage;
	}
	
	public HashMap<String, String> getWork(){
		return work;
	}
	
	public HashMap<String, String> getOther(){
		return other;
	}
}
