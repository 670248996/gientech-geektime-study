package com.gientech.iot.remoteapi;

import com.alibaba.fastjson.JSONObject;
import com.gientech.iot.remoteapi.vo.Response;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class RemoteapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoteapiApplication.class, args);
    }
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 远程url（作业二中的请求路径）
     */
    private final String remoteUrl = "http://127.0.0.1:8822/getCoffeesJson";

    /**
     * 远程请求 获取咖啡信息
     *
     * @return {@code CoffeeVO}
     */
    @RequestMapping(path = "getCoffees1", method = RequestMethod.GET)
    public Response getCoffees1() {
        Map<String, String> header = new HashMap<>(2);
        header.put("Content-Type", "application/json");
        // RestTemplate方式请求
        String resultStr = restTemplate.getForObject(remoteUrl, String.class, header);
        System.out.println("RestTemplate方式\n" + resultStr);
        Response response = JSONObject.parseObject(resultStr, Response.class);
        return Response.success(response);
    }

    /**
     * 远程请求 获取咖啡信息
     *
     * @return {@code CoffeeVO}
     */
    @RequestMapping(path = "getCoffees2", method = RequestMethod.GET)
    public Response getCoffees2() throws IOException {
        // HttpURL方式请求
        StringBuilder builder = new StringBuilder();
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(remoteUrl);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = new ByteInputStream();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(3000);
            inputStream = connection.getInputStream();
            byte[] bytes = new byte[]{8};
            while (inputStream.read(bytes) > 0) {
                builder.append(new String(bytes));
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        System.out.println("HttpURLConnection方式\n" + builder);
        Response response = JSONObject.parseObject(builder.toString(), Response.class);
        return Response.success(response);
    }
}
