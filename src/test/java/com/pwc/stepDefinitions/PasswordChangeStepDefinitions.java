package com.pwc.stepDefinitions;

import com.pwc.validations.Password;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

public class PasswordChangeStepDefinitions {

    Password password = new Password();

    @Given ("I enter the old password {string} and new password {string}")
    public void i_enter_the_old_password_and_new_password(String oldPassword, String newPassword) {
        password.changePassword(oldPassword, newPassword);
    }

    @Then ("I should get the message {string}")
    public void i_should_get_the_message(String expectedMessage) {
        assertEquals(expectedMessage, password.getThePasswordChangeStatus());
    }
}
