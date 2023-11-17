package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.domain.Enrol;
import com.tecnocampus.grouppablo.domain.EnrolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolRepository extends JpaRepository<Enrol, EnrolId> {

}
