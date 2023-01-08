package com.gientech.iot.springbucks;

import com.gientech.iot.springbucks.listener.ProgressBar;
import com.gientech.iot.springbucks.service.CoffeeService;
import com.gientech.iot.springbucks.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@EnableJpaRepositories
@RestController
public class SpringBucksApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private ProgressBar progressBar;

	/**
	 * 获取咖啡信息 返回json格式
	 *
	 * @return {@code CoffeeVO}
	 */
	@RequestMapping(path = "getCoffeesXml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public Response getCoffeesJson() {
		return Response.success(coffeeService.findAllCoffee());
	}

	/**
	 * 获取咖啡信息 返回xml格式
	 *
	 * @return {@code CoffeeVO}
	 */
	@RequestMapping(path = "getCoffeesJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCoffeesXml() {
		return Response.success(coffeeService.findAllCoffee());
	}

	/**
	 * 获取咖啡信息 返回xml格式
	 *
	 * @return {@code CoffeeVO}
	 */
	@RequestMapping(path = "upload", method = RequestMethod.POST)
	public Response upload(@RequestParam("file") MultipartFile file) throws InterruptedException {
		progressBar.init();
		for (int i = 1; i <= 100; i++) {
			progressBar.printResolvePercentProcess(i, 100);
			Thread.sleep(50);
		}
		System.out.println();
		return Response.success();
	}
}

