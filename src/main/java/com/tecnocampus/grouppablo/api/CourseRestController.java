package com.tecnocampus.grouppablo.api;

import com.tecnocampus.grouppablo.application.CourseService;
import com.tecnocampus.grouppablo.application.dto.CourseDTO;
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

    @GetMapping("/courses/{id}")
    public CourseDTO getCourse(@PathVariable String id) { return courseService.getCourse(id); }

    @PutMapping("/courses/{id}")
    public void updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable String id){
        courseService.updateCourse(courseDTO, id);
    }

    @PatchMapping("/courses/{id}/price")
    public void updatePrice(@RequestBody CourseDTO courseDTO, @PathVariable String id){
        courseService.updatePrice(courseDTO, id);
    }

    @PatchMapping("/courses/{id}/availability")
    public void updateAvailability(@RequestBody CourseDTO courseDTO, @PathVariable String id){
        courseService.updateAvailability(courseDTO, id);
    }
}