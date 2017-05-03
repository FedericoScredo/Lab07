package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	
	Model model;

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaTutti;
	@FXML
	private Button brnReset;

	@FXML
	void doReset(ActionEvent event) {
		txtResult.setText("");
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {

		try {
			txtResult.clear();
			txtResult.appendText(""+model.createGraph(Integer.parseInt(inputNumeroLettere.getText())).size()+"\n");
			for(String s: model.createGraph(Integer.parseInt(inputNumeroLettere.getText()))){
				txtResult.appendText(s+"\n");
			}
			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaTutti(ActionEvent event) {
		
		try {
			txtResult.clear();
			txtResult.appendText(""+model.displayAllNeighbours(inputParola.getText()).size()+"\n");
			for(String s:model.displayAllNeighbours(inputParola.getText())){
				txtResult.appendText(s+"\n");
			}
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		
		try {
			txtResult.clear();
			txtResult.appendText(""+model.displayNeighbours(inputParola.getText()).size()+"\n");
			for(String s: model.displayNeighbours(inputParola.getText())){
				txtResult.appendText(s+"\n");
			}
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}
	
    public void setModel(Model model) {
    	this.model=model;
    }

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaTutti != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
}