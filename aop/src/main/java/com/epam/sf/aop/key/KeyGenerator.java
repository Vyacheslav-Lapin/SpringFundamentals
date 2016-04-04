package com.epam.sf.aop.key;

import java.util.Random;

public class KeyGenerator {
    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_KEY = 0xACDF03F590AE56L;
    private Random rand = new Random();

    public long getKey() {
        return rand.nextInt(3) == 1 ? WEAK_KEY : STRONG_KEY;
    }
}
