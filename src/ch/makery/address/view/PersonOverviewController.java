package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

	@FXML
	private TableView<Person> personTable;

	@FXML
	private TableColumn<Person, String> firstNameColumn;

	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;

	@FXML
	private Label lastNameLabel;

	@FXML
	private Label streetLabel;

	@FXML
	private Label postalCodeLabel;

	@FXML
	private Label cityLabel;

	@FXML
	private Label birthdayLabel;

	private MainApp mainApp;

	public PersonOverviewController() {
	}

	@FXML
	private void initialize() {
		this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		showPersonDetails(null);

		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

	}

	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean onClicked = mainApp.showPersonEditDialog(tempPerson);

		if (onClicked) {
			mainApp.getPersonData().add(tempPerson);
		}
	}

	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();

		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);

			if (okClicked) {
				showPersonDetails(selectedPerson);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		this.personTable.setItems(mainApp.getPersonData());
	}

	private void showPersonDetails(Person person) {
		if (person != null) {
			this.firstNameLabel.setText(person.getFirstName());
			this.lastNameLabel.setText(person.getLastName());
			this.streetLabel.setText(person.getStreet());
			this.postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			this.cityLabel.setText(person.getCity());
			this.birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		} else {
			this.firstNameLabel.setText("");
			this.lastNameLabel.setText("");
			this.streetLabel.setText("");
			this.postalCodeLabel.setText("");
			this.cityLabel.setText("");
			this.birthdayLabel.setText("");
		}
	}

}
