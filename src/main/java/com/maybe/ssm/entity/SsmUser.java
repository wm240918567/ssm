package com.maybe.ssm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SsmUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private Integer age;
    /*
    JDK8 新日期API当使用jackson解析json格式数据时，传入时需要序列化转换
    格式必须严格按照pattern中规定的写。个位数的月，要补0
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
     */
    //fastjson解析支持JDK8 新的日期API
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate birthday;

}
