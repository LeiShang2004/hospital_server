package com.sheng.hospital_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDateVO {
    private Integer doctorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date date;
}
