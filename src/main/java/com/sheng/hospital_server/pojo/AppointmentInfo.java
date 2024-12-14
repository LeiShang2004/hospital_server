package com.sheng.hospital_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentInfo {
    private Integer appointmentId;
    private String doctorName;
    private String userName;
    private String patientName;
    private String specializationName;
    private Date appointmentDate;
    private Integer appointmentTime;
    private String appointmentTimeInfo;
    private Integer status;
    private Integer fee;
    private Date createdTime;
    private Timestamp createdTimeStamp;
}
