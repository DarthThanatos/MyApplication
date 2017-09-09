package com.example.vobis.myapplication.trail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.vobis.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Vobis on 2015-08-03.
 */
public class TrailThirdLevel extends BaseExpandableListAdapter {

    Context ctx;
    String key;
    ArrayList<String> Second = new ArrayList<String>();

    public TrailThirdLevel(Context ctx,String key,ArrayList<String> Second){
        this.key = key;
        this.Second = Second;
        this.ctx = ctx;
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Second.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return key;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Second.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_nested3,null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.third_child_item);
        textView.setText(key);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_nested4,null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.fourth_child_item);
        textView.setText((String)this.getChild(groupPosition,childPosition));
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

