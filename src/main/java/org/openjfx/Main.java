package org.openjfx;

import Model.SplayTree;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SplayTree<Integer> tree = new SplayTree<>();
        BorderPane pane = new BorderPane();
        SplayTreePane treePane = new SplayTreePane(tree);
        pane.setCenter(treePane);
        TextField textField = new TextField();
        textField.setPrefColumnCount(20);
        Button addBtn = new Button("Add");
        Button removeBtn = new Button("Remove");
        Button splayBtn = new Button("Splay");
        controls(textField, addBtn, removeBtn, splayBtn, tree, treePane);
        FlowPane root = new FlowPane(Orientation.HORIZONTAL, 5, 5, new Label("Enter a value:"), textField, addBtn, removeBtn, splayBtn);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.maxHeight(40);
        pane.setTop(root);

        Scene scene = new Scene(pane, 960, 540);
        stage.setScene(scene);
        stage.setTitle("Splay Tree");
        stage.show();
    }

    public static void controls(TextField textField, Button add, Button remove, Button splay, SplayTree tree, SplayTreePane pane) {
        add.setOnAction(event -> {
            if (textField.getText().length() != 0) {
                int key = Integer.parseInt(textField.getText());
                if (tree.contains(key)) pane.historyBar(key + " is already in tree");
                else {
                    pane.historyBar(key + " added");
                    tree.add(key);
                }
                pane.displayTree();
                textField.clear();
            }
        });

        remove.setOnAction(event -> {
            if (textField.getText().length() != 0) {
                int key = Integer.parseInt(textField.getText());
                if (!tree.contains(key)) pane.historyBar(key + " isn't in tree");
                else {
                    tree.remove(key);
                    pane.historyBar(key + " removed");
                }
                pane.displayTree();
                textField.clear();
            }
        });

        splay.setOnAction(event -> {
            if (textField.getText().length() != 0) {
                int key = Integer.parseInt(textField.getText());
                tree.find(key);
                pane.displayTree();
                textField.clear();
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }

}