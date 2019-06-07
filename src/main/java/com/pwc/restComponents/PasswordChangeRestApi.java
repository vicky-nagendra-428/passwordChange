package com.pwc.restComponents;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pwc.core.Password;

import static spark.Spark.get;
import static spark.Spark.put;

public class PasswordChangeRestApi {

    public static void main(String[] args) {

        //http://localhost:4567/changePassword

        get("/checkHealth", (request, response) -> "Up And Running" );

        put("/changePassword", (request, response) -> {

            JsonObject passwordHolder = new JsonParser().parse(request.body()).getAsJsonObject();

            String oldPassword = passwordHolder.get("oldPassword").getAsString();
            String newPassword = passwordHolder.get("newPassword").getAsString();

            Password password = new Password();
            password.changePassword(oldPassword, newPassword);

            JsonObject reponseJson = new JsonObject();
            response.type("application/json");
            reponseJson.addProperty("STATUS", password.getThePasswordChangeStatus());
            return reponseJson;
        });

    }
}

