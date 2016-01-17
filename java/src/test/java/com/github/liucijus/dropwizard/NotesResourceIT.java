package com.github.liucijus.dropwizard;

import com.github.liucijus.dropwizard.external.EmbeddedMySqlResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import javax.ws.rs.client.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class NotesResourceIT {
    private static final EmbeddedMySqlResource MYSQL = new EmbeddedMySqlResource();
    private static final DropwizardAppRule<Configuration> APPLICATION = new DropwizardAppRule<>(
            NotesApplication.class, "config.yml"
    );
    @ClassRule
    public static final TestRule chain = RuleChain.outerRule(MYSQL).around(APPLICATION);
    private static final Client client = ClientBuilder.newClient();

    @Test
    public void shouldGetNote() {
        Note note = new Note("foobar");
        aRequestTo("/notes").post(Entity.json(note));

        Note response = aRequestTo("/notes/" + note.getName()).get(Note.class);

        assertThat(response.getName(), equalTo("foobar"));

    }

    private Invocation.Builder aRequestTo(String path) {
        return client.target("http://localhost:" + APPLICATION.getLocalPort() + path).request();
    }
}
