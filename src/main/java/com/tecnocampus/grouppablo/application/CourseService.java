package com.tecnocampus.grouppablo.application;

import com.tecnocampus.grouppablo.application.dto.CategoryDTO;
import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import com.tecnocampus.grouppablo.application.dto.UserDTO;
import com.tecnocampus.grouppablo.application.exception.CourseNotFound;
import com.tecnocampus.grouppablo.application.exception.CourseAlreadyExists;
import com.tecnocampus.grouppablo.application.exception.UserNotFound;
import com.tecnocampus.grouppablo.domain.Category;
import com.tecnocampus.grouppablo.domain.Course;
import com.tecnocampus.grouppablo.domain.User;
import com.tecnocampus.grouppablo.persistence.CategoryRepository;
import com.tecnocampus.grouppablo.persistence.CourseRepository;
import com.tecnocampus.grouppablo.persistence.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, CategoryRepository categoryRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public CourseDTO addCourse(@Valid CourseDTO courseDTO) {
        if(courseRepository.findByTitle(courseDTO.getTitle()).isPresent()) throw new CourseAlreadyExists(courseDTO.getTitle());
        Course course = new Course(courseDTO);
        String id = UUID.randomUUID().toString();
        course.setId(id);
        course.setPublication(LocalDate.now());
        courseRepository.save(course);
        courseDTO.setId(id);
        return courseDTO;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findByAvailabilityTrueOrderByTitle().stream().map(CourseDTO::new).collect(Collectors.toList());
    }

    public CourseDTO getCourse(String id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));
        return new CourseDTO(course);
    }

    public List<CourseDTO> getCoursesTitleOrDescription(String search) {
        return courseRepository.findCoursesByTitleOrDescription(search);
    }

    @Transactional
    public CourseDTO updateCourse(CourseDTO courseDTO, String id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));

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
        
        course.setCurrentPrice(courseDTO.getCurrentPrice());
        course.setLastUpdate(LocalDate.now());
        return new CourseDTO(course);
    }

    @Transactional
    public CourseDTO updateAvailability(CourseDTO courseDTO, String id){
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new CourseNotFound(id));

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
}