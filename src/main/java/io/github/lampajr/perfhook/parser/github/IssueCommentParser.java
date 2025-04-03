package io.github.lampajr.perfhook.parser.github;

import io.github.lampajr.perfhook.model.Command;
import io.github.lampajr.perfhook.model.EventType;
import io.github.lampajr.perfhook.model.PerfJob;
import io.github.lampajr.perfhook.parser.WebhookEventParser;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Arrays;

@LookupIfProperty(name = "io.github.lampajr.perfhook.platform", stringValue = "github")
@ApplicationScoped
public class IssueCommentParser implements WebhookEventParser {

    @ConfigProperty(name = "io.github.lampajr.perfhook.prompt")
    String prompt;

    @Override
    public EventType getEventType() {
        return EventType.ISSUE_COMMENT;
    }

    @Override
    public PerfJob parseJob(Command command) {
        assert command.getEvent().equals(EventType.ISSUE_COMMENT);

        PerfJob job = new PerfJob();

        String[] comment = command.getPayload().get("comment").get("body").asText().split(" ");
        if (comment.length != 3 || !comment[0].equals(prompt) || !comment[1].equals("run")) {
            throw new IllegalArgumentException("Invalid comment format, expected [" + prompt + " run <job-name>] but got " + Arrays.toString(comment));
        }

        job.setName(comment[2]);
        return job;
    }
}
