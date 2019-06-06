Feature: Change Password Test cases

  @smoke
  Scenario: Incorrect old password

    Given I enter the old password "ThisIsOldPassword#1" and new password "ThisIsNewWordPass#2"
    Then I should get the message "Old Password Doesn't match with exising password"

  @smoke
  Scenario:  new password is matched 80% with old password

    Given I enter the old password "ThisIsOldPassword#1" and new password "ThisIsOldPassword#2"
    Then I should get the message "Similar password has been used, please use another one"

  @smoke
  Scenario:  new password is less than 18 characters

    Given I enter the old password "ThisIsOldPassword#1" and new password "ThisIsOldPassword"
    Then I should get the message "Password must be at least of 18 character length"

  @smoke
  Scenario:  new password has characters which are not allowed

    Given I enter the old password "ThisIsOldPassword#1" and new password "ThisIsOldPassword.1"
    Then I should get the message "Password must be a combination of alphanumeric and special characters !@#$&* only"

  @smoke
  Scenario:  new password should have atleast one upper case, lower case, numeric and special character

    Given I enter the old password "thisisoldpassword#1" and new password "ThisIsNewPassword#pass"
    Then I should get the message "Password should have at least 1 Upper case, 1 Lower case, 1 numeric value and 1 special character"

  @smoke
  Scenario:  new password with more than 4 repeated characters

    Given I enter the old password "ThisIsOldPassword#1" and new password "$!*AAbbCCddEEpassword#1"
    Then I should get the message "Password should not have more than a total of 4 repeated characters"

  @smoke
  Scenario:  new password with more than 4 special characters

    Given I enter the old password "ThisIsOldPassword#1" and new password "Pass1234567890!test!@#$&*"
    Then I should get the message "Password can not have more than 4 special characters"

  @smoke
  Scenario:  new password with more than 50% numeric characters

    Given I enter the old password "ThisIsOldPassword#1" and new password "Pass1234567890!test"
    Then I should get the message "Password should not contain 50% of numeric values of its length"