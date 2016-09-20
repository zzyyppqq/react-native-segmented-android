package com.higo.zhangyp.segmented;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/**
 * Created by zhangyipeng on 15/12/15.
 */
public class AndroidSegmentedEvent extends Event<AndroidSegmentedEvent> {

    public static final String EVENT_NAME = "topChange";
    private final int selectedPosition;

    public AndroidSegmentedEvent(int viewId, int selectedPosition) {
        super(viewId);
        this.selectedPosition = selectedPosition;
    }


    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());

    }


    @Override
    public short getCoalescingKey() {
        return 0;
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
//        eventData.putInt("target", getViewTag());
        eventData.putInt("selected", getPosition());

        return eventData;
    }

    private int getPosition() {
        return selectedPosition;
    }
}
