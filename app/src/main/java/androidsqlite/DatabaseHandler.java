package androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Ref;
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

    // Table names
    private static final String TABLE_TEAMS = "teams";
    private static final String TABLE_REFEREES = "referees";

    // Common columns
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    // Team table columns
    private static final String KEY_WIN_AMOUNTS = "win_amounts";
    private static final String KEY_LOSE_AMOUNTS = "lose_amounts";
    private static final String KEY_GOALS = "goals";
    private static final String KEY_POINTS = "points";
    // Referee table columns
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_CARDS = "cards";
    private static final String KEY_GAMES = "games";

    // Table Create Statements

    // Table Refereees Create Statement
    private static final String CREATE_TABLE_REFEREES = "CREATE TABLE "
            + TABLE_REFEREES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT," + KEY_SURNAME + " TEXT," + KEY_CARDS + " INTEGER," + KEY_GAMES + " INTEGER" + ")";

    // Table Teams Create Statement
    private static final String CREATE_TABLE_TEAMS = "CREATE TABLE "
            + TABLE_TEAMS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT," + KEY_WIN_AMOUNTS + " INTEGER,"
            + KEY_LOSE_AMOUNTS + " INTEGER," + KEY_GOALS + " INTEGER," + KEY_POINTS + " INTEGER" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating required tables
        db.execSQL(CREATE_TABLE_TEAMS);
        db.execSQL(CREATE_TABLE_REFEREES);
    }

    // Delete DB
    public  void deleteFootballStats() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEAMS, null, null);
        db.delete(TABLE_REFEREES, null, null);
        db.close();
    }

    // Upgrading DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table version, if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REFEREES);
        // create tables again
        onCreate(db);
    }

    // CRUD operations's implementation for TABLE_TEAM
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
                new String[]{String.valueOf(team.get_id())});
    }

    // Delete single team
    public void deleteTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEAMS, KEY_ID + " = ?",
                new String[]{String.valueOf(team.get_id())});
    }

    // CRUD Operations for TABLE_REFEREES
    // Adding new referee
    public void addReferee(Referee referee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, referee.get_name());
        values.put(KEY_SURNAME, referee.get_surname());
        values.put(KEY_CARDS, referee.get_cards());
        values.put(KEY_GAMES, referee.get_games());

        // Inserting row
        db.insert(TABLE_REFEREES, null, values);
        db.close();
    }

    // Getting single referee
    public Referee getReferee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REFEREES, new String[] {KEY_ID, KEY_NAME, KEY_SURNAME, KEY_CARDS, KEY_GAMES}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Referee referee = new Referee(cursor.getString(0), cursor.getString(1), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));

        return referee;
    }

    // Get all referees
    public List<Referee> getAllReferees() {
        List<Referee> refereeList = new ArrayList<Referee>();

        // Select all query
        String selectQuery = "SELECT * FROM " + TABLE_REFEREES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Referee referee = new Referee();
                referee.set_id(Integer.parseInt(cursor.getString(0)));
                referee.set_name(cursor.getString(1));
                referee.set_surname(cursor.getString(2));
                referee.set_cards(Integer.parseInt(cursor.getString(3)));
                referee.set_games(Integer.parseInt(cursor.getString(4)));

                // Adding referee to List
                refereeList.add(referee);
            } while (cursor.moveToNext());
        }

        // Returning List object
        return refereeList;
    }

    // Update single referee
    public int updateReferee(Referee referee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, referee.get_name());
        values.put(KEY_SURNAME, referee.get_surname());
        values.put(KEY_CARDS, referee.get_cards());
        values.put(KEY_GAMES, referee.get_games());

        // Updating row
        return db.update(TABLE_REFEREES, values, KEY_ID + " = ?",
                new String[] {String.valueOf(referee.get_id())});
    }

    // Delete a referee
    public void deleteReferee(Referee referee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REFEREES, KEY_ID + " = ?",
                new String[] {String.valueOf(referee.get_id())});
    }

    public boolean checkIsDataAlreadyInDBorNot(String TableName, String dbfield, String fieldValue) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
