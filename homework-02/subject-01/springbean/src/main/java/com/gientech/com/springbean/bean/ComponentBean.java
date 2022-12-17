package com.gientech.com.springbean.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description: @Component注解方式Bean
 * @author: 王强
 * @dateTime: 2022-12-17 16:13:25
 */
@Data
@Component
public class ComponentBean {

    public String invoke() {
        return "@Component注解方式Bean";
    }
}
