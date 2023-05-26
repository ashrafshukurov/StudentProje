package com.springboot.project.controller;

import com.springboot.project.dto.StudentDto;
import com.springboot.project.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    //Logger log= LoggerFactory.getLogger(StudentController.class); if you do not  write this you should add @SLf4j
    private final StudentService studentService;


    @ApiOperation(value = "adding student", notes = "Add to student to Student List", response =String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 400, message = "you have problem")
    })
    @PostMapping()
    public ResponseEntity<String> insert(@ApiParam(name = "Object", value = "StudentDto") @RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
        return ResponseEntity.ok("Data added successfully");
    }

    @ApiOperation(value = "Get Students list", notes = "All Students will retrieve", response = List.class)
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleting student", notes = "Deleting student based on ID", response = Integer.class)
    public ResponseEntity<String> deleteStudentById(@ApiParam(name = "ID", value = "Student ID", example = "20") @PathVariable int id) {

        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student with id " + id + " is deleted successfully");
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update student", notes = "Update student based on Id", response = Integer.class)
    public ResponseEntity<String> updateStudentById(@ApiParam(name = "ID", value = "Student id", example = "20") @PathVariable int id, @ApiParam(name = "Object", value = "StudentDto") @RequestBody StudentDto studentDto) {
        studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok("Data updated  successfully");
    }

    @ApiOperation(value = "get student", notes = "Getting student based on id", response = Integer.class)
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@ApiParam(name = "Id", value = "Student id", example = "20") @PathVariable int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
}
