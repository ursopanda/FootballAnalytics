package androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esyundyukov on 16/01/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // DB version
    private static final int DATABASE_VERSION = 1;
    // DB name
    private static final String DATABASE_NAME = "footballStats";
    // Teams table name
    private static final String TABLE_TEAMS = "teams";
    // Team table column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_WIN_AMOUNTS = "win_amounts";
    private static final String KEY_LOSE_AMOUNTS = "lose_amounts";
    private static final String KEY_GOALS = "goals";
    private static final String KEY_POINTS = "points";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creatinng tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEAMS_TABLE = "CREATE TABLE " + TABLE_TEAMS +
                KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +
                KEY_WIN_AMOUNTS + " INTEGER" + KEY_LOSE_AMOUNTS + " INTEGER" +
                KEY_GOALS + " INTEGER" + KEY_POINTS + " INTEGER" + ")";
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
    }

    // Upgrading DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table version, if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);

        // create tables again
        onCreate(db);
    }

    // CRUD operations's implementation

    // Adding new team
    public void addTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, team.get_name());
        values.put(KEY_WIN_AMOUNTS, team.get_win_amount());
        values.put(KEY_LOSE_AMOUNTS, team.get_lose_amount());
        values.put(KEY_GOALS, team.get_goals());
        values.put(KEY_POINTS, team.get_points());

        // Inserting row
        db.insert(TABLE_TEAMS, null, values);
        db.close();
    }

    // Getting single team
    public Team getTeam(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TEAMS, new String[]{KEY_ID, KEY_NAME, KEY_WIN_AMOUNTS,
                        KEY_LOSE_AMOUNTS, KEY_GOALS, KEY_POINTS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Team team = new Team(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));

        return team;
    }

    // Get all teams
    public List<Team> getAllTeams() {
        List<Team> teamList = new ArrayList<Team>();

        // Select all query
        String selectQuery = "SELECT * FROM " + TABLE_TEAMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Team team = new Team();
                team.set_id(Integer.parseInt(cursor.getString(0)));
                team.set_name(cursor.getString(1));
                team.set_win_amount(Integer.parseInt(cursor.getString(2)));
                team.set_lose_amount(Integer.parseInt(cursor.getString(3)));
                team.set_goals(Integer.parseInt(cursor.getString(4)));
                team.set_points(Integer.parseInt(cursor.getString(5)));

                // Adding team to List
                teamList.add(team);
            } while (cursor.moveToNext());
        }

        // Returning List object
        return teamList;
    }

    // Get team amount
    public int getTeamAmount() {
        String countQuery = "SELECT * FROM " + TABLE_TEAMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // Return amount of teams found in Table
        return cursor.getCount();
    }

    // Update single team
    public int updateTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, team.get_name());
        values.put(KEY_WIN_AMOUNTS, team.get_win_amount());
        values.put(KEY_LOSE_AMOUNTS, team.get_lose_amount());
        values.put(KEY_GOALS, team.get_goals());
        values.put(KEY_POINTS, team.get_points());

        // Updating row
        return db.update(TABLE_TEAMS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(team.get_id())} );
    }

    // Delete single team
    public void deleteTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEAMS, KEY_ID + " = ?",
                new String[] {String.valueOf(team.get_id())});
    }
}
