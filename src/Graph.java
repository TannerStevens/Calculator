import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {
	private EquationSolver eqSolve;
	private String[] equation;
	private int xMin, xMax;
	private int yMin, yMax;
	private int pSpacing;
	private Map<String, Integer> varValue = new HashMap<String, Integer>();
	private Map<String, Integer> varIndex = new HashMap<String, Integer>();
	
	public Graph(){}
	public void setEq(String eq){
		this.equation = eq.split(" ");
		for(int s=0;s<this.equation.length;s++){
			if(isVariable(this.equation[s])){
				varIndex.put(this.equation[s], s);
				varValue.put(this.equation[s], null);
			}
		}
		
	}
	public Set<String> getVariables(){
		return varIndex.keySet();
	}
	public void setVariableValue(String var, int value){
		varValue.replace(var, value);
	}
	
	private boolean isVariable(String c){
		if(Operator.isOperator(c))
			return false;
		else{
			try {
				Integer.parseInt(c);
				return false;
			}
			catch(NumberFormatException ex){return true;}
		}
	}
}
