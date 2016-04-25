import java.util.Scanner;
public class QuadraticSolver {
	public static void main(String[] args) {
		int a, b, c;
		double r1, r2, d;
		Scanner scan = new Scanner (System.in);
		System.out.println("Quadratic equation form: ax^2 + bx + c");
		System.out.print("Enter a value for a: ");
		a = scan.nextInt();
		System.out.print("Enter a value for b: ");
		b = scan.nextInt();
		System.out.print("Enter a value for c: ");
		c = scan.nextInt();
		
		d = b * b - 4 * a * c;
		if (d > 0){
			r1 = (-b + Math.sqrt(d))/(2*a);
			r2 = (-b - Math.sqrt(d))/(2*a);
			System.out.println("First root of the quadratic is: " + r1);
			System.out.println("Second root of the quadratic is: " + r2);
		}
		
		else if (d == 0) {
			r1 = (-b + Math.sqrt(d))/(2*a);
			System.out.println("Root of the quadratic is: " + r1);
		}
		
		else {
			System.out.println("No real roots.");
		}
	}

}
