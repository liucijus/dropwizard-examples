package com.github.liucijus.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class NotesApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new NotesApplication().run(args);
    }

    @Override
    public void run(Configuration config, Environment environment) throws Exception {
        DependencyInjector.inject(config, environment);
    }
}
