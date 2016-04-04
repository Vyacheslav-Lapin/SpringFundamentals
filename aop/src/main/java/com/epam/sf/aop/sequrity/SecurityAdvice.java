package com.epam.sf.aop.sequrity;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {

    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo user = securityManager.getLoggedOnUser()
                .orElseThrow(() -> new SecurityException(
                        "You must login before attempting to invoke the method: "
                                + method.getName()));
        if ("chris".equals(user.getUserName()))
            System.out.println("Logged in user is chris - ОКАУ !");
        else {
            System.out.println("Logged in user is " + user.getUserName() + " - that`s NOT GOOD :(");
            throw new SecurityException("User " + user.getUserName()
                    + " is not allowed access to method " + method.getName());
        }
    }
}
