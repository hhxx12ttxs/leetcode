package cn.edu.pku.telephonebook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View
{

	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	private int choose  = -1;
	private Paint paint = new Paint();
	private TextView text_dialog;
	
	public static String[] letters = {
			"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	public void setTextDialog(TextView _text_dialog){
		this.text_dialog = _text_dialog;
	}

	public SideBar(Context context, AttributeSet attrs, int defStyleAttr){
		super(context, attrs, defStyleAttr);
	}

	public SideBar(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	public SideBar(Context context){
		super(context);
	}

	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		int height = getHeight();
		int width = getWidth();
		int item_height = height / letters.length;
		
		for (int i = 0; i < letters.length; i++){
			paint.setColor(Color.rgb(33, 65, 98));
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(20);
			
			if(i == choose){
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
			}
			
			float x = width / 2 - paint.measureText(letters[i]) / 2;
			float y = item_height * i + item_height;
			canvas.drawText(letters[i], x, y, paint);
			paint.reset();
		}
	}
	
	

	@SuppressWarnings("deprecation")
	@Override
	public boolean dispatchTouchEvent(MotionEvent event){
		final int action = event.getAction();
		final float y = event.getY(); 
		final int origin_choose = choose;
		final OnTouchingLetterChangedListener changedListener = onTouchingLetterChangedListener;
		final int letter_pos = (int)( y / getHeight() * letters.length);
		
		switch (action){
			case MotionEvent.ACTION_UP:
				setBackgroundDrawable(new ColorDrawable(0x00000000));
				choose = -1;
				invalidate();
				if (text_dialog != null)
					text_dialog.setVisibility(View.INVISIBLE);
				break;
				
			default:
				setBackgroundResource(R.drawable.xml_sidebar_background);
				if (origin_choose != letter_pos ){
					if (letter_pos  >= 0 && letter_pos  < letters.length){
						if (changedListener != null)
							changedListener.onTouchingLetterChanged(letters[letter_pos ]);
						if (text_dialog != null){
							text_dialog.setText(letters[letter_pos ]);
							text_dialog.setVisibility(View.VISIBLE);
						}
						
						choose = letter_pos ;
						invalidate();
					}
				}
				break;
		}
		return true;
	}

	public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener changedListener){
		this.onTouchingLetterChangedListener = changedListener;
	}
	
	public interface OnTouchingLetterChangedListener{
		public void onTouchingLetterChanged(String str);
	}
}
