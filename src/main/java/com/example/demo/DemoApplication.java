package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.example"})
@MapperScan({"com.example.dao"})
@EnableDiscoveryClient
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@FeignClient(name = "service-provider")
	public interface EchoService {
		@GetMapping(value = "/echo/{str}")
		String echo(@PathVariable("str") String str);
	}

	@RestController
    class EchoController {
        @GetMapping(value = "/echo/{string}")
        public String echo(@PathVariable String string) {
            return string;
        }

		@Autowired
		private RestTemplate restTemplate;
		@Autowired
		private EchoService echoService;

		@GetMapping(value = "/echo-rest/{str}")
		public String rest(@PathVariable String str) {
			return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
		}
		@GetMapping(value = "/echo-feign/{str}")
		public String feign(@PathVariable String str) {
			return echoService.echo(str);
		}
    }
}
