package com.epam.sf.aop.sequrity;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityExample {
    public static void main(String[] args) {
        SecurityManager mgr = new SecurityManager();
        SecureBean bean = getSecureBean();
        mgr.login("chris", "pwd");
        bean.writeSecureMessage();
        mgr.logout();
        try {
            mgr.login("invalidUser", "pwd");
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        } finally {
            mgr.logout();
        }

        try {
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }
    }

    private static SecureBean getSecureBean() {
        SecureBean target = new SecureBean();
        SecurityAdvice advice = new SecurityAdvice();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice);
        return (SecureBean) factory.getProxy();
    }
}
