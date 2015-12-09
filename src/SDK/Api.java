package SDK;

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


public class Api {

    ServerConnection serverConnection = new ServerConnection();

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


    public ArrayList<User> getUsers() {
        String data = serverConnection.get("users/");
        return new Gson().fromJson(data, new TypeToken<ArrayList<User>>() {
        }.getType());
    }

    public String createGame(Game game) {
        //String Data modtager en værdi fra "games/" endpointet i Server.api.Api.
        //Dataen som kommer fra logikken bliver lavet om fra GSon til Json
        String data = serverConnection.post(new Gson().toJson(game), "games/");
        //Dataen der bliver returneret som JSON, men bliver lavet om til Gson.
        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        return hashMap.get("message");
    }

    public ArrayList<Game> getGames() {
        String data = serverConnection.get("games/open/");
        return new Gson().fromJson(data, new TypeToken<ArrayList<Game>>() {
        }.getType());
    }

    public String setOpponent(Game startGame) {
        String data = serverConnection.put(new Gson().toJson(startGame), "games/join");
        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        return hashMap.get("message");
    }

    public String startGame(Game startGame) {
        String data = serverConnection.put(new Gson().toJson(startGame), "games/start");
        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        if (hashMap.get("message") != null)
            return hashMap.get("message");
        else {
            Game playingGame = new Gson().fromJson(data, Game.class);
            startGame.setWinner(playingGame.getWinner());
            return startGame.getWinner().getId()+ "";
        }
    }

    public ArrayList<Game> getAvaiableGames(int id) {
        String data = serverConnection.get("games/host/" + id);
        return new Gson().fromJson(data, new TypeToken<ArrayList<Game>>() {
        }.getType());
    }

    public String deleteGame(int gameId) {
        String data = serverConnection.delete("games/" + gameId);
        HashMap<String, String> hashMap = new Gson().fromJson(data, HashMap.class);

        return hashMap.get("message");
    }

    public ArrayList<Game> getGamesByUserId(int id) {
        String games = serverConnection.get("games/host/" + id);
        return new Gson().fromJson(games, new TypeToken<ArrayList<Game>>() {
        }.getType());
    }

    public ArrayList<Score> getHighscores() {
        String data = serverConnection.get("Highscores/");
        return new Gson().fromJson(data, new TypeToken<ArrayList<Score>>() {
        }.getType());
    }
}