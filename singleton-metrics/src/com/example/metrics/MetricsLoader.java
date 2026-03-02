package com.example.metrics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads default metric keys/values from a properties file into the singleton registry.
 *
 * Uses {@link MetricsRegistry#getInstance()} — never creates a new instance.
 */
public class MetricsLoader {

    /**
     * Reads {@code path} and seeds the global registry with the values found there.
     *
     * @param path path to the .properties file (relative to the working directory)
     * @return the singleton registry (same object as {@code MetricsRegistry.getInstance()})
     */
    public MetricsRegistry loadFromFile(String path) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        }

        // Always work with the one true instance — never 'new MetricsRegistry()'
        MetricsRegistry registry = MetricsRegistry.getInstance();

        for (String key : props.stringPropertyNames()) {
            String raw = props.getProperty(key, "0").trim();
            long value;
            try {
                value = Long.parseLong(raw);
            } catch (NumberFormatException e) {
                value = 0L;
            }
            registry.setCount(key, value);
        }

        return registry;
    }
}
