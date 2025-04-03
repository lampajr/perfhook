package io.github.lampajr.perfhook.parser.github;

import io.github.lampajr.perfhook.model.Command;
import io.github.lampajr.perfhook.model.EventType;
import io.github.lampajr.perfhook.model.PerfJob;
import io.github.lampajr.perfhook.parser.CommandParser;
import io.github.lampajr.perfhook.parser.WebhookEventParser;
import io.quarkus.arc.All;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@LookupIfProperty(name = "io.github.lampajr.perfhook.platform", stringValue = "github")
@ApplicationScoped
public class GithubCommandParser implements CommandParser {

    private final Map<EventType, Function<Command, PerfJob>> jobMappers;

    public GithubCommandParser(@All List<WebhookEventParser> eventParsers) {
        jobMappers = new HashMap<>();
        eventParsers.forEach(parser -> jobMappers.put(parser.getEventType(), parser::parseJob));
    }

    @Override
    public PerfJob parseJob(Command command) {
        PerfJob job = new PerfJob();

        return jobMappers.getOrDefault(command.getEvent(), cmd -> {
            throw new IllegalArgumentException("Unsupported event type: " + command.getEvent());
        }).apply(command);
    }
}
