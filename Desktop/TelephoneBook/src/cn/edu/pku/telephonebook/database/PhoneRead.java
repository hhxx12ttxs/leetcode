package cn.edu.pku.telephonebook.database;

import cn.edu.pku.telephonebook.contact.Phone;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class PhoneRead {
	private final static int TYPE_MOBILE = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
	private final static int TYPE_HOME = ContactsContract.CommonDataKinds.Phone.TYPE_HOME;
	private final static int TYPE_WORK = ContactsContract.CommonDataKinds.Phone.TYPE_WORK;
	private final static int TYPE_FAX_WORK = ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK;
	private final static int TYPE_FAX_HOME = ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME;
	private final static int TYPE_CAR = ContactsContract.CommonDataKinds.Phone.TYPE_CAR;
	private final static int TYPE_OTHER = ContactsContract.CommonDataKinds.Phone.TYPE_OTHER;
	
	public static Phone readPhone(ContentResolver resolver, int contact_id){
		Cursor phone_cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
				null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contact_id, null, null);
		Phone phone = new Phone();
		while(phone_cursor.moveToNext()){
			String phone_number = phone_cursor.getString(phone_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			//System.out.println("phone_number " + phone_number);
			int type = phone_cursor.getInt(phone_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
			int data_id = phone_cursor.getInt(phone_cursor.getColumnIndex(ContactsContract.Data._ID));
            switch(type){
                case TYPE_MOBILE: phone.addMobile(data_id, phone_number); break;
                case TYPE_HOME: phone.addHome(data_id, phone_number); break;
                case TYPE_WORK: phone.addWork(data_id, phone_number); break;
                case TYPE_FAX_WORK: phone.addFaxWrok(data_id, phone_number); break;
                case TYPE_FAX_HOME:	phone.addFaxHome(data_id, phone_number); break;
                case TYPE_CAR: phone.addCar(data_id, phone_number); break;
                case TYPE_OTHER: phone.addOther(data_id, phone_number);
            }
		}
		return phone;
	}
}
