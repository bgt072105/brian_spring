package com.nighthawk.spring_portfolio.mvc.discussion;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface DiscussionBoardJpaRepository extends JpaRepository<DiscussionBoard, Long> {
    // JPA has many built in methods, these few have been prototyped for this application
    void save(String question);

    // A
    List<DiscussionBoard> findByProblemIgnoreCase(String question);  // look to see if Joke(s) exist
}
