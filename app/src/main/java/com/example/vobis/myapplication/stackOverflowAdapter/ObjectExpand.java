package com.example.vobis.myapplication.stackOverflowAdapter;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.vobis.myapplication.R;

import java.util.ArrayList;


public class ObjectExpand extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_expand);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        NewObject obj = new NewObject();
        obj.children = new ArrayList<NewObject>();
        for (int i = 0; i < Constant.state.length; i++) {
            NewObject root = new NewObject();
            root.title = Constant.state[i];
            root.children = new ArrayList<NewObject>();
            for (int j = 0; j < Constant.parent[i].length; j++) {
                NewObject parent = new NewObject();
                parent.title = Constant.parent[i][j];
                parent.children = new ArrayList<NewObject>();
                for (int k = 0; k < Constant.child[i][j].length; k++) {
                    NewObject child = new NewObject();
                    child.title = Constant.child[i][j][k];
                    parent.children.add(child);
                }
                root.children.add(parent);
            }
            obj.children.add(root);
        }
        if (!obj.children.isEmpty()) {
            final ExpandableListView elv = (ExpandableListView) findViewById(R.id.expList);

            elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {

                    return true; /* or false depending on what you need */
                }
            });


            ExpandableListView.OnGroupClickListener grpLst = new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView eListView, View view, int groupPosition,
                                            long id) {

                    return true/* or false depending on what you need */;
                }
            };


            ExpandableListView.OnChildClickListener childLst = new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView eListView, View view, int groupPosition,
                                            int childPosition, long id) {

                    return true/* or false depending on what you need */;
                }
            };

            ExpandableListView.OnGroupExpandListener grpExpLst = new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {

                }
            };

            final RootAdapter adapter = new RootAdapter(this, obj, grpLst, childLst, grpExpLst);
            elv.setAdapter(adapter);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_object_expand, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Settings selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(getApplicationContext(),"Search selected",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
