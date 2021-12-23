package prova;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controllerstage2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label benvenutotxt;
    
    public void myFunction(String testonome) {
    	
    	benvenutotxt.setText(testonome +" hai eseguito l'accesso" );
    }
    
    @FXML
    void initialize() {
        assert benvenutotxt != null : "fx:id=\"benvenutotxt\" was not injected: check your FXML file 'accessoeseguito.fxml'.";
       
    }

}
