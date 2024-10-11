package com.journal.JournalApplication.controller;

import com.journal.JournalApplication.entity.JournalEntry;
import com.journal.JournalApplication.entity.User;
import com.journal.JournalApplication.services.JournalEntryService;
import com.journal.JournalApplication.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        if (user.getUserName() == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public  ResponseEntity<?> updateUser(@RequestBody User user,
                                         @PathVariable String userName) {
        User userInDb = userService.findByUserName(user.getUserName());

        if(userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}