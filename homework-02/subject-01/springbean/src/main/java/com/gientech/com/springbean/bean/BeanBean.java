package com.gientech.com.springbean.bean;

import lombok.Data;

/**
 * @description: @Bean注解方式Bean
 * @author: 王强
 * @dateTime: 2022-12-17 16:13:31
 */
@Data
public class BeanBean {

    public String invoke() {
        return "@Bean注解方式Bean";
    }
}
