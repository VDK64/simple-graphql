package com.example.graphql.repository;

import com.example.graphql.entity.Subscription;
import com.example.graphql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByUser(User user);

    @Query(value = "select s from Subscription s join fetch s.user u where u in (:users)")
    Set<Subscription> findByUserIn(@Param("users") List<User> users);

}
