package kr.ac.kyonggi.dream.dream;

import android.os.Handler;
import android.util.Log;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samsung on 2015-05-29.
 */
public class DreamData {
    // 책 예제
    static Handler handler = new Handler();

    /*
    *   Restaurant List Parsing
    * */
    public static JSONObject[] getRestaList(int index, int count, int category){
        JSONParser parse = new JSONParser();

//        String urlStr = "http://create32.ddns.net:9080/public_html/dream/RestaListJson.php?index="+index+"&count="+count+"&category="+category;
        String urlStr = "http://1.224.193.99:9080/public_html/dream/RestaListJson.php?index="+index+"&count="+count+"&category="+category;
        ConnectThread thread = new ConnectThread(urlStr);
        thread.start();

        String response = ConnectThread.request(urlStr);

        JSONArray restasObj = null;
        try {
            restasObj = (JSONArray)parse.parse(response);
        } catch (ParseException e) {
            return null;
        }
        JSONObject[] restas = new JSONObject[restasObj.size()];
        for (int i = 0; i < restasObj.size(); i++)
        {
            restas[i] =  (JSONObject)restasObj.get(i);
        }

        return restas;
    }

    /*
    *   Restaurant's Menu List Parsing
    * */
    public static JSONObject[] getMenuList(int id){
        JSONParser parse = new JSONParser();

        String urlStr = "http://1.224.193.99:9080/public_html/dream/MenuListJson.php?id="+id;
        ConnectThread thread = new ConnectThread(urlStr);
        thread.start();

        String response = ConnectThread.request(urlStr);

        JSONArray restasObj = null;
        try {
            restasObj = (JSONArray)parse.parse(response);
        } catch (ParseException e) {
            return null;
        }
        JSONObject[] restas = new JSONObject[restasObj.size()];
        for (int i = 0; i < restasObj.size(); i++)
        {
            restas[i] =  (JSONObject)restasObj.get(i);
        }
        return restas;
    }

    static class ConnectThread extends Thread {
        String urlStr;

        public ConnectThread(String inStr){
            urlStr = inStr;
        }
        public void run() {
                try {
                    final String output = request(urlStr);
                    handler.post(new Runnable(){
                        public void run() {
                            System.out.println(output);
                        }
                    });
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        private static String request(String urlStr){
            StringBuilder output = new StringBuilder();
            try {
                URL url = new URL(urlStr);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    int resCode = conn.getResponseCode();
                    if(resCode == HttpURLConnection.HTTP_OK){
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        String line = null;
                        while(true) {
                            line = reader.readLine();
                            if(line == null){
                                break;
                            }
                            output.append(line+"\n");
                        }
                        reader.close();
                        conn.disconnect();
                    }
                }
            } catch(Exception ex) {
                Log.e("SampleHTTP", "Exception processing.", ex);
                ex.printStackTrace();
            }
            return output.toString();
        }
    }
}
