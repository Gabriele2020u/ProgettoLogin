package prova;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;

public class Controller {

	private int numerotentativi = 3;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Button;

    @FXML
    private Label Name;

    @FXML
    private Label Repeat;

    @FXML
    private TextField User;

    @FXML
    private TextField Password;
    

    @FXML
    private Label passwordvuota;

    @FXML
    private Label usernamevuoto;
    
    @FXML
    void handleconferma(ActionEvent event) {
    	
    	String pass = Password.getText();
    	String cipherpass = "";
    	try {
    	MessageDigest md = MessageDigest.getInstance("md5");
    	md.update(pass.getBytes(),0,pass.length());
    	BigInteger bigInt = new BigInteger(1,md.digest());
    	cipherpass = bigInt.toString(16);
    	 
    	}
    	catch (NoSuchAlgorithmException e){
    		e.printStackTrace();
    	}
    	UtenteDAO utenteDAO = new UtenteDAOimpl();
    	String user = User.getText();
    	if (numerotentativi <= 0 ) {
    		
    		try {
   			 Parent root3 = FXMLLoader.load(getClass().getResource("accessonegato.fxml"));
   			 Stage stage3 = new Stage();
   			 stage3.setTitle("accesso negato");
   		     stage3.setScene(new Scene(root3, 400, 200));
   		     stage3.show();
   		     ((Node)(event.getSource())).getScene().getWindow().hide();
   		
    		}
    		catch (Exception e) {
    			System.out.println("errore apertura finestra accesso negato");
    			e.printStackTrace();
   			}
    	}
    	else if (utenteDAO.controllautente(user , cipherpass)) {
    		
    		Utente utente = utenteDAO.getutente(user,cipherpass) ;
    		try {
    			 FXMLLoader loader = new FXMLLoader(getClass().getResource("accessoeseguito.fxml"));
    			 Parent root2 = (Parent) loader.load();
    			 Stage stage2 = new Stage();
    			 Controllerstage2 secController =  loader.getController();
    			 secController.myFunction(utente.getNome() + " " + utente.getCognome());
    			 stage2.setTitle("accesso eseguito");
    		     stage2.setScene(new Scene(root2, 380, 200));
    		     stage2.show();
    		     ((Node)(event.getSource())).getScene().getWindow().hide();
    		}
    		catch (Exception e) {
    			System.out.println("errore apertura finestra accesso eseguito");
    			e.printStackTrace();
    		}
    	}
    	else {
    		 
    		if (User.getText().isBlank()) {
    			usernamevuoto.setText("inserire username valido");
    		}
    		else {
    			usernamevuoto.setText(" ");
    		}
    		if (Password.getText().isBlank()) {
    			passwordvuota.setText("inserire password valida");
    		}
    		else {
    			passwordvuota.setText("");
    		}
    		String stringarepeat = " credenziali errate, riprovare. tentativi rimasti: " + numerotentativi ;
    		Repeat.setText(stringarepeat);
    		numerotentativi--;
    	}
    	
    	
    }

    @FXML
    void initialize() {
    	
    	 assert Button != null : "fx:id=\"Button\" was not injected: check your FXML file 'main.fxml'.";
         assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'main.fxml'.";
         assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'main.fxml'.";
         assert Repeat != null : "fx:id=\"Repeat\" was not injected: check your FXML file 'main.fxml'.";
         assert User != null : "fx:id=\"User\" was not injected: check your FXML file 'main.fxml'.";
         assert passwordvuota != null : "fx:id=\"passwordvuota\" was not injected: check your FXML file 'main.fxml'.";
         assert usernamevuoto != null : "fx:id=\"usernamevuoto\" was not injected: check your FXML file 'main.fxml'.";

    	}
    
}