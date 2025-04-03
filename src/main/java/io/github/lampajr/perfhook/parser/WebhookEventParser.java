package io.github.lampajr.perfhook.parser;

import io.github.lampajr.perfhook.model.Command;
import io.github.lampajr.perfhook.model.EventType;
import io.github.lampajr.perfhook.model.PerfJob;

public interface WebhookEventParser {

    EventType getEventType();

    PerfJob parseJob(Command cmd);
}
