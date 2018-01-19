package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import model.History;

/**
 * Created by akaash on 18/1/18.
 */

public class DBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Users.db";

    /* CONSTRAINTS */
    private static final String PRIMARY_KEY = " PRIMARY KEY ";

    /* COLUMN DATA TYPES */
    private static final String INTEGER = " INTEGER ";
    private static final String TEXT = " TEXT ";
    private static final String FLOAT = " FLOAT ";

    private static final String SELECT = " SELECT ";
    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String UPDATE = " UPDATE ";
    private static final String SET = " SET ";

    private DBHelper instance;
    private SQLiteDatabase mDatabase;


    private static final String TABLE_USER = " user_table ";
    private static final String KEYWORD = " keyword ";
    private static final String COUNT = " count ";

    private static int count = 0;

    private static final String QUERY_CREATE_TABLE_USER = "CREATE TABLE" + TABLE_USER
            + " ( "
            + KEYWORD + TEXT + PRIMARY_KEY + ","
            + COUNT + INTEGER
            + " ) ";

    public DBHelper(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY_CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertOrUpdateKeyword(String keyword) {
        mDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (isKeywordAlreadyExist(keyword)) {
            //TODO update count
          /*  String UPDATE_COUNT_QUERY=UPDATE+TABLE_USER+SET+COUNT + "="+getCount()+WHERE+KEYWORD+"="+keyword;
            mDatabase.execSQL(UPDATE_COUNT_QUERY);*/

            cv.put(COUNT, getCount(keyword));
            mDatabase.update(TABLE_USER, cv, KEYWORD + " = ?", new String[]{String.valueOf(keyword)});
        } else {
            //Insert
            cv.put(KEYWORD, keyword);
            cv.put(COUNT, getCount(keyword));

            mDatabase.insert(TABLE_USER, null, cv);
        }
        mDatabase.close();
    }

    private int getCount(String keyword) {
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{KEYWORD, COUNT}, KEYWORD + "=?", new String[]{String.valueOf(keyword)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(1);
        }
        count++;
        return count;


        /*int count = 0;
        String QUERY_GET_COUNT = "Select * from user_table where " + KEYWORD + " = '" + keyword + "'";
        Cursor cursor = mDatabase.rawQuery(QUERY_GET_COUNT, new String[]{String.valueOf(keyword)});
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(cursor.getColumnIndex(COUNT));
        }
        return count;*/
    }

    private boolean isKeywordAlreadyExist(String keyword) {
        //String CHECK_KEYWORD_QUERY = SELECT + KEYWORD + FROM + TABLE_USER + WHERE + KEYWORD + "=?";
        boolean flag = false;
        Cursor cursor = mDatabase.query(TABLE_USER, null, KEYWORD + " = ?", new String[]{String.valueOf(keyword)}, null, null, null);
        if (null != cursor && cursor.moveToFirst()) {
            flag = true;
            cursor.close();
        }
        return flag;
    }

    private int getSearchCount() {
        return count;
    }


    public ArrayList<History> getHistory() {
        ArrayList<History> historyArrayList = new ArrayList<>();
        mDatabase = getReadableDatabase();
        Cursor cursor = mDatabase.query(TABLE_USER, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                historyArrayList.add(new History(cursor.getString(0), cursor.getInt(1)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        mDatabase.close();
        return historyArrayList;
    }
}
