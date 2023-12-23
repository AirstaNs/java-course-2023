package edu.module5.project4.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pixel {
    private int red;
    private int green;
    private int blue;
    private int hitCount;
    private double normalizationFactor;

    public Pixel(int red, int green, int blue, int hitCount, double normalizationFactor) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hitCount = hitCount;
        this.normalizationFactor = normalizationFactor;
    }

    public void adjustColors(double normalizationFactor, double inverseGamma) {
        this.red = (int) (this.red * Math.pow(normalizationFactor, inverseGamma));
        this.green = (int) (this.green * Math.pow(normalizationFactor, inverseGamma));
        this.blue = (int) (this.blue * Math.pow(normalizationFactor, inverseGamma));
    }
}
