package io.github.lampajr.perfhook.svc.jenkins;

import io.quarkus.logging.Log;
import io.quarkus.rest.client.reactive.runtime.MicroProfileRestClientRequestFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.eclipse.microprofile.rest.client.ext.DefaultClientHeadersFactoryImpl;

@ApplicationScoped
public class JenkinsClient {

    private final WebTarget target;
    private final JenkinsConfig config;

    public JenkinsClient(JenkinsConfig jenkinsConfig) {
        Log.info("Setting up JenkinsClient with " + jenkinsConfig.baseUrl());
        this.config = jenkinsConfig;
        this.target = ClientBuilder.newClient()
                .register(new MicroProfileRestClientRequestFilter(new DefaultClientHeadersFactoryImpl()))
                .target(config.baseUrl())
                .path("job/");
    }

    public void triggerBuild(String jobName) {
        Log.info("Triggering build " + jobName + " with token " + config.token());
        // TODO: throw exception if 4xx or 5xx
        target.path(jobName)
                .path("build")
                .queryParam("token", config.token())
                .request()
                .get();
    }
}
