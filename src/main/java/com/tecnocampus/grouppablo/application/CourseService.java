package com.tecnocampus.grouppablo.application;

import com.tecnocampus.grouppablo.application.dto.*;
import com.tecnocampus.grouppablo.application.exception.*;
import com.tecnocampus.grouppablo.domain.*;
import com.tecnocampus.grouppablo.domain.secutiry.Role;
import com.tecnocampus.grouppablo.domain.secutiry.UserSecurity;
import com.tecnocampus.grouppablo.persistence.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.stream.IntStream;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LessonRepository lessonRepository;
    private final EnrolRepository enrolRepository;
    private final UserSecurityRepository userSecurityRepository;
    private final RoleRepository roleRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, CategoryRepository categoryRepository,
                         EnrolRepository enrolRepository, LessonRepository lessonRepository, UserSecurityRepository userSecurityRepository,
                         RoleRepository roleRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.lessonRepository = lessonRepository;
        this.enrolRepository = enrolRepository;
        this.userSecurityRepository = userSecurityRepository;
        this.roleRepository = roleRepository;
    }

    private List<CourseDTO> orderLessons(List<CourseDTO> courses){
        courses.forEach(courseDTO -> {
            List<LessonDTO> sortedLessons = courseDTO.getLessonsDTO().stream()
                    .sorted(Comparator.comparingInt(LessonDTO::getNumOrder))
                    .collect(Collectors.toList());
            courseDTO.setLessonsDTO(sortedLessons);
        });
        return courses;
    }

    public CourseDTO addCourse(@Valid CourseDTO courseDTO) {
        if(courseRepository.existsById(courseDTO.getTitle())) throw new CourseAlreadyExists(courseDTO.getTitle());
        Course course = new Course(courseDTO);

        String id = UUID.randomUUID().toString();
        course.setId(id);
        course.setTeacher(userRepository.findById(courseDTO.getTeacher().getUsername())
                .orElseThrow(() -> new UserNotFound(courseDTO.getTeacher().getUsername())));
        course.setPublication(LocalDate.now());

        courseRepository.save(course);
        courseDTO.setId(id);
        return courseDTO;
    }

    public List<CourseDTO> getAllCourses() {
        return orderLessons(courseRepository.findByAvailabilityTrueOrderByTitle().stream().map(CourseDTO::new).collect(Collectors.toList()));
    }

    public List<CourseDTO> getAllCoursesUnregistered(){
        return courseRepository.findAllCoursesUnregistered();
    }

    public CourseDTO getCourse(String id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));

        CourseDTO courseDTO = new CourseDTO(course);
        courseDTO.getLessonsDTO().sort(Comparator.comparingInt(LessonDTO::getNumOrder));
        return courseDTO;
    }

    public List<CourseDTO> getCoursesTitleOrDescription(String search) {
        return courseRepository.findCoursesByTitleOrDescription(search);
    }

    @Transactional
    public CourseDTO updateCourse(CourseDTO courseDTO, String id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));

        if(course.getAvailability()) throw new NotModifiable(id);
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setImageUrl(courseDTO.getImageUrl());
        course.setLastUpdate(LocalDate.now());
        return new CourseDTO(course);
    }

    @Transactional
    public CourseDTO updatePrice(CourseDTO courseDTO, String id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));

        if(course.getAvailability()) throw new NotModifiable(id);
        course.setCurrentPrice(courseDTO.getCurrentPrice());
        course.setLastUpdate(LocalDate.now());
        return new CourseDTO(course);
    }

    @Transactional
    public CourseDTO updateAvailability(CourseDTO courseDTO, String id){
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new CourseNotFound(id));

        if(course.getLessons().isEmpty()) throw new NoLessonsInCourse(id);
        course.setAvailability(courseDTO.getAvailability());
        course.setLastUpdate(LocalDate.now());
        return new CourseDTO(course);
    }

    public UserDTO getUser(String username){
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UserNotFound(username));
        return new UserDTO(user);
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> retCategories = new ArrayList<CategoryDTO>();
        for(Category c : categories){
            retCategories.add(new CategoryDTO(c));
        }
        return retCategories;
    }

    public CategoryDTO addCategory(@Valid CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO);
        categoryRepository.save(category);
        return categoryDTO;
    }

    public void deleteCategory(String category){
        categoryRepository.deleteById(category);
    }

    public List<CourseDTO> getCoursesCategoryOrLanguage(List<String> search){
        return courseRepository.findCoursesByCategoryOrLanguage(search);
    }

    public List<CourseDTO> getCoursesByTeacher(String teacherName){
        return courseRepository.findByTeacher(teacherName);
    }

    @Transactional
    public LessonDTO addLesson(String id, @Valid LessonDTO lessonDTO){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));

        if(course.getAvailability()) throw new NotModifiable(id);
        if(lessonRepository.existsById(lessonDTO.getTitle())) throw new LessonAlreadyExists(lessonDTO.getTitle());
        if(course.getLessons().size() >= lessonDTO.getNumOrder()) throw new RuntimeException("The order of the lesson is not correct.");

        Lesson newLesson = new Lesson(lessonDTO);
        lessonRepository.save(newLesson);

        List<Lesson> courseLessons = course.getLessons();
        courseLessons.add(newLesson);
        course.setLessons(courseLessons);
        course.setLastUpdate(LocalDate.now());
        return lessonDTO;
    }

    @Transactional
    public List<LessonDTO> updateOrderOfLessons(String id, List<Integer> newOrder) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));

        if(newOrder.size() != course.getLessons().size())
            throw new RuntimeException("List of numbers is not of the correct size.");

        IntStream.range(0, course.getLessons().size())
                .forEach(index -> course.getLessons().get(index).setNumOrder(newOrder.get(index)));

        List<Lesson> sortedLessons = course.getLessons().stream()
                .sorted(Comparator.comparingInt(Lesson::getNumOrder))
                .collect(Collectors.toList());

        course.setLessons(sortedLessons);
        courseRepository.save(course);

        return sortedLessons.stream()
                .map(LessonDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public EnrolDTO updateUserCourses(String userId, String courseId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFound(courseId));

        Enrol enrol = new Enrol(user, course);
        if(enrolRepository.existsById(enrol.getEnrolId())) throw new EnrolAlreadyExists(userId, courseId);
        enrolRepository.save(enrol);

        List<Lesson> lessons = course.getLessons();
        lessons.sort(Comparator.comparingInt(Lesson::getNumOrder));
        enrol.getCourse().setLessons(lessons);
        return new EnrolDTO(enrol);
    }

    public EnrolDTO getCourseByStudent(String userId, String courseId){
        Enrol enrol = enrolRepository.findById(new EnrolId(userId, courseId))
                .orElseThrow(() -> new EnrolNotFound(userId, courseId));

        List<Lesson> sortedLessons = enrol.getCourse().getLessons().stream()
                .sorted(Comparator.comparingInt(Lesson::getNumOrder))
                .toList();

        enrol.getCourse().setLessons(sortedLessons);
        return EnrolDTO.enrolDTODetails(enrol);
    }

    @Transactional
    public EnrolDTO updateFinishedLesson(String userId, String courseId, int lessonOrder){
        Enrol enrol = enrolRepository.findById(new EnrolId(userId, courseId))
                .orElseThrow(() -> new EnrolNotFound(userId, courseId));

        List<Lesson> courseLessons = enrol.getCourse().getLessons();
        if (lessonOrder <= 0 || lessonOrder > courseLessons.size())
            throw new RuntimeException("The lesson with index " + lessonOrder + " does not exist.");

        List<Lesson> finishedLessons = enrol.getFinishedLessons();
        courseLessons.sort(Comparator.comparingInt(Lesson::getNumOrder));
        Lesson currentLesson = courseLessons.get(lessonOrder - 1);

        if (!finishedLessons.isEmpty()) {
            if (finishedLessons.contains(currentLesson))
                throw new RuntimeException("You have already finished that lesson.");
            if (lessonOrder - 1 != finishedLessons.size())
                throw new LessonsNotFinished(courseLessons.get(lessonOrder - 2).getTitle());
        } else if (lessonOrder > 1) {
            throw new LessonsNotFinished(courseLessons.get(lessonOrder - 2).getTitle());
        }

        finishedLessons.add(currentLesson);
        enrol.setFinishedLessons(finishedLessons);
        enrol.getCourse().setLessons(courseLessons);
        return new EnrolDTO(enrol);
    }

    @Transactional
    public Set<Role> updateRoles(String username, Set<Role> roles){
        UserSecurity userSecurity = userSecurityRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFound(username));

        List<Role> newRoles = new ArrayList<>();
        for(Role r : roles){
            newRoles.add(roleRepository.findByName(r.getName()));
        }

        Set<Role> updatedRoles = userSecurity.getRoles();
        for(Role r : updatedRoles) {
            newRoles.remove(r);
        }

        if(!newRoles.isEmpty())
            updatedRoles.addAll(newRoles);

        userSecurity.setRoles(updatedRoles);
        return updatedRoles;
    }
}