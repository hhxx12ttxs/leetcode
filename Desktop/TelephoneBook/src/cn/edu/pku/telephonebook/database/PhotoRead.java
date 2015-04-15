package cn.edu.pku.telephonebook.database;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import cn.edu.pku.telephonebook.contact.Photo;

public class PhotoRead {
    public static Photo readPhoto(ContentResolver resolver, int photo_id){
		Cursor photo_cursor = resolver.query(ContactsContract.Data.CONTENT_URI, 
				null, ContactsContract.Data._ID + "=" + String.valueOf(photo_id), null, null);
		Photo photo = new Photo();
		while(photo_cursor.moveToNext()){
			byte[] photo_byte = photo_cursor.getBlob(photo_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO));
		    photo.setPhoto(photo_byte);
		}
		return photo;
    }
}
