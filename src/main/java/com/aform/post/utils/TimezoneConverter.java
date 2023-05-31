package com.aform.post.utils;

import java.time.LocalDateTime;

public class TimezoneConverter {
    public static LocalDateTime KST2UTC(LocalDateTime kst){
        return kst.minusHours(9);
    }

    public static LocalDateTime UTC2KST(LocalDateTime utc){
        return utc.plusHours(9);
    }
}
