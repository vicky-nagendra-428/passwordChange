package com.pwc.stepDefinitions;

import com.pwc.core.Password;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

public class PasswordChangeStepDefinitions {

    Password password = new Password();

    @Given ("I enter the old password {string} and new password {string}")
    public void i_enter_the_old_password_and_new_password(String oldPassword, String newPassword) {
        // Write code here that turns the phrase above into concrete actions
        password.changePassword(oldPassword, newPassword);
    }

    @Then ("I should get the message {string}")
    public void i_should_get_the_message(String expectedMessage) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(expectedMessage);
        System.out.println(password.getThePasswordChangeStatus());
        assertEquals(expectedMessage, password.getThePasswordChangeStatus());
    }
}
