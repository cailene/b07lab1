public class Polynomial{

	//this is a field. 
	double[] coefficientP = new double[20];

	//this is a constructor. it initializes references, primitives, objects????
	public Polynomial(){
	}
	
	public Polynomial(double[] array){
		for (int i = 0; i < array.length; i++)
		{
			coefficientP[i] = array[i];
		}
	}
	
	// (Polynomial poly) is an argument
	public Polynomial add(Polynomial poly){
		// assuming the argument and calling object have the same size array:
		for (int i = 0; i < poly.coefficientP.length; i++){
			poly.coefficientP[i] += this.coefficientP[i];
		}
	
		return poly;
	}

	public double evaluate(double x){
		double result = 0;
		double y = 1;
		
		for(int i = 0; i < this.coefficientP.length; i++){
			if (this.coefficientP[i] != 0) {
				for(int j = 0; j < i; j++){
					y = y*x;
				}
				result += this.coefficientP[i]*y;
				y = 1;
			}else { y = 1; }
		}
		return result;
	}
	
	public boolean hasRoot(double x){
		Polynomial p = new Polynomial(coefficientP);

		if (p.evaluate(x) == 0){
			return true;
		}else{
			return false;
		}
	}
}