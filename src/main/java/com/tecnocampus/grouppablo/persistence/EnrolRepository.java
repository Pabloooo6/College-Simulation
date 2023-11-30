package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.application.dto.ReviewDTO;
import com.tecnocampus.grouppablo.domain.Enrol;
import com.tecnocampus.grouppablo.domain.EnrolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrolRepository extends JpaRepository<Enrol, EnrolId> {

    @Query("""
            SELECT NEW com.tecnocampus.grouppablo.application.dto.ReviewDTO(e.review)
            FROM Enrol e
            WHERE e.course.id = :courseId""")
    List<ReviewDTO> findReviewsByCourse(String courseId);

    @Query("""
            SELECT e
            FROM Enrol e
            WHERE e.user.username = :id""")
    List<Enrol> findByUserId(String id);

    @Query("""
            SELECT e
            FROM Enrol e
            WHERE e.course.id = :id""")
    List<Enrol> findByCourseId(String id);

    @Query("""
            SELECT e.review.satisfaction
            FROM Enrol e
            WHERE e.course.id = :id""")
    List<Integer> findSatisfactionsByCourse(String id);

    @Query("""
            SELECT e.review.satisfaction
            FROM Enrol e
            WHERE e.course.teacher.username = :id AND YEAR(e.review.date) = :year""")
    List<Integer> findByTeacherIdAndYear(String id, int year);

}