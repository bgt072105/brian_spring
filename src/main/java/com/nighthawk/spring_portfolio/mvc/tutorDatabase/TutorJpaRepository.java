package com.nighthawk.spring_portfolio.mvc.tutorDatabase;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface TutorJpaRepository extends JpaRepository<Tutor, Long> {
    // JPA has many built in methods, these few have been prototyped for this
    // application
    void save(String name);

    // A
    List<Tutor> findByNameIgnoreCase(String name); // look to see if Joke(s) exist

    @Query(value = "SELECT * FROM Practice p WHERE p.problem LIKE ?1 or p.Tags LIKE ?1", nativeQuery = true)
    List<Tutor> findByNameorEmail(String term);

    @Query(value = "SELECT max(id) FROM Practice")
    long getMaxId();
}
