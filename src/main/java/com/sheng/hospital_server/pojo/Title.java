package com.sheng.hospital_server.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Title {
    private Integer titleId;
    private String name;
    private Integer fee;
}
