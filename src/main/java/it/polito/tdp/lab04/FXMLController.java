package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	 Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbSelezionaCorso;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	
    	this.handleCompila(event);
    	
    	int matricola=Integer.parseInt(txtMatricola.getText());
    	
    	for(Corso c:this.model.getCorsiFromMatricola(matricola))
    		txtResult.appendText(c.toString()+"\n");

    }

    @FXML
    void handleCercaIscritti(ActionEvent event) {
    	
    	if(this.cmbSelezionaCorso.getValue()!=null) {
    		
    		txtResult.clear();
    		Corso c=this.cmbSelezionaCorso.getValue();
    	
    		for(Studente s:this.model.getStudentiIscrittiAlCorso(c))
    			txtResult.appendText(s.toString());
    	}
    	 
    	else 
    		txtResult.setText("Corso non selezionato");

    }

    @FXML
    void handleCompila(ActionEvent event) {
    	
    	if(txtMatricola.getText()==null || txtMatricola.getText().equals(" "))
    		return ;
    	try {
    		Integer matricola=Integer.parseInt(txtMatricola.getText());
    		txtCognome.setText(model.getStudenteFromMatricola(matricola).getCognome());
    		txtNome.setText(model.getStudenteFromMatricola(matricola).getNome());
    		
    	} catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.setText("Errore nel formato");
    	}
    	
    	
    }

    @FXML
    void handleIscrivi(ActionEvent event) {
    	
    	this.handleCompila(event);
    	
    	if(this.cmbSelezionaCorso.getValue()!=null) {
    		
    		
    		
    		Corso c=this.cmbSelezionaCorso.getValue();
    		Integer matricola=Integer.parseInt(txtMatricola.getText());
    		Studente s=this.model.getStudenteFromMatricola(matricola);
    		
    		if(this.model.iscriviStudenteACorso(s, c)==true)
    			this.txtResult.setText("Studente già iscritto al corso");
    		else
    			this.txtResult.setText("Studente iscritto ora al corso");
    		
    	}
    	 
    	else 
    		txtResult.setText("Corso non selezionato");
    	
    }

    @FXML
    void handleReset(ActionEvent event) {
    	txtCognome.clear();
    	txtNome.clear();
    	txtMatricola.clear();
    	txtResult.clear();
    	

    }

    @FXML
    void initialize() {
        assert cmbSelezionaCorso != null : "fx:id=\"cmbSelezionaCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        this.model=new Model();
        cmbSelezionaCorso.getItems().clear();
        
        for(Corso c:model.getTuttiICorsi()) {
        	cmbSelezionaCorso.getItems().add(c);
        
        }
        
        Corso co=null;
        cmbSelezionaCorso.getItems().add(co);
    }

}
