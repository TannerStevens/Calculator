import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class Driver extends Application {
	
	private String equation;
	private GridPane grid;
	private Scene scene;
	private TextField filenameTF, equationTF;
	private Label filenameL, equationL;
	private TilePane numpadButtons;
	private HashMap<String, Button> accelerators = new HashMap<>();
	private String[][] numpadText = {
		{"7", "8", "9", "+", "*", "sin"},
		{"4", "5", "6", "-", "/", "cos"},
		{"1", "2", "3", "(", ")", "tan"},
		{"0", ".", "^", "pi", "clear", "="}
	};
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	equation = "";
        primaryStage.setTitle("Calculator");
        initGridPane();
        initTextFields();
        initLabels();
        initNumpadButtons();
        
        scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void initGridPane() {
    	grid = new GridPane();
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(25, 25, 25, 25));
    }
    
    private void initTextFields() {
    	filenameTF = new TextField();
    	equationTF = new TextField();
    	//equationTF.setStyle("-fx-background-color: midnightblue;");
    	grid.add(filenameTF, 0, 1);
    	grid.add(equationTF, 0, 3);
    }
    
    private void initLabels() {
    	filenameL = new Label("File name (Optional):");
    	equationL = new Label("Equation:");
    	grid.add(filenameL, 0, 0);
    	grid.add(equationL, 0, 2);
    	
    	
    }
    
    private void initNumpadButtons() {
    	numpadButtons = new TilePane();
    	numpadButtons.setVgap(7);
    	numpadButtons.setHgap(7);
    	numpadButtons.setPrefColumns(numpadText[0].length);
    	for(String[] s: numpadText) {
    		for(String t: s) {
    			numpadButtons.getChildren().add(createButton(t));
    		}
    	}
    }
    
    private Button createButton(String s) {
    	Button button = new Button(s);
    	button.setStyle("-fx-base: beige;");
    	accelerators.put(s,  button);
    	button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); 	
    	if(s.matches("[0-9]")) {
    		createNumButton(button);
    	} else if(s.equals("=")) {
    		createEqualButton(button);
    	} else if(s.equals("clear")){
    		createClearButton(button);
    	} else
    		createOperandButton(button);
    	
    	return button;
    }
    
    private void createNumButton(Button button) {
    	//TODO
    }
    
    private void createEqualButton(Button button) {
    	//TODO
    }
    
    private void createClearButton(Button button) {
    	//TODO
    }
    
    private void createOperandButton(Button button) {
    	button.setStyle("-fx-base: lightgray;");
    	button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent actionEvent) {
    			equation += " " + button.getText();
    			equationTF.setText(equation);
    		}
    	});
    }
    
    private void handleAccelerators(VBox layout) {
    	layout.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
    		@Override
    		public void handle(KeyEvent keyEvent) {
    			Button activated = accelerators.get(keyEvent.getText());
    			if(activated != null) {
    				activated.fire();
    			}
    		}
    	});
    }
}
