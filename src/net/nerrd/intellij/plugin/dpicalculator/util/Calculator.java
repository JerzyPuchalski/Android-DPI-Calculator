/*
 * Copyright 2015 Jerzy Puchalski
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * 			http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package net.nerrd.intellij.plugin.dpicalculator.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculator for DPI
 */
public class Calculator {

    /**
     * Calculates sizes for all densities
     *
     * @param originalSize size defined by user
     * @param dpi          {@link Dpi} of user input value
     * @return list of values for all densities
     */
    public static List<Float> calculate(final float originalSize, final int dpi) {
        float mdpi = 1f;
        switch (dpi) {
            case Dpi.XXXHDPI:
                mdpi = originalSize / DpiRatio.RATIO_XXXHDPI;
                break;
            case Dpi.XXHDPI:
                mdpi = originalSize / DpiRatio.RATIO_XHDPI;
                break;
            case Dpi.XHDPI:
                mdpi = originalSize / DpiRatio.RATIO_XHDPI;
                break;
            case Dpi.HDPI:
                mdpi = originalSize / DpiRatio.RATIO_HDPI;
                break;
            case Dpi.MDPI:
                mdpi = originalSize / DpiRatio.RATIO_MDPI;
                break;
            case Dpi.LDPI:
                mdpi = originalSize / DpiRatio.RATIO_LDPI;
                break;
            case Dpi.TVDPI:
                mdpi = originalSize / DpiRatio.RATIO_TVDPI;
                break;
        }

        final List<Float> sizes = new ArrayList<>(Constants.DENSITIES_COUNT);
        sizes.add(Dpi.XXXHDPI, mdpi * DpiRatio.RATIO_XXXHDPI);
        sizes.add(Dpi.XXHDPI, mdpi * DpiRatio.RATIO_XXHDPI);
        sizes.add(Dpi.XHDPI, mdpi * DpiRatio.RATIO_XHDPI);
        sizes.add(Dpi.HDPI, mdpi * DpiRatio.RATIO_HDPI);
        sizes.add(Dpi.MDPI, mdpi);
        sizes.add(Dpi.LDPI, mdpi * DpiRatio.RATIO_LDPI);
        sizes.add(Dpi.TVDPI, mdpi * DpiRatio.RATIO_TVDPI);

        return sizes;

    }

    /**
     * Rounds original calculations to "best" scaled ratio
     *
     * @param originalCalculations original calculations without rounding
     * @return list of rounded densities
     */
    public static List<Integer> calculateRoundedToScale(List<Float> originalCalculations) {
        final List<Integer> rounded = new ArrayList<>(Constants.DENSITIES_COUNT - 1);

        rounded.add(Dpi.XXXHDPI, calculateRoundedValuesToNearestScaleRatioMultiplication(originalCalculations.get(Dpi.XXXHDPI), DpiBestRatio.RATIO_XXXHDPI));
        rounded.add(Dpi.XXHDPI, calculateRoundedValuesToNearestScaleRatioMultiplication(originalCalculations.get(Dpi.XXHDPI), DpiBestRatio.RATIO_XXHDPI));
        rounded.add(Dpi.XHDPI, calculateRoundedValuesToNearestScaleRatioMultiplication(originalCalculations.get(Dpi.XHDPI), DpiBestRatio.RATIO_XHDPI));
        rounded.add(Dpi.HDPI, calculateRoundedValuesToNearestScaleRatioMultiplication(originalCalculations.get(Dpi.HDPI), DpiBestRatio.RATIO_HDPI));
        rounded.add(Dpi.MDPI, calculateRoundedValuesToNearestScaleRatioMultiplication(originalCalculations.get(Dpi.MDPI), DpiBestRatio.RATIO_MDPI));
        rounded.add(Dpi.LDPI, calculateRoundedValuesToNearestScaleRatioMultiplication(originalCalculations.get(Dpi.LDPI), DpiBestRatio.RATIO_LDPI));
        rounded.add(Dpi.TVDPI, (int) Math.ceil(originalCalculations.get(Dpi.TVDPI)));

        return rounded;
    }

    private static int calculateRoundedValuesToNearestScaleRatioMultiplication(float value, int multiplier) {
        final int roundedValue = Math.round(value / multiplier) * multiplier;

        return roundedValue == 0 ? multiplier : roundedValue;
    }

}
