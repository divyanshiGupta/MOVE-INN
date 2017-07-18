package moveinn.par.thunderbolt.moveinn.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import moveinn.par.thunderbolt.moveinn.User;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_LNAME = "user_lname";
    private static final String COLUMN_USER_FNAME = "user_fname";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_USER_CONTACT ="user_contact";

    private static final String[] allColumns = {
            COLUMN_USER_ID,
            COLUMN_USER_FNAME,
            COLUMN_USER_LNAME,
            COLUMN_USER_EMAIL,
            COLUMN_USER_PASSWORD,
            COLUMN_USER_CONTACT,
            COLUMN_USERNAME
    };

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_FNAME + " TEXT, " + COLUMN_USER_LNAME + " TEXT, "
            + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_USER_CONTACT + " TEXT, " + COLUMN_USERNAME + " TEXT " + ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DataBaseHelper(Context context) {
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

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FNAME, user.getFName());
        values.put(COLUMN_USER_LNAME, user.getLName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_CONTACT, user.getContact());
        values.put(COLUMN_USERNAME,user.getUsername());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
       // Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                     //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public String getSinlgeEntry(String userName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

       /* String selectQuery = "SELECT * FROM "+TABLE_USER+" WHERE "+ COLUMN_USER_EMAIL +"=?;";
        Cursor cursor = db.rawQuery(selectQuery, new String[] { userName});

       // Cursor cursor=db.query(TABLE_USER, null, " user_email=?", new String[]{userName}, null, null, null);*/

        Cursor cursor = db.query(TABLE_USER,allColumns,COLUMN_USERNAME + "=?",new String[]{String.valueOf(userName)},null,null, null, null);

        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }

        if (cursor.moveToFirst()) {
           String password= cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD));
            cursor.close();
            return password;
        }
       return "";

    }

}
