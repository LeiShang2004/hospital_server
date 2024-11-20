package com.sheng.hospital_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private Integer doctorId;
    private String name;
    private String gender;
    private Integer departmentId;
    private String departmentName;
    private Integer titleId;
    private String title;
    private String email;
    private String phone;
    private String photoUrl;
    private String introduction;
}