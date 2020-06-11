package com.example.messagingstompwebsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMock {
    private static final Logger LOG = LoggerFactory.getLogger(LogMock.class);
    public static void main(String[] args) throws Exception {
        int slowCount = 3;
        int fastCount = 3;
        while (true) {
            for (int i = 0; i < slowCount; i++) {
                LOG.debug("abc 123");
                Thread.sleep(10000);
            }
            for (int i = 0; i < fastCount; i++) {
                LOG.warn("cde 456");
                Thread.sleep(5000);
            }
            for (int i = 0; i < slowCount; i++) {
                LOG.warn("obj 789");
                Thread.sleep(8000);
            }
        }
    }
}
