package com.kimcheese.kimchidomainappserver.core.mixin;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeletedTimeMixin {
    private LocalDate deletedAt;
    private Boolean isDeleted;
}
