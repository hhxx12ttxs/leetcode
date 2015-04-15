package cn.edu.pku.telephonebook.database;

import java.util.ArrayList;



import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.Data;

public class ContactWrite {

	public static void updatePhoto(Context context, int contact_id, byte[] photo){
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
				.withSelection(
				Data.RAW_CONTACT_ID + "=?" + " AND "+ ContactsContract.Data.MIMETYPE + " = ?",
				new String[] { String.valueOf(contact_id),Photo.CONTENT_ITEM_TYPE})
				.withValue(Photo.PHOTO, photo).build());
	    try {
			context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}
	}
}
