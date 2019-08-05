package com.it.cloud.service;

import com.it.cloud.entity.Dept;

import java.util.List;

public interface DeptService {

    boolean addDept(Dept dept);
    Dept    findById(Long id);
    List<Dept> findAll();
}
