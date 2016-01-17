package com.github.liucijus.dropwizard;

import com.google.common.base.Optional;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class NotesService {
    private NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public void create(Note note) {
        notesRepository.insert(note.getName());
    }

    public Optional<Note> findByName(String name) {
        return Optional.fromNullable(notesRepository.getByName(name)).transform(Note::new);
    }

    public List<Note> findAll() {
        return notesRepository.findNames().stream().map(Note::new).collect(toList());
    }
}
