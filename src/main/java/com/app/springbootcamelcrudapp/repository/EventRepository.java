package com.app.springbootcamelcrudapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.springbootcamelcrudapp.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID>{

}
