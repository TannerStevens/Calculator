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
	
		public static double Sin(double x, String ch) {
		if(ch.equals("Sin") || ch.equals("sin")) {
			double rad = Math.sin(Math.toRadians(x));
			return rad;
		}
		else
			System.out.print("write Sin or sin");
			return 0;
	}
	
	public static double Cos(double x, String ch) {
		if(ch.equals("Cos") || ch.equals("cos")) {
			double rad = Math.cos(Math.toRadians(x));
			return rad;
		}
		else
			System.out.print("write Cos or cos");
			return 0;
	}
	
	public static double Tan(double x, String ch) {
		if(ch.equals("Tan") || ch.equals("tan")) {
			double rad = Math.tan(Math.toRadians(x));
			return rad;
		}
		else
			System.out.print("write Tan or tan");
			return 0;
	}
	
	public static double Rad(double x) {
			double rad = Math.toRadians(x);
			return rad;
	}
	
	public static double Log(double x) {
		double rad = Math.log10(x);
		return rad;
	}
}
