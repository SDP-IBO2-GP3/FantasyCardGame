package edu.insightr.fantasycardgame;


import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class BoardViewController implements Initializable{

    @FXML
    private ImageView deck;

    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stu
        // Reflechir sur quand on clique sur le deck, une carte sort aléatoirement
    }

    public void getACard(MouseEvent e)
    {
        deck.setRotate(43);
    }
}
