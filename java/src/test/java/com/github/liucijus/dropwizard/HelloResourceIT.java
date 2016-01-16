package com.github.liucijus.dropwizard;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class HelloResourceIT {

    @ClassRule
    public static final DropwizardAppRule<HelloWorldConfiguration> RULE =
            new DropwizardAppRule<>(HelloWorldApplication.class);

    @Test
    public void shoudlShowHello() {
        Client client = ClientBuilder.newClient();

        HelloResponse response = client
                .target("http://localhost:" + RULE.getLocalPort() + "/hello-world")
                .queryParam("name", "foobar")
                .request()
                .get(HelloResponse.class);

        assertThat(response.getMessage(), equalTo("Hello, foobar"));

    }
}
