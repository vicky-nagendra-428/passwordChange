package com.pwc.junitTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class PasswordChangeApiTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://0.0.0.0:4567";
    }

    public String prepareRequestBody(String oldPwd, String newPwd) {
        return "{" +
                "\"oldPassword\":\"" + oldPwd + "\"," +
                "\"newPassword\":\"" + newPwd + "\"" +
                "}";
    }

    public Response runChangePasswordCall(String oldPwd, String newPwd) {
        Response response = given().contentType(ContentType.JSON).body(prepareRequestBody(oldPwd, newPwd))
                .when().put("/changePassword");
        assertEquals(200, response.getStatusCode());
        return response;
    }

    @Test
    public void changePasswordWithIncorrectOldPassword() {
        String oldPassword = "FalseThisIsOldPassword#01";
        String newPassword = "ThisIsNewWordPass#2";

        Response response = runChangePasswordCall(oldPassword, newPassword);

        String responseMessgae = response.body().jsonPath().getString("STATUS");
        assertEquals("Old Password Doesn't match with exising password", responseMessgae);
    }

    @Test
    public void changePasswordWithNewPasswordMatchingWithOldPassword() {
        String oldPassword = "ThisIsOldPassword#01";
        String newPassword = "ThisIsOldPassword#02";

        Response response = runChangePasswordCall(oldPassword, newPassword);
        String responseMessgae = response.body().jsonPath().getString("STATUS");
        assertEquals("Similar password has been used, please use another one", responseMessgae);
    }

}
