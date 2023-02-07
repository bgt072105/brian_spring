package com.nighthawk.spring_portfolio.mvc.equations;

import java.util.List;

import javax.transaction.Transactional;

import com.nighthawk.spring_portfolio.mvc.person.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquationJpaRepository extends JpaRepository<Equation, Long> {
    // List<Person> findByPersonId(Long id);
    // List<Person> findAllByOrderByNameAsc();

    @Transactional
    void deleteByPersonId(long id); // Delete by person id

    @Transactional
    void deleteById(long id); // Delete by equation id
}
