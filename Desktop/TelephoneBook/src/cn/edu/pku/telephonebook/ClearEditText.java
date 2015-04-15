package cn.edu.pku.telephonebook;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

public class ClearEditText extends EditText implements  OnFocusChangeListener, TextWatcher
{

	private Drawable clear_drawable; 
 
    public ClearEditText(Context context) { 
    	this(context, null); 
    } 
 
    public ClearEditText(Context context, AttributeSet attrs) { 
    	this(context, attrs, android.R.attr.editTextStyle); 
    } 
    
    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    
    
    private void init() { 
    	clear_drawable = getCompoundDrawables()[2]; 
        if (clear_drawable == null) { 
        	clear_drawable = getResources() 
                    .getDrawable(R.drawable.btn_tips_close); 
        } 
        clear_drawable.setBounds(-20, 0, clear_drawable.getIntrinsicWidth()-20, clear_drawable.getIntrinsicHeight()); 
        setClearIconVisible(false); 
        setOnFocusChangeListener(this); 
        addTextChangedListener(this); 
    } 
 
	@Override 
    public boolean onTouchEvent(MotionEvent event) { 
    	 if (event.getAction() == MotionEvent.ACTION_UP) { 
            if (getCompoundDrawables()[2] != null) { 
                boolean touchable = 
            			event.getX() > (getWidth() - getPaddingRight() - clear_drawable.getIntrinsicWidth()) 
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) 
                    this.setText(""); 
            } 
        } 
 
        return super.onTouchEvent(event); 
    } 
 
    public void onFocusChange(View v, boolean hasFocus) { 
        if (hasFocus) { 
            setClearIconVisible(getText().length() > 0); 
        } else { 
            setClearIconVisible(false); 
        } 
    } 
 
    protected void setClearIconVisible(boolean visible) { 
        Drawable right = visible ? clear_drawable : null; 
        setCompoundDrawables(getCompoundDrawables()[0], 
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]); 
    } 
     
    @Override 
    public void onTextChanged(CharSequence s, int start, int count, 
            int after) { 
        setClearIconVisible(s.length() > 0); 
    } 
 
    public void beforeTextChanged(CharSequence s, int start, int count, 
            int after) { 
         
    } 
 
    public void afterTextChanged(Editable s) { 
         
    } 
    

    public void setShakeAnimation(){
    	this.setAnimation(shakeAnimation(5));
    }
    
    public static Animation shakeAnimation(int counts){
    	Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
    	translateAnimation.setInterpolator(new CycleInterpolator(counts));
    	translateAnimation.setDuration(1000);
    	return translateAnimation;
    }
}
