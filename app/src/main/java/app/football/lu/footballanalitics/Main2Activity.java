package app.football.lu.footballanalitics;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Main2Activity extends ListActivity {

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://api.androidhive.info/contacts/";

    // JSON Node names
    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_MOBILE = "mobile";
    private static final String TAG_PHONE_HOME = "home";
    private static final String TAG_PHONE_OFFICE = "office";

    // contacts JSONArray
    JSONArray contacts = null;
    JSONObject spele = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        contactList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        String jsonString;
        JSONObject jsonObject = null;

        try {
            InputStream inputStream = getAssets().open("elements.json");
            int sizeOfJSONFile = inputStream.available();

            //array that will store all the data
            byte[] bytes = new byte[sizeOfJSONFile];

            //reading data into the array from the file
            inputStream.read(bytes);

            //close the input stream
            inputStream.close();

            jsonString = new String(bytes, "UTF-8");
            jsonObject = new JSONObject(jsonString);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException x) {
            x.printStackTrace();
        }

        try {
            contacts = jsonObject.getJSONArray("contacts");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (contacts != null) {
            for (int i=0; i < contacts.length(); i++) {
                try {
                    JSONObject c = contacts.getJSONObject(i);

                    String id = c.getString("id");
                    String name = c.getString("name");
                    String email = c.getString("email");
                    String address = c.getString("address");
                    String gender = c.getString("gender");

                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");

                    HashMap<String,String> contact = new HashMap<String, String>();

                    contact.put("id", id);
                    contact.put("name", name);
                    contact.put("email", email);
                    contact.put("mobile", mobile);

                    contactList.add(contact);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            InputStream inputStream = getAssets().open("futbols0.json");
            int sizeOfJSONFile = inputStream.available();

            //array that will store all the data
            byte[] bytes = new byte[sizeOfJSONFile];

            //reading data into the array from the file
            inputStream.read(bytes);

            //close the input stream
            inputStream.close();

            jsonString = new String(bytes, "UTF-8");
            jsonObject = new JSONObject(jsonString);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException x) {
            x.printStackTrace();
        }

        try {
            spele = jsonObject.getJSONObject("Spele");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (spele != null) {
//            for (int i = 0; i < spele.length(); i++) {
                try {
                    JSONArray judges = spele.getJSONArray("T");
                    JSONArray teams = spele.getJSONArray("Komanda");

                    for (int i = 0; i < teams.length(); i++) {
                        JSONObject team = teams.getJSONObject(i);
                        String name = team.getString("Nosaukums");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
//        }

        ListAdapter listAdapter = new SimpleAdapter(
                Main2Activity.this, contactList,
                R.layout.list_item, new String[] {"name", "email", "mobile"}, new int[] {R.id.name, R.id.email, R.id.mobile});

        setListAdapter(listAdapter);
    }
}