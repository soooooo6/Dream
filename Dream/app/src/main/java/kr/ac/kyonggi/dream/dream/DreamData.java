package kr.ac.kyonggi.dream.dream;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by samsung on 2015-05-29.
 */
public class DreamData {
    private String USER_AGENT = "Mozilla/5.0";

    public static JSONObject[] getRestaList(int index, int count){
        JSONParser parse = new JSONParser();
        //String response = getRestaList(index,count);
        String response = "[{\"id\":\"1090\",\"name\":\"피자집0\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1091\",\"name\":\"피자집1\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1092\",\"name\":\"피자집2\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1093\",\"name\":\"피자집3\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1094\",\"name\":\"피자집4\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1095\",\"name\":\"피자집5\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1096\",\"name\":\"피자집6\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1097\",\"name\":\"피자집7\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1098\",\"name\":\"피자집8\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1099\",\"name\":\"피자집9\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1100\",\"name\":\"피자집10\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1101\",\"name\":\"피자집11\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1102\",\"name\":\"피자집12\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1103\",\"name\":\"피자집13\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1104\",\"name\":\"피자집14\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1105\",\"name\":\"피자집15\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1106\",\"name\":\"피자집16\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1107\",\"name\":\"피자집17\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1108\",\"name\":\"피자집18\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"},{\"id\":\"1109\",\"name\":\"피자집19\",\"phone\":\"010-0000-0000\",\"image_path\":\"test.jpg\"}]";

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

//      JSONObject jsonObj = (JSONObject) JSONValue.parse(value);
//      String test = (String)jsonObj.get("key1");
        return restas;
    }
}
