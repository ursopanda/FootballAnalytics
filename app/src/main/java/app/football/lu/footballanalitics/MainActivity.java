package app.football.lu.footballanalitics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidsqlite.DatabaseHandler;
import androidsqlite.Referee;
import androidsqlite.Team;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing method to parse JSON file
        readJSON("futbols0.json");

        // Turnira tabula
        final Button tabulaPoga = (Button) findViewById(R.id.tabulaPoga);
        tabulaPoga.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewChampionshipTable.class);
                startActivity(intent);
            }
        }));

        // Desmit labākie spēlētāji
        final Button speletajuPoga = (Button) findViewById(R.id.playerPoga);
        speletajuPoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ViewPlayerStatistics.class);
                    startActivity(intent);
                }
        });

        // 5 labākie vārtsargi
        final Button vartsarguPoga = (Button) findViewById(R.id.goalkeeperPoga);
        vartsarguPoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewGoalkeeperStatistics.class);
                startActivity(intent);
            }
        });

        // Rupjākie spēlētāji
        final Button rupjakiePoga = (Button) findViewById(R.id.rupjakieButton);
        rupjakiePoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewRudePlayers.class);
                startActivity(intent);
            }
         });

        // Stingrāko tiesnešu skaits
        final Button tiesnesisPoga = (Button) findViewById(R.id.tiesnesisPoga);
        tiesnesisPoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewReferee.class);
                startActivity(intent);
            }
        });

        // Visa vienas komandas statistika
        final Button komandaPoga = (Button) findViewById(R.id.teamStatButton);
        komandaPoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewTeamStats.class);
                startActivity(intent);
            }
        });

        // Testing JSON Parsing
        final Button jsonParsing = (Button) findViewById(R.id.jsonPoga);
        jsonParsing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    public void readJSON(String jsonFileName) {

        JSONObject spele = null;

        DatabaseHandler db = new DatabaseHandler(this);

        // Cleaning from old entries
        db.deleteFootballStats();

//        contactList = new ArrayList<HashMap<String, String>>();
//        Map teamMap = new HashMap<>();
        Map<String, Integer> teamMap = new HashMap<>();
        boolean isExtraPeriod = false;

//        ListView lv = getListView();

        String jsonString;
        JSONObject jsonObject = null;

//        try {
//            InputStream inputStream = getAssets().open("elements.json");
//            int sizeOfJSONFile = inputStream.available();
//
//            //array that will store all the data
//            byte[] bytes = new byte[sizeOfJSONFile];
//
//            //reading data into the array from the file
//            inputStream.read(bytes);
//
//            //close the input stream
//            inputStream.close();
//
//            jsonString = new String(bytes, "UTF-8");
//            jsonObject = new JSONObject(jsonString);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (JSONException x) {
//            x.printStackTrace();
//        }
//
//        try {
//            contacts = jsonObject.getJSONArray("contacts");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        if (contacts != null) {
//            for (int i=0; i < contacts.length(); i++) {
//                try {
//                    JSONObject c = contacts.getJSONObject(i);
//
//                    String id = c.getString("id");
//                    String name = c.getString("name");
//                    String email = c.getString("email");
//                    String address = c.getString("address");
//                    String gender = c.getString("gender");
//
//                    JSONObject phone = c.getJSONObject("phone");
//                    String mobile = phone.getString("mobile");
//                    String home = phone.getString("home");
//                    String office = phone.getString("office");
//
//                    HashMap<String,String> contact = new HashMap<String, String>();
//
//                    contact.put("id", id);
//                    contact.put("name", name);
//                    contact.put("email", email);
//                    contact.put("mobile", mobile);
//
//                    contactList.add(contact);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        try {
            InputStream inputStream = getAssets().open(jsonFileName);
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
            try {
                int cardNumber = 0;
                JSONArray teams = spele.getJSONArray("Komanda");

                for (int i = 0; i < teams.length(); i++) {
                    // Getting information on teams
                    JSONObject team = teams.getJSONObject(i);
                    JSONObject goals = team.getJSONObject("Varti");
                    JSONArray goal = goals.getJSONArray("VG");
                    for (int g = 0; g < goal.length(); g++) {
                        JSONObject currentGoal = goal.getJSONObject(g);
                        String goalTime = currentGoal.get("Laiks").toString().replaceAll(":", "");
                        if (Integer.parseInt(goalTime) > 6000) {
                            isExtraPeriod = true;
                            break;
                        }
                    }
                    // Getting HashMap: TeamName -> Goals
                    teamMap.put(team.getString("Nosaukums"), goal.length());

                    // Getting information on cards
                    JSONObject cardObject = team.getJSONObject("Sodi");
                    try {
                        JSONArray cards = cardObject.getJSONArray("Sods");
                        cardNumber += cards.length();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        cardNumber++;
                    }
                }

                // Game analytics - getting info on winner/loser
                Map.Entry<String,Integer> entry=teamMap.entrySet().iterator().next();
                String key1 = entry.getKey();
                String value1 = entry.getValue().toString();
                String key2 = teamMap.entrySet().iterator().next().getKey();
                String value2 = teamMap.entrySet().iterator().next().getValue().toString();

                if (Integer.parseInt(value1) > Integer.parseInt(value2)) {
                    if (!isExtraPeriod) {
                        db.addTeam(new Team(key1, 1, 0, Integer.parseInt(value1), 5));
                        db.addTeam(new Team(key2, 0, 1, Integer.parseInt(value2), 1));
                    }
                    else {
                        db.addTeam(new Team(key1, 1, 0, Integer.parseInt(value1), 3));
                        db.addTeam(new Team(key1, 0, 1, Integer.parseInt(value2), 2));
                    }
                }
                else {
                    if (!isExtraPeriod) {
                        db.addTeam(new Team(key2, 1, 0, Integer.parseInt(value2), 5));
                        db.addTeam(new Team(key1, 0, 1, Integer.parseInt(value1), 1));
                    }
                    else {
                        db.addTeam(new Team(key2, 1, 0, Integer.parseInt(value2), 3));
                        db.addTeam(new Team(key1, 0, 1, Integer.parseInt(value1), 2));
                    }
                }

                // Gathering info about referee
                JSONObject referee = spele.getJSONObject("VT");
                // Putting Referee into the DB
//                    if (!db.checkIsDataAlreadyInDBorNot("referees","name",referee.getString("Vards"))
//                            && (!db.checkIsDataAlreadyInDBorNot("referees","surname",referee.getString("Surname"))))
                db.addReferee(new Referee(referee.getString("Vards"),referee.getString("Uzvards"), cardNumber, 1));
                db.getAllReferees();
                // If the following referee exists in DB
//                    else
//                        db.updateReferee()
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        List allReferees = db.getAllReferees();
        List allTeams = db.getAllTeams();

        db.close();
    }
}
