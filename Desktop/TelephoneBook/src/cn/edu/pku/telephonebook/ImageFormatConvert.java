package cn.edu.pku.telephonebook;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageFormatConvert {
	public static Bitmap getBitmapFromByte(byte[] byte_photo) {
		if (byte_photo != null) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(byte_photo, 0, byte_photo.length);
			return bitmap;
		}
		return null;
	}
	
	public static byte[] getByteFromBitmap(Bitmap bmp){
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
		bmp.compress(Bitmap.CompressFormat.PNG, 100, os);  
		return os.toByteArray();
	}
}
