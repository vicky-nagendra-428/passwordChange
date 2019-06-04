package com.pwc.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PasswordChangeStepDefinitions {

    @Given ("I enter the old password {string} and new password {string}")
    public void i_enter_the_old_password_and_new_password(String oldPassword, String newPassword) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then ("I should get the message {string}")
    public void i_should_get_the_message(String expectedMessage) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}
