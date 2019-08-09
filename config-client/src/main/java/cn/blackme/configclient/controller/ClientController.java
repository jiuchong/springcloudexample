package cn.blackme.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Value("${config.name}")
    private String val;

    @GetMapping("/hello")
    public String hello() {
        return "config name is:" + this.val;
    }

}
