package com.pwc.junitTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PasswordChangeApiTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://0.0.0.0:4567";
        assertEquals(given().get("/checkHealth").getStatusCode(), 200);
    }

    @Test(dataProvider = "ApiTestDataProvider", invocationCount = 5, threadPoolSize = 5)
    public void changePasswordWithIncorrectOldPassword(String oldPassword, String newPassword, String responseMessageExpected) {
        Response response = runChangePasswordCall(oldPassword, newPassword);
        assertEquals(response.body().jsonPath().getString("STATUS"), responseMessageExpected);
    }

    @DataProvider(name = "ApiTestDataProvider")
    public Object[][] apiTestDataProvider() {
        return new Object[][] {
                {"FalseThisIsOldPassword#01", "ThisIsNewWordPass#2", "Old Password Doesn't match with existing password"},
                {"ThisIsOldPassword#01", "ThisIsOldPassword#02", "Similar password has been used, please use another one"},
                {"ThisIsOldPassword#1", "ThisIsNewWordPa#2", "Password must be at least of 18 character length"},
                {"ThisIsOldPassword#1", "ThisIsOldPassword.1", "Password must be a combination of alphanumeric and special characters !@#$&* only"},
                {"ThisIsOldPassword#1", "thisisnewwordpass!2", "Password should have at least 1 Upper case, 1 Lower case, 1 numeric value and 1 special character"},
                {"ThisIsOldPassword#1", "$!*AAbbCCddEEpassword#1", "Password should not have more than a total of 4 repeated characters"},
                {"ThisIsOldPassword#1", "Pass1234567890!test!@#$&*", "Password can not have more than 4 special characters"},
                {"ThisIsOldPassword#1", "Pass1234567890!test", "Password should not contain 50% of numeric values of its length"},
                {"ThisIsOldPassword#1", "ThisIsNewWordPass#2", "Password Updated successfully"},
                {"", "", "Failed to process your request. Possible cause : missing required information"}
        };
    }

    private String prepareRequestBody(String oldPwd, String newPwd) {
        return "{" +
                "\"oldPassword\":\"" + oldPwd + "\"," +
                "\"newPassword\":\"" + newPwd + "\"" +
                "}";
    }

    private Response runChangePasswordCall(String oldPwd, String newPwd) {
        Response response = given().contentType(ContentType.JSON).body(prepareRequestBody(oldPwd, newPwd))
                .when().put("/changePassword");
        assertEquals(200, response.getStatusCode());
        return response;
    }

}
