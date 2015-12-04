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

public class Api {

    ServerConnection serverConnection = new ServerConnection();

    public int login(User user) {
        int data = serverConnection.postLogin(new Gson().toJson(user), "login/");

        JSONParser parser = new JSONParser();

        return data;
    }

    
}
