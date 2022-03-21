/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.librettovoti;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Libretto;
import model.Voto;

public class FXMLController {
	
	private Libretto model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="bntAggiungi"
    private Button bntAggiungi; // Value injected by FXMLLoader

    @FXML // fx:id="cmbPunti"
    private ComboBox<Integer> cmbPunti; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblErrore"
    private Label lblErrore; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtVoto"
    private TextArea txtVoto; // Value injected by FXMLLoader

    @FXML
    void handleNuovoVoto(ActionEvent event) {
    	
    	//1. Acquisizione e controllo dati
    	String nome = txtNome.getText();
    	Integer punti = cmbPunti.getValue();
    	
    	//controlli di validità
    	if(nome.equals("") || punti == null) {
    		//errore, non posso eseguire l'operazione 
    		txtVoto.setText("ERRORE: occorre inserire nome e voto\n");
    		return;
    	}
    	
    	//2. esecuzione dell'operazione (== chiedere al Model di farla)
    	boolean ok=model.add(new Voto(nome, punti));
    	
    	//3. Visualizzazione/aggiornamento del risultato
    	if(ok) {
        	List<Voto> voti=model.getVoti();
        	txtVoto.clear();
        	txtVoto.appendText("Hai superato "+ voti.size()+ " esami\n");
        	for(Voto vi: voti) {
        		txtVoto.appendText(vi.toString()+"\n");
        	}
        	txtNome.clear();
        	cmbPunti.setValue(null);
        	lblErrore.setText("");
    	} else {
    		lblErrore.setText("ERRORE");
    	}
    	
    	//Adesso è controller che decide come visualizzare i dati
    	
    }
    
    public void setModel(Libretto model) {
    	this.model = model;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert bntAggiungi != null : "fx:id=\"bntAggiungi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoto != null : "fx:id=\"txtVoto\" was not injected: check your FXML file 'Scene.fxml'.";
        //assert lblErrore != null : "fx:id=\"lblErrore\" was not injected: check your FXML file 'Scene.fxml'.";
        
        cmbPunti.getItems().clear();
        for(int p=18;p<=30;p++) {
        	cmbPunti.getItems().add(p);
        }

    }

}