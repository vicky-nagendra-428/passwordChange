package com.pwc.validations;

import com.pwc.core.DB;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;

public class DBValidations {

    @Spy
    DB dbObj;
    DB dbSpy;

    private static final Logger logger = LoggerFactory.getLogger(DBValidations.class);

    public DBValidations() {
        MockitoAnnotations.initMocks(this);
        this.dbSpy = spy(dbObj);
    }

    public boolean checkPasswordMatchAgainstDb(String oldPassword) {
        logger.info("Inside checkPasswordMatchAgainstDb method");
        doReturn(true).when(dbSpy).checkPasswordAgainstDBValue("ThisIsOldPassword#01");
        doReturn(false).when(dbSpy).checkPasswordAgainstDBValue(matches("False\\w+"));
        return dbSpy.checkPasswordAgainstDBValue(oldPassword);
    }

    public boolean checkThePasswordIsAlreadyUsed(String newPassword) {
        logger.info("Inside checkThePasswordIsAlreadyUsed method");
        doReturn(true).when(dbSpy).isPasswordSimilarToOldPassword("ThisIsOldPassword#02");
        doReturn(false).when(dbSpy).isPasswordSimilarToOldPassword(matches("\\w+.NewWordPass.*"));
        return dbSpy.isPasswordSimilarToOldPassword(newPassword);
    }

    public void updateThePassword(String newPassword) {
        logger.info("Inside updateThePassword method");
        dbSpy.setPassword(newPassword);
    }
}
