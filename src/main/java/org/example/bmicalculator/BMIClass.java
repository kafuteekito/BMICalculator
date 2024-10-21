package org.example.bmicalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BMIClass {

    @FXML
    private TextField fieldWeight, fieldHeight, fieldBMIValue, fieldStatus;
    @FXML
    private Button CalculateButton, ChangeMU;
    @FXML
    private MenuItem clear, Exit;
    @FXML
    private Text heightText, weightText, MainText, BMIValueText, StatusText, close;
    @FXML
    private AnchorPane pane;
    @FXML
    private ToggleGroup unitToggleGroup;

    private boolean isMetric = true; // Default unit system is metric

    /**
     * Calculate the BMI based on the user inputs (weight, height) and selected units.
     * Metric: BMI = weight / (height^2)
     * English: BMI = 703 * weight / (height^2)
     */
    @FXML
    public void calculate(ActionEvent event) {
        try {
            // Retrieve user inputs
            double weight = Double.parseDouble(fieldWeight.getText());
            double height = Double.parseDouble(fieldHeight.getText());
            double BMIValue;

            // Calculate BMI depending on selected units
            if (isMetric) {
                BMIValue = weight / ((height / 100) * (height / 100)); // cm to meters
            } else {
                BMIValue = 703 * (weight / (height * height)); // Imperial units
            }

            // Display the result in appropriate fields
            setResult(BMIValue);
            fieldWeight.clear();
            fieldHeight.clear();
        } catch (NumberFormatException e) {
            // Handle invalid inputs
            fieldStatus.setText("Invalid input. Please enter numbers.");
        }
    }

    /**
     * Changes the unit system between Metric (kg/cm) and English (lbs/inches).
     * It also updates the prompt texts accordingly.
     */
    @FXML
    public void change(ActionEvent event) {
        isMetric = !isMetric;

        if (isMetric) {
            fieldHeight.setPromptText("(cm)");
            fieldWeight.setPromptText("(kg)");
            ChangeMU.setText("Change to English Units");
        } else {
            fieldHeight.setPromptText("(inches)");
            fieldWeight.setPromptText("(lbs)");
            ChangeMU.setText("Change to Metric Units");
        }
    }

    /**
     * Displays the BMI value and its status (Underweight, Normal, Overweight, Obese).
     * @param BMIValue calculated BMI
     */
    void setResult(Double BMIValue) {
        fieldBMIValue.setText(String.format("%.2f", BMIValue));

        // Determine BMI status based on the value
        if (BMIValue <= 18.5) {
            fieldStatus.setText("Underweight");
        } else if (BMIValue >= 18.6 && BMIValue <= 24.9) {
            fieldStatus.setText("Normal");
        } else if (BMIValue >= 25 && BMIValue <= 29.9) {
            fieldStatus.setText("Overweight");
        } else {
            fieldStatus.setText("Obese");
        }
    }

    //Clears all the input fields (height, weight) and result fields (BMI value, status).
    @FXML
    void clearText(ActionEvent event) {
        fieldWeight.clear();
        fieldHeight.clear();
        fieldBMIValue.clear();
        fieldStatus.clear();
    }

    // Closes the application.
    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    // Handles the close button (X icon) for closing the application.
    @FXML
    void close(MouseEvent event) {
        System.exit(0);
    }

    // Displays the Help dialog with instructions on how to use the BMI calculator.
    @FXML
    void HelpText(ActionEvent event) {
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
        helpAlert.setTitle("Help");
        helpAlert.setHeaderText("How to use the BMI Calculator");
        helpAlert.setContentText("Enter your height and weight in the fields provided.\n" +
                "You can switch between Metric (kg/cm) and Imperial (lbs/inches) units using the button.");
        helpAlert.showAndWait();
    }
}
