package com.journal.JournalApplication.services;

import com.journal.JournalApplication.entity.JournalEntry;
import com.journal.JournalApplication.entity.User;
import com.journal.JournalApplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(String.valueOf(id));
    }

    public void deleteById(ObjectId id, String userName) {
        User user =userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        journalEntryRepository.deleteById(String.valueOf(id));
    }
}
