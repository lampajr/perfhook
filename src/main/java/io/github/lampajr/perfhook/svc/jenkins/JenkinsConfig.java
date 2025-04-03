package io.github.lampajr.perfhook.svc.jenkins;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

/**
 * Jenkins specific configuration
 */
@ConfigMapping(prefix = "io.github.lampajr.perfhook.perflab.jenkins")
public interface JenkinsConfig {
    @WithDefault("http://localhost:18080")
    String baseUrl();

    @WithDefault("change_me")
    String token();
}
