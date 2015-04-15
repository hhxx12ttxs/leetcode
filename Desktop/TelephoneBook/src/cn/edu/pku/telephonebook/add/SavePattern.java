package cn.edu.pku.telephonebook.add;

import java.util.ArrayList;

import cn.edu.pku.telephonebook.R;
import cn.edu.pku.telephonebook.R.color;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class SavePattern{
    private ArrayList<String> list = new ArrayList<String>();
    private Activity activity;
    private Context context;
    private TextView button;
    private PopupWindow pw;
    
	public SavePattern(Activity _activity, Context _context){
		activity = _activity;
		context = _context;
		list.add("ÊÖ»ú");
		list.add("SIM¿¨");
		
		button = (TextView) _activity.findViewById(R.id.select_save_pattern);
		button.setText(list.get(0));
		button.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				View view = activity.getLayoutInflater().inflate(R.layout.pop, null);
				pw = new PopupWindow(view, 300, LayoutParams.WRAP_CONTENT, true);
				pw.setBackgroundDrawable(context.getResources().getDrawable(color.deepskyblue));
				pw.setFocusable(true);
                pw.showAsDropDown(button);
                
                ListView lv = (ListView) view.findViewById(R.id.lv_pop);
                lv.setAdapter(new ListViewAdapter(context.getApplicationContext(), list));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
					public void onItemClick(AdapterView<?> parent, View v,
							int position, long id) {
						button.setText(list.get(position));
						pw.dismiss();
					}
                
                });
                
			}
			
		});
	}
}
