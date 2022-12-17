package com.gientech.com.springbean;

import com.gientech.com.springbean.bean.BeanBean;
import com.gientech.com.springbean.bean.ComponentBean;
import com.gientech.com.springbean.bean.XmlBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:xml-bean.xml") // xml配置文件路径
public class SpringbeanApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbeanApplication.class, args);
    }

    /**
     * xml配置文件方式
     */
    @Autowired
    private XmlBean xmlBean;
    /**
     * component注解方式
     */
    @Autowired
    private ComponentBean componentBean;
    /**
     * bean注解方式
     */
    @Autowired
    private BeanBean beanBean;

    /**
     * bean注解方式
     */
    @Bean
    public BeanBean setBean() {
        return new BeanBean();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(xmlBean.invoke());
        System.out.println(componentBean.invoke());
        System.out.println(beanBean.invoke());
    }
}
