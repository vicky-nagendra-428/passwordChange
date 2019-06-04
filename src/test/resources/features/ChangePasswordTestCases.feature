Feature: Change Password Test cases

  Scenario: Incorrect old password

    Given I enter the old password "ThisIsOldPassword#1" and new password "ThisIsNewWordPass#2"
    Then I should get the message "Old Password Doesn't match with exising password"

  Scenario:  new password is matched 80% with old password

    Given I enter the old password "ThisIsOldPassword#1" and new password "ThisIsOldPassword#2"
    Then I should get the message "Similar password has been used, please use another one"

  Scenario:  new password is less than 18 characters

    Given I enter the old password "ThisIsOldPassword#1" and new password "ThisIsOldPassword"
    Then I should get the message "Password should be atleast 18 character length"

  Scenario:  new password has characters which are not allowed

    Given I enter the old password "ThisIsOldPassword#1" and new password "ThisIsOldPassword.1"
    Then I should get the message "Password can contain alpha numeric and special chars !@#$&*"

  Scenario:  new password should have atleast one upper case, lower case, numeric and special character

    Given I enter the old password "thisisoldpassword#1" and new password "ThisIsNewPassword#2"
    Then I should get the message "Password should have atleast one upper case, lower case, numeric and special character"

  Scenario:  new password with more than 4 repeated characters

    Given I enter the old password "ThisIsOldPassword#1" and new password "AAbbCCddEEpassword#1"
    Then I should get the message "Password has more than 4 repeated characters"

  Scenario:  new password with more than 4 special characters

    Given I enter the old password "ThisIsOldPassword#1" and new password "NewPasswordWith!@#$&*"
    Then I should get the message "Password is not allowed to have more than 4 special characters"

  Scenario:  new password with more than 50% numeric characters

    Given I enter the old password "ThisIsOldPassword#1" and new password "Password1234567890"
    Then I should get the message "Password should not have more than 50% numeric characters"