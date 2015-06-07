package kr.ac.kyonggi.dream.dream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list);

        Intent intent = getIntent();
        String str = intent.getStringExtra("PARAM1");
        this.setTitle(intent.getStringExtra("TITLE"));
        int category = intent.getIntExtra("Category", 1);
        TextView txtOutput = (TextView)findViewById(R.id.vl_textView1);
        txtOutput.setText(str);

        // DB select query
        final String querySelectAll = String.format("SELECT * FROM %s where %s=%d", DBUpdate.CreateDB._TABLENAME, DBUpdate.CreateDB.CATEGORY, category);
        mDbOpenHelper = new DbOpenHelper(this);

        if( isNetworkConnected(this)) {
            // JSONObject parsing?
            JSONObject[] restas = null;
            restas = DreamData.getRestaList(0, 20, category);
            String[] id, name, phone;
            id = new String[restas.length];
            name = new String[restas.length];
            phone = new String[restas.length];
            for (int i = 0; i < restas.length; i++) {
                id[i] = String.valueOf(restas[i].get("id"));
                name[i] = String.valueOf(restas[i].get("name"));
                phone[i] = String.valueOf(restas[i].get("phone"));
            }

            //        DBInsert(id, name, phone);  // DB Create and Open
            try {
                mDbOpenHelper.open();
                mDbOpenHelper.insertColumn(id, name, phone, category);
            } catch (SQLException e) {
                e.printStackTrace();
                Log.d("DB", "open fail");
            }
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("네트워크 연결 오류").setMessage("네트워크 연결 상태 확인 후 다시 시도해 주십시요.")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick( DialogInterface dialog, int which ){
                            finish();
                        }
                    }).show();
        }

        // DB Select
        ListView list = (ListView) findViewById(R.id.vl_listView);
        cursor = DbOpenHelper.mDB.rawQuery(querySelectAll, null);
        myAdapter = new DBAdapter(this, cursor);

        list.setAdapter(myAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "test"+ myAdapter.getItemId(position), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(View_list.this, Rest_info.class);
                intent.putExtra("PARAM1", String.valueOf(myAdapter.getItemId(position)));
                startActivityForResult(intent, RECEIVE_EVENT);
            }
        });
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

    // 인터넷 연결 상태 확인
    public boolean isNetworkConnected(Context context){
        boolean isConnected = false;

        ConnectivityManager manager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mobile.isConnected() || wifi.isConnected()){
            isConnected = true;
        }else{
            isConnected = false;
        }
        return isConnected;
    }
}