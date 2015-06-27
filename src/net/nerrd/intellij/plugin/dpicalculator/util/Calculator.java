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

        final List<Float> ratios = new ArrayList<>(Constants.DENSITIES_COUNT);
        ratios.add(Dpi.XXXHDPI, mdpi * DpiRatio.RATIO_XXXHDPI);
        ratios.add(Dpi.XXHDPI, mdpi * DpiRatio.RATIO_XXHDPI);
        ratios.add(Dpi.XHDPI, mdpi * DpiRatio.RATIO_XHDPI);
        ratios.add(Dpi.HDPI, mdpi * DpiRatio.RATIO_HDPI);
        ratios.add(Dpi.MDPI, mdpi);
        ratios.add(Dpi.LDPI, mdpi * DpiRatio.RATIO_LDPI);
        ratios.add(Dpi.TVDPI, mdpi * DpiRatio.RATIO_TVDPI);

        return ratios;

    }
}
