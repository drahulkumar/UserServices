package com.rahul.userservicev1.repositories;

import com.rahul.userservicev1.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session save(Session session);
}
