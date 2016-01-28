package app.football.lu.footballanalitics;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import androidsqlite.DatabaseHandler;
import androidsqlite.Team;

public class ViewChampionshipTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_championship_table);


        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
        DatabaseHandler handler = new DatabaseHandler(this);
        // Get access to the underlying writeable database
        SQLiteDatabase db = handler.getWritableDatabase();
        // Query for items from the database and get a cursor back
        Cursor todoCursor = db.rawQuery("SELECT  * FROM teams", null);

        // Find ListView to populate
//        ListView lvItems = (ListView) findViewById(R.id);
//// Setup cursor adapter using cursor from last step
//        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, todoCursor, 0);
// Attach cursor adapter to the ListView
//        lvItems.setAdapter(todoAdapter);


    }
}
