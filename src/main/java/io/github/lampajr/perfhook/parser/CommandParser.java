package io.github.lampajr.perfhook.parser;

import io.github.lampajr.perfhook.model.Command;
import io.github.lampajr.perfhook.model.PerfJob;

public interface CommandParser {
    PerfJob parseJob(Command command);
}
