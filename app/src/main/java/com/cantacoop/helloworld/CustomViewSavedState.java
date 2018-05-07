package com.cantacoop.helloworld;

import android.annotation.TargetApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class CustomViewSavedState extends View.BaseSavedState {

    private Boolean blue;

    public Boolean isBlue() {
        return blue;
    }

    public void setBlue(Boolean blue) {
        this.blue = blue;
    }

    public CustomViewSavedState(Parcel source) {
        super(source);

        // Read back
        blue = source.readInt() == 1;
    }

    @TargetApi(24)
    public CustomViewSavedState(Parcel source, ClassLoader loader) {
        super(source, loader);
    }

    public CustomViewSavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);

        // Write var here
        out.writeInt(blue ? 1 : 0);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new CustomViewSavedState(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new CustomViewSavedState[size];
        }
    };
}
