package cn.blackme.eurekaconsumerhystrix.controller;

import cn.blackme.eurekaconsumerhystrix.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    RemoteService remoteService;

    @GetMapping("/hello/{name}")
    public String home(@PathVariable("name") String name) {
        return remoteService.home(name);
    }

}
