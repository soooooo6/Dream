package kr.ac.kyonggi.dream.dream;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

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
        new MyHandle().get("/public_html/dream/RestaListJson.php?index=0&count=10", null, rh);

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
        Toast.makeText(context, res.str,Toast.LENGTH_SHORT).show();
      }
    }

    class MyHandle  {
        private static final String BASE_URL = "http://create32.ddns.net:9080";

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
