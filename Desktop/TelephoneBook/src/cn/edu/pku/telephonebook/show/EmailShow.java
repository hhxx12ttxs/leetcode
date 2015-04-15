package cn.edu.pku.telephonebook.show;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.edu.pku.telephonebook.R;
import cn.edu.pku.telephonebook.config.Config;
import cn.edu.pku.telephonebook.contact.Contact;

public class EmailShow {
	
    private int TITLE_TEXT_SIZE;
    private int EMAIL_IMAGE_WIDTH;
    private int EMAIL_IMAGE_HEIGHT;
    private int BLUE_LINE_HEIGHT;
    private int GRAY_LINE_HEIGHT;
    private int TITLE_PADDING_TOP;
    private int TEXT_PADDING_TOP;
    private int TITLE_LINE_MARGIN_TOP;
    private int TEXT_LINE_MARGIN_TOP;
    
    private String EMAIL_TITLE = "邮箱";
    
	private Activity activity;
	private Context context;
	private Contact contact;
	private LinearLayout lv;
	
	private SparseArray<String> ID_EMAIL = new SparseArray<String>();
    
	public EmailShow(Activity _activity, Context _context, Contact _contact){
		
		if(_contact.getEmail().getEmailNum() == 0)
			return;
		
		activity = _activity;
		context = _context;
		contact = _contact;
		
		initField();
		
		SparseArray<String> home = contact.getEmail().getHome();//TYPE_HOME 住宅
		SparseArray<String> work = contact.getEmail().getWork();//TYPE_WORK 工作
		SparseArray<String> other = contact.getEmail().getOther();//TYPE_OTHER 其他
		
		showEmailTitle();
		
		showEachTypeEmail(home, "住宅");
		showEachTypeEmail(work, "单位");
		showEachTypeEmail(other, "其他");
	}
	
	private void initField(){

		lv = (LinearLayout) activity.findViewById(R.id.linearlayout_contact_info);
		
	    TITLE_TEXT_SIZE = DensityUtil.sp2px(context, 6);
	    EMAIL_IMAGE_WIDTH = DensityUtil.dip2px(context, 39);
	    EMAIL_IMAGE_HEIGHT = DensityUtil.dip2px(context, 40);
	    BLUE_LINE_HEIGHT = DensityUtil.dip2px(context, 2);
	    GRAY_LINE_HEIGHT = DensityUtil.dip2px(context, 1);
	    TITLE_PADDING_TOP =  DensityUtil.dip2px(context, 8);
	    TEXT_PADDING_TOP = DensityUtil.dip2px(context, 3);
	    TITLE_LINE_MARGIN_TOP = DensityUtil.dip2px(context, 30);
	    TEXT_LINE_MARGIN_TOP = DensityUtil.dip2px(context, 43);
	}
	
	private void showEmailTitle(){	
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout phone_title_relative_layout = new RelativeLayout(context);
		phone_title_relative_layout.setLayoutParams(params);
		phone_title_relative_layout.setPadding(0, TITLE_PADDING_TOP, 0, 0);
		
		TextView title_text = createTitleText(EMAIL_TITLE);
		View line = createLine(RelativeLayout.LayoutParams.MATCH_PARENT, BLUE_LINE_HEIGHT, 
				Color.BLUE, TITLE_LINE_MARGIN_TOP);
		
		phone_title_relative_layout.addView(title_text);
		phone_title_relative_layout.addView(line);
		
		lv.addView(phone_title_relative_layout);
	}
	
	private void showEachTypeEmail(SparseArray<String> email, String label){
		for(int i = 0; i < email.size(); i ++)
			lv.addView(createEmailRelativeLayout(email.valueAt(i), label));
	}
	
	private RelativeLayout createEmailRelativeLayout(String email, String label){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout phone_relative_layout = new RelativeLayout(context);
		phone_relative_layout.setLayoutParams(params);
		phone_relative_layout.setPadding(0, TEXT_PADDING_TOP, 0, 0);
		
		TextView email_text = createEmailText(email, label);
		ImageView email_image = createEmailImage(email);
		View line = createLine(RelativeLayout.LayoutParams.MATCH_PARENT, GRAY_LINE_HEIGHT, 
				Color.GRAY, TEXT_LINE_MARGIN_TOP);
		
		phone_relative_layout.addView(email_text);
		phone_relative_layout.addView(email_image);
		phone_relative_layout.addView(line);
		
		return phone_relative_layout;
	}
	
	private ImageView createEmailImage(String email){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(EMAIL_IMAGE_WIDTH, EMAIL_IMAGE_HEIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		ImageView image_view = new ImageView(context);
		image_view.setId(Config.DYNAMIC_VIEW_ID ++);
		ID_EMAIL.put(Config.DYNAMIC_VIEW_ID - 1, email);
		image_view.setLayoutParams(params);
		image_view.setImageResource(R.drawable.email); 
		image_view.setOnClickListener(email_listener);
		return image_view;
	}
	
	private OnClickListener email_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("mailto:" + ID_EMAIL.get(v.getId())); 
			Intent it = new Intent(Intent.ACTION_SENDTO, uri); 
			activity.startActivity(it); 
		}
	};
	
    private TextView createTitleText(String text){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		TextView text_view = new TextView(context);
		text_view.setLayoutParams(params);
		text_view.setTextSize(TITLE_TEXT_SIZE);
		text_view.setText(EMAIL_TITLE);
		text_view.setTextColor(Color.BLUE);
		return text_view;
    }
    
	private View createLine(int width, int height, int color, int margin_top){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.setMargins(0, margin_top, 0, 0);
		View line = new View(context);
		line.setLayoutParams(params);
		line.setBackgroundColor(color);
		return line;
	}
	
    private TextView createEmailText(String text, String label){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		TextView text_view = new TextView(context);
		text_view.setLayoutParams(params);
		
		SpannableStringBuilder word = new SpannableStringBuilder();
		
		word.append(text);
		int start = 0, end = text.length();
		word.setSpan(new ForegroundColorSpan(Color.BLACK), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		word.append("\r\n" + label);
		start = end;
		end += label.length();
		word.setSpan(new ForegroundColorSpan(Color.BLACK), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		text_view.setText(word);
		
		return text_view;
    }
	
}
