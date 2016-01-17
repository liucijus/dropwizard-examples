package com.github.liucijus.dropwizard;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface NotesRepository {
    @SqlUpdate("insert into note (name) values (:name)")
    void insert(@Bind("name") String name);

    @SqlQuery("select name from note")
    List<String> findNames();

    @SqlQuery("select name from note where name = :name")
    String getByName(@Bind("name") String name);
}
