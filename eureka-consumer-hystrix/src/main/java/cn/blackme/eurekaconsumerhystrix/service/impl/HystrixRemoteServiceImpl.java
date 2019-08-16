package cn.blackme.eurekaconsumerhystrix.service.impl;

import cn.blackme.eurekaconsumerhystrix.service.RemoteService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HystrixRemoteServiceImpl implements RemoteService {

    @Override
    public String home(@RequestParam("name") String name) {
        return "error message!";
    }
}
