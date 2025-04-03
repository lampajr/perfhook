package io.github.lampajr.perfhook.svc;

import io.github.lampajr.perfhook.model.PerfJob;

/**
 * Represents a generic service that can trigger remote performance jobs
 */
public interface PerfJobTrigger {

    /**
     * Trigger a performance job
     *
     * @param job internal representation of a job
     */
    void trigger(PerfJob job);
}
