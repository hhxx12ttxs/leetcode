package cn.edu.pku.telephonebook.contact;

import android.util.SparseArray;

public class Phone {
	private SparseArray<String> mobile = new SparseArray<String>();//TYPE_MOBILE 手机
	private SparseArray<String> home = new SparseArray<String>();//TYPE_HOME 住宅
	private SparseArray<String> work = new SparseArray<String>();//TYPE_WORK 工作
	private SparseArray<String> fax_work = new SparseArray<String>();//TYPE_FAX_WORK 工作传真
	private SparseArray<String> fax_home = new SparseArray<String>();//TYPE_FAX_HOME 住宅传真
	private SparseArray<String> car = new SparseArray<String>();//TYPE_CAR 车载电话
	private SparseArray<String> other = new SparseArray<String>();//TYPE_OTHER 其他
	
	public int getPhoneNum(){
		return mobile.size() + home.size() + work.size() +
				fax_work.size() + fax_home.size() + car.size() + other.size();
	}
	public void addMobile(int _data_id, String _mobile){
		mobile.put(_data_id, _mobile);
	}
	
	public void addHome(int _data_id, String _home){
		home.put(_data_id, _home);
	}
	
	public void addWork(int _data_id, String _work){
		work.put(_data_id, _work);
	}
	
	public void addFaxWrok(int _data_id, String _fax_work){
		fax_work.put(_data_id, _fax_work);
	}
	
	public void addFaxHome(int _data_id, String _fax_home){
		fax_home.put(_data_id, _fax_home);
	}
	
	public void addCar(int _data_id, String _car){
		car.put(_data_id, _car);
	}
	
	public void addOther(int _data_id, String _other){
		other.put(_data_id, _other);
	}

	public void setMobile(SparseArray<String> _mobile){
		mobile = _mobile;
	}

	public void setHome(SparseArray<String> _home){
		home = _home;
	}
	
	public void setWork(SparseArray<String>  _work){
		work = _work;
	}

	public void setFaxWork(SparseArray<String> _fax_work){
		fax_work = _fax_work;
	}
	
	public void setFaxHome(SparseArray<String> _fax_home){
		fax_home = _fax_home;
	}
	
	public void setCar(SparseArray<String> _car){
		car = _car;
	}
	
	public void setOther(SparseArray<String> _other){
		other = _other;
	}
	
	public SparseArray<String> getMobile(){
		return mobile;
	}

	public SparseArray<String> getHome(){
		return home;
	}
	
	public SparseArray<String> getWork(){
		return work;
	}

	public SparseArray<String> getFaxWork(){
		return fax_work;
	}
	
	public SparseArray<String> getFaxHome(){
		return fax_home;
	}
	
	public SparseArray<String> getCar(){
		return car;
	}
	
	public SparseArray<String> getOther(){
		return other;
	}
}
