import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {
	
	private String equation;
	private GridPane grid;
	private Scene scene;
	private TextField equationTF;
	private Label equationL;
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
    
    /**
     * Initialize components and call methods to construct main window.
     */
    @Override
    public void start(Stage primaryStage) {
    	equation = "";
        primaryStage.setTitle("Calculator");
        initGridPane();
        initFileChooser(primaryStage);
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
    	equationTF = new TextField();
    	equationTF.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
    	grid.add(equationTF, 0, 2);
    }
    
    private void initFileChooser(Stage stage) {
    	FileChooser fileChooser = new FileChooser();
    	Button fileButton = new Button("Solve from File");
    	fileButton.setOnAction(
    			new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						List<File> inputFileList = fileChooser.showOpenMultipleDialog(stage);
						if(inputFileList != null) {
							try {
								createOutputFile(inputFileList);
							} catch (IOException e) {
								System.out.println("Error with solving from file.");
								e.printStackTrace();
							}
						}
					}
    			});
    	grid.add(fileButton, 0, 0);
    }
    
    /**
     * Return Double solution to String equation using Parser and EquationSolver.
     * @param equation
     * @return solution
     */
    private Double solveEquation(String equation) {
    	Double solution;
		Parser parser = new Parser();
		EquationSolver eqSolver = new EquationSolver();
		String postfixEq = parser.translate(equation);
		solution = eqSolver.Solve(postfixEq);
		return solution;
    }
    
    /**
     * Create and write solutions to output file.
     * @param inputFileList
     * @throws IOException
     */
    private void createOutputFile(List<File> inputFileList) throws IOException {
		Parser parser = new Parser();
    	for(File inputFile : inputFileList) {
			String outputFilename = "Solution-" + inputFile.getName();
			PrintWriter writer = new PrintWriter(outputFilename, "UTF-8");
			ArrayList<String> equations = parser.parseFile(inputFile);
			//Solve equations and write results to output file
			for(int i = 0; i < equations.size(); i++) {
				writer.println(solveEquation(equations.get(i)));
			}
			writer.close();
		}
    }
    
    private void initLabels() {
    	equationL = new Label("Equation:");
    	grid.add(equationL, 0, 1);
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
    	grid.add(numpadButtons, 0, 3);
    }
    
    /**
     * Create initial buttons for entering equation.
     * @param s
     * @return
     */
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
    			equation = solveEquation(equation) + "";
    			equationTF.setText(equation);
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
