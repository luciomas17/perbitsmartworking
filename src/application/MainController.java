package application;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Model;

public class MainController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtANUUser;

    @FXML
    private TextField txtANUName;

    @FXML
    private TextField txtANUSurname;

    @FXML
    private TextField txtANUEmail;

    @FXML
    private ComboBox<String> boxANUEmail;

    @FXML
    private ComboBox<String> boxANUDivision;

    @FXML
    private ComboBox<String> boxANUResponsible;

    @FXML
    private ComboBox<String> boxANURole;

    @FXML
    private ComboBox<String> boxANUProvince;

    @FXML
    private ComboBox<String> boxANUCity;

    @FXML
    private TextField txtANUCO2emissions;

    @FXML
    private ComboBox<String> boxANUFuelType;

    @FXML
    private CheckBox boxANUConsent;

    @FXML
    private TextField txtUsersUser;

    @FXML
    private ComboBox<String> boxUsersDivisions;

    @FXML
    private TextArea txtUsersOutput;

    @FXML
    private TextField txtDataUser;

    @FXML
    private TextArea txtDataOutput;

    @FXML
    void addItemsToANUBoxCity(ActionEvent event) {
    	this.boxANUCity.getItems().clear();
    	String province = this.boxANUProvince.getSelectionModel().getSelectedItem();
    	if(!province.equals("")) {
	    	List<String> temp = model.getCitiesFromProvince(province);
	    	Collections.sort(temp);
	    	this.boxANUCity.getItems().addAll(temp);
    	}
    }

    @FXML
    void addItemsToANUBoxResponsible(ActionEvent event) {
    	this.boxANUResponsible.getItems().clear();
    	String division = this.boxANUDivision.getSelectionModel().getSelectedItem();
    	if(!division.equals("")) {
	    	List<String> temp = model.getResponsibleFromDivision(division);
	    	Collections.sort(temp);
	    	temp.add("Other");
	    	this.boxANUResponsible.getItems().addAll(temp);
    	}
    }

    @FXML
    void doANUReset(ActionEvent event) {

    }

    @FXML
    void doANUSubmit(ActionEvent event) {

    }

    @FXML
    void doDataFilterByCO2SavedADay(ActionEvent event) {

    }

    @FXML
    void doDataFilterByCO2SavedAYear(ActionEvent event) {

    }

    @FXML
    void doDataFilterByKmsSavedADay(ActionEvent event) {

    }

    @FXML
    void doDataFilterByKmsSavedAYear(ActionEvent event) {

    }

    @FXML
    void doDataFindUserByUsername(ActionEvent event) {

    }

    @FXML
    void doUsersFilterByDivision(ActionEvent event) {

    }

    @FXML
    void doUsersFindUserByUsername(ActionEvent event) {

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	addItemsToBoxANUEmail();
    	addItemsToBoxANUDivision();
    	addItemsToBoxANURole();
    	addItemsToBoxANUProvince();
    	addItemsToBoxANUFuelType();
    	addItemsToBoxUsersDivisions();
    }

    private void addItemsToBoxUsersDivisions() {
		List<String> temp = model.getDivisions();
		Collections.sort(temp);
		temp.add("Other");
		this.boxUsersDivisions.getItems().addAll(temp);
	}

	private void addItemsToBoxANUFuelType() {
		List<String> temp = model.getFuelTypes();
		Collections.sort(temp);
		this.boxANUFuelType.getItems().addAll(temp);
	}

	private void addItemsToBoxANUProvince() {
		List<String> temp = model.getProvinces();
		Collections.sort(temp);
		this.boxANUProvince.getItems().addAll(temp);
	}

	private void addItemsToBoxANURole() {
		List<String> temp = model.getRoles();
		Collections.sort(temp);
		temp.add("Other");
		this.boxANURole.getItems().addAll(temp);
	}

	private void addItemsToBoxANUDivision() {
		List<String> temp = model.getDivisions();
		Collections.sort(temp);
		temp.add("Other");
		this.boxANUDivision.getItems().addAll(temp);
	}

	private void addItemsToBoxANUEmail() {
		List<String> temp = model.getEmailDomains();
		Collections.sort(temp);
		this.boxANUEmail.getItems().addAll(temp);
	}

	@FXML
    void initialize() {
        assert txtANUUser != null : "fx:id=\"txtANUUser\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUName != null : "fx:id=\"txtANUName\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUSurname != null : "fx:id=\"txtANUSurname\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUEmail != null : "fx:id=\"txtANUEmail\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUEmail != null : "fx:id=\"boxANUEmail\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUDivision != null : "fx:id=\"boxANUDivision\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUResponsible != null : "fx:id=\"boxANUResponsible\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANURole != null : "fx:id=\"boxANURole\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUProvince != null : "fx:id=\"boxANUProvince\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUCity != null : "fx:id=\"boxANUCity\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUCO2emissions != null : "fx:id=\"txtANUCO2emissions\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUFuelType != null : "fx:id=\"boxANUFuelType\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxANUConsent != null : "fx:id=\"boxANUConsent\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtUsersUser != null : "fx:id=\"txtUsersUser\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert boxUsersDivisions != null : "fx:id=\"boxUsersDivisions\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtUsersOutput != null : "fx:id=\"txtUsersOutput\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtDataUser != null : "fx:id=\"txtDataUser\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtDataOutput != null : "fx:id=\"txtDataOutput\" was not injected: check your FXML file 'pswFXML.fxml'.";

    }
}
