package com.higo.zhangyp.segmented;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.facebook.react.uimanager.ThemedReactContext;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by zhangyipeng on 15/12/15.
 */
public class AndroidSegmented extends SegmentedGroup{


    public void setSegmentOrientation(String str){
        if(str.equals("horizontal")){
            setOrientation(RadioGroup.HORIZONTAL);
        }else if(str.equals("vertical")){
            setOrientation(RadioGroup.VERTICAL);
        }
    }


    public AndroidSegmented(ThemedReactContext context) {
        super(context);
        setGravity(Gravity.CENTER);

        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
    }

    private final Runnable mLayoutRunnable = new Runnable() {
        @Override
        public void run() {
            measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

    @Override
    public void requestLayout() {
        super.requestLayout();
        post(mLayoutRunnable);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

}
