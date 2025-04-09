package com.example.tp_poo_kennedy;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label typeLabel = new Label("Type d'appareil :");
        TextField inputType = new TextField();
        HBox typeBox = new HBox(15, typeLabel, inputType);
        typeBox.setPadding(new Insets(10));

        Label serieLabel = new Label("Numéro de série :");
        TextField inputSerie = new TextField();
        HBox nBox = new HBox(15, serieLabel, inputSerie);
        nBox.setPadding(new Insets(10));

        Label marqueLabel = new Label("Marque :");
        TextField inputMarque = new TextField();
        HBox descBox = new HBox(15, marqueLabel, inputMarque);
        descBox.setPadding(new Insets(10));

        Button btnCheck = new Button("Vérifier");
        Button btnAjouter = new Button("Ajouter comme volé");
        Label result = new Label();
        VBox buttonBox = new VBox(15, btnCheck, btnAjouter, result);
        buttonBox.setAlignment(Pos.CENTER);

        btnCheck.setOnAction(e -> {
            String serie = inputSerie.getText();
            boolean estVole = VolDB.estVole(serie);
            result.setText(estVole ? "⚠️ Appareil volé !" : "✅ Aucun vol signalé.");
        });

        btnAjouter.setOnAction(e -> {
            String type = inputType.getText();
            String serie = inputSerie.getText();
            String desc = inputMarque.getText();
            VolDB.ajouterAppareil(new Appareil(type,serie, desc));
            result.setText("✅ Appareil ajouté !");
        });

        VBox root = new VBox(10, typeBox, nBox, descBox, buttonBox);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Détection appareil volé");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
