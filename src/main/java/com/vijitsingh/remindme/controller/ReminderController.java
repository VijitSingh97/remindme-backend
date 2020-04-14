package com.vijitsingh.remindme.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijitsingh.remindme.exception.ResourceNotFoundException;
import com.vijitsingh.remindme.model.Reminder;
import com.vijitsingh.remindme.repository.ReminderRepository;

@RestController
@RequestMapping("/api")
public class ReminderController {

    @Autowired
    ReminderRepository reminderRepository;

    // Get All Reminders
    @GetMapping("/reminders")
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/reminders")
    public Reminder createReminder(@Valid @RequestBody Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    // Get a Single Note
    @GetMapping("/reminders/{id}")
    public Reminder getReminderById(@PathVariable(value = "id") Long reminderId) {
        return reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder", "id", reminderId));
    }

    // Update a Note
    @PutMapping("/reminders/{id}")
    public Reminder updateReminder(@PathVariable(value = "id") Long reminderId,
                                            @Valid @RequestBody Reminder reminderDetails) {

        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", reminderId));

        reminder.setSubject(reminderDetails.getSubject());
        reminder.setContent(reminderDetails.getContent());
        reminder.setRemindAt(reminderDetails.getRemindAt());

        Reminder updatedreminder = reminderRepository.save(reminder);
        return updatedreminder;
    }

    // Delete a Note
    @DeleteMapping("/reminders/{id}")
    public ResponseEntity<?> deleteReminder(@PathVariable(value = "id") Long reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", reminderId));

        reminderRepository.delete(reminder);

        return ResponseEntity.ok().build();
    }
}