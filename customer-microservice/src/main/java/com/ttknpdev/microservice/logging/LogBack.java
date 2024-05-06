package com.ttknpdev.microservice.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBack {

    public static Logger log;

    public static void setLog(Class c) {
        log = LoggerFactory.getLogger(c);
    }
}
