package com.kimcheese.kimchidomainappserver.core.mixin;

import com.google.cloud.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;


@Getter
@Setter
public class TimestampMixin {
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private Boolean isDeleted;

//    public void gets(){
//        LocalDate.now(ZoneId.of("UTC"));
//    }
}
