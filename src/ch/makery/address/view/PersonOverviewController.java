package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import javafx.fxml.FXML;
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
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		this.personTable.setItems(this.mainApp.getPersonData());
	}
}
