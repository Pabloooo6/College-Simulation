package com.tecnocampus.grouppablo.application;

import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import com.tecnocampus.grouppablo.application.exception.CourseNotFound;
import com.tecnocampus.grouppablo.domain.Course;
import com.tecnocampus.grouppablo.persistence.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public CourseDTO addCourse(@Valid CourseDTO courseDTO) {
        Course course = new Course(courseDTO);
        courseRepository.save(course);
        return courseDTO;
    }

    public List<CourseDTO> getAllCourses(){
        return courseRepository.findAll(Sort.by(Sort.Direction.ASC, "title")).stream().filter(Course::getAvailability).map(CourseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public Course updateCourse(CourseDTO courseDTO, Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound(id));

        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setImageUrl(courseDTO.getImageUrl());

        //return para verlo en el postman que se ha update bien
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFound(id));
    }

    @Transactional
    public void updatePrice(CourseDTO courseDTO, Long id){


        Course course = new Course(courseDTO);
    }

    @Transactional
    public void updateAvailability(CourseDTO courseDTO, Long id){



        Course course = new Course(courseDTO);
    }

}