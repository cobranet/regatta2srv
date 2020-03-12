package com.cobranet.regatta;


import com.cobranet.regatta.oauth.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class RegattaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegattaApplication.class, args);
	}

}
