package com.nighthawk.spring_portfolio.mvc.equations;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.nighthawk.spring_portfolio.mvc.person.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquationJpaRepository extends JpaRepository<Equation, Long> {

    @Transactional
    void deleteByPersonId(long id); // Delete by person id

    @Transactional
    void deleteById(long id); // Delete by equation id

    Optional<List<Equation>> findAllById(long id); // Find all equations by id

    Optional<List<Equation>> findByPersonId(long person_id); // Find all equations by person id
}
