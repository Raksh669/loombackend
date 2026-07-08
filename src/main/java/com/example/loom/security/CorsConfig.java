package com.example.loom.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5502",
                                        "http://localhost:5500",
                                        "http://127.0.0.1",
                                    "http://127.0.0.1:5502"));
        config.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); 
        UrlBasedCorsConfigurationSource urlData=new UrlBasedCorsConfigurationSource();
        urlData.registerCorsConfiguration("/**",config);
        return urlData;                       
    }

    
}
