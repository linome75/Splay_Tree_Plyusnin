package org.openjfx;

import Model.SplayTree;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.awt.*;

public class SplayTreePane extends Pane {
    private SplayTree<Integer> tree;
    private int radius = 20;
    private double hGap = 50;
    private double vGap = 50;

    SplayTreePane(SplayTree tree) {
        this.tree = tree;
        historyBar("Tree is empty");
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void historyBar(String message) {
        TextArea textrArea = new TextArea();
        getChildren().add(new Text(20, 20, message));
    }

    public void displayTree() {
        this.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), getWidth() / 2, getWidth() / 4, hGap);
        }
    }

    private void displayTree(SplayTree.Node node, double x, double y, double hGap) {
        if (node.left != null) {
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(node.left, x - hGap, y + vGap, hGap / 2);
        }
        if (node.right != null) {
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(node.right, x + hGap, y + vGap, hGap / 2);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.RED);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, String.valueOf(node.value)));
    }
}
