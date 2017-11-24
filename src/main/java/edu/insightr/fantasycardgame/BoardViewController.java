package edu.insightr.fantasycardgame;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class BoardViewController implements Initializable{

    @FXML
    private ImageView deck;

    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stu
        // Reflechir sur quand on clique sur le deck, une carte sort aléatoirement
    }

    public void getACard()
    {
        //System.out.println("on clique");
        int cardId = (int) (Math.random() * 6 );
        System.out.println(cardId);
        Card card;
        switch(cardId){
            case 0:
                card = new Card(Card.Race.Korrigan);
                break;
            case 1:
                card = new Card(Card.Race.Troll);
                break;
            case 2:
                card = new Card(Card.Race.Goblin);
                break;
            case 3:
                card = new Card(Card.Race.Elf);
                break;
            case 4:
                card = new Card(Card.Race.Dryad);
                break;
            case 5:
                card = new Card(Card.Race.Gnome);
                break;
        }
    }
}
