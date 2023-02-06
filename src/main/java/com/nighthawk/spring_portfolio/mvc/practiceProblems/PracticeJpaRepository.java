package com.nighthawk.spring_portfolio.mvc.practiceProblems;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface PracticeJpaRepository extends JpaRepository<Practice, Long> {
    // JPA has many built in methods, these few have been prototyped for this application
    void save(String problem);

    // A
    List<Practice> findByProblemIgnoreCase(String problem);  // look to see if Joke(s) exist

    List<Practice> findByProblemOrTag(String problem, String tag);

    @Query(
        value = "SELECT max(id) FROM Practice")
    int getMaxId();
}
