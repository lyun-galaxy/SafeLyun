package com.paly.service;

import com.paly.domain.Student;

public interface AdminUserService extends BaseService<Student> {
    public void importStudentBaseMSG(String path);
}
