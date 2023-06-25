package com.springboot.project.service.imp;

import com.springboot.project.dto.request.StudentRequest;
import com.springboot.project.dto.response.GroupResponse;
import com.springboot.project.dto.response.StudentResponse;
import com.springboot.project.dto.response.TeacherResponse;
import com.springboot.project.exception.NoSuchElementException;
import com.springboot.project.exception.StudentAlreadyExistsException;
import com.springboot.project.mapper.GroupMapper;
import com.springboot.project.mapper.StudentMapper;
import com.springboot.project.model.Student;
import com.springboot.project.model.StudentGroup;
import com.springboot.project.model.StudentInfo;
import com.springboot.project.model.Teacher;
import com.springboot.project.repository.GroupRepository;
import com.springboot.project.repository.StudentRepository;
import com.springboot.project.repository.TeacherRepository;
import com.springboot.project.service.StudentService;
import com.springboot.project.validation.AgeChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;
    private final AgeChecker ageChecker;
    private final StudentMapper studentMapper;
    private final TeacherRepository teacherRepository;
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;


    @Override
    public StudentResponse addStudent(StudentRequest studentRequest) {
        try {
            log.info("Student added successfully {}", studentRequest);
            Student student = studentMapper.studentRequestToStudent(studentRequest);

            final StudentGroup studentGroup = groupMapper.requestToGroup(studentRequest);
            student.setGroup(studentGroup);


            final StudentInfo studentInfo = new StudentInfo();
            studentInfo.setAddress(studentRequest.getAddress());
            studentInfo.setPhoneNumber(studentRequest.getPhoneNumber());
            student.setStudentInfo(studentInfo);

            final Teacher teacher = new Teacher();
            teacher.setTeacherName(studentRequest.getTeacherName());
            teacher.setTeacherAge(studentRequest.getTeacherAge());
            teacher.setTeacherSurname(studentRequest.getTeacherSurname());
            teacher.addStudent(student);
            teacherRepository.save(teacher);

            studentRepository.save(student);

            return studentMapper.studentToStudentResponse(student);


        } catch (Exception e) {
            throw new StudentAlreadyExistsException(e.getMessage());
        }
    }

    @Override
    public List<GroupResponse> getAllStudents() {
        try {
         final List<StudentGroup> groups=groupRepository.findAll();
         final List<GroupResponse> groupResponses=new ArrayList<>();
         for (var group:groups){
            GroupResponse groupResponse=new GroupResponse();
            groupResponse.setGroupName(group.getGroupName());
            groupResponse.setGroupNumber(group.getGroupNumber());
            groupResponse.setId(group.getId());

            for (var student:group.getStudents()){
                StudentResponse studentResponse=new StudentResponse();
                studentResponse.setName(student.getName());
                studentResponse.setGrade(student.getGrade());
                studentResponse.setId(student.getId());
                studentResponse.setAge(student.getAge());
                groupResponse.getStudentResponses().add(studentResponse);


                for (var teacher:student.getTeachers()){
                    TeacherResponse teacherResponse=new TeacherResponse();
                    teacherResponse.setTeacherName(teacher.getTeacherName());
                    teacherResponse.setTeacherAge(teacher.getTeacherAge());
                    teacherResponse.setTeacherSurname(teacher.getTeacherSurname());
                    teacherResponse.setId(teacher.getId());
                    studentResponse.getTeacherResponses().add(teacherResponse);
                }
            }
           groupResponses.add(groupResponse);
         }
         log.info("Students got successfully");
         return groupResponses;

        } catch (Exception e) {
           throw new NoSuchElementException(e.getMessage());
        }

    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such element exception"));
        return studentMapper.studentToStudentResponse(student);
    }

    @Override
    public void updateStudent(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new NoSuchElementException("There is no such id in the Db Table");
        } else {
            student.setAge(studentRequest.getAge());
            student.setEmail(studentRequest.getEmail());
            studentRepository.save(student);
        }

    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Students deleted successfully by id:{}", id);
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no such id in the Db Table"));
    }


}
