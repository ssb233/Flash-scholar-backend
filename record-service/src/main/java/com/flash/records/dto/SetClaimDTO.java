package com.flash.records.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetClaimDTO {
    private Integer uid;
    private Integer documentId;
    private Integer ans;
}
