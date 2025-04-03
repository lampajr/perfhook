package io.github.lampajr.perfhook.svc.jenkins;

import io.github.lampajr.perfhook.model.PerfJob;
import io.github.lampajr.perfhook.svc.PerfJobTrigger;
import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@LookupIfProperty(name = "io.github.lampajr.perfhook.perflab", stringValue = "jenkins")
@ApplicationScoped
public class JenkinsJobTrigger implements PerfJobTrigger {

    @Inject
    JenkinsClient jenkinsClient;

    @Override
    public void trigger(PerfJob job) {
        Log.info("Triggering jenkins job " + job.getName());
        jenkinsClient.triggerBuild(job.getName());
    }
}
