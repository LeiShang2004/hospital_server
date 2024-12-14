package com.sheng.hospital_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Integer scheduleId;
    private Integer specializationId;
    private Integer doctorId;
    private java.sql.Date date;
    private Integer time;
    private Integer availableNumber;
}
