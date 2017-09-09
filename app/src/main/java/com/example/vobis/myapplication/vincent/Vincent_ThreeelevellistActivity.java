package com.example.vobis.myapplication.vincent;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.example.vobis.myapplication.R;

public class Vincent_ThreeelevellistActivity extends ActionBarActivity {
    ExpandableListView explvlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail);
        explvlist = (ExpandableListView) findViewById(R.id.ParentLevel);
        explvlist.setAdapter(new ParentLevel());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_trail, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_save:
                Toast.makeText(getApplicationContext(),"File saved",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_new:
                Toast.makeText(getApplicationContext(), "New Activity started", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_load:
                Toast.makeText(getApplicationContext(), "File Loaded", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(getApplicationContext(),"Search selected",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public class ParentLevel extends BaseExpandableListAdapter {

        @Override
        public Object getChild(int arg0, int arg1) {
            return arg1;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            CustExpListview SecondLevelexplv = new CustExpListview(Vincent_ThreeelevellistActivity.this);
            SecondLevelexplv.setAdapter(new SecondLevelAdapter());
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 3;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return 5;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            TextView tv = new TextView(Vincent_ThreeelevellistActivity.this);
            tv.setText("->FirstLevel");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);
            tv.setBackgroundColor(Color.BLUE);
            tv.setPadding(10, 7, 7, 7);

            return tv;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }










    public class CustExpListview extends ExpandableListView {

        public CustExpListview(Context context) {
            super(context);
        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //System.out.println(MeasureSpec.makeMeasureSpec(960, MeasureSpec.AT_MOST) + " Width to be viewed -2147482688");
            //System.out.println(MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST) + " Height to be viewed -2147483048");
            super.onMeasure(-2147482688,-2147483048);
        }
    }













    public class SecondLevelAdapter extends BaseExpandableListAdapter {

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            CustExpListview thirdLvl = new CustExpListview(Vincent_ThreeelevellistActivity.this);
            thirdLvl.setAdapter(new ThirdLevelAdapter());
            thirdLvl.setGroupIndicator(null);
            return thirdLvl;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 5;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            TextView tv = new TextView(Vincent_ThreeelevellistActivity.this);
            tv.setText("-->Second Level");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);
            tv.setPadding(12, 7, 7, 7);
            tv.setBackgroundColor(Color.RED);

            return tv;
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
        }

    }





























    public class ThirdLevelAdapter extends BaseExpandableListAdapter {

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            TextView tv = new TextView(Vincent_ThreeelevellistActivity.this);
            tv.setText("child");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);
            tv.setPadding(15, 5, 5, 5);
            tv.setBackgroundColor(Color.YELLOW);
            tv.setLayoutParams(new ListView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            return tv;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 5;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
            TextView tv = new TextView(Vincent_ThreeelevellistActivity.this);
            tv.setText("-->Third Level");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);
            tv.setPadding(12, 7, 7, 7);
            tv.setBackgroundColor(Color.GRAY);

            return tv;
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
        }

    }
}
