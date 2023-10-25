package com.tecnocampus.grouppablo.api;

import com.tecnocampus.grouppablo.application.CourseService;
import com.tecnocampus.grouppablo.application.dto.CategoryDTO;
import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import com.tecnocampus.grouppablo.application.dto.UserDTO;
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

    @GetMapping("/courses/searchByTitleOrDescription")
    public List<CourseDTO> getCoursesTitleOrDescription(@RequestParam String titleOrDescription) {
        return courseService.getCoursesTitleOrDescription(titleOrDescription);
    }

    @PutMapping("/courses/{id}")
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable String id){
        return courseService.updateCourse(courseDTO, id);
    }

    @PatchMapping("/courses/{id}/price")
    public CourseDTO updatePrice(@RequestBody CourseDTO courseDTO, @PathVariable String id){
        return courseService.updatePrice(courseDTO, id);
    }

    @PatchMapping("/courses/{id}/availability")
    public CourseDTO updateAvailability(@RequestBody CourseDTO courseDTO, @PathVariable String id){
        return courseService.updateAvailability(courseDTO, id);
    }

    @GetMapping("/users/{username}")
    public UserDTO getUser(@PathVariable String username){return courseService.getUser(username);}

    @GetMapping("/category")
    public List<CategoryDTO> getCategories(){return courseService.getAllCategories();}

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO addCategory(@RequestBody @Valid CategoryDTO categoryDTO){return courseService.addCategory(categoryDTO);}

    @DeleteMapping("/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable String category){courseService.deleteCategory(category);}

    @GetMapping("/courses/searchByCategoryOrLanguage")
    public List<CourseDTO> getCoursesCategoryOrLanguage(@RequestParam List<String> categoryOrLanguage){
        return courseService.getCoursesCategoryOrLanguage(categoryOrLanguage);
    }

    @GetMapping("/courses/searchByTeacher/{teacherName}")
    public List<CourseDTO> getCoursesByTeacher(@PathVariable String teacherName){
        return courseService.getCoursesByTeacher(teacherName);
    }
}