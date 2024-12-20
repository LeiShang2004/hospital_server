package com.sheng.hospital_server.pojo;

import co.elastic.clients.elasticsearch._types.mapping.FieldType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
/**
 * 医生类
 * 对应ES中的doctor索引
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class DoctorES {
    private Integer id;
    private String introduction;
}
