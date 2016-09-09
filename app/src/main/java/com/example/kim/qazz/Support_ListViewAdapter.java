package com.example.kim.qazz;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kim on 16. 7. 19.
 */
public class Support_ListViewAdapter extends BaseAdapter {

    public ArrayList<Support_ListData> mlistData = new ArrayList<Support_ListData>();

    public Support_ListViewAdapter(){
    }

    public class ViewHolder{
        public ImageView micon;
        public TextView mtext;
    }

    @Override
    public int getCount() {
        return mlistData.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_support, parent, false);
        }

        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.image_support);
        TextView textView = (TextView)convertView.findViewById(R.id.text_support);

        Support_ListData listData = mlistData.get(position);

        iconImageView.setImageDrawable(listData.getIcon());
        textView.setText(listData.getText());

        return convertView;
    }

    public void addItem(Drawable icon, String text){
        Support_ListData addInfo = new Support_ListData();
        addInfo.support_icon = icon;
        addInfo.support_text = text;

        mlistData.add(addInfo);
    }
}
