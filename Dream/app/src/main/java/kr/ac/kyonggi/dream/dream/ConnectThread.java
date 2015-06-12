package kr.ac.kyonggi.dream.dream;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sookyung on 2015-06-08.
 */
class ConnectThread extends Thread {
    String urlStr;
    private Handler handler;

    public ConnectThread(String inStr) {
        urlStr = inStr;
    }

    public void run() {
        try {
            final String output = request(urlStr);
            handler.post(new Runnable() {
                public void run() {
                    System.out.println(output);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static String request(String urlStr) {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while (true) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        output.append(line + "\n");
                    }
                    reader.close();
                    conn.disconnect();
                }
            }
        } catch (Exception ex) {
            Log.e("SampleHTTP", "Exception processing.", ex);
            ex.printStackTrace();
        }
        return output.toString();
    }
}