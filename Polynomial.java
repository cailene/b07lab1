import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Polynomial{

	//these are fields 
	double[] coefficientP;
	int[] exponentP;

	//my constructors. 
	public Polynomial(){
		coefficientP = new double[] {0};
		exponentP = new int[] {0};
	}
	
	public Polynomial(double[] array){
		int numZ = findZero(array);
		coefficientP = new double[array.length - numZ];
		exponentP = new int[array.length - numZ];
		
		int j = 0;
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] != 0) {
				coefficientP[j] = array[i];
				exponentP[j] = i;
				j++;
			}
		}
	}
	
	public Polynomial(File file) throws Exception {
		int negativeOp = 0;
		int positiveOp = 0;
		int idx = 0;
		boolean hasConstant = false; 
		String poly = readFile(file);
		String[] splitPoly = new String[0];
		
		if (poly.length() > 0) {
			splitPoly = poly.split("x");
		}
		
		if (splitPoly.length == 1) {
			coefficientP = new double[1];
			exponentP = new int[1];
			coefficientP[0] = Double.parseDouble(splitPoly[0]);
			exponentP[0] = 0;
		}
		/*
		 * this section does two things: determines if there is a constant or not, and
		 * initialize the correct size for the fields coefficientP and exponentP
		 */
		if (splitPoly.length > 1) {
			negativeOp = splitPoly[0].indexOf("-");
			positiveOp = splitPoly[0].indexOf("+");
			if (negativeOp == 0) {
				// this means that the first number is a negative number
				negativeOp = splitPoly[0].indexOf("-", negativeOp+1);
				positiveOp = splitPoly[0].indexOf("+", negativeOp+1);
				// checks if there is an operator within this index of string to determine if there is also a coefficient
				if (negativeOp != -1 || positiveOp != -1) {
					coefficientP = new double[splitPoly.length];
					exponentP = new int[splitPoly.length];
					hasConstant = true;
					if (negativeOp != -1) {
						coefficientP[0] = Double.parseDouble(splitPoly[0].substring(0, negativeOp));
						exponentP[0] = 0;
						coefficientP[1] = Double.parseDouble(splitPoly[0].substring(negativeOp));
					}else {
						coefficientP[0] = Double.parseDouble(splitPoly[0].substring(0, positiveOp));
						exponentP[0] = 0;
						coefficientP[1] = Double.parseDouble(splitPoly[0].substring(positiveOp+1));
					}
				}else{
					// no operator, therefore a negative leading coefficient
					hasConstant = false;
					coefficientP = new double[splitPoly.length - 1];
					exponentP = new int[splitPoly.length - 1];
					coefficientP[0] = Double.parseDouble(splitPoly[1]);
				}
			}else if(negativeOp != -1 || positiveOp != -1){
				// checks if there is an operator within this index of string
				hasConstant = true;
				coefficientP = new double[splitPoly.length];
				exponentP = new int[splitPoly.length];
				if (negativeOp != -1) {
					coefficientP[0] = Double.parseDouble(splitPoly[0].substring(0, negativeOp));
					exponentP[0] = 0;
					coefficientP[1] = Double.parseDouble(splitPoly[0].substring(negativeOp));
				}else {
					coefficientP[0] = Double.parseDouble(splitPoly[0].substring(0, positiveOp));
					exponentP[0] = 0;
					coefficientP[1] = Double.parseDouble(splitPoly[0].substring(positiveOp+1));
				}
			}else{
				// positive leading coefficient
				hasConstant = false;
				coefficientP = new double[splitPoly.length - 1];
				exponentP = new int[splitPoly.length - 1];
				
				coefficientP[0] = Double.parseDouble(splitPoly[0]);
			}
		}
		
		// this second part constructs the rest of the Polynomial. 
		// it deals with purely coefficients and the degree of exponents 
		idx = 1;
		if (splitPoly.length > 1) {
			while (idx < splitPoly.length) {
				negativeOp = splitPoly[idx].indexOf("-");
				positiveOp = splitPoly[idx].indexOf("+");
				// degree of exponents can never be negative, and we'll never have a positive sign before an exponents
				if (negativeOp != -1) {
					// locating the degree of exponent and coefficient and parsing it
					if (hasConstant) {
						coefficientP[idx+1] = Integer.parseInt(splitPoly[idx].substring(negativeOp));
						exponentP[idx] = Integer.parseInt(splitPoly[idx].substring(0, negativeOp));
					}else {
						coefficientP[idx] = Integer.parseInt(splitPoly[idx].substring(negativeOp));
						exponentP[idx-1] = Integer.parseInt(splitPoly[idx].substring(0, negativeOp));
					}
				}else if(positiveOp != -1) {
					if (hasConstant) {
						coefficientP[idx+1] = Integer.parseInt(splitPoly[idx].substring(positiveOp+1));
						exponentP[idx] = Integer.parseInt(splitPoly[idx].substring(0, positiveOp));
					}else {
						coefficientP[idx] = Integer.parseInt(splitPoly[idx].substring(positiveOp+1));
						exponentP[idx-1] = Integer.parseInt(splitPoly[idx].substring(0, positiveOp));
					}
				}else {
					// no operator means we've reached the end of reading the polynomial file
					// the last number are exponents and we can parse them as such
					if (hasConstant) {
						exponentP[idx] = Integer.parseInt(splitPoly[idx]);
					}else {
						exponentP[idx-1] = Integer.parseInt(splitPoly[idx]);
					}
				}
				idx++;
			}
		}
	}
	// end of constructors. 
	
	public String readFile(File file) throws Exception {
		Scanner scanner = new Scanner (file);
		String poly = scanner.next();
		scanner.close();
		System.out.println(poly);
		return poly;
	}
	
	public void saveToFile(String name) throws FileNotFoundException{
		PrintStream ps = new PrintStream(name);
		for (int i = 0; i < coefficientP.length; i++) {
			if (exponentP[i] == 0) {
				ps.print(coefficientP[i]);
			}else {
				if (coefficientP[i] > 0) {
					ps.print("+"+coefficientP[i]);
				}else {
					ps.print(coefficientP[i]);
				}
				if (i + 1 == coefficientP.length) {
					ps.print("x"+exponentP[i]);
				}else {
					ps.print("x"+exponentP[i]);
				}
				
			}
		}
		ps.close();
	}
	
	public Polynomial add(Polynomial poly){
		Polynomial result = new Polynomial();
		int maxExp = 0;
		
		maxExp = Math.max(poly.exponentP[poly.exponentP.length - 1], exponentP[exponentP.length - 1]);
		double[] polyArray = new double[maxExp+1];
		
		for(int i = 0; i < poly.exponentP.length; i++) {
			polyArray[poly.exponentP[i]] += poly.coefficientP[i];
		}
		for(int i = 0; i < exponentP.length; i++) {
			polyArray[exponentP[i]] += coefficientP[i];
		}
		
		result = new Polynomial(polyArray);
		return result;
	}
	
	public int find(int value) {
		for(int i = 0; i < exponentP.length; i++) {
			if (exponentP[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public Polynomial multiply(Polynomial poly) {		
		Polynomial totalResult = new Polynomial(), iterResult = new Polynomial();
		iterResult.coefficientP = new double[coefficientP.length];
		iterResult.exponentP = new int[exponentP.length];
		
		for(int i = 0; i < poly.coefficientP.length; i++) {			
			for(int j = 0; j < coefficientP.length; j++) {
				iterResult.coefficientP[j] = poly.coefficientP[i] * coefficientP[j];
				iterResult.exponentP[j] = poly.exponentP[i] + exponentP[j];
			}
			totalResult = totalResult.add(iterResult);
		}
		if (totalResult != null)
			return totalResult;
		else
			totalResult = new Polynomial();
			return totalResult;
	}

	public double evaluate(double x){
		double result = 0;
		
		for (int i = 0; i < coefficientP.length; i++) {
			result += coefficientP[i] * Math.pow(x, exponentP[i]);
			
		}
		return result;
	}
	
	public boolean hasRoot(double x){

		if (this.evaluate(x) == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static int findZero(double[] array)
	{
		int cnt = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 0) {
				cnt++;
			}
		}
		return cnt;
	}
	
	public void print() {
		for (int i = 0; i < coefficientP.length; i++) {
			if (coefficientP[i] != 0) {
				if (exponentP[i] == 0) {
					System.out.print(coefficientP[i]+" + ");
				}else if (exponentP[i] == 1){
					System.out.print(coefficientP[i]+"x + ");
				}else {
					System.out.print(coefficientP[i]+"x^"+exponentP[i]+" + ");
				}
			}
		}
		System.out.print("\n");
	}
}