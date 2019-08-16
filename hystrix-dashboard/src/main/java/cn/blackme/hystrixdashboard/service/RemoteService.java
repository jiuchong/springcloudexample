package cn.blackme.hystrixdashboard.service;

import cn.blackme.hystrixdashboard.service.impl.HystrixRemoteServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "eureka-client", fallback = HystrixRemoteServiceImpl.class)
public interface RemoteService {

    @RequestMapping("/hello")
    public String home(@RequestParam(value = "name") String name);

}
