package com.comsultant.domain.account.util;

import com.comsultant.global.config.security.AccountDetails;

public class AccountUtil {
    public static boolean isAccountDetailsNull(AccountDetails accountDetails) {
        return accountDetails == null || accountDetails.getAccount() == null;
    }
}
