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

public class PhoneShow {

    private int TITLE_TEXT_SIZE;
    private int PHONE_IMAGE_WIDTH;
    private int PHONE_IMAGE_HEIGHT;
    private int MESSAGE_IMAGE_WIDTH;
    private int MESSAGE_IMAGE_HEIGHT;
    private int BLUE_LINE_HEIGHT;
    private int GRAY_LINE_HEIGHT;
    private int TITLE_PADDING_TOP;
    private int TEXT_PADDING_TOP;
    private int TITLE_LINE_MARGIN_TOP;
    private int TEXT_LINE_MARGIN_TOP;
    private int PHONE_IMAGE_MARGIN_LEFT;
    
    private String PHONE_TITLE = "手机";
    
	private Activity activity;
	private Context context;
	private Contact contact;
	private LinearLayout lv;
	
	private SparseArray<String> ID_PHONE = new SparseArray<String>();
	
	public  PhoneShow(Activity _activity, Context _context, Contact _contact){
		
		if(_contact.getPhone().getPhoneNum() == 0)
			return;
		
        activity = _activity;
        context = _context;
        contact = _contact;
         
        initField();
        
		SparseArray<String> mobile = contact.getPhone().getMobile();//TYPE_MOBILE 手机
		SparseArray<String> home = contact.getPhone().getHome();//TYPE_HOME 住宅
		SparseArray<String> work = contact.getPhone().getWork();//TYPE_WORK 工作
		SparseArray<String> fax_work = contact.getPhone().getFaxWork();//TYPE_FAX_WORK 工作传真
		SparseArray<String> fax_home = contact.getPhone().getFaxHome();//TYPE_FAX_HOME 住宅传真
		SparseArray<String> car = contact.getPhone().getCar();//TYPE_CAR 车载电话
		SparseArray<String> other = contact.getPhone().getOther();//TYPE_OTHER 其他
		
        showPhoneTitle();
        
		showEachTypePhone(mobile, "手机");
		showEachTypePhone(home, "住宅");
		showEachTypePhone(work, "单位");
		showEachTypePhone(fax_work, "单位传真");
		showEachTypePhone(fax_home, "住宅传真");
		showEachTypePhone(car, "车载");
		showEachTypePhone(other, "其他");
	}
	
	private void initField(){

		lv = (LinearLayout) activity.findViewById(R.id.linearlayout_contact_info);
		
	    TITLE_TEXT_SIZE = DensityUtil.sp2px(context, 6);
	    PHONE_IMAGE_WIDTH = DensityUtil.dip2px(context, 39);
	    PHONE_IMAGE_HEIGHT = DensityUtil.dip2px(context, 40);
	    MESSAGE_IMAGE_WIDTH = DensityUtil.dip2px(context, 40);
	    MESSAGE_IMAGE_HEIGHT = DensityUtil.dip2px(context, 40);
	    BLUE_LINE_HEIGHT = DensityUtil.dip2px(context, 2);
	    GRAY_LINE_HEIGHT = DensityUtil.dip2px(context, 1);
	    TITLE_PADDING_TOP =  DensityUtil.dip2px(context, 8);
	    TEXT_PADDING_TOP = DensityUtil.dip2px(context, 3);
	    TITLE_LINE_MARGIN_TOP = DensityUtil.dip2px(context, 30);
	    TEXT_LINE_MARGIN_TOP = DensityUtil.dip2px(context, 43);
	    PHONE_IMAGE_MARGIN_LEFT = DensityUtil.dip2px(context, 220);
	}
	
	private void showPhoneTitle(){	
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout phone_title_relative_layout = new RelativeLayout(context);
		phone_title_relative_layout.setLayoutParams(params);
		phone_title_relative_layout.setPadding(0, TITLE_PADDING_TOP, 0, 0);
		
		TextView title_text = createTitleText();
		View line = createLine(RelativeLayout.LayoutParams.MATCH_PARENT, BLUE_LINE_HEIGHT, 
				Color.BLUE, TITLE_LINE_MARGIN_TOP);
		
		phone_title_relative_layout.addView(title_text);
		phone_title_relative_layout.addView(line);
		
		lv.addView(phone_title_relative_layout);
	}
	
	private void showEachTypePhone(SparseArray<String> phone, String label){
		for(int i = 0; i < phone.size(); i ++)
			lv.addView(createPhoneRelativeLayout(phone.valueAt(i), label));
	}

	private RelativeLayout createPhoneRelativeLayout(String phone_num, String label){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout phone_relative_layout = new RelativeLayout(context);
		phone_relative_layout.setLayoutParams(params);
		phone_relative_layout.setPadding(0, TEXT_PADDING_TOP, 0, 0);
		
		TextView phone_text = createPhoneText(phone_num, label);
		ImageView phone_image = createPhoneImage(phone_num);
		ImageView message_image = createMessageImage(phone_num);
		View line = createLine(RelativeLayout.LayoutParams.MATCH_PARENT, GRAY_LINE_HEIGHT, 
				Color.GRAY, TEXT_LINE_MARGIN_TOP);
		
		phone_relative_layout.addView(phone_text);
		phone_relative_layout.addView(phone_image);
		phone_relative_layout.addView(message_image);
		phone_relative_layout.addView(line);
		
		return phone_relative_layout;
	}
	
	private View createLine(int width, int height, int color, int margin_top){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.setMargins(0, margin_top, 0, 0);
		View line = new View(context);
		line.setLayoutParams(params);
		line.setBackgroundColor(color);
		return line;
	}
	
	private ImageView createPhoneImage(String phone){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(PHONE_IMAGE_WIDTH, PHONE_IMAGE_HEIGHT);
		params.setMargins(PHONE_IMAGE_MARGIN_LEFT, 0, 0, 0);
		ImageView image_view = new ImageView(context);
		image_view.setId(Config.DYNAMIC_VIEW_ID ++);
		ID_PHONE.put(Config.DYNAMIC_VIEW_ID - 1, phone);
		image_view.setLayoutParams(params);
		image_view.setImageResource(R.drawable.telephone); 
		image_view.setOnClickListener(phone_listener);
		return image_view;
	}
	
	private OnClickListener phone_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("tel:" + ID_PHONE.get(v.getId()));
			Intent it = new Intent(Intent.ACTION_DIAL, uri);
			activity.startActivity(it);
		}
	};
	
	private ImageView createMessageImage(String phone){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(MESSAGE_IMAGE_WIDTH, MESSAGE_IMAGE_HEIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		ImageView image_view = new ImageView(context);
		image_view.setId(Config.DYNAMIC_VIEW_ID ++);
		ID_PHONE.put(Config.DYNAMIC_VIEW_ID - 1, phone);
		image_view.setLayoutParams(params);
		image_view.setImageResource(R.drawable.short_message); 
		image_view.setOnClickListener(message_listener);
		return image_view;
	}
	
	private OnClickListener message_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("smsto:" + ID_PHONE.get(v.getId()));
			Intent it = new Intent(Intent.ACTION_SENDTO,
					uri);
			activity.startActivity(it);
		}
	};
	
    private TextView createTitleText(){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		TextView text_view = new TextView(context);
		text_view.setLayoutParams(params);
		text_view.setTextSize(TITLE_TEXT_SIZE);
		text_view.setText(PHONE_TITLE);
		text_view.setTextColor(Color.BLUE);
		return text_view;
    }
    
    private TextView createPhoneText(String text, String label){
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

