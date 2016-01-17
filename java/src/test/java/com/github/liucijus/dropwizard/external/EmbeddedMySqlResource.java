package com.github.liucijus.dropwizard.external;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;
import org.junit.rules.ExternalResource;

import static com.wix.mysql.ScriptResolver.classPathFiles;

public class EmbeddedMySqlResource extends ExternalResource {
    private MysqldConfig config = MysqldConfig
            .aMysqldConfig(Version.v5_6_latest)
            .withCharset(Charset.UTF8)
            .withPort(3306)
            .withUser("dropwizard", "dropwizard")
            .build();

    private EmbeddedMysql embeddedMysql;

    @Override
    protected void before() throws Throwable {
        System.out.println("starting embedded MySQL");
        embeddedMysql = EmbeddedMysql
                .anEmbeddedMysql(config)
                .addSchema("dropwizard", classPathFiles("db/*"))
                .start();

    }

    @Override
    protected void after() {
        System.out.println("stopping embedded MySQL");
        embeddedMysql.stop();
    }
}
