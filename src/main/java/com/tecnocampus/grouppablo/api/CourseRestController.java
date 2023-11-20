package com.tecnocampus.grouppablo.api;

import com.tecnocampus.grouppablo.application.CourseService;
import com.tecnocampus.grouppablo.application.dto.*;
import com.tecnocampus.grouppablo.domain.secutiry.Role;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/coursesWithoutLessons")
    public List<CourseDTO> getCoursesUnregistered(){return courseService.getAllCoursesUnregistered();}

    @GetMapping("/courses/{id}")
    public CourseDTO getCourse(@PathVariable String id) { return courseService.getCourse(id); }

    @GetMapping("/courses/searchByTitleOrDescription")
    public List<CourseDTO> getCoursesTitleOrDescription(@RequestParam String titleOrDescription) {
        return courseService.getCoursesTitleOrDescription(titleOrDescription);
    }

    @PutMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable String id){
        return courseService.updateCourse(courseDTO, id);
    }

    @PatchMapping("/courses/{id}/price")
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO updatePrice(@RequestBody CourseDTO courseDTO, @PathVariable String id){
        return courseService.updatePrice(courseDTO, id);
    }

    @PatchMapping("/courses/{id}/availability")
    @ResponseStatus(HttpStatus.OK)
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

    /*@GetMapping("/myCourses")
    public List<CourseDTO> getCoursesByTeacher(Principal principal){
        return this.courseService.getCoursesByTeacher(principal.getName());
        getName() devuelve el ID del usuario que esta login
    }*/

    @GetMapping("/courses/searchByTeacher/{teacherName}")
    public List<CourseDTO> getCoursesByTeacher(@PathVariable String teacherName){
        return courseService.getCoursesByTeacher(teacherName);
    }

    @PostMapping("/courses/{id}/lessons")
    @ResponseStatus(HttpStatus.CREATED)
    public LessonDTO addLesson(@PathVariable String id, @RequestBody @Valid LessonDTO lessonDTO){
        return this.courseService.addLesson(id, lessonDTO);
    }

    @PutMapping("/courses/{id}/lessons")
    @ResponseStatus(HttpStatus.OK)
    public List<LessonDTO> updateOrderOfLessons(@PathVariable String id, @RequestBody List<Integer> newOrder){
        return this.courseService.updateOrderOfLessons(id, newOrder);
    }

    @PutMapping("/users/{id}/courses/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public EnrolDTO updateUserCourses(@PathVariable String id, @PathVariable String courseId){
        return this.courseService.updateUserCourses(id, courseId);
    }

    @GetMapping("/users/{id}/courses/{courseId}")
    public EnrolDTO getCourseByStudent(@PathVariable String id, @PathVariable String courseId){
        return this.courseService.getCourseByStudent(id, courseId);
    }

    @PutMapping("/users/{id}/courses/{courseId}/lessons/{lesson}")
    @ResponseStatus(HttpStatus.OK)
    public EnrolDTO updateFinishedLesson(@PathVariable String id, @PathVariable String courseId, @PathVariable int lesson){
        return this.courseService.updateFinishedLesson(id, courseId, lesson);
    }

    @PutMapping("/users/{username}/updateRoles")
    @ResponseStatus(HttpStatus.OK)
    public Set<Role> updateRoles(@PathVariable String username, @RequestBody Set<Role> roles){
        return this.courseService.updateRoles(username, roles);
    }
}