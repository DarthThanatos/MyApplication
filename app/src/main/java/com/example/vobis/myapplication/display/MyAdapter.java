package com.example.vobis.myapplication.display;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.vobis.myapplication.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vobis on 2015-07-31.
 */
public class MyAdapter extends BaseExpandableListAdapter{
    private List<String> header_titles;
    private HashMap<String,List<String>> child_titles;
    private Context ctx;

    MyAdapter(Context ctx,List<String> header_titles,HashMap<String,List<String>> child_titles){
        this.ctx = ctx;
        this.child_titles=child_titles;
        this.header_titles=header_titles;
    }
    @Override
    public int getGroupCount() {
        return header_titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = header_titles.get(groupPosition);
        return child_titles.get(key).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return header_titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_titles.get(header_titles.get(groupPosition)).get(childPosition);
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
        String title = (String)(this.getGroup(groupPosition));
        if(convertView==null){
            LayoutInflater layoutInfalter= (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInfalter.inflate(R.layout.activity_parent_layout, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.heading_item);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String title = (String)getChild(groupPosition,childPosition);
        if (convertView==null){
            LayoutInflater layoutInfalter= (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInfalter.inflate(R.layout.activity_child_layout, null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.child_item);
        textView.setText(title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
