package com.nighthawk.spring_portfolio.mvc.practiceProblems;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface PracticeJpaRepository extends JpaRepository<Practice, Long> {
    // JPA has many built in methods, these few have been prototyped for this application
    void save(String Joke);

    // A
    List<Practice> findAllByOrderByJokeAsc();  // returns a List of Jokes in Ascending order
    List<Practice> findByJokeIgnoreCase(String joke);  // look to see if Joke(s) exist
}
