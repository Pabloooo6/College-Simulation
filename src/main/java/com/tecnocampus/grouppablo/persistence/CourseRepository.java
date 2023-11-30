package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import com.tecnocampus.grouppablo.application.dto.UserDTO;
import com.tecnocampus.grouppablo.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository <Course, String>{

    List<Course> findByAvailabilityTrueOrderByTitle();

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.CourseDTO(c.title, c.description)
            FROM Course c
            WHERE c.title LIKE %:search% OR c.description LIKE %:search%
            ORDER BY c.title ASC""")
    List<CourseDTO> findCoursesByTitleOrDescription(String search);

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.CourseDTO(c.title, c.description, c.publication, c.lastUpdate, c.imageUrl, c.currentPrice, c.availability, c.category, c.language, c.teacher)
            FROM Course c
            WHERE (c.category.category IN :searchTerms OR c.language.language IN :searchTerms)""")
    List<CourseDTO> findCoursesByCategoryOrLanguage(List<String> searchTerms);

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.CourseDTO(c.title, c.description, c.publication, c.lastUpdate, c.imageUrl, c.currentPrice, c.availability, c.category, c.language, c.teacher)
            FROM Course c
            WHERE c.teacher.username =:teacherName ORDER BY c.lastUpdate DESC""")
    List<CourseDTO> findByTeacher(@Param("teacherName") String teacherName);

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.UserDTO(c.teacher)
            FROM Course c""")
    List<UserDTO> findTeachers();
}
