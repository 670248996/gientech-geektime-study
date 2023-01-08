package com.gientech.iot.apollo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApolloController {

    @Value("${timeout}")
    private String timeout;

    @RequestMapping(method = RequestMethod.GET, value = "/timeout")
    public String timeout() {
        return timeout;
    }
}
