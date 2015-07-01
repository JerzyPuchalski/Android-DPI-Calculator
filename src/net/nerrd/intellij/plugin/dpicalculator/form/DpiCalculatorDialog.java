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

package net.nerrd.intellij.plugin.dpicalculator.form;

import net.nerrd.intellij.plugin.dpicalculator.util.Calculator;
import net.nerrd.intellij.plugin.dpicalculator.util.Constants;
import net.nerrd.intellij.plugin.dpicalculator.util.Dpi;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * {@link JDialog} with UI to calculate Android DPI sizes
 */
public class DpiCalculatorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField xxxhdpiTextField;
    private JTextField xxhdpiTextField;
    private JTextField xhdpiTextField;
    private JTextField hdpiTextField;
    private JTextField mdpiTextField;
    private JTextField ldpiTextField;
    private JTextField tvdpiTextField;
    private JLabel roundedTvdpi;
    private JLabel roundedLdpi;
    private JLabel roundedMdpi;
    private JLabel roundedHdpi;
    private JLabel roundedXhdpi;
    private JLabel roundedXxhdpi;
    private JLabel roundedXxxhdpi;
    private List<JTextField> dpiTextFieldsSet;
    private List<JLabel> roundedDpiLabelsSet;
    private List<Float> sizes;
    private List<Integer> sizesRounded;

    /**
     * Creates a new dialog to calculate DPI
     */
    public DpiCalculatorDialog() {
        setContentPane(contentPane);
        setTitle("Android DPI Calculator");
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        final ActionListener escapeKeyListener = e -> setVisible(false);
        getRootPane().registerKeyboardAction(escapeKeyListener, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        initLists();
        initListeners();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void applyNewDensityValues(final JTextField currentDpiTextField) {
        try {
            final float originalSize = Float.valueOf(currentDpiTextField.getText());
            sizes = Calculator.calculate(originalSize, dpiTextFieldsSet.indexOf(currentDpiTextField));
            sizesRounded = Calculator.calculateRoundedToScale(sizes);

            updateInputs(currentDpiTextField);
            updateRoundedValues();
        } catch (NumberFormatException ex) {
            currentDpiTextField.setToolTipText("Enter integers or floating point numbers");
            removeValues();
        }
    }

    private void updateRoundedValues() {
        roundedXxxhdpi.setText(String.valueOf(sizesRounded.get(Dpi.XXXHDPI)));
        roundedXxhdpi.setText(String.valueOf(sizesRounded.get(Dpi.XXHDPI)));
        roundedXhdpi.setText(String.valueOf(sizesRounded.get(Dpi.XHDPI)));
        roundedHdpi.setText(String.valueOf(sizesRounded.get(Dpi.HDPI)));
        roundedMdpi.setText(String.valueOf(sizesRounded.get(Dpi.MDPI)));
        roundedLdpi.setText(String.valueOf(sizesRounded.get(Dpi.LDPI)));
        roundedTvdpi.setText(String.valueOf(sizesRounded.get(Dpi.TVDPI)));
    }

    private void moveRoundedValuesToInputs() {
        xxxhdpiTextField.setText(String.valueOf(sizesRounded.get(Dpi.XXXHDPI)));
        xxhdpiTextField.setText(String.valueOf(sizesRounded.get(Dpi.XXHDPI)));
        xhdpiTextField.setText(String.valueOf(sizesRounded.get(Dpi.XHDPI)));
        hdpiTextField.setText(String.valueOf(sizesRounded.get(Dpi.HDPI)));
        mdpiTextField.setText(String.valueOf(sizesRounded.get(Dpi.MDPI)));
        ldpiTextField.setText(String.valueOf(sizesRounded.get(Dpi.LDPI)));
        tvdpiTextField.setText(String.valueOf(sizesRounded.get(Dpi.TVDPI)));
    }

    private void updateInputs(final JTextField currentDpiTextField) {
        for (final JTextField textField : dpiTextFieldsSet) {
            if (currentDpiTextField != null && textField != currentDpiTextField) {
                textField.setText(formatSize(sizes.get(dpiTextFieldsSet.indexOf(textField))));
            }
        }
    }

    private String formatSize(final float size) {
        String formattedSize;
        if (size == Math.ceil(size)) {
            formattedSize = String.valueOf(Math.round(size));
        } else {
            formattedSize = String.format("%.2f", size);
        }

        return formattedSize.replaceAll(",", ".");
    }

    private void removeValues() {
        for (final JTextField textField : dpiTextFieldsSet) {
            textField.setText("");
        }

        for (final JLabel label : roundedDpiLabelsSet) {
            label.setText(" ");
        }
    }

    private void initLists() {
        dpiTextFieldsSet = new ArrayList<>(Constants.DENSITIES_COUNT);
        dpiTextFieldsSet.add(Dpi.XXXHDPI, xxxhdpiTextField);
        dpiTextFieldsSet.add(Dpi.XXHDPI, xxhdpiTextField);
        dpiTextFieldsSet.add(Dpi.XHDPI, xhdpiTextField);
        dpiTextFieldsSet.add(Dpi.HDPI, hdpiTextField);
        dpiTextFieldsSet.add(Dpi.MDPI, mdpiTextField);
        dpiTextFieldsSet.add(Dpi.LDPI, ldpiTextField);
        dpiTextFieldsSet.add(Dpi.TVDPI, tvdpiTextField);

        roundedDpiLabelsSet = new ArrayList<>(Constants.DENSITIES_COUNT);
        roundedDpiLabelsSet.add(Dpi.XXXHDPI, roundedXxxhdpi);
        roundedDpiLabelsSet.add(Dpi.XXHDPI, roundedXxhdpi);
        roundedDpiLabelsSet.add(Dpi.XHDPI, roundedXhdpi);
        roundedDpiLabelsSet.add(Dpi.HDPI, roundedHdpi);
        roundedDpiLabelsSet.add(Dpi.MDPI, roundedMdpi);
        roundedDpiLabelsSet.add(Dpi.LDPI, roundedLdpi);
        roundedDpiLabelsSet.add(Dpi.TVDPI, roundedTvdpi);
    }

    private void onOK() {
        setVisible(false);
    }

    private void initListeners() {
        buttonOK.addActionListener(e -> onOK());

        KeyListener inputChangeListener = new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                    applyNewDensityValues((JTextField) keyEvent.getSource());
                }
            }
        };

        for (final JTextField textField : dpiTextFieldsSet) {
            textField.addKeyListener(inputChangeListener);
            textField.getInputContext().selectInputMethod(new Locale("en", "US"));
        }

        MouseListener roundedValuesLabelClick = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                moveRoundedValuesToInputs();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        for (final JLabel label : roundedDpiLabelsSet) {
            label.addMouseListener(roundedValuesLabelClick);
        }

    }

}
