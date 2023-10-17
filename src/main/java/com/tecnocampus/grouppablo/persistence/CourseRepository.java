package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository <Course, String>{

    List<Course> findByAvailabilityTrueOrderByTitle();

    Optional<Course> findByTitle(String title);
}
