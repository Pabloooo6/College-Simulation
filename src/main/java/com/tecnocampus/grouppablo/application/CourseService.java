package com.tecnocampus.grouppablo.application;

import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import com.tecnocampus.grouppablo.application.exception.CourseNotFound;
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
        Course course = new Course(courseDTO);
        UUID id = UUID.randomUUID();
        course.setId(id);
        courseRepository.save(course);
        courseDTO.setId(id);
        return courseDTO;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findByAvailabilityTrueOrderByTitle().stream().map(CourseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateCourse(CourseDTO courseDTO, UUID id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));

        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setImageUrl(courseDTO.getImageUrl());
        course.setLastUpdate(LocalDate.now());
    }

    @Transactional
    public void updatePrice(CourseDTO courseDTO, UUID id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));
        
        course.setCurrentPrice(courseDTO.getCurrentPrice());
        course.setLastUpdate(LocalDate.now());
    }

    @Transactional
    public void updateAvailability(CourseDTO courseDTO, UUID id){
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new CourseNotFound(id));

        course.setAvailability(courseDTO.getAvailability());
        course.setLastUpdate(LocalDate.now());
    }

}