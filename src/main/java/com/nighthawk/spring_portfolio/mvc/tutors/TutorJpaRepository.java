package com.nighthawk.spring_portfolio.mvc.tutors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// jpa repo for db
public interface TutorJpaRepository extends JpaRepository<Tutor, Long> {
    void save(String Tutor);

    // db lookup functions for specific info
    List<Tutor> findAllByOrderByNameAsc();

    List<Tutor> findByNameIgnoreCase(String name);

    List<Tutor> findByEmailIgnoreCase(String email);

    List<Tutor> findByExperience(String experience);
    
    List<Tutor> findByAge (int age);


    Optional<Tutor> findByName(String name);
}