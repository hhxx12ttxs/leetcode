package cn.edu.pku.telephonebook.show;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.edu.pku.telephonebook.BitmapProcess;
import cn.edu.pku.telephonebook.ImageFormatConvert;
import cn.edu.pku.telephonebook.R;
import cn.edu.pku.telephonebook.ShareContacts;
import cn.edu.pku.telephonebook.config.Config;
import cn.edu.pku.telephonebook.contact.Contact;
import cn.edu.pku.telephonebook.database.ContactRead;
import cn.edu.pku.telephonebook.database.ContactWrite;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactShow extends Activity{
    private Contact contact = new Contact();
	private File photo_file;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("Enter into SowContact");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.show_contact);
		
		getContactInfo();
		fillContactInfo();
		initListener();
	}
	
	private void getContactInfo(){
		Intent intent = getIntent();
		String contact_id = intent.getStringExtra("id");
		ShareContacts share_contacts = (ShareContacts) getApplication();
		contact = share_contacts.getContactById(Integer.parseInt(contact_id));
		ContactRead.readContact(this, contact);
	}
	
	private void fillContactInfo(){
		TextView tv_contact_name = (TextView) findViewById(R.id.contact_name);
		tv_contact_name.setText(contact.getStructuredName().getDisplayName());
		fillPhoto();
		new PhoneShow(this, this, contact);
		new EmailShow(this, this, contact);		
	}
	
	private void fillPhoto(){
		ImageView iv_contact_photo = (ImageView) findViewById(R.id.contact_photo);
		if(contact.getPhoto().getPhoto() == null){
			Bitmap bmp_photo = BitmapFactory.decodeResource(getResources(), R.drawable.contact_photo_default);
			bmp_photo = BitmapProcess.getRoundedCornerBitmap(bmp_photo);
			iv_contact_photo = (ImageView) findViewById(R.id.contact_photo);
			iv_contact_photo.setImageBitmap(bmp_photo);
		}
		else
			iv_contact_photo.setImageBitmap(ImageFormatConvert.getBitmapFromByte(contact.getPhoto().getPhoto()));
	}
	
	private void initListener(){
		ImageView iv_back = (ImageView) findViewById(R.id.back_to_listview);
		ImageView iv_contact_photo = (ImageView) findViewById(R.id.contact_photo);
		
		iv_back.setOnClickListener(back_listener);
		iv_contact_photo.setOnClickListener(new PhotoListener(this));
	}
	
	private OnClickListener back_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
            finish();
		}
	};
	
	public class PhotoListener implements OnClickListener{
		private Context context;

	    public PhotoListener(Context _context){
	    	context = _context;
	    }
	    
		@Override
		public void onClick(View v) {
			createPhotoDialog().show();
		}
		
		private Dialog createPhotoDialog() {
			   String[] choices = new String[]{"拍摄新照片", "从图库中选择图片"};
			   ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
						android.R.layout.simple_list_item_1, choices);
			   AlertDialog.Builder builder = new AlertDialog.Builder(context).setSingleChoiceItems(
				    adapter, 0, new DialogInterface.OnClickListener() {
				        @Override
				        public void onClick(DialogInterface dialog, int which) {
				            dialog.dismiss();
				            switch (which) {
				                case 0:
				                	String state = Environment.getExternalStorageState();
				                	if(state.equals(Environment.MEDIA_MOUNTED))//判断SD卡是否可用
				                		takePhoto();//拍照
				                	else
				                		showToast("没有SD卡");
				                	break;
				                case 1:
				                    pickPhotoFromGallery();// 从相册中去获取
				                    break;
				             }
				        }
			         });
			   return builder.create();
	   }
	}
	
	private void takePhoto() {
		Config.PHOTO_DIR.mkdirs();// 创建照片的存储目录
		photo_file = new File(Config.PHOTO_DIR, getPhotoName());// 给新照的照片文件命名
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo_file));
		startActivityForResult(intent, Config.CAMERA_PHOTO);
	}
	   
	private void pickPhotoFromGallery() {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");  
		startActivityForResult(intent, Config.PICK_PHOTO);
	}

	private void cropPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", Config.PHOTO_ASPECTX);
		intent.putExtra("aspectY", Config.PHOTO_ASPECTY);
		intent.putExtra("outputX", Config.PHOTO_OUTPUTX);
		intent.putExtra("outputY", Config.PHOTO_OUTPUTY);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, Config.SET_PHOTO);
	}
	   
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode){
			case 1: cropPhoto(Uri.fromFile(photo_file)); break;
			case 2: cropPhoto(data.getData()); break;
			case 3: setPhoto(data); updateContactInfo(); break;
			default: break;
		}
		super.onActivityResult(requestCode, resultCode, data);  
	}
				
	private void setPhoto(Intent data){
		if(data == null) 
			return;
		Bundle extras = data.getExtras();
			if(extras == null)
				return;
		Bitmap bmp_photo = extras.getParcelable("data"); 
		bmp_photo = BitmapProcess.getRoundedCornerBitmap(bmp_photo);
		contact.getPhoto().setPhoto(ImageFormatConvert.getByteFromBitmap(bmp_photo));
		ImageView iv_contact_photo = (ImageView) findViewById(R.id.contact_photo);
		iv_contact_photo.setImageBitmap(bmp_photo);
	}
	
	private String getPhotoName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		return dateFormat.format(date) + ".jpg";
	}
	
	private void updateContactInfo(){
		ContactWrite.updatePhoto(this, contact.getRawContactId(), contact.getPhoto().getPhoto());
	}
	
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    } 
    
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}

