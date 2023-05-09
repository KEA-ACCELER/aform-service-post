package com.aform.post.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aform.post.config.BearerAuthFeignConfig;
import com.aform.post.config.MyRequestInterceptor;
import com.aform.post.domain.post.PostRepository;
import com.aform.post.feign.AuthenticationServiceFromUser;
import com.aform.post.web.dto.UserDto;
import com.aform.post.web.dto.UserDto.GetUserResponseDto;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    
    @Autowired
    private static PostRepository postRepository;

    @Autowired
    AuthenticationServiceFromUser authenticationServiceFromUser;
    

    @Transactional
    public Optional<GetUserResponseDto> getUserInfo(HttpServletRequest request){
        //MyRequestInterceptor myRequestInterceptor = new MyRequestInterceptor();
        //myRequestI.setJwt(request.getHeader("authorization"));
        // BearerAuthFeignConfig.setJwt(request.getHeader("authorization"));
        log.info("getUserInfo : " + request.getHeader("authorization"));
        
        return authenticationServiceFromUser.ifUserLogin(request.getHeader("authorization"));
    }

    @Transactional
    public String test(){
            return authenticationServiceFromUser.test();
    }
}
