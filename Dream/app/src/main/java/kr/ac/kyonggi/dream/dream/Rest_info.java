package kr.ac.kyonggi.dream.dream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONObject;

import java.sql.SQLException;

/**
 * Created by sookyung on 2015-06-01.
 */
public class Rest_info extends ActionBarActivity {
    // DB select
    private DbOpenHelper mDbOpenHelper;
    Cursor cursor;
    MenuDBAdapter myAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_info);

        Intent intent = getIntent();
        String id = intent.getStringExtra("PARAM1");
        String title = "";
        int index = Integer.parseInt(id);

        // DB Select
        mDbOpenHelper = new DbOpenHelper(this);
        final String querySelectName = String.format("SELECT %s FROM %s where %s=%d",
                DBUpdate.CreateDB.NAME, DBUpdate.CreateDB._TABLENAME, DBUpdate.CreateDB._ID, index);
        cursor = DbOpenHelper.mDB.rawQuery(querySelectName, null);

        cursor.moveToFirst();
        title = cursor.getString(cursor.getColumnIndex("name"));    // 가게 이름
        this.setTitle(title);

            // JSONObject parsing?
            JSONObject[] restas = null;
            restas = DreamData.getMenuList(index);
            int[] _id, price;
            String[] name;
            _id = new int[restas.length];
            name = new String[restas.length];
            price = new int[restas.length];

            for (int i = 0; i < restas.length; i++) {
                _id[i] = Integer.parseInt(id);
                name[i] = String.valueOf(restas[i].get("name"));
                price[i] = Integer.parseInt(String.valueOf(restas[i].get("price")));
                Log.d("menu", _id[i]+","+name[i]+","+price[i]+","+restas[i]);
            }
                try {
                mDbOpenHelper.open();
                mDbOpenHelper.insertColumn(_id, name, price);
            } catch (SQLException e) {
                e.printStackTrace();
                Log.d("DB", "open fail");
            }

        // DB Select
//        ListView list = (ListView) findViewById(R.id.ri_listView);
//        cursor = executeRawQueryParam(String.valueOf(index));
//        startManagingCursor(cursor);
//
//        String[] columns = new String[] {DBUpdate.MenuDB.NAME, DBUpdate.MenuDB.PRICE};
//        int[] to = new int[] {R.id.tv_name, R.id.tv_phone};
////        int[] to = new int[] {android.R.id.text1, android.R.id.text2};
//        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.rest_info, cursor, columns, to);
////        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, columns, to);
//        list.setAdapter(mAdapter);
        ListView list = (ListView) findViewById(R.id.ri_listView);
        String SQL = "select * from " + DBUpdate.MenuDB._TABLENAME
                + " where _id = ?";
        String[] args = {String.valueOf(index)};
        cursor = DbOpenHelper.mDB.rawQuery(SQL, args);
        myAdapter = new MenuDBAdapter(this, cursor);

        list.setAdapter(myAdapter);
    }

//    private Cursor executeRawQueryParam(String index){
//        System.out.println("\nexecuteRawQureryParam called.\n");
//
//        String SQL = "select * from " + DBUpdate.MenuDB._TABLENAME
//                + " where _id = ?";
//        String[] args = {index};
//        Cursor c1 = DbOpenHelper.mDB.rawQuery(SQL, args);
//
//        return c1;
//    }

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
//        mDbOpenHelper.close();
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
