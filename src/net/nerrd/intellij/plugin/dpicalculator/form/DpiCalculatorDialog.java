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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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

        initDpiTextFieldsSet();
        initListeners();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void applyNewDensityValues(JTextField currentDpiTextField) {
        // TODO: logic to calculate values for all densities
    }

    private void initDpiTextFieldsSet() {
        dpiTextFieldsSet = new ArrayList<>();
        dpiTextFieldsSet.add(xxxhdpiTextField);
        dpiTextFieldsSet.add(xxhdpiTextField);
        dpiTextFieldsSet.add(xhdpiTextField);
        dpiTextFieldsSet.add(hdpiTextField);
        dpiTextFieldsSet.add(mdpiTextField);
        dpiTextFieldsSet.add(ldpiTextField);
        dpiTextFieldsSet.add(tvdpiTextField);
    }

    private void removeNonNumericValues(JTextField textField) {
        textField.setText(textField.getText().toString().replaceAll("\\D++", ""));
    }

    private void onOK() {
        dispose();
    }

    private void initListeners() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                onOK();
            }
        });

        xxxhdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {

            }

            @Override
            public void keyPressed(final KeyEvent e) {

            }

            @Override
            public void keyReleased(final KeyEvent e) {
                removeNonNumericValues(xxxhdpiTextField);
                applyNewDensityValues(xxxhdpiTextField);
            }
        });

        xxhdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {

            }

            @Override
            public void keyPressed(final KeyEvent e) {

            }

            @Override
            public void keyReleased(final KeyEvent e) {
                removeNonNumericValues(xxhdpiTextField);
                applyNewDensityValues(xxhdpiTextField);
            }
        });

        xhdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {

            }

            @Override
            public void keyPressed(final KeyEvent e) {

            }

            @Override
            public void keyReleased(final KeyEvent e) {
                removeNonNumericValues(xhdpiTextField);
                applyNewDensityValues(xhdpiTextField);
            }
        });

        hdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {

            }

            @Override
            public void keyPressed(final KeyEvent e) {

            }

            @Override
            public void keyReleased(final KeyEvent e) {
                removeNonNumericValues(hdpiTextField);
                applyNewDensityValues(hdpiTextField);
            }
        });

        mdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {

            }

            @Override
            public void keyPressed(final KeyEvent e) {

            }

            @Override
            public void keyReleased(final KeyEvent e) {
                removeNonNumericValues(mdpiTextField);
                applyNewDensityValues(mdpiTextField);
            }
        });

        ldpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {

            }

            @Override
            public void keyPressed(final KeyEvent e) {

            }

            @Override
            public void keyReleased(final KeyEvent e) {
                removeNonNumericValues(ldpiTextField);
                applyNewDensityValues(ldpiTextField);
            }
        });

        tvdpiTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {

            }

            @Override
            public void keyPressed(final KeyEvent e) {

            }

            @Override
            public void keyReleased(final KeyEvent e) {
                removeNonNumericValues(tvdpiTextField);
                applyNewDensityValues(tvdpiTextField);
            }
        });
    }

}
