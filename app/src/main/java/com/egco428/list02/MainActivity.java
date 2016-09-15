package com.egco428.list02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView myList = (ListView)findViewById(R.id.myList);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i=0; i<values.length; i++){
            list.add(values[i]);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,list);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                final String item = (String)adapterView.getItemAtPosition(i);
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        list.remove(item);
                        adapter.notifyDataSetChanged();
                        view.setAlpha(1);
                    }
                });
            }
        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String>{
        HashMap<String ,Integer> mIsMap = new HashMap<String, Integer>();
        public StableArrayAdapter(Context context, int textViewResourceID, List<String> objects){
            super(context, textViewResourceID, objects);
            for (int i=0; i<objects.size(); i++){
                mIsMap.put(objects.get(i),i);
            }
        }
        public boolean hasStableIds(){
            return true;
        }
    }
}
