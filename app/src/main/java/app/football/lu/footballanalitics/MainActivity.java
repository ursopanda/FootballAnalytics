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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        final Button jsonParser = (Button) findViewById(R.id.jsonPoga);
        jsonParser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

//        TextView output = (TextView) findViewById(R.id.textView1);
//
//            JSONObject jsonObject;
//            jsonObject = parseJSONData();
//            JSONArray jsonArray = null;
//
//        try {
//            jsonArray = jsonObject.getJSONArray("Komanda");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String data = "";
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                JSONObject jsonObject1 = null;
//                try {
//                    jsonObject1 = jsonArray.getJSONObject(i);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                String uzvards = jsonObject1.optString("Uzvards").toString();
//                String vards = jsonObject1.optString("Vards").toString();
//
//                data += "Node " + i + " \n Uzvards= " + uzvards + " \n Vards " + vards + "\n";
//            }
//            output.setText(data);


    }

    // Method for reading JSON file from Assets folder
    public JSONObject parseJSONData() {
        String jsonString = null;
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
            return null;
        }
        catch (JSONException x) {
            x.printStackTrace();
            return null;

        }
        return jsonObject;
    }
}
