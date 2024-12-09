package com.sheng.hospital_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private Integer appointmentId;
    private Integer doctorId;
    private Integer patientId;
    private Integer userId;
    private Date appointmentDate;
    private Integer appointmentTime;
    private String appointmentTimeInfo;
    private Integer status;
    private String information;
    private Date createdTime;
}
