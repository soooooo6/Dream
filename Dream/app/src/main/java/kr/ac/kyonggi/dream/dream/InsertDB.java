package kr.ac.kyonggi.dream.dream;

import android.os.AsyncTask;
import android.os.Handler;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.net.URL;

/**
 * Created by samsung on 2015-06-08.
 */
public class InsertDB extends AsyncTask<String, Integer, JSONObject[]> {
    public InsertDB(String s) {

    }

    protected JSONObject[] doInBackground(String... urls) {
        JSONParser parse = new JSONParser();
        JSONObject[] restas = null;
            ConnectThread thread = new ConnectThread(urls[0]);
            thread.start();
            String response = ConnectThread.request(urls[0]);

            JSONArray restasObj = null;
            try {
                restasObj = (JSONArray) parse.parse(response);
            } catch (ParseException e) {
                return null;
            }

            restas = new JSONObject[restasObj.size()];
            for (int j = 0; j < restasObj.size(); j++) {
                restas[j] = (JSONObject) restasObj.get(j);
            }

        return restas;
    }
}