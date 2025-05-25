package com.flash.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yury
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonNetDTO {
    private String id;
    private String name;
    private Integer symbolSize;
    private Integer x;
    private Integer y;
    private Integer value;
    private Integer category;
}
