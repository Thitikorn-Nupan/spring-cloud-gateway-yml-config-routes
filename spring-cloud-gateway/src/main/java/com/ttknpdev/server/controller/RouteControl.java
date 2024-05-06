package com.ttknpdev.server.controller;

import com.ttknpdev.server.logging.LogBack;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/spring-cloud")
public class RouteControl {

    public RouteControl() {
        LogBack.setLog(RouteControl.class);
    }

    @GetMapping(value = "/")
    public ResponseEntity<String> testRoute() {
        LogBack.log.info("you requested http://localhost:8888/spring-cloud/");
        return ResponseEntity.ok("Hello World");
    }

}
