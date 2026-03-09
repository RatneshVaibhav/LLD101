package com.example.map;

/**
 * Represents a single pin on the map.
 *
 * Extrinsic state (unique per marker): lat, lng, label.
 * Intrinsic state (shared via Flyweight): the MarkerStyle reference.
 *
 * The constructor now takes a pre-built MarkerStyle obtained from
 * MarkerStyleFactory, so identical visual configurations share one object.
 */
public class MapMarker {

    private final double lat;
    private final double lng;
    private final String label;

    // Shared flyweight — many markers can point to the same instance
    private final MarkerStyle style;

    public MapMarker(double lat, double lng, String label, MarkerStyle style) {
        this.lat   = lat;
        this.lng   = lng;
        this.label = label;
        this.style = style;
    }

    public double getLat()      { return lat; }
    public double getLng()      { return lng; }
    public String getLabel()    { return label; }
    public MarkerStyle getStyle() { return style; }
}
