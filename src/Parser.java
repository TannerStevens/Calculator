import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Parser {
	public Parser(){}
	
	public String translate(String eq){
		if(eq.charAt(eq.length()-1)!=')' && Operator.isOperator(eq.charAt(eq.length()-1)))
			return eq; //Already Postfix
		
		Stack<Character> operators = new Stack<Character>();
		String outStr = "";
		for(int i=0;i<eq.length();i++){
			char c = eq.charAt(i);
			if(Operator.isOperator(c)){
				if(operators.empty()){
					operators.push(c);
					continue;
				}
				
				char sTop = operators.peek();
				if(sTop=='(') 
					operators.push(c);
				else if(c=='(')
					operators.push(c);
				else if(c==')'){
					char tempC;
					tempC = operators.pop();
					while (tempC!='('){
						outStr+=" "+tempC;
						tempC = operators.pop();
					}
				}
				else{
					while(true){
						if(operators.empty()){
							operators.push(c);
							break;
						}
						
						sTop = operators.peek();
						if(Operator.findPrecedence(c)<Operator.findPrecedence(sTop)){
							operators.push(c);
							break;
						}
						else if(Operator.findPrecedence(c)==Operator.findPrecedence(sTop)){
							//Determine Association
							if(c=='^'){//Right Associative
								operators.push(c);
							}
							else{//Left Associative
								outStr+=" "+operators.pop();
								operators.push(c);
							}
							break;
						}
						else if(Operator.findPrecedence(c)>Operator.findPrecedence(sTop)){
							outStr+=" "+operators.pop();
						}
					}
				}
			}
			else{
				outStr+=" "+c;
				for(int j=i+1;j<eq.length();j++){
					char tC = eq.charAt(j);
					if(!Operator.isOperator(tC)) {
						outStr+=tC;
						i++;
					}
					else break;
				}
			}
		}
		
		while(!operators.empty()){
			outStr+=" "+operators.pop();
		}
		return outStr.substring(1);
	}
	public String[] parseFile(String fName){
		return null;
	}
}
