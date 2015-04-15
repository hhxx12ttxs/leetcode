package cn.edu.pku.telephonebook.contact;

import android.util.SparseArray;

/**
 * 邮箱
 * @author yjxxtd
 *
 */

public class Email {
	private SparseArray<String> home = new SparseArray<String>();//TYPE_HOME  家庭
	private SparseArray<String> work = new SparseArray<String>();//TYPE_WORK  工作
	private SparseArray<String> other = new SparseArray<String>();//TYPE_OTHER  其他
	
	public Email(){
	}
	
	public Email(SparseArray<String> _home, SparseArray<String> _work, SparseArray<String> _other){
		home = _home;
		work = _work;
		other = _other;
	}
	
	public int getEmailNum(){
		return home.size() + work.size() + other.size();
	}
	
	public void addHome(int _data_id, String _home){
		home.put(_data_id, _home);
	}
	
	public void addWork(int _data_id, String _work){
		work.put(_data_id, _work);
	}
	
	public void addOther(int _data_id, String _other){
	    other.put(_data_id, _other);	
	}
	
	public void setHome(SparseArray<String> _home){
		home = _home;
	}
	
	public void setWork(SparseArray<String> _work){
		work = _work;
	}
	
	public void setOther(SparseArray<String> _other){
		other = _other;
	}
	
	public SparseArray<String> getHome(){
		return home;
	}
	
	public SparseArray<String> getWork(){
		return work;
	}
	
	public SparseArray<String> getOther(){
		return other;
	}
}
