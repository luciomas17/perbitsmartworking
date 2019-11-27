package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Data;
import model.Model;
import model.User;

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
    private TextField txtSmartDays;
    
    @FXML
    private TextField txtANUZip;

    @FXML
    private TextField txtANUStreet;

    @FXML
    private TextField txtANUNumber;

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
    	this.txtANUUser.clear();
    	this.txtANUName.clear();
    	this.txtANUSurname.clear();
    	this.txtANUEmail.clear();
    	this.boxANUEmail.getSelectionModel().clearSelection();
    	this.boxANUDivision.getSelectionModel().clearSelection();
    	this.boxANUResponsible.getSelectionModel().clearSelection();
    	this.boxANURole.getSelectionModel().clearSelection();
    	this.boxANUProvince.getSelectionModel().clearSelection();
    	this.boxANUCity.getSelectionModel().clearSelection();
    	this.txtSmartDays.clear();
    	this.boxANUFuelType.getSelectionModel().clearSelection();
    	this.txtANUCO2emissions.clear();
    	this.boxANUConsent.setSelected(false);
    }

	@FXML
    void doANUSubmit(ActionEvent event) {
    	String user = this.txtANUUser.getText().toUpperCase();
    	if(user.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your username please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	String name = this.txtANUName.getText().toUpperCase();
    	if(name.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your name please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	String surname = this.txtANUSurname.getText().toUpperCase();
    	if(surname.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your surname please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	String email = this.txtANUEmail.getText().toLowerCase();
    	if(email.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your email please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	email += "@";
    	if(this.boxANUEmail.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your email domain please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} else
    		email += this.boxANUEmail.getSelectionModel().getSelectedItem();
    	
    	String division = "";
    	if(this.boxANUDivision.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your division please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
			division = this.boxANUDivision.getSelectionModel().getSelectedItem();
    	
    	String responsible = "";
    	if(this.boxANUResponsible.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your responsible please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
			responsible = this.boxANUResponsible.getSelectionModel().getSelectedItem();
    	
    	String role = "";
    	if(this.boxANURole.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your role please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
			role = this.boxANURole.getSelectionModel().getSelectedItem();
    	
    	String province = "";
    	if(this.boxANUProvince.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your domicile province please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
			province = this.boxANUProvince.getSelectionModel().getSelectedItem();
    	String city = "";
    	if(this.boxANUCity.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your domicile city please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
			city = this.boxANUCity.getSelectionModel().getSelectedItem().toUpperCase();
    	int zip = 0;
    	if(this.txtANUZip.getText().equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your domicile zip code please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else {
			try {
				zip = Integer.parseInt(this.txtANUZip.getText());
			} catch (NumberFormatException e1) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
					BorderPane root = loader.load();
					DialogController controller = loader.getController();
					controller.setTxtDialog("Error: incorrect zip code number format.");
					Parent content = root;
					Scene scene = new Scene(content);
					Stage window = new Stage();
					window.setScene(scene);
					window.show();
					return;
					
				} catch (IOException e) {
					e.printStackTrace();
				} 
				e1.printStackTrace();
			}
    	}
    	String street = "";
    	if(this.txtANUStreet.getText().equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your domicile street please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
    		street = this.txtANUStreet.getText().toUpperCase();
    	String number = "";
    	if(this.txtANUNumber.getText().equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your domicile number please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
    		number = this.txtANUNumber.getText().toUpperCase();
    	String address = zip + " " + street + " " + number;
    	
    	int smartDays = 0;
    	if(this.txtSmartDays.getText().equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your smart days count in 2019 please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else {
			try {
				smartDays = Integer.parseInt(this.txtSmartDays.getText());
			} catch (NumberFormatException e1) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
					BorderPane root = loader.load();
					DialogController controller = loader.getController();
					controller.setTxtDialog("Error: incorrect smart days number format.");
					Parent content = root;
					Scene scene = new Scene(content);
					Stage window = new Stage();
					window.setScene(scene);
					window.show();
					return;
					
				} catch (IOException e) {
					e.printStackTrace();
				} 
				e1.printStackTrace();
			}
    	}
    	
    	String fuelType = "";
    	if(this.boxANUFuelType.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select your car's fuel type please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
			fuelType = this.boxANUFuelType.getSelectionModel().getSelectedItem();
    	
    	double gramsOfCO2 = 0;
    	if(this.txtANUCO2emissions.getText().equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type your car's CO₂ emissions please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else {
			try {
				gramsOfCO2 = Double.parseDouble(this.txtANUCO2emissions.getText());
			} catch (NumberFormatException e1) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
					BorderPane root = loader.load();
					DialogController controller = loader.getController();
					controller.setTxtDialog("Error: incorrect CO₂ emissions number format.");
					Parent content = root;
					Scene scene = new Scene(content);
					Stage window = new Stage();
					window.setScene(scene);
					window.show();
					return;
					
				} catch (IOException e) {
					e.printStackTrace();
				} 
				e1.printStackTrace();
			}
    	}
    	
    	boolean consent = false;
    	if(!this.boxANUConsent.isSelected()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("We need you to give your consent to process these sensitive data to continue.\n"
						+ "If you agree with this, check the box please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	} else
    		consent = true;
    	
    	if(model.isUserPresent(user)) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialog2FXML.fxml"));
				BorderPane root = loader.load();
				Dialog2Controller controller = loader.getController();
				controller.setTxtDialog("User already exists.\n"
						+ "Do you want to overwrite existing data?");
				controller.setModel(model);
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.showAndWait();
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    		
    		if(model.isOverwrite()) {
				model.editExistingUser(user, name, surname, email, division, responsible, role, fuelType, gramsOfCO2, province, city, address, smartDays, consent);
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
					BorderPane root = loader.load();
					DialogController controller = loader.getController();
					controller.setTxtDialog("User updated!");
					Parent content = root;
					Scene scene = new Scene(content);
					Stage window = new Stage();
					window.setScene(scene);
					window.show();
		
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
    	} else {
    		model.addNewUser(user, name, surname, email, division, responsible, role, fuelType, gramsOfCO2, province, city, address, smartDays, consent);
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("User added!");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    }

    @FXML
    void doDataFilterByCO2SavedADay(ActionEvent event) {
    	this.txtDataOutput.clear();
    	List<Data> result = model.getDataListOrderedByGramsOfCO2SavedADay();
    	StringBuilder sb = this.reformatData(result);
    	this.txtDataOutput.appendText(sb.toString());
    }

    @FXML
    void doDataFilterByCO2SavedAYear(ActionEvent event) {
    	this.txtDataOutput.clear();
    	List<Data> result = model.getDataListOrderedByGramsOfCO2SavedAYear();
    	StringBuilder sb = this.reformatData(result);
    	this.txtDataOutput.appendText(sb.toString());
    }

    @FXML
    void doDataFilterByKmsSavedADay(ActionEvent event) {
    	this.txtDataOutput.clear();
    	List<Data> result = model.getDataListOrderedByKmsSavedADay();
    	StringBuilder sb = this.reformatData(result);
    	this.txtDataOutput.appendText(sb.toString());
    }

    @FXML
    void doDataFilterByKmsSavedAYear(ActionEvent event) {
    	this.txtDataOutput.clear();
    	List<Data> result = model.getDataListOrderedByKmsSavedAYear();
    	StringBuilder sb = this.reformatData(result);
    	this.txtDataOutput.appendText(sb.toString());
    }

    @FXML
    void doDataFindUserByUsername(ActionEvent event) {
    	String user = this.txtDataUser.getText().toUpperCase();
    	
    	if(user.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type an username please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	
    	Data result = model.getDataFromUsername(user);
    	if(result == null) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog(user + " not found.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	else {
    		this.txtDataOutput.clear();
	    	List<Data> temp = new ArrayList<>();
	    	temp.add(result);
	    	StringBuilder sb = this.reformatData(temp);
	    	this.txtDataOutput.appendText(sb.toString());
    	}
    }
    
    @FXML
    void doDataReload(ActionEvent event) {
    	this.txtDataOutput.clear();
    	this.addItemsToTxtDataOutput();
    	
    	this.txtDataUser.clear();
    }
    
    @FXML
    void doUsersReload(ActionEvent event) {
    	this.txtUsersOutput.clear();
    	this.addItemsToTxtUsersOutput();
    	
    	this.txtUsersUser.clear();
    	this.boxUsersDivisions.getSelectionModel().clearSelection();
    }

    @FXML
    void doUsersFilterByDivision(ActionEvent event) {
    	if(this.boxUsersDivisions.getSelectionModel().isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Select a division please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	
    	String division = this.boxUsersDivisions.getSelectionModel().getSelectedItem();
    	List<User> result = model.getUsersListFilteredByDivision(division);
    	if(result.isEmpty()) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("No user found for " + division + " division.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	else {
    		this.txtUsersOutput.clear();
	    	StringBuilder sb = this.reformatUsers(result);
	    	this.txtUsersOutput.appendText(sb.toString());
    	}
    }

    @FXML
    void doUsersFindUserByUsername(ActionEvent event) {
    	String user = this.txtUsersUser.getText().toUpperCase();
    	
    	if(user.equals("")) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog("Type an username please.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	
    	User result = model.getUserFromUsername(user);
    	if(result == null) {
    		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("dialogFXML.fxml"));
				BorderPane root = loader.load();
				DialogController controller = loader.getController();
				controller.setTxtDialog(user + " not found.");
				Parent content = root;
				Scene scene = new Scene(content);
				Stage window = new Stage();
				window.setScene(scene);
				window.show();
				return;
	
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	else {
    		this.txtUsersOutput.clear();
	    	List<User> temp = new ArrayList<>();
	    	temp.add(result);
	    	StringBuilder sb = this.reformatUsers(temp);
	    	this.txtUsersOutput.appendText(sb.toString());
    	}
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	addItemsToBoxANUEmail();
    	addItemsToBoxANUDivision();
    	addItemsToBoxANURole();
    	addItemsToBoxANUProvince();
    	addItemsToBoxANUFuelType();
    	addItemsToBoxUsersDivisions();
    	addItemsToTxtUsersOutput();
    	addItemsToTxtDataOutput();
    }
    
    private StringBuilder reformatUsers(List<User> users) {
    	StringBuilder result = new StringBuilder();
    	for(User u : users) {
    		result.append(String.format("%-19s ", u.getUser()));
    		result.append(String.format("%-24s ", u.getDivision()));
    		result.append(String.format("%-34s ", u.getResponsible()));
    		result.append(String.format("%-19s ", u.getRole()));
    		result.append(String.format("%-40s ", u.getDomicile()));
    		result.append(String.format("%-10s ", u.getFuelType()));
    		result.append("\n");
    	}
    	
    	return result;
    }
    
    private StringBuilder reformatData(List<Data> data) {
    	StringBuilder result = new StringBuilder();
    	for(Data d : data) {
    		result.append(String.format("%-19s ", d.getUser().getUser()));
    		result.append(String.format("%-22d ", d.getUser().getSmartDays()));
    		result.append(String.format("%-32.2f ", d.getKmsSavedADay()));
    		result.append(String.format("%-30.2f ", d.getGramsOfCO2SavedADay()));
    		result.append(String.format("%-31.2f ", d.getKmsSavedAYear()));
    		result.append(String.format("%-20.2f ", d.getGramsOfCO2SavedAYear()));
    		result.append("\n");
    	}
    	
    	return result;
    }

    private void addItemsToTxtDataOutput() {
    	StringBuilder sb = new StringBuilder();
		List<Data> data = model.getDataList();
		
		if(data.isEmpty())
			sb.append("No data found.");
		else 
			sb = reformatData(data);
		
		this.txtDataOutput.appendText(sb.toString());
	}

	private void addItemsToTxtUsersOutput() {
		StringBuilder sb = new StringBuilder();
		List<User> users = model.getUsersList();
		
		if(users.isEmpty())
			sb.append("No user found.");
		else
			sb = reformatUsers(users);
		
		this.txtUsersOutput.appendText(sb.toString());
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
        assert txtSmartDays != null : "fx:id=\"txtSmartDays\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUZip != null : "fx:id=\"txtANUZip\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUStreet != null : "fx:id=\"txtANUStreet\" was not injected: check your FXML file 'pswFXML.fxml'.";
        assert txtANUNumber != null : "fx:id=\"txtANUNumber\" was not injected: check your FXML file 'pswFXML.fxml'.";
        
    }
}
