package ru.tishin.cloud.front;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.tishin.cloud.common.ProductDto;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class FrontApp {

    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(FrontApp.class, args);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/v1/products")
    public List<ProductDto> getData() {
        ResponseEntity<List<ProductDto>> responseEntity = restTemplate.exchange("http://product-service/api/v1/products",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDto>>() {
                        });
        return responseEntity.getBody();
    }
}
