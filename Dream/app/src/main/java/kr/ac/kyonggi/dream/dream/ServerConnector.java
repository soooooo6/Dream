package kr.ac.kyonggi.dream.dream;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Future;
/**
 * Created by samsung on 2015-05-28.
 */

 class ServerConnector {
    Context  context;
    ServerConnector(Context context)
    { this.context = context;}

    class BOX {
        public String str = null;
    }

    void test(String value) {
        RH rh = new RH();
        BOX res = new BOX();
        rh.setRes(res);
        new MyHandle().get("http://create32.ddns.net:9080/index.html", null, rh);

        Toast.makeText(context, res.str,Toast.LENGTH_SHORT).show();
    }
    class RH extends AsyncHttpResponseHandler
    {
        BOX res = null;

        public void setRes(BOX res) {
            this.res = res;
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            // TODO Auto-generated method stub
            Log.i("AA", "onFailure " + arg0 + " " + arg2 + " " + arg3.toString());
            for (int i = 0; i < arg1.length; i++) {
                Log.i("AA", "onFailure " + arg1[i].getName() + " " + arg1[i].getValue() + " ");
            }

        }
        // 전송된 결과 값은 onSuccess 메서드를 통해 받을 수 있다.
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
        // TODO Auto-generated method stub

        Log.i("AA", "onSuccess " + arg0 + " ?? " + arg2);
        for (int i = 0; i < arg1.length; i++)
            Log.i("AA", "onSuccess " + arg1[i].getName() + " ?? " + arg1[i].getValue() + " ");

        res.str = arg2.toString();
      }
    }

    class MyHandle  {
        private static final String BASE_URL = "http://create32.ddns.net:9080/";

        private  AsyncHttpClient client = new AsyncHttpClient();

        public  void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.get(getAbsoluteUrl(url), params, responseHandler);
        }

        public  void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.post(getAbsoluteUrl(url), params, responseHandler);
        }

        private  String getAbsoluteUrl(String relativeUrl) {
            return BASE_URL + relativeUrl;
        }

    }
}
