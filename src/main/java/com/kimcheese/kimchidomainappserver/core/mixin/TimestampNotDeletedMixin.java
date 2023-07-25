package com.kimcheese.kimchidomainappserver.core.mixin;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;

@Getter
@Setter
public class TimestampNotDeletedMixin {
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public void gets(){
        LocalDate.now(ZoneId.of("UTC"));
    }
}
