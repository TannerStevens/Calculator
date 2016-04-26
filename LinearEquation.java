import java.util.Scanner;

public class LinearEquation {
	public static void main (String args[]){
	
	char var[] = {'t', 'x','y','z'};
	System.out.println("Enter the number of variables: ");
	Scanner scan = new Scanner (System.in);
	int a = scan.nextInt();
	System.out.println("Enter the coefficients of the variables: ");
	System.out.println("ax + by + cz + ... = d");
	double mat[][] = new double[a][a];
	double constant[][] = new double [a][1]; {
	
	for (int i = 0; i < a; i++) {
		for (int j = 0; j < a; j++) {
			mat[i][j] = scan.nextDouble();
			
		}
		
		constant[i][0] = scan.nextDouble();
	}
	
	for (int i = 0; i < a; i++) {
		for (int j = 0; j < a; j++) {
			System.out.print(" "+mat[i][j]);
		}
		System.out.print("  "+ var[i]);
		System.out.print("  =  " + constant[i][0]);
		System.out.println();
	}
	
	double invert_mat[][] = invert(mat);
	System.out.println("The inverse is: ");
	for (int i = 0; i < a; ++i) {
		for (int j = 0; j < a; ++j) {
			System.out.println(invert_mat[i][j]+" ");
		}
		System.out.println();
	}
	
	double result[][] = new double[a][1];
	for (int i = 0; i < a; i++) {
		for (int j = 0; j < 1; j++) {
			for (int k = 0; k < a; k++){
			result[i][j] = result[j][j] + invert_mat[i][k] * constant[k][j];
			}
		}
	}
	
	System.out.println("Product: ");
	for (int i = 0; i < a; i++) {
		System.out.println(result[i][0] + " ");
	}
	
	scan.close();
	
	public static double[][] invert(double b[][]) {
		int n = b.length;
		double x[][] = new double[n][n];
		double d[][] = new double [n][n];
		int index[] = new int[n];
		for (int i = 0; i < n; ++i) {
			d[i][i] = 1;
		}
		
		gaussian(b,index);
		
		for (int i = 0; i < n-1; ++i){
			for (int j = i + 1; j < n; ++j){
				for (int k = 0; k < n; ++k) {
					d[index[j]][k] -= b[index[j]][i]*d[index[i]][k];
				}
			}
		}
		
		for (int i = 0; i < n; ++i) {
			x[n-1][i] = d[index[n-1]][i]/b[index[n-1]][n-1];
			for (int j = n-2; j >= 0; --j) {
				x[j][i] = d[index[j]][i];
				for (int k = j+1; k < n; ++k) {
					x[j][i] -= b[index[j]][k]*x[k][i];
				}
				x[j][i] /= b[index[j]][j];
			}
		}
		return x;
	}
	
	public static void gaussian(double b[][], int index[]) {
		int n = index.length;
		double e[] = new double[n];
		
		for (int i = 0; i < n; ++i) {
			index[i] = i;
		}
		
		for (int i = 0; i < n; ++i) {
			double e1 = 0;
			for (int j = 0; j < n; ++j) {
				double e0 = Math.abs(b[i][j]);
				if (e0 > e1){
					e1 = e0;
				}
				e[i] = e1;
			}
			
			int k = 0;
			for (int j = 0; j < n-1; ++j) {
				double p1 = 0;
				for (int i = j; i < n; ++i) {
					double p0 = Math.abs(b[index[i]][j]);
					p0 /= e[index[i]];
					if (p0 > p1) {
						p1 = p0;
						k = i;
					}
				}
				
			int temp = index[j];
			index[j] = index[k];
			index[k] = temp;
			
			for (int i = j+1; i < n; ++i) {
				double pj = b[index[i]][j]/b[index[j]][j];
				b[index[i]][j] = pj;
				for (int l = j+1; l < n; ++l) {
					b[index[i]][l] -= pj*b[index[j]][l];
				}
			}
			}
		}
	}
	
	
	
	

}
}
}
	
