import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class EquationSolver {
	private Map<String, Runnable> operators = new HashMap<>();
	private Stack<Integer> eqStack = new Stack<Integer>();
	
	public EquationSolver(){
		operators.put("^", ()->{ //x^y
			int y = eqStack.pop();
			int x = eqStack.pop();
			eqStack.push(x^y);
		});
		operators.put("*", ()->{
			eqStack.push(eqStack.pop()*eqStack.pop());
		});
		operators.put("/", ()->{ //x/y
			int y = eqStack.pop();
			int x = eqStack.pop();
			eqStack.push(x/y);
		});
		operators.put("+", ()->{
			eqStack.push(eqStack.pop()+eqStack.pop());
		});
		operators.put("-", ()->{//x-y
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
