package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long>{



}
