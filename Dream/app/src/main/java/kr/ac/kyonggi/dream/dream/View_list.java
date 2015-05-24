package kr.ac.kyonggi.dream.dream;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpServerConnection;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class View_list extends ActionBarActivity {
    static int RECEIVE_EVENT = 1000;

    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;

    private static final String USER_AGENT = "Mozilla/5.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list);

        Intent intent = getIntent();
        String str = intent.getStringExtra("PARAM1");
        this.setTitle(intent.getStringExtra("TITLE"));
        TextView txtOutput = (TextView)findViewById(R.id.vl_textView1);

        JSONParser parse = new JSONParser();

//        txtOutput.setText(str+" 검색한다.");

//        JSONArray jsonArr = null;

//        jsonArr.get("id");

  /*      try {
            jsonArr = doGet("http://create32.ddns.net:9080/public_html/dream/RestaListJson.php?index=0&count=10");
            txtOutput.setText((CharSequence) jsonArr);
            Log.e("json", String.valueOf(jsonArr));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Button btn = (Button)findViewById(R.id.vl_btnCancle);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String str = editText1.getText().toString();
                //Intent resultIntent = new Intent();
                //resultIntent.putExtra("MY_VALUE", str);
                //setResult(RECEIVE_EVENT, resultIntent);
                finish();
            }
        });*/
    }

    /*
    public JSONArray doGet(String url) throws JSONException {
        try {
            HttpGet method = new HttpGet(url);
            DefaultHttpClient client = new DefaultHttpClient();
            // 헤더 설정
            method.setHeader("Connection", "Keep-Alive");

            HttpResponse response = client.execute(method);
            // response status 가 400이 아니라면
            int status = response.getStatusLine().getStatusCode();
            if( status != HttpStatus.SC_OK ) {
                Log.e("status", "error");
                throw new Exception("");    // 실패
            }
            // response 받기 JSONArray 로 파싱
            String str = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(str);
            return new JSONArray(str);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
*/

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
}
