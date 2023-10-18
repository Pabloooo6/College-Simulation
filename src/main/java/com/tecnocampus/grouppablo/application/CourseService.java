package com.tecnocampus.grouppablo.application;

import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import com.tecnocampus.grouppablo.application.exception.CourseNotFound;
import com.tecnocampus.grouppablo.application.exception.CourseAlreadyExists;
import com.tecnocampus.grouppablo.domain.Course;
import com.tecnocampus.grouppablo.persistence.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
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
}