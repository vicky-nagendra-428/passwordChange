package com.pwc.restComponents;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pwc.validations.Password;

import static spark.Spark.get;
import static spark.Spark.put;

public class PasswordChangeRestApi {

    public static void main(String[] args) {

        //http://localhost:4567/changePassword

        get("/checkHealth", (request, response) -> {
            JsonObject reponseJson = new JsonObject();
            response.type("application/json");
            reponseJson.addProperty("STATUS", "Server is Up And Running");
            return reponseJson;
        });

        put("/changePassword", (request, response) -> {

            JsonObject responseJson = new JsonObject();
            response.type("application/json");
            if (request.body() == null) {
                responseJson.addProperty("STATUS",
                        "API '/changePassword' needs a JSON body with information to process the request");
                return responseJson;
            }

            String oldPassword = "";
            String newPassword = "";

            try {

                JsonObject passwordHolder = new JsonParser().parse(request.body()).getAsJsonObject();
                oldPassword = passwordHolder.get("oldPassword").getAsString();
                newPassword = passwordHolder.get("newPassword").getAsString();

            } catch (NullPointerException | IllegalStateException | JsonSyntaxException e) {
                responseJson.addProperty("STATUS",
                        "API '/changePassword' needs a well formed JSON body with information to process the request." +
                                "Possible cause : Empty Json body/ missing required data/ incorrect Json format");
                return responseJson;
            }

            if (oldPassword.isEmpty() || newPassword.isEmpty()) {
                responseJson.addProperty("STATUS", "Failed to process your request. Possible cause : missing required information");
            } else {
                Password password = new Password();
                password.changePassword(oldPassword, newPassword);
                responseJson.addProperty("STATUS", password.getThePasswordChangeStatus());
            }

            return responseJson;
        });

    }
}

