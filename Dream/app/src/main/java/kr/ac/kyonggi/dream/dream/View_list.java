package kr.ac.kyonggi.dream.dream;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.simple.JSONObject;

import java.sql.SQLException;

public class View_list extends ActionBarActivity {
    static int RECEIVE_EVENT = 1000;
    // DB
    private DbOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    final static String dbName = DbOpenHelper.DATABASE_NAME;
    final static int dbVersion = DbOpenHelper.DATABASE_VERSION;

    // DB select
    Cursor cursor;
    DBAdapter myAdapter;
    final static String querySelectAll = String.format("SELECT * FROM %s", DBUpdate.CreateDB._TABLENAME);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list);

        Intent intent = getIntent();
        String str = intent.getStringExtra("PARAM1");
        this.setTitle(intent.getStringExtra("TITLE"));
        TextView txtOutput = (TextView)findViewById(R.id.vl_textView1);
        txtOutput.setText(str);

        // JSONObject parsing?
        JSONObject[] restas = null;
        restas = DreamData.getRestaList(0,20);
        String [] id, name, phone;
        id = new String[restas.length];
        name = new String[restas.length];
        phone = new String[restas.length];
        for(int i = 0;i < restas.length; i++) {
            id[i] = String.valueOf(restas[i].get("id"));
            name[i] = String.valueOf(restas[i].get("name"));
            phone[i] = String.valueOf(restas[i].get("phone"));
        }

//        DBInsert(id, name, phone);  // DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        try {
            mDbOpenHelper.open();
            mDbOpenHelper.insertColumn(id, name, phone);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("DB", "open fail");
        }

        // DB Select
        ListView list = (ListView) findViewById(R.id.vl_listView);
        cursor = DbOpenHelper.mDB.rawQuery(querySelectAll, null);
        myAdapter = new DBAdapter(this, cursor);

        list.setAdapter(myAdapter);
    }

    private void DBInsert(String[] id, String[] name, String[] phone){
        // DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        try {
            mDbOpenHelper.open();
            mDbOpenHelper.insertColumn(id, name, phone);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("DB", "open fail");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }
}
