import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Parser {
	private Map<Character, Integer> opPrecedence = new HashMap<Character, Integer>(4);
	public Parser(){
		opPrecedence.put('^', 2);
		opPrecedence.put('*', 3);
		opPrecedence.put('/', 3);
		opPrecedence.put('+', 4);
		opPrecedence.put('-', 4);
	}
	
	private boolean isOperator(char c){
		boolean result = false;
		switch(c){
		case '(':
		case ')':
		case '^':
		case '*':
		case '/':
		case '+':
		case '-':
			result = true;
			break;
		default: break;
		}
		return result;
	}
	public String translate(String eq){
		if(isOperator(eq.charAt(eq.length()-1)))
			return eq; //Already Postfix
		
		Stack<Character> operators = new Stack<Character>();
		String outStr = "";
		for(int i=0;i<eq.length();i++){
			char c = eq.charAt(i);
			if(isOperator(c)){
				char sTop = operators.peek();
				if(operators.empty() || sTop=='(') 
					operators.push(c);
				else if(c=='(')
					operators.push(c);
				else if(c==')'){
					char tempC;
					tempC = operators.pop();
					while (tempC!='('){
						outStr+=tempC;
						tempC = operators.pop();
					}
				}
				else{
					while(true){
						sTop = operators.peek();
						if(opPrecedence.get(c)>opPrecedence.get(sTop)){
							operators.push(c);
							break;
						}
						else if(opPrecedence.get(c)==opPrecedence.get(sTop)){
							//Determine Association
							if(c=='^'){//Right Associative
								operators.push(c);
							}
							else{//Left Associative
								outStr+=operators.pop();
								operators.push(c);
							}
							break;
						}
						else if(opPrecedence.get(c)<opPrecedence.get(sTop)){
							outStr+=operators.pop();
						}
					}
				}
			}
			else outStr+=c;
		}
		return outStr;
	}
	public String[] parseFile(String fName){
		return null;
	}
}
