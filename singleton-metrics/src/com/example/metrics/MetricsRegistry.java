package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe, lazy-initialized Singleton metrics registry.
 *
 * Guarantees:
 *  - Only one instance per JVM run (static holder ensures lazy + thread-safe init).
 *  - Reflection cannot create a second instance (constructor guard).
 *  - Deserialization returns the same instance (readResolve).
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------------
    // Singleton plumbing — static holder idiom (lazy + thread-safe, no locks)
    // -----------------------------------------------------------------------

    /** Loaded on first access of getInstance(); JVM guarantees thread-safety. */
    private static final class Holder {
        static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    /**
     * Private constructor.
     * The reflection guard below ensures nobody can call it a second time
     * even if they bypass access control with setAccessible(true).
     */
    private MetricsRegistry() {
        // Guard against reflection attacks: if the singleton is already alive,
        // a second construction attempt must be rejected.
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException(
                "MetricsRegistry is a singleton — use getInstance() instead of calling the constructor directly.");
        }
    }

    /** Returns the single global instance. */
    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    // -----------------------------------------------------------------------
    // Serialization safety
    // -----------------------------------------------------------------------

    /**
     * Called by the JVM after deserialization.
     * Returning the existing singleton means the deserialized bytes are discarded
     * and no second object is ever used.
     */
    @Serial
    protected Object readResolve() {
        return Holder.INSTANCE;
    }

    // -----------------------------------------------------------------------
    // Core state
    // -----------------------------------------------------------------------

    private final Map<String, Long> counters = new HashMap<>();

    // -----------------------------------------------------------------------
    // Public API — all writes are synchronized so concurrent threads are safe
    // -----------------------------------------------------------------------

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    /** Returns a snapshot copy — callers cannot mutate internal state. */
    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
