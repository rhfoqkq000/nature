package com.donga.nature.npe;

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
 * Created by nature on 16. 7. 19.
 */
public class Success_ListViewAdapter extends BaseAdapter {

    public ArrayList<ListData> mlistData = new ArrayList<ListData>();

    public Success_ListViewAdapter(){
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
            convertView = inflater.inflate(R.layout.listview, parent, false);
        }

        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.mimage);
        TextView textView = (TextView)convertView.findViewById(R.id.mtext);

        ListData listData = mlistData.get(position);

        iconImageView.setImageDrawable(listData.getIcon());
        textView.setText(listData.getText());

        return convertView;
    }

    public void addItem(Drawable icon, String text){
        ListData addInfo = new ListData();
        addInfo.micon = icon;
        addInfo.mtext = text;

        mlistData.add(addInfo);
    }
}
