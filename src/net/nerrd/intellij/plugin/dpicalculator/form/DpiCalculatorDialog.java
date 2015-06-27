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
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<JTextField> dpiTextFieldsSet;

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

        initDpiTextFieldsSet();
        initListeners();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void applyNewDensityValues(final JTextField currentDpiTextField) {
        try {
            final float originalSize = Float.valueOf(currentDpiTextField.getText());
            final List<Float> ratios = Calculator.calculate(originalSize, dpiTextFieldsSet.indexOf(currentDpiTextField));

            for (final JTextField textField : dpiTextFieldsSet) {
                if (textField != currentDpiTextField) {
                    textField.setText(formatSize(ratios.get(dpiTextFieldsSet.indexOf(textField))));
                }
            }
        } catch (NumberFormatException ex) {
            currentDpiTextField.setToolTipText("Enter integers or floating point numbers");
        }
    }

    private String formatSize(final float size) {
        String formattedSize;
        if (size == Math.ceil(size)) {
            formattedSize = String.valueOf(Math.round(size));
        } else {
            formattedSize = String.format("%.2f", size);
        }

        return formattedSize;
    }

    private void initDpiTextFieldsSet() {
        dpiTextFieldsSet = new ArrayList<>(Constants.DENSITIES_COUNT);
        dpiTextFieldsSet.add(Dpi.XXXHDPI, xxxhdpiTextField);
        dpiTextFieldsSet.add(Dpi.XXHDPI, xxhdpiTextField);
        dpiTextFieldsSet.add(Dpi.XHDPI, xhdpiTextField);
        dpiTextFieldsSet.add(Dpi.HDPI, hdpiTextField);
        dpiTextFieldsSet.add(Dpi.MDPI, mdpiTextField);
        dpiTextFieldsSet.add(Dpi.LDPI, ldpiTextField);
        dpiTextFieldsSet.add(Dpi.TVDPI, tvdpiTextField);
    }

    private void onOK() {
        setVisible(false);
    }

    private void initListeners() {
        buttonOK.addActionListener(e -> onOK());

        xxxhdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                    applyNewDensityValues(xxxhdpiTextField);
                }
            }
        });

        xxhdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                applyNewDensityValues(xxhdpiTextField);
            }
        });

        xhdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                applyNewDensityValues(xhdpiTextField);
            }
        });

        hdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                applyNewDensityValues(hdpiTextField);
            }
        });

        mdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                applyNewDensityValues(mdpiTextField);
            }
        });

        ldpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                applyNewDensityValues(ldpiTextField);
            }
        });

        tvdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                applyNewDensityValues(tvdpiTextField);
            }
        });
    }

}
