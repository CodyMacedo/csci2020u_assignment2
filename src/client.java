import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;

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
import javafx.stage.WindowEvent;


//All the manditory libraries
import java.time.LocalDate;


public class client extends Application {
    
    private Stage window;
    private BorderPane layout;
<<<<<<< HEAD
	protected String compName = "";
	protected String filename = "";
	private Socket socket = null;
	private OutputStream outputStream = null;
	private BufferedReader in = null;
	private PrintWriter networkOut = null;
	private BufferedReader networkIn = null;
	ObservableList<String> clientList = FXCollections.observableArrayList();
	ObservableList<String> serverList = FXCollections.observableArrayList();
	   	
	
	public  static String SERVER_ADDRESS = "localhost";
	public  static int    SERVER_PORT = 8080;
=======
    private TableView table;
    private ListView list;
	//declare what utilities we're using
>>>>>>> a5c5d1d552c56e3f807ee7c410ffd69ec801a37e

	

    @Override
    public void start(Stage primaryStage) throws Exception {
        
<<<<<<< HEAD
        compName = getParameters().getRaw().get(0);
		filename = getParameters().getRaw().get(1);
        
        makeConnection();
        
		primaryStage.setTitle("File Sharer v1.0");
		//insert
		
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
=======
	primaryStage.setTitle("File Sharer v1.0");
	//insert
	GridPane editArea = new GridPane();
	editArea.setPadding(new Insets(0, 0, 0, 0));
        /*
        editArea.setVgap(5);
        editArea.setHgap(5);
	*/
	layout = new BorderPane();
	//create a border field
	layout.setTop(editArea);
        Scene scene = new Scene(layout, 495, 410, Color.LIGHTGREY);
 	//declare dimensions anf characteristics for interface
	final FileChooser fileChooser = new FileChooser();
	
	Button button = new Button("Download");
	button.setOnAction(
	//Download button with event handler meant to open/select a file
		new EventHandler<ActionEvent>() {
>>>>>>> a5c5d1d552c56e3f807ee7c410ffd69ec801a37e
			@Override
			public void handle(WindowEvent event) {
				try {
					networkOut.close();
					networkIn.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		layout = new BorderPane();
		Scene scene = new Scene(layout, 495, 410, Color.LIGHTGREY);
		
		
		GridPane editArea2 = new GridPane();
		editArea2.setPadding(new Insets(0, 0, 0, 0));
	
		ListView<String> list = new ListView<>();
		editArea2.add(list, 0, 0);

<<<<<<< HEAD
		ListView<String> list2 = new ListView<>();
		editArea2.add(list2, 1, 0);
	
		layout.setCenter(editArea2);
		
		
		GridPane editArea = new GridPane();
		editArea.setPadding(new Insets(0, 0, 0, 0));
	
		Button button = new Button("Download");
		button.setOnAction(
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(final ActionEvent e) {
					String currentItem = list2.getSelectionModel().getSelectedItem();
					networkOut.println("DOWNLOAD " + currentItem);
					try {
						// get file from client
						File newFile = new File(filename + "/" + currentItem);
						if (!newFile.exists()) {
							PrintWriter fout = new PrintWriter(newFile);
							String line = "";
							while ((line = networkIn.readLine()) != null) {
								System.out.println(line);
							}
							// update client list
							clientList.add(currentItem);
							list.setItems(clientList);
						}
					} catch (IOException q) {
						System.err.println("Error reading from socket.");
					}
=======
	Button button2 = new Button("Upload");
	//Upload button with event handler meant to open/select a file
	button2.setOnAction(
		new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file1 = fileChooser.showOpenDialog(primaryStage);
				if (file1!=null) {
				openFile(file1);
>>>>>>> a5c5d1d552c56e3f807ee7c410ffd69ec801a37e
				}
			});
		editArea.add(button, 0, 0);

		Button button2 = new Button("Upload");
		button2.setOnAction(
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(final ActionEvent e) {
					String currentItem = list.getSelectionModel().getSelectedItem();
					networkOut.println("UPLOAD " + currentItem);
					
					FileInputStream fileIn = new FileInputStream(filename + "/" + currentItem);
					copyAllBytes(fileIn,outputStream);
					fileIn.close();
					// update server list
					serverList.add(currentItem);
					list2.setItems(serverList);
				}
			});
		editArea.add(button2, 1, 0);
	
		layout.setTop(editArea);
	
<<<<<<< HEAD
	
=======
	GridPane editArea2 = new GridPane();
	editArea2.setPadding(new Insets(0, 0, 0, 0));
	//additional grid to separate the buttons from the lists

	ListView<String> list = new ListView<>();
	ObservableList<String> items =FXCollections.observableArrayList ();
	list.setItems(items);
	editArea2.add(list, 0, 0);
	//the list of files will be input into the lists
	//this is the IMPROVEMENT that selects the file from your personal repositories.

	ListView<String> list2 = new ListView<>();
	ObservableList<String> items2 =FXCollections.observableArrayList ();
	list2.setItems(items2);
	editArea2.add(list2, 1, 0);
>>>>>>> a5c5d1d552c56e3f807ee7c410ffd69ec801a37e
	
		// Adding the lists
		
		
		// list
		File shared = new File(filename + "/");
		File[] files = shared.listFiles();
		for (int i = 0; i < files.length; i++) {
			clientList.add(files[i].getName());
		}
		list.setItems(clientList);
		list.getSelectionModel().select(0);
		
		// list2
		networkOut.println("DIR");
		// read response
		try {
			String line = "";
			while ((line = networkIn.readLine()) != null) {
                serverList.add(line);
            }
		} catch (IOException e) {
			System.err.println("Error reading from socket.");
		}
		list2.setItems(serverList);
		list2.getSelectionModel().select(0);
	
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}	
	
	private void copyAllBytes(InputStream fileIn, OutputStream out) 
                                               throws IOException {
        byte[] buffer = new byte[1024];
        int numBytes = -1;
        while ((numBytes = fileIn.read(buffer)) > 0) {
            out.write(buffer);
        }
    }
    
    private void makeConnection() {
    	try {
			socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
		} catch (UnknownHostException e) {
			System.err.println("Unknown host: "+SERVER_ADDRESS);
		} catch (IOException e) {
			System.err.println("IOEXception while connecting to server: "+SERVER_ADDRESS);
		}
		if (socket == null) {
			System.err.println("socket is null");
		}
        
        try {
<<<<<<< HEAD
        	outputStream = socket.getOutputStream();
			networkOut = new PrintWriter(outputStream, true);
			networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println("IOEXception while opening a read/write connection");
		}
    }
=======
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                FileChooserSample.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }	//this method works in unicen with the upper method for selecting a file
>>>>>>> a5c5d1d552c56e3f807ee7c410ffd69ec801a37e

    public static void main(String[] args) {
        launch(args);
    }
}
