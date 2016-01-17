package com.github.liucijus.dropwizard;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.ArrayList;
import java.util.List;

public class DependencyInjector {
    public static void inject(Configuration config, Environment environment) {
        List<Object> resources = Resources.wiredFor(config, environment).resources;

        resources.stream().forEach(resource -> environment.jersey().register(resource));
    }
}

class Repositories {
    final NotesRepository notesRepository;

    private Repositories(Configuration config, Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "MySQL");
        this.notesRepository = jdbi.onDemand(NotesRepository.class);
    }

    static Repositories wiredFor(Configuration config, Environment environment) {
        return new Repositories(config, environment);
    }
}

class Services {
    final NotesService notesService;

    public Services(Repositories repositories) {
        this.notesService = new NotesService(repositories.notesRepository);
    }

    static Services wiredFor(Repositories repositories) {
        return new Services(repositories);
    }
}

class Resources {
    final Services services;
    final List<Object> resources;

    private Resources(Configuration config, Environment environment) {
        this.services = Services.wiredFor(Repositories.wiredFor(config, environment));

        resources = new ArrayList<>();
        resources.add(new NotesResource(services.notesService));
    }

    static Resources wiredFor(Configuration config, Environment environment) {
        return new Resources(config, environment);
    }
}
