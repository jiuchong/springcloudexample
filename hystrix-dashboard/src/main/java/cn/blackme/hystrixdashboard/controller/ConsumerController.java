package cn.blackme.hystrixdashboard.controller;

import cn.blackme.hystrixdashboard.service.RemoteService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    RemoteService remoteService;

    // 需要使用HystrixCommand才能看到图表
    @HystrixCommand
    @GetMapping("/hello/{name}")
    public String home(@PathVariable("name") String name) {
        return remoteService.home(name);
    }

}
