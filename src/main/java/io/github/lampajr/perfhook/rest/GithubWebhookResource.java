package io.github.lampajr.perfhook.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.lampajr.perfhook.model.Command;
import io.github.lampajr.perfhook.model.EventType;
import io.github.lampajr.perfhook.model.PerfJob;
import io.github.lampajr.perfhook.parser.CommandParser;
import io.github.lampajr.perfhook.svc.PerfJobTrigger;
import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

@LookupIfProperty(name = "io.github.lampajr.perfhook.platform", stringValue = "github")
@Path("/github")
public class GithubWebhookResource {

    @Inject
    protected CommandParser parser;

    @Inject
    protected PerfJobTrigger trigger;

    @POST
    @ResponseStatus(204)
    @Produces(MediaType.APPLICATION_JSON)
    // TODO(user): implement validation https://docs.github.com/en/webhooks/using-webhooks/securing-your-webhooks
    public void webhook(@RestHeader("X-Github-Event") String event,
                        @RestHeader("X-GitHub-Hook-ID") String hookId,
                        ObjectNode payload) {
        Log.info("Received webhook event: " + event + " hookId: " + hookId);

        // TODO: events should be enqueued asynchronously to avoid blocking
        Command cmd = new Command().setEvent(EventType.ISSUE_COMMENT).setHookId(hookId).setPayload(payload);
        PerfJob job = parser.parseJob(cmd);
        trigger.trigger(job);
    }
}
