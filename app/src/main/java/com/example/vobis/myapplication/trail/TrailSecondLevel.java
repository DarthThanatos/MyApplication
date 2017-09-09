package com.example.vobis.myapplication.trail;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.vobis.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vobis on 2015-08-03.
 */
public class TrailSecondLevel extends BaseExpandableListAdapter{

    Context ctx;
    ArrayList<String> Keys = new ArrayList<String>();
    HashMap<String,ArrayList<String>> First = new HashMap<String,ArrayList<String>>();

    public TrailSecondLevel(Context ctx, ArrayList<String> Keys, HashMap<String, ArrayList<String>> First){
        this.ctx = ctx;
        this.Keys = Keys;
        this.First = First;
    }


    @Override
    public int getGroupCount() {
        return Keys.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return First.get(Keys.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return Keys.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return First.get(Keys.get(groupPosition)).get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String) this.getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_nested1,null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.second_child_item);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        CustExpListView SecondLevelexplv = new CustExpListView(ctx);
        ArrayList<String> Second = new ArrayList<String>();
        for (int i = 0; i<Constant.child[groupPosition][childPosition].length;i++)
            Second.add(Constant.child[groupPosition][childPosition][i]);
        String key = (String) this.getChild(groupPosition,childPosition);
        SecondLevelexplv.setAdapter(new TrailThirdLevel(ctx,key,Second));
        SecondLevelexplv.setGroupIndicator(null);
        return SecondLevelexplv;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

