package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import com.tecnocampus.grouppablo.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository <Course, String>{

    List<Course> findByAvailabilityTrueOrderByTitle();

    Optional<Course> findByTitle(String title);

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.CourseDTO(c.title, c.description)
            FROM Course c
            WHERE c.title LIKE %:search% OR c.description LIKE %:search%
            ORDER BY c.title ASC""")
    List<CourseDTO> findCoursesByTitleOrDescription(String search);
}
