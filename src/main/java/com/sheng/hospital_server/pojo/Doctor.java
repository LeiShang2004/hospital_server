package com.sheng.hospital_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private String name;
    private Integer gender;
    private String genderName;
    private Integer departmentId;
    private String departmentName;
}