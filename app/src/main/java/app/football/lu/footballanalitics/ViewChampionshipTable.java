package app.football.lu.footballanalitics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import androidsqlite.DatabaseHandler;
import androidsqlite.Team;

public class ViewChampionshipTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_championship_table);

        DatabaseHandler db = new DatabaseHandler(this);

        List<Team> teamsList = db.getAllTeams();
    }
}
