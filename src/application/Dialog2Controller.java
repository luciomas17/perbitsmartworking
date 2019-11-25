package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Model;

public class Dialog2Controller {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtDialog;

    @FXML
    void doNo(ActionEvent event) {
    	model.setOverwrite(false);
    	Node  source = (Node) event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doYes(ActionEvent event) {
    	model.setOverwrite(true);
    	Node  source = (Node) event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

    @FXML
    void initialize() {
        assert txtDialog != null : "fx:id=\"txtDialog\" was not injected: check your FXML file 'dialog2FXML.fxml'.";

    }

	public void setTxtDialog(String dialog) {
		this.txtDialog.setText(dialog);
	}
}