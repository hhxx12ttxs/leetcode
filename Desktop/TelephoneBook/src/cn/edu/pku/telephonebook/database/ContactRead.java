package cn.edu.pku.telephonebook.database;

import cn.edu.pku.telephonebook.ShareContacts;
import cn.edu.pku.telephonebook.contact.Contact;
import cn.edu.pku.telephonebook.contact.Email;
import cn.edu.pku.telephonebook.contact.Phone;
import cn.edu.pku.telephonebook.contact.Photo;
import cn.edu.pku.telephonebook.contact.StructuredName;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import android.provider.ContactsContract;
import android.util.SparseArray;

public class ContactRead {
	public static void readAllContactsName(Context context, Activity activity){
		SparseArray<Contact> sparse_contacts = new SparseArray<Contact>();
		ContentResolver resolver = context.getContentResolver();
		Cursor contact_cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, 
				null, null, null, null);
		while(contact_cursor.moveToNext()){
			int contact_id = contact_cursor.getInt(contact_cursor.getColumnIndex(ContactsContract.Contacts._ID));
			String display_name = contact_cursor.getString(contact_cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            int photo_id = contact_cursor.getInt(contact_cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
            //int phone_num = contact_cursor.getInt(contact_cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            Photo photo = new Photo();
            if(photo_id > 0)
            	photo.setId(photo_id);
                //photo = PhotoRead.readPhoto(resolver, photo_id);
			//Phone phone = new Phone();
			//if(phone_num > 0)
              //  phone = PhoneRead.readPhone(resolver, contact_id);
			//Email email = EmailRead.readEmail(resolver, contact_id);
            
			Contact contact = new Contact();
			contact.setRawContactId(contact_id);
			contact.setStructuredName(new StructuredName(display_name));
			contact.setPhoto(photo);
			//contact.setPhone(phone);
			//contact.setEmail(email);
			sparse_contacts.put(contact_id, contact);
			//Cursor event_cursor = resolver.query(ContactsContract.Data.CONTENT_URI, 
				//	null, ContactsContract.CommonDataKinds.Event.CONTACT_ID + "=" + contact_id, null, null);
		}
		ShareContacts share_contacts = (ShareContacts) activity.getApplication();
		share_contacts.setContacts(sparse_contacts);
		//return contacts;
	}
	
	public static void readContact(Context context, Contact contact){
		int contact_id = contact.getRawContactId();
		ContentResolver resolver = context.getContentResolver();
		Cursor contact_cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, 
				null, ContactsContract.Contacts._ID + "=" + contact_id, null, null);
		while(contact_cursor.moveToNext()){
			Phone phone = PhoneRead.readPhone(resolver, contact_id);
			Email email = EmailRead.readEmail(resolver, contact_id);

			contact.setPhone(phone);
			contact.setEmail(email);
			//Cursor event_cursor = resolver.query(ContactsContract.Data.CONTENT_URI, 
				//	null, ContactsContract.CommonDataKinds.Event.CONTACT_ID + "=" + contact_id, null, null);
		}
	}
    
	public static void readContactPhotoById(Context context, Contact contact){
		contact.setPhoto(PhotoRead.readPhoto(context.getContentResolver() , contact.getPhoto().getPhotoId()));		
	}
}
