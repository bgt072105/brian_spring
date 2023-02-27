package com.nighthawk.spring_portfolio.mvc.schedulestest;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.nighthawk.spring_portfolio.mvc.person.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long> {

    @Transactional
    void deleteByPersonId(long id); // Delete by person id

    @Transactional
    void deleteById(long id); // Delete by equation id

    Optional<List<Schedule>> findAllById(long id); // Find all equations by id

    Optional<List<Schedule>> findByPersonId(long person_id); // Find all equations by person id
}
