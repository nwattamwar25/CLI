package com.yash.cmsapi.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class JwtValidationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${jwt.validation.url}")
    private String jwtValidationUrl;

    public boolean validateToken(String token) {
        try {
            String url = jwtValidationUrl + "?token=" + token;
 //           Boolean isValid = restTemplate.getForObject(url, Boolean.class);         
//            System.out.println(isValid);
//            return isValid != null && isValid;
            
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            // Log the error
            return false;
        }
    }
    
//    public boolean validateToken(String token) {
//        try {
//            String url = jwtValidationUrl + "?token=" + token;
//            
//            // Add error handling for null or empty token
//            if (token == null || token.trim().isEmpty()) {
//                //logger.warn("Empty or null token provided");
//                return false;
//            }
//            
//            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Map<String, Object>>() {}
//            );
//            
//            // Check if response body exists
//            if (response.getBody() == null) {
//                //logger.warn("Empty response body received from token validation service");
//                return false;
//            }
//            
//            // First check HTTP status
//            if (!response.getStatusCode().is2xxSuccessful()) {
//                //logger.warn("Token validation failed with status code: {}", response.getStatusCode());
//                return false;
//            }
//            
//            // Check response body for validation result
//            // Assuming the service returns {"valid": true/false} or similar
//            Boolean isValid = (Boolean) response.getBody().get("valid");
//            if (isValid == null) {
//                //logger.warn("Invalid response format from token validation service");
//                return false;
//            }
//            
//            return isValid;
//            
//        } catch (HttpClientErrorException e) {
//            // Handle specific HTTP errors (400, 401, 403, etc.)
//            //logger.warn("Token validation failed with client error: {}", e.getMessage());
//            return false;
//        } catch (HttpServerErrorException e) {
//            // Handle server errors (500, 502, etc.)
//            //logger.error("Token validation service error: {}", e.getMessage());
//            return false;
//        } catch (Exception e) {
//            // Handle any other unexpected errors
//            //logger.error("Unexpected error during token validation: {}", e.getMessage());
//            return false;
//        }
//    }
}