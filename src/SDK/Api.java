package SDK;

//Diveres klasser importeres

import SDK.Model.Game;
import SDK.Model.Score;
import SDK.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

//GSON importeres
//JSON importeres


public class Api {

    //Objekt af klassen serverConnection oprettes
    ServerConnection serverConnection = new ServerConnection();

    //Metode til at logge ind.
    //Sender data til serverconnection via post-metoden og modtager JSON som parses til GSON og derefter returneres der en message
    public String login(User data) {
        String serverResponse = serverConnection.post(new Gson().toJson(data), "login/");

        JSONParser parser = new JSONParser();

        JSONObject jsonObject = null;
        try {
            Object object = parser.parse(serverResponse);
            jsonObject = (JSONObject) object;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (jsonObject != null) {

            if (jsonObject.get("userid") != null)
                data.setId((int) (long) jsonObject.get("userid"));

            return (String) jsonObject.get("message");
        }
        return "";
    }

    //Metode til at oprette et spil i serveren
    public String createGame(Game game) {
        String data = serverConnection.post(new Gson().toJson(game), "games/");
        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        return hashMap.get("message");
    }

    //ArrayList af alle game deklareres til getGames
    public ArrayList<Game> getGames() {
        String data = serverConnection.get("games/open/");
        return new Gson().fromJson(data, new TypeToken<ArrayList<Game>>() {
        }.getType());
    }

    //ArrayList af alle users i systemets deklares som getUsers
    public ArrayList<User> getUsers() {
        String data = serverConnection.get("users/");
        return new Gson().fromJson(data, new TypeToken<ArrayList<User>>() {
        }.getType());
    }


    //Metode til at ramme endpoint i serveren som starter et spil
    public String startGame(Game startGame) {
        String data = serverConnection.put(new Gson().toJson(startGame), "games/start");
        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        if (hashMap.get("message") != null)
            return hashMap.get("message");
        else {
            Game gamedPlayed = new Gson().fromJson(data, Game.class);
            startGame.setWinner(gamedPlayed.getWinner());
            return startGame.getWinner().getId()+ "";
        }
    }

    //Metode til at ramme endpoint i serveren som sørger for spil bliver slettet
    public String deleteGame(int gameId) {
        String data = serverConnection.delete("games/" + gameId);
        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        return hashMap.get("message");
    }

    //Arraylist af alle scores deklareres til getHighscores
    public ArrayList<Score> getHighscores() {
        String data = serverConnection.get("Highscores/");
        return new Gson().fromJson(data, new TypeToken<ArrayList<Score>>() {
        }.getType());
    }
}