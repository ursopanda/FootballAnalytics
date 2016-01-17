package app.football.lu.footballanalitics;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidsqlite.DatabaseHandler;
import androidsqlite.Referee;
import androidsqlite.Team;
import jsonparser.JSONParser;

public class Main2Activity extends ListActivity {

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


//        ListAdapter listAdapter = new SimpleAdapter(
//                Main2Activity.this, contactList,
//                R.layout.list_item, new String[] {"name", "email", "mobile"}, new int[] {R.id.name, R.id.email, R.id.mobile});
//
//        setListAdapter(listAdapter);
    }
}