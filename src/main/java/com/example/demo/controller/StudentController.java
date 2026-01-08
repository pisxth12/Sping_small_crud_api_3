package com.example.demo.controller;

import com.example.demo.apiResponse.ApiResponse;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {


    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ApiResponse<Student> create(@RequestBody Student s){
        if(s.getActive() == null){
            s.setActive(true);
        }
        Student saved = studentRepository.save(s);

        return new ApiResponse<>(true, "Student Create success", saved);
    }


    @GetMapping
    public ApiResponse<List<Student>> getALL(){
        List<Student> list = studentRepository.findAll();
        return new ApiResponse<>(true, "Get all student success", list);
    }

    @GetMapping("/{id}")
    public ApiResponse<Student> getById(@PathVariable Long id){
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student with id: "+id+"Not Founnd"));
        return new ApiResponse<>(true, "Get Student success", student);
    }

    @PutMapping("/{id}")
    public ApiResponse<Student> update(@PathVariable Long id, @RequestBody Student s){
        Student exist = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student not found"));
        exist.setName(s.getName());
        exist.setAge(s.getAge());
        Student updated = studentRepository.save(exist);
        return new ApiResponse<>(true , "Student update successfully", updated);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id){
        studentRepository.deleteById(id);
        return new ApiResponse<>(true,"Delete success", null);
    }


    @PatchMapping("/{id}/toggle-status")
    public ApiResponse<Student> toggleStatus(@PathVariable Long id){
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        s.setActive(!s.getActive());

        Student updated = studentRepository.save(s);
        String status = updated.getActive() ? "Active" : "Inactive";
        return new ApiResponse<>(true, "Student status changed to "+status ,  updated);
    }








}
