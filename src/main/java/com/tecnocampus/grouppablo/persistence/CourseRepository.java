package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long>{

    List<Course> findByAvailabilityTrueOrderByTitle();

    Optional<Course> findById(UUID id);

}
