package strategy.calculatorExample;


public class CalcClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Operators opts = new Operators();
		opts.setOpt1(10);
		opts.setOpt2(8);
		
		System.out.println(Calculator.calc(opts, new Add()));
		System.out.println(Calculator.calc(opts, new Sub()));
	}

}

class Add implements Calculation{

	@Override
	public int calc(int a, int b) {
		return a + b;
	}
	
}

class Sub implements Calculation{

	@Override
	public int calc(int a, int b) {
		return a - b;
	}
	
}
