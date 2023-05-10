package com.aform.post.feign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.aform.post.web.dto.UserDto.GetUserResponseDto;


@FeignClient(
    name = "authentication-service", 
    url = "http://172.17.0.1:8080/app/user"
)
public interface AuthenticationServiceFromUser {

    @GetMapping(path="/test")
    public String test();


    @GetMapping(path="/info")
    public Optional<GetUserResponseDto> ifUserLogin(@RequestHeader("authorization") String jwt);
}
