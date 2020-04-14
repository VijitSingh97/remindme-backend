package com.vijitsingh.remindme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijitsingh.remindme.model.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

}
