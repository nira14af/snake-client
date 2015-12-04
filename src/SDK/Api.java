package SDK;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Api
{


        ServerConnection serverConnection = new ServerConnection();

        public String login(User user)
        {

            String data = serverConnection.post(new Gson().toJson(user), "login/");

            JSONParser parser = new JSONParser();

            JSONObject jsonObject = null;
            try
            {
                Object object = parser.parse(data);
                jsonObject = (JSONObject) object;
            } catch (ParseException e)
            {
                e.printStackTrace();
            }

            user.setId((int) (long) jsonObject.get("userid"));

        return "";
        }
}


