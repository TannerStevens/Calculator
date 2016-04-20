import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class EquationSolver {
	private Map<String, Runnable> operators = new HashMap<String, Runnable>();
	private Stack<Integer> eqStack = new Stack<Integer>();
	
	public EquationSolver(){
		operators.put(Operator.EXPONENTIATION.symbol(), ()->{ //x^y
			int y = eqStack.pop();
			int x = eqStack.pop();
			eqStack.push(x^y);
		});
		operators.put(Operator.MULTIPLICATION.symbol(), ()->{
			eqStack.push(eqStack.pop()*eqStack.pop());
		});
		operators.put(Operator.DIVISION.symbol(), ()->{ //x/y
			int y = eqStack.pop();
			int x = eqStack.pop();
			eqStack.push(x/y);
		});
		operators.put(Operator.ADDITION.symbol(), ()->{
			eqStack.push(eqStack.pop()+eqStack.pop());
		});
		operators.put(Operator.SUBTRACTION.symbol(), ()->{//x-y
			int y = eqStack.pop();
			int x = eqStack.pop();
			eqStack.push(x-y);
		});
	}
	public int Solve(String eq){
		String[] sEq = eq.split(" ");
		for(int s=0;s<sEq.length;s++){
			if(operators.containsKey(sEq[s])){
				operators.get(sEq[s]).run();
			}
			else eqStack.push(Integer.parseInt(sEq[s]));
		}
		
		return eqStack.pop();
	}
}
