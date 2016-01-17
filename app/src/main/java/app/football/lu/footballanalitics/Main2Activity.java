package app.football.lu.footballanalitics;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidsqlite.DatabaseHandler;


public class Main2Activity extends ListActivity {

    // Hashmap for ListView
    List contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DatabaseHandler db = new DatabaseHandler(this);
        contactList = db.getAllTeams();

//  TO-DO! Create vizualization from DB
//        ListAdapter listAdapter = new SimpleAdapter(
//                Main2Activity.this, contactList,
//                R.layout.list_item, new String[] {"name", "email", "mobile"}, new int[] {R.id.name, R.id.email, R.id.mobile});
//
//        setListAdapter(listAdapter);
    }
}