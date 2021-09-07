package com.andile.student.management.application.service;

import com.andile.student.management.application.exception.ResourceNotFoundException;
import com.andile.student.management.application.model.Student;
import com.andile.student.management.application.model.payloads.request.StudentRequest;
import com.andile.student.management.application.model.payloads.response.MessageResponse;
import com.andile.student.management.application.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
* This class holds the business Logic
**/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public MessageResponse create(StudentRequest studentRequest) {
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAddress(studentRequest.getAddress());
        student.setMobile(studentRequest.getMobile());
        student.setEmail(studentRequest.getEmail());
        student.setFacality(studentRequest.getFacality());

        studentRepository.save(student);
        return new MessageResponse("New Student created successfully");

    }

    @Override
    public Optional<Student> update(Long studentId, StudentRequest studentRequest) throws ResourceNotFoundException {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student != null) {
            throw new ResourceNotFoundException("Student", "id", studentId);
        } else
            student.get().setName(studentRequest.getName());
        student.get().setAddress(studentRequest.getAddress());
        student.get().setMobile(studentRequest.getMobile());
        student.get().setEmail(studentRequest.getEmail());
        student.get().setFacality(studentRequest.getFacality());
        studentRepository.save(student.get());
        return student;
    }

    @Override
    public Student findById(Long studentId) throws ResourceNotFoundException {
        return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(Long studentId) throws ResourceNotFoundException {
        if (studentRepository.getById(studentId).getId()==studentId) {
            studentRepository.deleteById(studentId);
        } else throw new ResourceNotFoundException("Student", "id", studentId);

    }
}
