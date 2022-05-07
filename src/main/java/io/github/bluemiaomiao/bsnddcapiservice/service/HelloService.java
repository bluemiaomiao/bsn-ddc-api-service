package io.github.bluemiaomiao.bsnddcapiservice.service;

import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface HelloService {
    ResponseEntity<GlobalResponseEntity<String>> pong();
}
