package org.example.bmicalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BMIClass {

    @FXML
    private Text BMIValueText;

    @FXML
    private Button CalculateButton;

    @FXML
    private Text MainText;

    @FXML
    private Text StatusText;

    @FXML
    private Text close;

    @FXML
    private TextField fieldBMIValue;

    @FXML
    private TextField fieldStatus;

    @FXML
    private TextField fieldcm;

    @FXML
    private TextField fieldkg;

    @FXML
    private Text heightText;

    @FXML
    private AnchorPane pane;

    @FXML
    private Text weightText;

    @FXML
    void calculate(ActionEvent event) {
        try {
            Double weight = Double.parseDouble(fieldkg.getText());
            Double height = Double.parseDouble(fieldcm.getText());
            Double BMIValue = weight / ((height/100) * (height/100));
            fieldkg.clear();
            fieldcm.clear();
            setResult(Double.valueOf(String.format("%.2f", BMIValue)));
        } catch (Exception e) {

        }
    }

    void setResult(Double BMIValue) {
        fieldBMIValue.setText(String.valueOf(BMIValue));
        if(BMIValue <= 18.5) {
            fieldStatus.setText("Underweight");
        } else if(BMIValue >= 18.6 && BMIValue <= 24.9) {
            fieldStatus.setText("Normal");
        } else if(BMIValue >= 25 && BMIValue <= 29.9) {
            fieldStatus.setText("Overweight");
        } else if(BMIValue >= 30 && BMIValue <= 39.9) {
            fieldStatus.setText("Obese");
        }
    }

    @FXML
    void close(MouseEvent event) {
        System.exit(0);
    }

}