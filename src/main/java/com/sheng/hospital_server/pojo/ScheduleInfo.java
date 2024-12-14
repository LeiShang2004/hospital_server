package com.sheng.hospital_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfo {
    private Integer scheduleId;
    private Integer specializationId;
    private Integer doctorId;
    private String doctorName;
    private String photoUrl;
    private String titleId;
    private java.sql.Date date;
    private Integer time;
    private String timeInfo;
    private Integer availableNumber;
}
