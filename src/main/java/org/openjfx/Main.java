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

public class Main extends Application {
    SplayTree<Integer> tree = new SplayTree<>();
    @Override
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        //SplitPane splitPane = new SplitPane();
        SplayTreePane treePane = new SplayTreePane(tree);
        //SplayTreePane lastTreePane = new SplayTreePane(tree);
        //splitPane.getItems().addAll(lastTreePane, treePane);
        pane.setCenter(treePane);

        TextField textField = new TextField();
        textField.setPrefColumnCount(20);
        Button addBtn = new Button("Add");
        Button removeBtn = new Button("Remove");
        Button splayBtn = new Button("Splay");
        Button cleanBtn = new Button("Clean");
        Button addAllBtn = new Button("Add all");
        Scene scene = new Scene(pane, 960, 540);
        controls(scene, textField, addBtn, removeBtn, splayBtn, cleanBtn, addAllBtn, tree, treePane);
        FlowPane root = new FlowPane(Orientation.HORIZONTAL, 5, 5, new Label("Enter a value:"), textField, addBtn, removeBtn, splayBtn, cleanBtn, addAllBtn);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.maxHeight(40);
        pane.setTop(root);


        stage.setScene(scene);
        stage.setTitle("Splay Tree");
        stage.show();
    }

    public static void controls(Scene scene, TextField textField, Button add, Button remove, Button splay, Button clean, Button addAll, SplayTree tree, SplayTreePane pane) {
        add.setOnAction(event -> {
            if (textField.getText().length() != 0) {
                Integer key = tryParse(textField.getText());
                if (key == null) pane.message = "Wrong data";
                else if (tree.contains(key)) {
                    pane.message = key + " is already in tree";
                } else {
                    //lastTreePane.message = pane.message;
                    //lastTreePane.displayTree();
                    tree.add(key);
                    pane.message = key + " added";
                }
                pane.displayTree();
                textField.clear();
            }
        });
        addAll.setOnAction(event -> {
            String[] arr = textField.getText().split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            for (String s : arr) {
                Integer key = tryParse(s);
                if (key == null) {
                    pane.message = "Wrong data";
                    pane.displayTree();
                    textField.clear();
                    return;
                }
                list.add(Integer.parseInt(s));
            }
            pane.message = Arrays.asList(arr) + " added";
            tree.addAll(list);
            pane.displayTree();
            textField.clear();
        });
        remove.setOnAction(event -> {
            if (textField.getText().length() != 0) {
                Integer key = tryParse(textField.getText());
                if (key == null) pane.message = "Wrong data";
                else if (!tree.contains(key) && !tree.isEmpty()) {
                    pane.message = key + " isn't in tree";

                } else {
                    //lastTreePane.message = pane.message;
                    //lastTreePane.displayTree();
                    tree.remove(key);
                    pane.message = key + " removed";

                }
                pane.displayTree();
                textField.clear();
            }
        });

        splay.setOnAction(event -> {
            if (textField.getText().length() != 0) {
                Integer key = tryParse(textField.getText());
                if (key == null) pane.message = "Wrong data";
                else {
                    //lastTreePane.message = pane.message;
                    //lastTreePane.displayTree();
                    tree.find(key);
                    pane.message = key + " splayed";
                }
                pane.displayTree();
                textField.clear();
            }
        });
        clean.setOnAction(event -> {
            //lastTreePane.message = pane.message;
            //lastTreePane.displayTree();
            tree.clear();
            pane.message = "Tree was cleared";
            pane.displayTree();
            textField.clear();
        });
        scene.setOnKeyPressed(event -> {
            char key = event.getText().toUpperCase().charAt(0);
            switch (key) {
                case ('D') -> {
                    //lastTreePane.left();
                    pane.left();
                }
                case ('A') -> {
                    //lastTreePane.right();
                    pane.right();
                }
                case ('W') -> {
                    //lastTreePane.upper();
                    pane.upper();
                }
                case ('S') -> {
                    //lastTreePane.downer();
                    pane.downer();
                }
                case ('E') -> {
                    pane.bigger();
                }
                case ('Q') -> {
                    //lastTreePane.smaller();
                    pane.smaller();
                }
            }
        });
    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        launch();
    }

}