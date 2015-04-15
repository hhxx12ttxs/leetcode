package cn.edu.pku.telephonebook.add;

import java.util.ArrayList;

import cn.edu.pku.telephonebook.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<String> list;
	
	public ListViewAdapter(Context _context, ArrayList<String> _list) {
	    context = _context;
	    list = _list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convert_view, ViewGroup parent) {
		if(convert_view == null){
			convert_view = View.inflate(context, R.layout.lv_items, null);
		}
		
		TextView tv = (TextView)convert_view.findViewById(R.id.text);
		tv.setText(list.get(position));
		
		return convert_view;
	}
}
