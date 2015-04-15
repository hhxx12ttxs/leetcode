package cn.edu.pku.telephonebook;
import java.util.ArrayList;
import java.util.Collections;
import cn.edu.pku.telephonebook.contact.Contact;
import android.app.Application;
import android.util.SparseArray;
public class ShareContacts extends Application {
	private SparseArray<Contact> sparse_contacts = new SparseArray<Contact>();
	
	public void setContacts(SparseArray<Contact>  _sparse_contacts){
		sparse_contacts = _sparse_contacts;
	}
	
	public SparseArray<Contact>  getSparseContacts(){
		return sparse_contacts;
	}
	
	public Contact getContactById(int _id){
		return sparse_contacts.get(_id);
	}
	
	public ArrayList<Contact> getAllContacts(){
		ArrayList<Contact> vals = new ArrayList<Contact>();
        for(int i = 0; i < sparse_contacts.size(); i ++){
        	Contact contact = sparse_contacts.valueAt(i);
            vals.add(contact);
        }
        Collections.sort(vals);
        return vals;
	}
}
