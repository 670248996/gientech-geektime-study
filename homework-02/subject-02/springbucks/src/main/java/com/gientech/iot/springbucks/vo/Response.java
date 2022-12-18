package com.gientech.iot.springbucks.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Response {

    private String code;

    private String message;

    private Object data;

    public static Response success() {
        Response response = new Response();
        response.setCode("200");
        response.setMessage("success");
        return response;
    }

    public static Response success(Object data) {
        Response response = new Response();
        response.setCode("200");
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    public static Response fail() {
        Response response = new Response();
        response.setCode("500");
        response.setMessage("fail");
        return response;
    }
}
