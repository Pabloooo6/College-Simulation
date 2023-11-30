package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
}
