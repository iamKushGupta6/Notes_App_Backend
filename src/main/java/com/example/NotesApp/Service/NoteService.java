package com.example.NotesApp.Service;


import com.example.NotesApp.Model.Note;
import com.example.NotesApp.Repositary.NoteRepository;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class NoteService {
    private final NoteRepository repo;
    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }
    public List<Note> listByUser(Long userId) {
        return repo.findByUserId(userId);
    }
    public Note create(Long userId, Note n) {
        n.setUserId(userId);
        n.setCreatedAt(OffsetDateTime.now());
        n.setUpdatedAt(OffsetDateTime.now());
        n.setVersion(0L);
        return repo.save(n);
    }
    public Optional<Note> get(Long id) {
        return repo.findById(id);
    }
    public Note update(Long id, Note incoming, Long userId) {
        Note e = repo.findById(id).orElseThrow();
        if(!e.getUserId().equals(userId)) throw new RuntimeException();
        e.setTitle(incoming.getTitle());
        e.setContent(incoming.getContent());
        e.setUpdatedAt(OffsetDateTime.now());
        return repo.save(e);
    }
    public void delete(Long id, Long userId) {
        Note e = repo.findById(id).orElseThrow();
        if(!e.getUserId().equals(userId)) throw new RuntimeException();
        repo.deleteById(id);
    }
}
