import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Driver extends Application {
	
	private String filename, equation;
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
		{"0", "clear", "=", ".", "^", "log"}
	};
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	filename = "";
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
    	grid.setHgap(2);
    	grid.setVgap(2);
    	grid.setPadding(new Insets(5));
    }
    
    private void initTextFields() {
    	filenameTF = new TextField();
    	equationTF = new TextField();
    	//equationTF.setStyle("-fx-background-color: deepskyblue;"
    	//		+ "-fx-text-fill: honeydew;");
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
    	grid.add(numpadButtons, 0, 5);
    }
    
    private Button createButton(String s) {
    	Button button = new Button(s);
    	accelerators.put(s, button);
    	button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); 	
    	if(s.matches("[0-9]")) {
    		createNumButton(button);
    	} else if(s.equals("=")) {
    		createEqualButton(button);
    	} else if(s.equals("clear")){
    		createClearButton(button);
    	} else
    		createOperatorButton(button);
    	
    	return button;
    }
    
    /**
     * Create number operand button.
     * Action: Append equation with number pressed.
     * @param button
     */
    private void createNumButton(Button button) {
    	button.setStyle("-fx-base: lightgray;");
    	button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				equation += button.getText();
				equationTF.setText(equation);
			}
    		
    	});
    }
    
    /**
     * Create "=" button.
     * Action: Pass equation to Parser and EquationSolver then display result.
     * @param button
     */
    private void createEqualButton(Button button) {
    	button.setStyle("-fx-base: honeydew;");
    	button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent actionEvent) {
    			Parser parser = new Parser();
    			EquationSolver esolver = new EquationSolver();
    			String postfixEquation = parser.translate(equation);
    			Double result = esolver.Solve(postfixEquation);
    			equationTF.setText(result+ "");
    		}
    	});
    }
    
    /**
     * Create "clear" button.
     * Action: Set equation to empty string.
     * @param button
     */
    private void createClearButton(Button button) {
    	button.setStyle("-fx-base: honeydew;");
    	button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent actionEvent) {
    			equation = "";
    	    	equationTF.setText(equation);
    		}
    	});
    }
    
    /**
     * Create operator button.
     * Action: Append equation with operator sign pressed.
     * @param button
     */
    private void createOperatorButton(Button button) {
    	button.setStyle("-fx-base: lightslategrey;");
    	button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent actionEvent) {
    			equation += button.getText();
    			equationTF.setText(equation);
    		}
    	});
    }
}
