package cn.edu.pku.telephonebook.database;

import cn.edu.pku.telephonebook.contact.Email;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class EmailRead {
	private final static int TYPE_HOME = ContactsContract.CommonDataKinds.Email.TYPE_HOME;
	private final static int TYPE_WORK = ContactsContract.CommonDataKinds.Email.TYPE_WORK;
	private final static int TYPE_OTHER = ContactsContract.CommonDataKinds.Email.TYPE_OTHER;
	
	public static Email readEmail(ContentResolver resolver, int contact_id){ 
        Email email = new Email();
		Cursor email_cursor = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
				null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contact_id, null, null);
		while(email_cursor.moveToNext()){
			String email_str = email_cursor.getString(email_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA1));
			int type = email_cursor.getInt(email_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
			int data_id = email_cursor.getInt(email_cursor.getColumnIndex(ContactsContract.Data._ID));
            switch(type){
                case TYPE_HOME: email.addHome(data_id, email_str); break;
                case TYPE_WORK: email.addWork(data_id, email_str); break;
                case TYPE_OTHER: email.addOther(data_id, email_str); break;
            }
		}
		return email;
	}
}
