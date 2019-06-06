package com.pwc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {

    private String passwordChangeResult = null;
    private static final Logger logger = LoggerFactory.getLogger(Password.class);

    public void changePassword(String oldPassword, String newPassword) {

        passwordChangeResult = checkNewPasswordValidity(newPassword);

        if (passwordChangeResult == null) {

        }
    }

    public String getThePasswordChangeStatus() {
        return passwordChangeResult;
    }

    /**
     *
     * @param newPassword
     * @return This method returns a validation string based on multiple validations
     */
    private String checkNewPasswordValidity(String newPassword) {
        String returnMessage = null;

        if (newPassword.length() < 18) {
            returnMessage = "Password must be at least of 18 character length";
        } else if (checkForUnacceptableCharacters(newPassword) || newPassword.length() < 18) {
            returnMessage = "Password must be a combination of alphanumeric and special characters !@#$&* only";
        } else if (! checkForUpperCaseLowerCaseNumericAndSpecialCharacterValidity(newPassword)) {
            returnMessage = "Password should have at least 1 Upper case, 1 Lower case, 1 numeric value and 1 special character";
        } else if (checkForDuplicateCharacters(newPassword) > 4) {
            returnMessage = "Password should not have more than a total of 4 repeated characters";
        } else if (getSpecialCharacterCount(newPassword) > 4) {
            returnMessage = "Password can not have more than 4 special characters";
        } else if (getTheNumericCharacterPercentage(newPassword) >= 50) {
            returnMessage = "Password should not contain 50% of numeric values of its length";
        }


        return returnMessage;
    }

    /**
     *
     * @param newPassword
     * @return This method retunrs a true if there are any unacceptable characters exist in the input, else false
     */
    private boolean checkForUnacceptableCharacters(String newPassword) {
        logger.info("Inside checkForUnacceptableCharacters method");
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9!@#$&*]");
        Matcher matcher = pattern.matcher(newPassword);
        return matcher.find();
    }

    /**
     *
     * @param newPassword
     * @return This method returns true if the password meets the criteria of
     *         atleast 1 Uppercase, 1 lowercase, 1 numeric and 1 special character
     */
    private boolean checkForUpperCaseLowerCaseNumericAndSpecialCharacterValidity(String newPassword) {
        logger.info("Inside checkForUpperCaseLowerCaseNumericAndSpecialCharacterValidity method");
        List<String> patternsList = Arrays.asList("[A-Z]", "[a-z]", "[0-9]", "[!@#$&*]");
        boolean metCriteria = true;

        for (String eachPattern : patternsList) {
            if (! Pattern.compile(eachPattern).matcher(newPassword).find()) {
                metCriteria = false;
                break;
            }
        }
        return metCriteria;
    }

    /**
     *
     * @param newPassword
     * @return This method returns the count of characters which are repeated
     */
    private int checkForDuplicateCharacters(String newPassword) {
        logger.info("Inside checkForDuplicateCharacters method");

        int duplicateCharCount = 0;

        for (int i = 0; i < newPassword.length(); i++) {

            char currentCharacter = newPassword.charAt(i);
            if (currentCharacter == '_') {
                continue;
            }

            String stringMatcher = String.format("%s", currentCharacter);

            if (Pattern.compile("[^a-zA-Z0-9]").matcher(String.valueOf(currentCharacter)).find()) {
                stringMatcher = String.format("\\%s", currentCharacter);
            }

            int count = getMatchedCount(stringMatcher, newPassword);
            if (count > 1) {
                duplicateCharCount++;
            }

            newPassword = Pattern.compile(stringMatcher).matcher(newPassword).replaceAll("_");
        }

        return duplicateCharCount;
    }

    /**
     *
     * @param newPassword
     * @return this method returns the count of special characters
     */
    private int getSpecialCharacterCount(String newPassword) {
        logger.info("Inside getSpecialCharacterCount method");
        return getMatchedCount("[^a-zA-Z0-9]", newPassword);
    }

    /**
     *
     * @param newPassword
     * @return this method returns the percentage of numeric characters
     */
    private int getTheNumericCharacterPercentage(String newPassword) {
        logger.info("Inside getTheNumericCharacterPercentage method");
        int numericCharacterCount = getMatchedCount("\\d", newPassword);
        int percen = (int) (((float)numericCharacterCount/newPassword.length()) * 100);
        return percen;
    }

    /**
     *
     * @param regex
     * @param inputString
     * @return returns the count of matches
     */
    private int getMatchedCount(String regex, String inputString) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

}
