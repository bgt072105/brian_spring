package com.nighthawk.spring_portfolio.mvc.tutors;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface TutorsJpaRepository extends JpaRepository<Tutors, Long> {
    // JPA has many built in methods, these few have been prototyped for this
    // application
    void save(String problem);

    // A
    List<Tutors> findByProblemIgnoreCase(String problem); // look to see if Joke(s) exist

    List<Tutors> findByProblemOrTag(String problem, String tag);

    @Query(value = "SELECT max(id) FROM Practice")
    long getMaxId();
}
