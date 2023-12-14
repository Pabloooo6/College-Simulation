package com.tecnocampus.grouppablo.api;

import com.tecnocampus.grouppablo.application.CourseService;
import com.tecnocampus.grouppablo.application.dto.*;
import com.tecnocampus.grouppablo.domain.secutiry.Role;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
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

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable String id){return courseService.getUser(id);}

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

    @GetMapping("/users/{id}/courses")
    public List<CourseDTO> getAllCoursesByStudent(@PathVariable String id){
        return this.courseService.getAllCoursesByStudent(id);
    }

    @GetMapping("/users/{id}/finishedCourses")
    public List<CourseDTO> getAllFinishedCoursesByStudent(@PathVariable String id){
        return this.courseService.getAllFinishedCoursesByStudent(id);
    }

    @PostMapping("/users/{id}/courses/{courseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDTO addReview(@PathVariable String id, @PathVariable String courseId, @RequestBody ReviewDTO reviewDTO){
        return this.courseService.addReview(id, courseId, reviewDTO);
    }

    @GetMapping("/coursesWithoutLessons/order")
    public List<CourseDTO> getAllCoursesUnregisteredOrder(@RequestParam String order){
        return courseService.getAllCoursesUnregisteredOrder(order);
    }

    @GetMapping("/coursesWithoutLessons/{id}")
    public CourseDTO getCourseUnregistered(@PathVariable String id){
        return courseService.getCourseUnregistered(id);
    }

    @GetMapping("/users/{id}/courses/{courseId}/allStudents")
    public List<UserDTO> getAllStudentsByCourse(@PathVariable String id, @PathVariable String courseId){
        return this.courseService.getAllStudentsByCourse(id, courseId);
    }

    @GetMapping("/bestTeachers/{num}/{year}")
    public List<UserDTO> getBestsTeachers(@PathVariable int num, @PathVariable int year){
        return this.courseService.getBestsTeachers(num, year);
    }

    @GetMapping("/users/bestsStudents/{num}")
    public List<UserDTO> getBestsStudents(@PathVariable int num){
        return this.courseService.getBestsStudents(num);
    }

    @PostMapping("/users/messages/{receiver}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO sendMessage(Principal principal, @PathVariable String receiver, @RequestBody @Valid MessageDTO messageDTO) {
        return this.courseService.addMessage(principal.getName(), receiver, messageDTO);
    }

    @GetMapping("/users/messages/send")
    public List<MessageDTO> getAllSendMessages(Principal principal) {
        return this.courseService.getAllSendMessages(principal.getName());
    }

    @GetMapping("/users/messages/received")
    public List<MessageDTO> getAllReceivedMessages(Principal principal){
        return this.courseService.getAllReceivedMessages(principal.getName());
    }

    @GetMapping("/users/messages/received/read")
    public List<MessageDTO> getReadOrNotMessages(Principal principal, @RequestParam String read){
        return this.courseService.getReadOrNotMessages(principal.getName(), read);
    }

    @GetMapping("/users/messages/{userId}")
    public List<MessageDTO> getConversation(Principal principal, @PathVariable String userId) {
        return this.courseService.getConversation(principal.getName(), userId);
    }

    @PatchMapping("/users/messages/{messageId}/read")
    @ResponseStatus(HttpStatus.OK)
    public boolean markMessageAsRead(Principal principal, @PathVariable String messageId) {
        return this.courseService.markMessageAsRead(principal.getName(), messageId);
    }
}