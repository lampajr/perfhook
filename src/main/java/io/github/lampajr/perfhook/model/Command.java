package io.github.lampajr.perfhook.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class Command {
    private String hookId;
    private EventType event;
    private ObjectNode payload;

    public String getHookId() {
        return hookId;
    }

    public Command setHookId(String hookId) {
        this.hookId = hookId;
        return this;
    }

    public EventType getEvent() {
        return event;
    }

    public Command setEvent(EventType event) {
        this.event = event;
        return this;
    }

    public ObjectNode getPayload() {
        return payload;
    }

    public Command setPayload(ObjectNode payload) {
        this.payload = payload;
        return this;
    }
}
