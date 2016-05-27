package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.awt.*;

/**
 * Created by novsky on 25.08.2015.
 */
public class ResultBox {

    public static void display (String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMaxWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Ok, Спасибо!");
        closeButton.setOnAction(e -> window.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }


}
