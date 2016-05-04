package com.higo.zhangyp.segmented;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;

/**
 * Created by zhangyipeng on 15/12/15.
 */
public class AndroidSegmentedManager extends SimpleViewManager<AndroidSegmented> {

    public static final String REACT_CLASS = "AndroidSegmented";

    private static final String COLOR_REGEX = "^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$";

    private Context context;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected AndroidSegmented createViewInstance(ThemedReactContext reactContext) {
        this.context = reactContext;
        return new AndroidSegmented(reactContext);
    }


    @Override
    protected void addEventEmitters(final ThemedReactContext reactContext, final AndroidSegmented view) {

        view.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int childCount = view.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ((RadioButton)view.getChildAt(i)).setChecked(false);
                    if (view.getChildAt(i).getId() == checkedId) {
                        ((RadioButton)view.getChildAt(i)).setChecked(true);


                        reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher()
                                .dispatchEvent(
                                        new AndroidSegmentedEvent(
                                                view.getId(),
                                                SystemClock.uptimeMillis(),
                                                i));
                    }
                }
            }
        });


    }


    @ReactProp(name = "childText")
    public void setChildText(AndroidSegmented view, ReadableArray data) {
        int childCount = data.size();

        for (int i = 0; i < childCount; ++i) {
            RadioButton child = (RadioButton) LayoutInflater.from(context).inflate(R.layout.radio_button, null);

            child.setText(data.getString(i));
            view.addView(child);


        }
    }


    @ReactProp(name = "selectedPosition")
    public void setSelectedChild(AndroidSegmented view, int position) {
        RadioButton radioBt = (RadioButton) (view.getChildAt(position));
        radioBt.setChecked(true);
    }


    @ReactProp(name = "orientation")
    public void setOrientation(AndroidSegmented view, String orientation) {
        view.setSegmentOrientation(orientation);
    }


    @ReactProp(name = "tintColor")
    public void setTintColor(AndroidSegmented view, ReadableArray data) {

        String type0 = data.getType(0).name();
        String type1 = data.getType(1).name();

        if ("String".equals(type0) && "String".equals(type1)) {
            String color0 = data.getString(0);
            String color1 = data.getString(1);
            if (color0 != null && color1 != null) {
                if (color0.matches(COLOR_REGEX) && color1.matches(COLOR_REGEX)) {

                    view.setTintColor(Color.parseColor(color0), Color.parseColor(color1));
                } else {
                    throw new JSApplicationIllegalArgumentException("Invalid arrowColor property: " + color0);
                }
            }
        }
    }
}
