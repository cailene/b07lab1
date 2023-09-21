public class Polynomial{

	//this is a field. 
	//double[] coefficientP = new double[20];
	double[] coefficientP;

	//this is a constructor. it initializes references, primitives, objects????
	public Polynomial(){
		coefficientP = new double[] {0};
	}
	
	public Polynomial(double[] array){
		coefficientP = new double[array.length];
		for (int i = 0; i < array.length; i++)
		{
			coefficientP[i] = array[i];
		}
	}
	
	// (Polynomial poly) is an argument
	public Polynomial add(Polynomial poly){
		if (poly.coefficientP.length <= coefficientP.length) {
			for (int i = 0; i < poly.coefficientP.length; i++){	
				poly.coefficientP[i] += coefficientP[i];
			}
			return poly;
		}else {
			for (int i = 0; i < coefficientP.length; i++){	
				poly.coefficientP[i] += coefficientP[i];
			}
			return poly;
		}
	}

	public double evaluate(double x){
		double result = 0;
		double y = 1;
		
		for(int i = 0; i < coefficientP.length; i++){
			if (coefficientP[i] != 0) {
				for(int j = 0; j < i; j++){
					y = y*x;
				}
				result += coefficientP[i]*y;
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
