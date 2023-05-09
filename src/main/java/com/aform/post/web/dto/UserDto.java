package com.aform.post.web.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetUserResponseDto {
        private String userId ;
        private String userPw;
        private String email;
        private String name;
        private String phone;
        private String address;
        private LocalDateTime birth;
        private Boolean gender;

        @Builder
        public GetUserResponseDto(String userId, String userPw, String email, String name, String phone, String address, LocalDateTime birth, Boolean gender){
            this.userId = userId;
            this.userPw = userPw;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.birth = birth;
            this.gender = gender;
            this.name = name;
        }
    }
}
