package com.pedroblome.stocks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StockApplication {
    // @Bean
    // public WebClient webClient(WebClient.Builder builder){
    //     return builder
    //         .baseUrl("http://localhost:8089/stocks")         
    //         .defaultHeader( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    //         //.defaultHeader(HttpHeaders.AUTHORIZATION, token)
    //         .build();
    // }
    

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
//     @Configuration
//     @EnableWebSecurity
//     class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//         @Override
//         protected void configure(HttpSecurity http) throws Exception {
//             http.cors().and()
//                     .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//                     .oauth2ResourceServer().jwt();
//         }
// }
}