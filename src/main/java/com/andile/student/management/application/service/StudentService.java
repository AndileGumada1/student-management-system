package com.andile.student.management.application.service;
import com.andile.student.management.application.exception.ResourceNotFoundException;
import com.andile.student.management.application.model.Student;
import com.andile.student.management.application.model.payloads.request.StudentRequest;
import com.andile.student.management.application.model.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface StudentService {
    MessageResponse create(StudentRequest studentRequest);
    Optional<Student> update(Long studentId, StudentRequest studentRequest) throws ResourceNotFoundException;
    void delete(Long studentId) throws ResourceNotFoundException;
    Student findById(Long studentId) throws ResourceNotFoundException;
    List<Student> findAll();
}
