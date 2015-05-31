package kr.ac.kyonggi.dream.dream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by sookyung on 2015-05-29.
 */
public class DbOpenHelper {
    public static final String DATABASE_NAME = "restlist.db";
    public static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    public DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {
        // 생성자
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        // 최초 DB를 만들때 한번만 호출됨
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DBUpdate.CreateDB._CREATE);
        }
        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DBUpdate.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context) {
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void insertColumn(String[] id, String[] name, String[] phone) {
        mDB.beginTransaction();
        try {
            for (int i = 0; i < id.length; i++) {
                String sql = "insert into "+DBUpdate.CreateDB._TABLENAME +"("+
                        DBUpdate.CreateDB.ID+","+DBUpdate.CreateDB.NAME + ","+
                        DBUpdate.CreateDB.PHONE+")"+" values ('"+
                        id[i]+"','" + name[i] + "','" + phone[i] + "')";
                mDB.execSQL(sql);
            }
            // Database transaction success
            mDB.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("DB", "insert error");
        } finally {
            // Database transaction close
            mDB.endTransaction();
        }
        Log.d("DB", "insert success");
    }

    public void close() {
        mDB.close();
    }
}