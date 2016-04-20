import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class EquationSolver {
	private Map<String, Runnable> operators = new HashMap<String, Runnable>();
	private Stack<Double> eqStack = new Stack<Double>();
	
	public EquationSolver(){
		operators.put(Operator.EXPONENTIATION.symbol(), ()->{ //x^y
			double y = eqStack.pop();
			double x = eqStack.pop();
			eqStack.push(Math.pow(x, y));
		});
		operators.put(Operator.MULTIPLICATION.symbol(), ()->{
			eqStack.push(eqStack.pop()*eqStack.pop());
		});
		operators.put(Operator.DIVISION.symbol(), ()->{ //x/y
			double y = eqStack.pop();
			double x = eqStack.pop();
			eqStack.push(x/y);
		});
		operators.put(Operator.ADDITION.symbol(), ()->{
			eqStack.push(eqStack.pop()+eqStack.pop());
		});
		operators.put(Operator.SUBTRACTION.symbol(), ()->{//x-y
			double y = eqStack.pop();
			double x = eqStack.pop();
			eqStack.push(x-y);
		});
	}
	public double Solve(String eq){
		String[] sEq = eq.split(" ");
		for(int s=0;s<sEq.length;s++){
			if(operators.containsKey(sEq[s])){
				operators.get(sEq[s]).run();
			}
			else eqStack.push(Double.parseDouble(sEq[s]));
		}
		
		return eqStack.pop();
	}
}
