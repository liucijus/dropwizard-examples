package com.github.liucijus.dropwizard;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/notes")
@Produces(MediaType.APPLICATION_JSON)
public class NotesResource {
    private NotesService notesService;

    public NotesResource(NotesService notesService) {
        this.notesService = notesService;
    }

    @GET
    @Timed
    @Path("{name}")
    public Note getNote(@PathParam("name") String name) {
        return notesService.findByName(name).orNull();
    }

    @POST
    @Timed
    public void postNote(Note note) {
        notesService.create(note);
    }

    @GET
    @Timed
    public List<Note> getNotes() {
        return notesService.findAll();
    }
}

