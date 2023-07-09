package com.springboot.project.controller;


import com.springboot.project.dto.request.StudentRequest;
import com.springboot.project.dto.response.GroupResponse;
import com.springboot.project.dto.response.StudentResponse;
import com.springboot.project.service.StudentService;
import com.springboot.project.util.FileUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students1")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final FileUtil fileUtil;
    @GetMapping("/home")
    public String getHome(){
        return "Home";
    }


    @ApiOperation(value = "adding student", notes = "Add to student to Student List", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 400, message = "you have problem")
    })
    @PostMapping()
    public ResponseEntity<String> insert(@ApiParam(name = "Object", value = "StudentDto") @RequestBody StudentRequest studentRequest) {
        studentService.addStudent(studentRequest);
        return ResponseEntity.ok("Data added successfully");
    }

    @ApiOperation(value = "Get Students list", notes = "All Students will retrieve", response = List.class)
    @GetMapping("/students")
    public ResponseEntity<List<GroupResponse>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleting student", notes = "Deleting student based on ID", response = Integer.class)
    public ResponseEntity<String> deleteStudentById(@ApiParam(name = "ID", value = "Student ID", example = "20") @PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student with id " + id + " is deleted successfully");
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update student", notes = "Update student based on Id", response = Integer.class)
    public ResponseEntity<String> updateStudentById(@ApiParam(name = "ID", value = "Student id", example = "20") @PathVariable Long id, @ApiParam(name = "Object", value = "StudentDto") @RequestBody StudentRequest studentRequest) {
        studentService.updateStudent(id, studentRequest);
        return ResponseEntity.ok("Data updated  successfully");
    }

    @ApiOperation(value = "get student", notes = "Getting student based on id", response = Integer.class)
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@ApiParam(name = "Id", value = "Student id", example = "20") @PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

//    @PostMapping("/upload")
//    public void upLoadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        log.info("size:{}", file.getSize());
//        log.info("name:{}", file.getOriginalFilename());
////        file.transferTo(path);
//        Files.copy(file.getInputStream(), this.path.resolve(file.getOriginalFilename()));
//    }
//    @GetMapping("/download")
//    public ResponseEntity<Resource> download(@RequestParam String fileName){
//       Resource resource=fileUtil.load(fileName,this.path);
//       return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + fileName + "\"").body(resource);
//    }

}
