package com.tecnocampus.grouppablo.persistence;

import com.tecnocampus.grouppablo.domain.secutiry.ERole;
import com.tecnocampus.grouppablo.domain.secutiry.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(ERole erole);
}