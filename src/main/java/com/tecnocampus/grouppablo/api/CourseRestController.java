package com.tecnocampus.grouppablo.api;

import com.tecnocampus.grouppablo.application.CourseService;
import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import com.tecnocampus.grouppablo.domain.Course;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO addCourse(@RequestBody @Valid CourseDTO courseDTO){
        return courseService.addCourse(courseDTO);
    }

    @GetMapping("/courses")
    public List<CourseDTO> getCourses(){
        return courseService.getAllCourses();
    }

    @PutMapping("/courses/{id}")
    public Course updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable Long id){
        //return para verlo en el postman que se ha update bien
        return courseService.updateCourse(courseDTO, id);
    }

    @PatchMapping("/courses/{id}/price")
    public void updatePrice(@RequestBody CourseDTO courseDTO, @PathVariable Long id){
        courseService.updatePrice(courseDTO, id);
    }

    @PatchMapping("/courses/{id}/availability")
    public void updateAvailability(@RequestBody CourseDTO courseDTO, @PathVariable Long id){
        courseService.updateAvailability(courseDTO, id);
    }
}