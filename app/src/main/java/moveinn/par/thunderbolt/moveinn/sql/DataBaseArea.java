package moveinn.par.thunderbolt.moveinn.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import moveinn.par.thunderbolt.moveinn.Details;



public class DataBaseArea extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserArea.db";

    // User table name
    private static final String TABLE_USER = "userArea";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_CHOICE = "choice";
    private static final String COLUMN_USER_TYPE = "type";
    private static final String COLUMN_USER_AMOUNT = "amount";
    private static final String COLUMN_USER_ROOM = "room";


    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_CHOICE + " TEXT,"
            + COLUMN_USER_TYPE + " TEXT," + COLUMN_USER_AMOUNT + " TEXT," + COLUMN_USER_ROOM + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    /**
     * Constructor
     *
     * @param context
     */
    public DataBaseArea(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }

    public void addArea(Details detail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_CHOICE, detail.getChoice());
        values.put(COLUMN_USER_TYPE, detail.getType());
        values.put(COLUMN_USER_AMOUNT, detail.getAmount());
        values.put(COLUMN_USER_ROOM,detail.getRooms());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public List<Details> getAllArea() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_CHOICE,
                COLUMN_USER_TYPE,
                COLUMN_USER_AMOUNT,
                COLUMN_USER_ROOM
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_CHOICE+ " ASC";
        List<Details> userList = new ArrayList<Details>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Details detail = new Details();
                detail.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                detail.setChoice(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CHOICE)));
                detail.setType(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TYPE)));
                detail.setAmount(cursor.getString(cursor.getColumnIndex(COLUMN_USER_AMOUNT)));
                detail.setRooms(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROOM)));
                // Adding user record to list
                userList.add(detail);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
}
