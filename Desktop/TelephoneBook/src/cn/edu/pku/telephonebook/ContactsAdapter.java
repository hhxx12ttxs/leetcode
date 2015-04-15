package cn.edu.pku.telephonebook;

import java.util.ArrayList;
import cn.edu.pku.telephonebook.contact.Contact;
import cn.edu.pku.telephonebook.database.ContactRead;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactsAdapter extends BaseAdapter {
    private ArrayList<Contact> contacts;
    private Context context;
    
	final static class ViewHolder 
	{
		TextView tv_contact_name;
		ImageView iv_contact_photo;
		TextView tv_catalog;
		TextView tv_contacts_num;
		ImageView iv_line_break;
	}
	
    public ContactsAdapter(Context _context, ArrayList<Contact> _contacts){
    	context = _context;
    	contacts = _contacts;
    }
    
    public ArrayList<Contact> getContacts(){
    	return contacts;
    }
    
	public void updateListView(ArrayList<Contact> _contacts){
		contacts = _contacts;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return contacts.size();
	}

	@Override
	public Object getItem(int position) {
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convert_view, ViewGroup parent) {
		ViewHolder holder;
		Contact contact = contacts.get(position);
		if (convert_view == null){
			holder = new ViewHolder();
			convert_view = View.inflate(context, R.layout.contacts_listview, null);
			holder.tv_contact_name = (TextView) convert_view.findViewById(R.id.contact_name);
			holder.iv_contact_photo = (ImageView) convert_view.findViewById(R.id.contact_photo);
			holder.tv_catalog = (TextView) convert_view.findViewById(R.id.catalog);
			holder.tv_contacts_num = (TextView) convert_view.findViewById(R.id.contacts_num);
			holder.iv_line_break = (ImageView) convert_view.findViewById(R.id.line_break);
			convert_view.setTag(holder);
		}
		
		else
			holder = (ViewHolder) convert_view.getTag();
		
		if(position == 0){
			holder.tv_contacts_num.setVisibility(View.VISIBLE);
			holder.tv_contacts_num.setText(String.valueOf(contacts.size()) + " 位联系人");
		}
		
		else
			holder.tv_contacts_num.setVisibility(View.GONE);
		
		if(isBeginOfSection(position)){
			holder.tv_catalog.setVisibility(View.VISIBLE);
			String section = String.valueOf(contacts.get(position).getStructuredName().getLetters().charAt(0));
			holder.tv_catalog.setText(section);
			holder.iv_line_break.setVisibility(View.VISIBLE);
		}
		
		else{
			holder.tv_catalog.setVisibility(View.GONE);
			holder.iv_line_break.setVisibility(View.GONE);
		}
		
		holder.tv_contact_name.setText(contact.getStructuredName().getDisplayName());
		ContactRead.readContactPhotoById(context, contact);
		byte[] photo = contact.getPhoto().getPhoto();
		if(photo == null){
			Bitmap bmp_photo = BitmapFactory.decodeResource(context.getResources(), R.drawable.contact_photo_default);
			bmp_photo = BitmapProcess.getRoundedCornerBitmap(bmp_photo);
			holder.iv_contact_photo.setImageBitmap(bmp_photo);
		}
		else
			holder.iv_contact_photo.setImageBitmap(ImageFormatConvert.getBitmapFromByte(photo));
		return convert_view;
	}
	
	private boolean isBeginOfSection(int target_position){
		char target_str = contacts.get(target_position).getStructuredName().getLetters().charAt(0);
		int section_position = getStrPosition(target_str);
		if(target_position == section_position)
			return true;
		return false;
	}
	
	public int getStrPosition(char target_str){
		for(int i = 0; i < contacts.size(); i ++){
			char curr_str = contacts.get(i).getStructuredName().getLetters().charAt(0);
			if(curr_str == target_str)
				return i;
		}
		return -1;
	}
}
