import java.io.File;

public class Driver {
	public static void main(String [] args) throws Exception {
		Polynomial p = new Polynomial();
		//p.print();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);	
		//p1.print();
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		//p2.print();
		
		double [] c3 = {6,-2,0,5};
		Polynomial p3 = new Polynomial(c3);
		//p3.print();
		double [] c4 = {1,1};
		Polynomial p4 = new Polynomial(c4);
		//p4.print();
		Polynomial m1 = p3.multiply(p4);
		//m1.print();
		
		double [] c8 = {6,-2,0,5};
		Polynomial p8 = new Polynomial(c8);
		//p8.print();
		double [] c9 = {0};
		Polynomial p9 = new Polynomial(c9);
		//System.out.println("this is p9 ");
		//p9.print();
		Polynomial m2 = p8.multiply(p9);
		//m2.print();
		System.out.println(m2.evaluate(3));
		
		double [] c5 = {6,-2,0,5};
		Polynomial p5 = new Polynomial(c5);
		//p5.print();
		double [] c6 = {0,6,-2,0,5};
		Polynomial p6 = new Polynomial(c6);
		//p6.print();
		Polynomial s2 = p5.add(p6);
		//s2.print();
		
		Polynomial s1 = p4.add(p3);
		//s1.print();
		
		Polynomial s = p1.add(p2);
		
		//s.print();
		
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		
		File myObj = new File("C:\\Users\\caile\\Desktop\\CSCB07\\polynomial example.txt");
		Polynomial p7 = new Polynomial(myObj);
		//p7.print();
		
		String outputName = "C:\\Users\\caile\\Desktop\\CSCB07\\polynomial example output.txt";
		p7.saveToFile(outputName);
		
	}
}