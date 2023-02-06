package com.nighthawk.spring_portfolio.mvc.students;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// jpa repo for db
public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    void save(String Student);

    // db lookup functions for specific info
    List<Student> findAllByOrderByNameAsc();

    List<Student> findByNameIgnoreCase(String name);

    List<Student> findByEmailIgnoreCase(String email);

    List<Student> findByEvent(String event);

    Optional<Student> findByName(String name);
}