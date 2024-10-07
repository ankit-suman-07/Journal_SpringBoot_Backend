package com.journal.JournalApplication.repository;

import com.journal.JournalApplication.entity.JournalEntry;
import com.journal.JournalApplication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
