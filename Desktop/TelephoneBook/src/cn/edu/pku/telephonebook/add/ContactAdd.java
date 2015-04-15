package cn.edu.pku.telephonebook.add;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import cn.edu.pku.telephonebook.R;

public class ContactAdd extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("Enter into AddContact");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.add_contact);
		new SavePattern(this, this);
	}
	
}
