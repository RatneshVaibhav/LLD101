package com.example.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates synthetic markers for demo and testing purposes.
 *
 * Styles are fetched from a shared MarkerStyleFactory, so markers that happen
 * to have the same shape/color/size/fill configuration will reference the
 * same MarkerStyle instance in memory rather than holding a private copy.
 */
public class MapDataSource {

    private static final String[] SHAPES = {"PIN", "CIRCLE", "SQUARE"};
    private static final String[] COLORS = {"RED", "BLUE", "GREEN", "ORANGE"};
    private static final int[] SIZES = {10, 12, 14, 16};

    // One factory per data-source; it acts as the cache for all styles we create.
    private final MarkerStyleFactory styleFactory = new MarkerStyleFactory();

    public List<MapMarker> loadMarkers(int count) {
        Random rnd = new Random(7);
        List<MapMarker> out = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            double lat = 12.9000 + rnd.nextDouble() * 0.2000;
            double lng = 77.5000 + rnd.nextDouble() * 0.2000;
            String label = "M-" + i;

            // Small pools guarantee many duplicates — perfect for Flyweight gains
            String shape = SHAPES[rnd.nextInt(SHAPES.length)];
            String color = COLORS[rnd.nextInt(COLORS.length)];
            int size     = SIZES[rnd.nextInt(SIZES.length)];
            boolean filled = rnd.nextBoolean();

            // Obtain a shared style from the factory rather than newing one up
            MarkerStyle sharedStyle = styleFactory.get(shape, color, size, filled);
            out.add(new MapMarker(lat, lng, label, sharedStyle));
        }
        return out;
    }

    /** Exposes how many unique styles were cached (should be ≤ 96 for this data set). */
    public int uniqueStyleCount() {
        return styleFactory.cacheSize();
    }
}
