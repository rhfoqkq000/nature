package com.donga.nature.npe;

import android.graphics.drawable.Drawable;

/**
 * Created by nature on 16. 7. 19.
 */
public class ListData {

    public Drawable micon;
    public String mtext;

    public void setIcon(Drawable icon){
        micon=icon;
    }
    public void setText(String text){
        mtext=text;
    }

    public Drawable getIcon(){
        return this.micon;
    }
    public String getText(){
        return this.mtext;
    }
}
