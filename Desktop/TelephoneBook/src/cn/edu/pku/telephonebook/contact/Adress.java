package cn.edu.pku.telephonebook.contact;

import java.util.HashMap;
/**
 * ��ַ
 * @author wangjie
 *
 */
public class Adress {
	private HashMap<String, String> home;//TYPE_HOME סլ
	private HashMap<String, String> work;//TYPE_WORK ����
	private HashMap<String, String> other;//TYPE_OTHER ����
	
	public Adress(){
		
	}
	
	public Adress(HashMap<String, String> _home, HashMap<String, String> _work, HashMap<String, String> _other){
		home = new HashMap<String, String>(_home);
		work = new HashMap<String, String>(_work);
		other = new HashMap<String, String>(_other);
	}
	
	public void setHome(HashMap<String, String> _home){
		home = new HashMap<String, String>(_home);
	}
	
	public void setWork(HashMap<String, String> _work){
		work = new HashMap<String, String>(_work);
	}
	
	public void setOther(HashMap<String, String> _other){
		other = new HashMap<String, String>(_other);
	}
	
	public HashMap<String, String> getHome(){
		return home;
	}
	
	public HashMap<String, String> getWork(){
		return work;
	}
	
	public HashMap<String, String> getOther(){
		return other;
	}
}
