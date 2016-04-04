package com.epam.sf.aop.sequrity;

import java.util.Optional;

public class SecurityManager {

    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public void login(String userName, String password) {
        threadLocal.set(new UserInfo(userName, password));
    }

    public void logout() {
        threadLocal.set(null);
    }

    public Optional<UserInfo> getLoggedOnUser() {
        return Optional.ofNullable(threadLocal.get());
    }
}
