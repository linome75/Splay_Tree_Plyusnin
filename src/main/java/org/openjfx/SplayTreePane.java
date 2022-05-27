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
    private double radius = 20;
    private double hGap = 50;
    private double vGap = 50;
    private double c = 20;
    private double center = getWidth()/2;
    private double height = getHeight()/8;
    public String message;

    SplayTreePane(SplayTree tree) {
        this.tree = tree;
        historyBar("Tree is empty");
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void historyBar(String message) {
        getChildren().add(new Text(20, 20, message));
    }

    public void displayTree() {
        this.getChildren().clear();
        historyBar(message);
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), center, height, hGap);
        }
    }

    private void displayTree(SplayTree.Node node, double x, double y, double hGap) {
        if (node.left != null) {
            if (hGap < 12) hGap = 50;
            getChildren().add(new Line(x - hGap - c, y + vGap, x, y));
            displayTree(node.left, x - hGap - c, y + vGap, hGap / 2);
        }
        if (node.right != null) {
            getChildren().add(new Line(x + hGap + c, y + vGap, x, y));
            displayTree(node.right, x + hGap + c, y + vGap, hGap / 2);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.RED);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, String.valueOf(node.value)));
    }

    public void smaller(){
        radius = radius*0.75;
        hGap = hGap *0.75;
        vGap = vGap*0.75;
        c = c*0.75;
        displayTree();
    }
    public void bigger(){
        radius = radius*1.25;
        hGap = hGap *1.25;
        vGap = vGap*1.25;
        c = c*1.25;
        displayTree();
    }
    public void left(){
        center -= 50;
        displayTree();
    }
    public void right(){
        center+=50;
        displayTree();
    }
    public void upper(){
        height+=50;
        displayTree();
    }
    public void downer(){
        height-=50;
        displayTree();
    }
}
