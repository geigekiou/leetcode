package com.logtest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Test {
    public static final Logger LOGGER = LoggerFactory.getLogger("Test.class");
    public static void main(String[] args) {
        try{
        LOGGER.debug("main start!");
        LOGGER.info("opeartion start!\n");
            LOGGER.trace("123");
    }
        catch(Exception e){
            e.printStackTrace();
            LOGGER.error("error: " + e);
        }
    }
}
