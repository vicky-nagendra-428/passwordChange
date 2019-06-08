Feature: Change Password function Test cases

  @smoke
  Scenario Outline: Incorrect old password

    Given I enter the old password "<oldPassword>" and new password "<newPassword>"
    Then I should get the message "Old Password Doesn't match with existing password"
    Examples:
      |oldPassword                       |newPassword           |
      |FalseThisIsOldPassword#01         |ThisIsNewWordPass#2   |

  @smoke
  Scenario:  new password is matched 80% with old password

    Given I enter the old password "ThisIsOldPassword#01" and new password "ThisIsOldPassword#02"
    Then I should get the message "Similar password has been used, please use another one"

  @smoke
  Scenario Outline:  new password is less than 18 characters

    Given I enter the old password "<oldPassword>" and new password "<newPassword>"
    Then I should get the message "Password must be at least of 18 character length"
    Examples:
      |oldPassword          |newPassword           |
      |ThisIsOldPassword#1  |ThisIsNewWordPa#2     |
      |ThisIsOldPassword#1  |                      |

  @smoke
  Scenario Outline:  new password has characters which are not allowed

    Given I enter the old password "<oldPassword>" and new password "<newPassword>"
    Then I should get the message "Password must be a combination of alphanumeric and special characters !@#$&* only"
    Examples:
      |oldPassword          |newPassword           |
      |ThisIsOldPassword#1  |ThisIsOldPassword.1   |
      |ThisIsOldPassword#1  |This..NewWordPass_2   |

  @smoke
  Scenario Outline:  new password should have atleast one upper case, lower case, numeric and special character

    Given I enter the old password "<oldPassword>" and new password "<newPassword>"
    Then I should get the message "Password should have at least 1 Upper case, 1 Lower case, 1 numeric value and 1 special character"
    Examples:
      |oldPassword          |newPassword           |
      |ThisIsOldPassword#1  |ThisIsNewPassword#pass|
      |ThisIsOldPassword#1  |thisisnewwordpass!2   |
      |ThisIsOldPassword#1  |THISISNEWWORDPASS#2   |
      |ThisIsOldPassword#1  |ThisIsNewPassword1234 |

  @smoke
  Scenario Outline:  new password with more than 4 repeated characters

    Given I enter the old password "<oldPassword>" and new password "<newPassword>"
    Then I should get the message "Password should not have more than a total of 4 repeated characters"
    Examples:
      |oldPassword          |newPassword            |
      |ThisIsOldPassword#1  |$!*AAbbCCddEEpassword#1|
      |ThisIsOldPassword#1  |!!22AAaabbdefghijkl    |
      |ThisIsOldPassword#1  |HHISISNEWWoRDPPSS#2    |
      |ThisIsOldPassword#1  |Thisis1234aword1234!@  |

  @smoke
  Scenario:  new password with more than 4 special characters

    Given I enter the old password "ThisIsOldPassword#1" and new password "Pass1234567890!test!@#$&*"
    Then I should get the message "Password can not have more than 4 special characters"

  @smoke
  Scenario Outline:  new password with more than 50% numeric characters

    Given I enter the old password "<oldPassword>" and new password "<newPassword>"
    Then I should get the message "Password should not contain 50% of numeric values of its length"
    Examples:
      |oldPassword          |newPassword            |
      |ThisIsOldPassword#1  |Pass1234567890!test    |
      |ThisIsOldPassword#1  |23224356785aA*1234!    |

  @smoke
  Scenario Outline: correct old password and valid new password

    Given I enter the old password "<oldPassword>" and new password "<newPassword>"
    Then I should get the message "Password Updated successfully"
    Examples:
      |oldPassword          |newPassword            |
      |ThisIsOldPassword#1  |ThisIsNewWordPass#2    |
      |ThisIsOldPassword#1  |123456NewWordPass#2    |

