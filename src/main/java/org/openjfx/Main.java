package org.openjfx;

import Model.SplayTree;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
    SplayTree<Integer> tree = new SplayTree<>();
    SplayTree<Integer> lastTree = new SplayTree<>();

    @Override
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        SplitPane splitPane = new SplitPane();
        SplayTreePane treePane = new SplayTreePane(tree);
        SplayTreePane lastTreePane = new SplayTreePane(tree);
        splitPane.getItems().addAll(lastTreePane, treePane);
        pane.setCenter(splitPane);

        TextField textField = new TextField();
        textField.setPrefColumnCount(20);
        Button addBtn = new Button("Add");
        Button removeBtn = new Button("Remove");
        Button splayBtn = new Button("Splay");
        Button cleanBtn = new Button("Clean");
        Button addAllBtn = new Button("Add all");
        Button smaller = new Button("-");
        Button bigger = new Button("+");
        Button center = new Button("Center");
        center.setOnAction(event -> {
            treePane.displayTree();
            lastTreePane.displayTree();
        });
        smaller.setOnAction(event -> {
            treePane.smaller();
            lastTreePane.smaller();
        });
        bigger.setOnAction(event -> {
            treePane.bigger();
            lastTreePane.bigger();
        });
        Scene scene = new Scene(pane, 960, 540);
        controls(scene,textField, addBtn, removeBtn, splayBtn, cleanBtn, addAllBtn, tree, treePane, lastTreePane);
        FlowPane root = new FlowPane(Orientation.HORIZONTAL, 5, 5, new Label("Enter a value:"), textField, addBtn, removeBtn, splayBtn, cleanBtn, addAllBtn, smaller, bigger, center);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.maxHeight(40);
        pane.setTop(root);


        stage.setScene(scene);
        stage.setTitle("Splay Tree");
        stage.show();
    }

    public static void controls(Scene scene, TextField textField, Button add, Button remove, Button splay, Button clean, Button addAll, SplayTree tree, SplayTreePane pane, SplayTreePane lastTreePane) {
        add.setOnAction(event -> {
            if (textField.getText().length() != 0) {
                int key = Integer.parseInt(textField.getText());
                if (tree.contains(key) && !tree.isEmpty()) {
                    pane.message = key + " is already in tree";
                    pane.displayTree();
                    textField.clear();
                } else {
                    lastTreePane.message = pane.message;
                    lastTreePane.displayTree();
                    tree.add(key);
                    pane.message = key + " added";
                    pane.displayTree();
                    textField.clear();
                }

            }
        });
        addAll.setOnAction(event -> {
            String[] arr = textField.getText().split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i<arr.length; i++) {
                list.add(Integer.parseInt(arr[i]));
            }
            pane.message = Arrays.asList(arr) + " added";
            tree.addAll(list);
            pane.displayTree();
            textField.clear();
        });
        remove.setOnAction(event -> {
            if (textField.getText().length() != 0) {
                int key = Integer.parseInt(textField.getText());
                if (!tree.contains(key) && !tree.isEmpty()) {
                    pane.message = key + " isn't in tree";
                    pane.displayTree();
                    textField.clear();
                } else {
                    lastTreePane.message = pane.message;
                    lastTreePane.displayTree();
                    tree.remove(key);
                    pane.message = key + " removed";
                    pane.displayTree();
                    textField.clear();
                }
            }
        });

        splay.setOnAction(event -> {
            if (textField.getText().length() != 0) {
                int key = Integer.parseInt(textField.getText());
                lastTreePane.message = pane.message;
                lastTreePane.displayTree();
                tree.find(key);
                pane.message = key + " splayed";
                pane.displayTree();
                textField.clear();
            }
        });
        clean.setOnAction(event -> {
            lastTreePane.message = pane.message;
            lastTreePane.displayTree();
            tree.clear();
            pane.message = "Tree was cleared";
            pane.displayTree();
            textField.clear();
        });
        scene.setOnKeyPressed(event -> {
            char key = event.getText().toUpperCase().charAt(0);
            switch (key){
                case ('D') :
                    lastTreePane.left();
                    pane.left();
                    break;
                case ('A') :
                    lastTreePane.right();
                    pane.right();
                    break;
                case ('W') :
                    lastTreePane.upper();
                    pane.upper();
                    break;
                case ('S') :
                    lastTreePane.downer();
                    pane.downer();
                    break;
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}