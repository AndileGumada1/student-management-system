package com.andile.student.management.application.controller;

import com.andile.student.management.application.exception.ResourceNotFoundException;
import com.andile.student.management.application.model.Student;
import com.andile.student.management.application.model.payloads.request.StudentRequest;
import com.andile.student.management.application.model.payloads.response.MessageResponse;
import com.andile.student.management.application.service.StudentService;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Class is used to expose the End-points of the application
 * 
**/
@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."),
}
)
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * End-point used to retrieve a list of all the students
     * @return ResponseEntity
    **/
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents () {
        List<Student> studentList = studentService.findAll();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }
    /**
     * End-point used to create a new students
     * @return ResponseEntity
     **/
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createNewStudent( @RequestBody StudentRequest request) {
        MessageResponse newEmployee = studentService.create(request);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    /**
     * End-point used to retrieve a student based on the ID
     * @return ResponseEntity
     * @throws ResourceNotFoundException when the student record is not found
     **/
    @GetMapping("/find/{id}")
    public ResponseEntity<Student> getStudentById (@PathVariable("id") Long id) throws ResourceNotFoundException {
        Student student = studentService.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    /**
     * End-point used to delete a student based on the ID
     * @return ResponseEntity
     * @throws ResourceNotFoundException when the student record is not found
     **/
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        studentService.delete(id);
        return new ResponseEntity<String>("Student is deleted successfully.!", HttpStatus.OK);
    }
    /**
     * End-point used to update a student based on the ID
     * @return ResponseEntity
     * @throws ResourceNotFoundException when the student record is not found
     **/
    @PutMapping("/update/{id}")
    public ResponseEntity<MessageResponse> update( @PathVariable Long id, @RequestBody StudentRequest request) throws ResourceNotFoundException {
        studentService.update(id, request);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
