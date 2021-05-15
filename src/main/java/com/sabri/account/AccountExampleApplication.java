package com.sabri.account;

import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.sabri.account.filter.CorrelationIdInterceptor;

@SpringBootApplication
public class AccountExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountExampleApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate() {
            {
                setInterceptors(Collections.singletonList(new CorrelationIdInterceptor()));
            }
        };
    }
	
	
	@Bean	
    public ModelMapper initModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }
}
