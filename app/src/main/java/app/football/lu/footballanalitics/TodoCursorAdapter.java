package app.football.lu.footballanalitics;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidsqlite.DatabaseHandler;

/**
 * Created by Emil on 28/01/16.
 */
public class TodoCursorAdapter extends CursorAdapter {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TodoCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView teamName = (TextView) view.findViewById(R.id.teamName);
        TextView winAmount = (TextView) view.findViewById(R.id.winAmount);
        TextView loseAmount = (TextView) view.findViewById(R.id.loseAmount);
        TextView goals = (TextView) view.findViewById(R.id.goalAmount);
        TextView points = (TextView) view.findViewById(R.id.pointsAmount);
        // Extract properties from cursor
        String teamNameString = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        int winAmountCount = cursor.getInt(cursor.getColumnIndexOrThrow("win_amounts"));
        int loseAmountCount = cursor.getInt(cursor.getColumnIndexOrThrow("lose_amounts"));
        int goalsAmount = cursor.getInt(cursor.getColumnIndexOrThrow("goals"));
        int pointsAmount = cursor.getInt(cursor.getColumnIndexOrThrow("points"));
        // Populate fields with extracted properties
        teamName.setText(teamNameString);
        winAmount.setText(String.valueOf(winAmountCount));
        loseAmount.setText(String.valueOf(loseAmountCount));
        goals.setText(String.valueOf(goalsAmount));
        points.setText(String.valueOf(pointsAmount));

        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
        DatabaseHandler handler = new DatabaseHandler(context);
        // Get access to the underlying writeable database
        SQLiteDatabase db = handler.getWritableDatabase();
        // Query for items from the database and get a cursor back
        Cursor todoCursor = db.rawQuery("SELECT  * FROM teams", null);

//        // Find ListView to populate
//        ListView lvItems = (ListView) find
//// Setup cursor adapter using cursor from last step
//        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(co, todoCursor);
//// Attach cursor adapter to the ListView
//        lvItems.setAdapter(todoAdapter);
    }
}
