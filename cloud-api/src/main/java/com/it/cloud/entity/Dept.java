package com.it.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//全参构造函数
@AllArgsConstructor
//空参构造函数
@NoArgsConstructor
@Data
//链式风格编程
@Accessors(chain = true)
public class Dept implements Serializable {

    /**
     * 主键
     */
    private Long deptno;

    /**
     * 部门
     */
    private String dname;

    /**
     * 每个部门连接的数据库
     */
    private String dbSource;

}
