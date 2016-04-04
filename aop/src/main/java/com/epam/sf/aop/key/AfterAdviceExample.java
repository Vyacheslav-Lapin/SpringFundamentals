package com.epam.sf.aop.key;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceExample {

    private static KeyGenerator getKeyGenerator() {
        KeyGenerator target = new KeyGenerator();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new WeakKeyCheckAdvice());
        return (KeyGenerator) factory.getProxy();
    }

    public static void main(String[] args) {
        KeyGenerator keyGen = getKeyGenerator();
        for(int х = 0; х < 10; х++) {
            try {
                long key = keyGen.getKey();
                System.out.println("Key: " + key);
            } catch (SecurityException e) {
                System.out.println("Weak Кеу Generated!"); // Сгенерирован слабый ключ
            }
        }
    }
}
