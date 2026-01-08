package com.example.demo.controller;

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

    //Create
    @PostMapping
    public Student create(@RequestBody Student s){
        return studentRepository.save(s);
    }

    @GetMapping
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Student> getByID(@PathVariable Long id){
        return studentRepository.findById(id);
    }

    //Update
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student s){
        s.setId(id);
        return studentRepository.save(s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        studentRepository.deleteById(id);
    }



}
