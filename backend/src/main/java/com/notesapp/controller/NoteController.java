package com.notesapp.controller;

import com.notesapp.entity.Note;
import com.notesapp.repository.NoteRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:63342", allowCredentials = "true")
public class NoteController {

    @Autowired
    private NoteRepository repository;

    @GetMapping
    public ResponseEntity<?> getNotes(HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not logged in. Please login first.");
        }

        List<Note> notes = repository.findByUsername(username);
        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody Note note,
                           HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not logged in. Please login first.");
        }

        note.setUsername(username);
        Note savedNote = repository.save(note);
        return ResponseEntity.ok(savedNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id,
                           @RequestBody Note updatedNote,
                           HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not logged in. Please login first.");
        }

        Note existingNote = repository.findById(id).orElse(null);

        if (existingNote != null &&
                existingNote.getUsername().equals(username)) {

            existingNote.setTitle(updatedNote.getTitle());
            existingNote.setContent(updatedNote.getContent());

            Note savedNote = repository.save(existingNote);
            return ResponseEntity.ok(savedNote);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Note not found or unauthorized");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id,
                           HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not logged in. Please login first.");
        }

        Note note = repository.findById(id).orElse(null);

        if (note != null &&
                note.getUsername().equals(username)) {

            repository.delete(note);
            return ResponseEntity.ok("Note deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Note not found or unauthorized");
    }
}