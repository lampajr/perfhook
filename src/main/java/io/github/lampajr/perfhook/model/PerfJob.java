package io.github.lampajr.perfhook.model;

/**
 * This represents the performance job to be triggered
 */
public class PerfJob {
    private String name;

    public String getName() {
        return name;
    }

    public PerfJob setName(String name) {
        this.name = name;
        return this;
    }
}
