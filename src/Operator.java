import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Operator {
	EXPONENTIATION ("^", 2),
	MULTIPLICATION ("*", 3),
	DIVISION ("/", 3),
	ADDITION ("+", 4),
	SUBTRACTION ("-", 4);
	
	private static final Map<String, Operator> operators;
	static{
		Map<String, Operator> ops = new HashMap<String, Operator>();
		ops.put(Operator.EXPONENTIATION.symbol(), Operator.EXPONENTIATION);
		ops.put(Operator.MULTIPLICATION.symbol(), Operator.MULTIPLICATION);
		ops.put(Operator.DIVISION.symbol(), Operator.DIVISION);
		ops.put(Operator.ADDITION.symbol(), Operator.ADDITION);
		ops.put(Operator.SUBTRACTION.symbol(), Operator.SUBTRACTION);
		operators = Collections.unmodifiableMap(ops);
	}
	
	private final String symbol;
	private final int precedence;
	Operator(String symbol, int precedence){
		this.symbol = symbol;
		this.precedence = precedence;
	}
	public String symbol(){return symbol;}
	public int precedence(){return precedence;}
	
	public static int findPrecedence(char c){
		return operators.get(String.valueOf(c)).precedence();
	}
	public static int findPrecedence(String c){
		return operators.get(c).precedence();
	}
	public static boolean isOperator(char c){
		return operators.containsKey(String.valueOf(c));
	}
	public static boolean isOperator(String c){
		return operators.containsKey(c);
	}
}
