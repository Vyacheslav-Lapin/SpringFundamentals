package com.epam.sf.aop.helloWorld;

import org.springframework.aop.framework.ProxyFactory;

public class HelloWorldAOPExample {
    public static void main(String[] args) {
        MessageWriter target = new MessageWriter();
        target.writeMessage();

        ProxyFactory pf = new ProxyFactory() ;
        pf.addAdvice(new MessageDecorator());
        pf.setTarget(target);
        MessageWriter proxy = (MessageWriter) pf.getProxy();
        System.out.println("");
        proxy.writeMessage();
    }
}
