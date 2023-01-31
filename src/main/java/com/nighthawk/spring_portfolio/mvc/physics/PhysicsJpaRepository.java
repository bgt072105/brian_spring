package com.nighthawk.spring_portfolio.mvc.physics;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface PhysicsJpaRepository extends JpaRepository<Physics, Long> {
    // JPA has many built in methods, these few have been prototyped for this application
    void save(String Equation);

    // A
    List<Physics> findAllByOrderByEquationAsc();  // returns a List of Jokes in Ascending order
    List<Physics> findByEquationIgnoreCase(String equation);  // look to see if Joke(s) exist
}
