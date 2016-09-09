package com.example.kim.qazz;

import android.graphics.drawable.Drawable;

/**
 * Created by kim on 16. 7. 19.
 */
public class Support_ListData {

    public Drawable support_icon;
    public String support_text;

    public void setIcon(Drawable icon){
        support_icon=icon;
    }
    public void setText(String text){
        support_text=text;
    }

    public Drawable getIcon(){
        return this.support_icon;
    }
    public String getText(){
        return this.support_text;
    }
}
