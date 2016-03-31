import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.image.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.*;
import java.awt.Desktop;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.Object;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.control.Control;
import javafx.scene.control.MenuBar;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


//All the manditory libraries
import java.time.LocalDate;


public class client extends Application {
    
    private Stage window;
    private BorderPane layout;
    private TableView table;
    private ListView list;

    private Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(Stage primaryStage) throws Exception {
        
	primaryStage.setTitle("File Sharer v1.0");
	//insert
	GridPane editArea = new GridPane();
	editArea.setPadding(new Insets(0, 0, 0, 0));
        /*
        editArea.setVgap(5);
        editArea.setHgap(5);
	*/
	layout = new BorderPane();
	layout.setTop(editArea);
        Scene scene = new Scene(layout, 495, 410, Color.LIGHTGREY);
 
	final FileChooser fileChooser = new FileChooser();
	
	Button button = new Button("Download");
	button.setOnAction(
		new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file!=null) {
				openFile(file);
				}
			}
		});
	editArea.add(button, 0, 0);

	Button button2 = new Button("Upload");
	button2.setOnAction(
		new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file!=null) {
				openFile(file);
				}
			}
		});
	editArea.add(button2, 1, 0);
	
	layout.setTop(editArea);
	
	GridPane editArea2 = new GridPane();
	editArea2.setPadding(new Insets(0, 0, 0, 0));
	

	ListView<String> list = new ListView<>();
	ObservableList<String> items =FXCollections.observableArrayList (
   	"Single", "Double", "Suite", "Family App");
	list.setItems(items);
	editArea2.add(list, 0, 0);

	ListView<String> list2 = new ListView<>();
	ObservableList<String> items2 =FXCollections.observableArrayList (
   	"Single", "Double", "Suite", "Family App");
	list2.setItems(items2);
	editArea2.add(list2, 1, 0);
	
	layout.setCenter(editArea2);
	
        /* create the table's columns */
        /*table = new TableView();
        table.setEditable(false);

	editArea.add(table, 0, 3);
	*/
	
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                FileChooserSample.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }	

    public static void main(String[] args) {
        launch(args);
    }
}
