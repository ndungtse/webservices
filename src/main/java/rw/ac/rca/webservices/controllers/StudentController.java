package rw.ac.rca.webservices.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.webservices.models.Student;
import rw.ac.rca.webservices.repository.StudentRepository;
import rw.ac.rca.webservices.utils.CustomResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    final
    StudentRepository studentRepo;

    public StudentController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping("")
    public String getStudent() {
        return "Hello Student 1234";
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Student>>> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return ResponseEntity.ok(new CustomResponse<>("All Students", students));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Student>> getStudentById(@PathVariable("id") String id) {
        Optional<Student> student = studentRepo.findById(id);
        return student.map(value -> ResponseEntity.ok(new CustomResponse<>("Operation successful", value))).orElseGet(() -> ResponseEntity.ok(new CustomResponse<>("Student not found")));
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Student>> addStudent(@RequestBody Student student) {
        Student newStudent = studentRepo.save(student);
        return ResponseEntity.ok(new CustomResponse<>("Operation successful", newStudent));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Student>> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        Optional<Student> studentOptional = studentRepo.findById(id);
        if (studentOptional.isPresent()) {
            Student newStudent = studentOptional.get();
            newStudent.setFirstName(student.getFirstName());
            newStudent.setLastName(student.getLastName());
            newStudent.setAge(student.getAge());
            newStudent.setGender(student.getGender());
            return ResponseEntity.ok(new CustomResponse<>("Operation successful", studentRepo.save(newStudent)));
        } else {
            return ResponseEntity.ok(new CustomResponse<>("Student not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<String>> deleteStudent(@PathVariable("id") String id) {
        Optional<Student> studentOptional = studentRepo.findById(id);
        if (studentOptional.isPresent()) {
            studentRepo.delete(studentOptional.get());
            return ResponseEntity.ok(new CustomResponse<>("Operation successful"));
        } else {
            return ResponseEntity.ok(new CustomResponse<>("Student not found"));
        }
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<CustomResponse<List<Student>>> getStudentByAge(@PathVariable("age") int age) {
        List<Student> students = studentRepo.findByAge(age);
        return ResponseEntity.ok(new CustomResponse<>("Operation successful", students));
    }

    @GetMapping("/sorted/firstName/{lastName}")
    public ResponseEntity<CustomResponse<List<Student>>> getSortedByLastName(@PathVariable("lastName") String lastName){
        List<Student> students = studentRepo.findSortedLname(lastName);
        return ResponseEntity.ok(new CustomResponse<>(students));
    }
    @GetMapping("/sorted/age")
    public ResponseEntity<CustomResponse<List<Student>>> getSortedByAge(){
        List<Student> students = studentRepo.findByAgeSorted();
        return ResponseEntity.ok(new CustomResponse<>("Students ordered by age", students));
    }

}