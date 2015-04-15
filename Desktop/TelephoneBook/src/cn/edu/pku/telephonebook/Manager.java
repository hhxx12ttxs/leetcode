package cn.edu.pku.telephonebook;

import java.util.ArrayList;
import java.util.Collections;

import cn.edu.pku.telephonebook.add.ContactAdd;
import cn.edu.pku.telephonebook.contact.Contact;
import cn.edu.pku.telephonebook.database.ContactRead;
import cn.edu.pku.telephonebook.show.ContactShow;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.edu.pku.telephonebook.SideBar.OnTouchingLetterChangedListener;

public class Manager extends Activity {
    
	private ListView contacts_list_view;
	private ArrayList<Contact> contacts = new ArrayList<Contact>();
	private ContactsAdapter contacts_adapter;
	private EditText search_box;
	private SideBar side_bar;
	private TextView letter_dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.main);
		initLayout();
	}
	
    
	@Override
	protected void onStart() {
		super.onStart();
		initLayout();
	}
	
	private void initLayout(){
		initContacts();
		initSearchBox();
		initSideBar();
		initContactsListView();
		initBottomBar();
	}

	private void initContacts(){
		ContactRead.readAllContactsName(this, this);
		ShareContacts share_contacts = (ShareContacts) getApplication();
		contacts = share_contacts.getAllContacts();

	}
	
	
	private void initSearchBox(){
		search_box = (EditText) findViewById(R.id.search_box);
		search_box.addTextChangedListener(filter_text_watcher);
	}
	
	private TextWatcher filter_text_watcher = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable search_content) {
		}

		@Override
		public void beforeTextChanged(CharSequence search_content, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence constraint, int start, int before,
				int count) {
			performFiltering(constraint);
		}
	};
	
	private void initSideBar(){
		side_bar = (SideBar) findViewById(R.id.side_bar);
		letter_dialog = (TextView) findViewById(R.id.letter_dialog);
		side_bar.setTextDialog(letter_dialog);
		side_bar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener(){
			public void onTouchingLetterChanged(String str){
				int position =  contacts_adapter.getStrPosition(str.charAt(0));
				if (position != -1)
					contacts_list_view.setSelection(position);
			}
		});
	}
	
    private void initContactsListView(){
		contacts_list_view = (ListView) findViewById(R.id.contacts_listview);
		contacts_list_view.setSelector(new ColorDrawable(Color.TRANSPARENT));
		contacts_adapter = new ContactsAdapter(getApplicationContext(), contacts);
		contacts_list_view.setAdapter(contacts_adapter);
		contacts_list_view.setOnItemClickListener(contact_click_listener);
    }
	
    private void initBottomBar(){
    	ImageView add_contact = (ImageView) findViewById(R.id.add_contact_icon);
    	add_contact.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Manager.this, ContactAdd.class);
				startActivity(intent);
			}
    		
    	});
    	
    	ImageView search_contact = (ImageView) findViewById(R.id.search_contact_icon);
    	search_contact.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
			      search_box.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, search_box.getLeft()+5, search_box.getTop()+5, 0));
		          search_box.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, search_box.getLeft()+5, search_box.getTop()+5, 0));
			}
    	});
    }
    
 	private OnItemClickListener contact_click_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(Manager.this, ContactShow.class);
			Contact contact = contacts_adapter.getContacts().get(position);
			intent.putExtra("id", String.valueOf(contact.getRawContactId()));
			startActivity(intent);
		}
 	};
 	
	@SuppressLint("DefaultLocale")
	private void performFiltering(CharSequence constraint) {
		ArrayList<Contact> results = new ArrayList<Contact>();
		if (constraint == null || constraint.length() == 0) 
            results = contacts;
		else {
			for (Contact contact: contacts) {
				if (contact.getStructuredName().getLetters().startsWith(constraint.toString().toUpperCase())) {
					results.add(contact);
				}
			}
		}
		
		Collections.sort(results);
		contacts_adapter.updateListView(results);
		contacts_list_view.setSelection(0);
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

