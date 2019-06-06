package com.pwc.validations;

import com.pwc.core.DB;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

public class DBValidations {

    @Spy
    DB dbObj;
    DB dbSpy;

    public DBValidations() {
        MockitoAnnotations.initMocks(this);
        this.dbSpy = spy(dbObj);
    }

    public boolean checkPasswordMatchAgainstDb(String oldPassword) {
        doReturn(true).when(dbSpy).checkPasswordAgainstDBValue("ThisIsOldPassword#01");
        doReturn(false).when(dbSpy).checkPasswordAgainstDBValue(matches("False\\w+"));
        return dbSpy.checkPasswordAgainstDBValue(oldPassword);
    }

    public boolean checkThePasswordIsAlreadyUsed(String newPassword) {
        doReturn(true).when(dbSpy).isPasswordSimilarToOldPassword("ThisIsOldPassword#02");
        doReturn(false).when(dbSpy).isPasswordSimilarToOldPassword(matches("\\w+.NewWordPass.*"));
        return dbSpy.isPasswordSimilarToOldPassword(newPassword);
    }

    public void updateThePassword(String newPassword) {
        dbSpy.setPassword(newPassword);
    }
}
