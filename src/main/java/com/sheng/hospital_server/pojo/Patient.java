package com.sheng.hospital_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private Integer patientId;
    private String name;
    private String gender;
    private java.sql.Date birthDate;
    private String phone;
    private String information;
    private String cleartextId;
    private String plaintextId;
    private String hashedId;
}
