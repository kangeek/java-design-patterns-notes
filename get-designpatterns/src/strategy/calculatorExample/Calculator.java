package strategy.calculatorExample;


public class Calculator {

	public static int calc(Operators opts, Calculation c){
		return c.calc(opts.getOpt1(), opts.getOpt2());
	}
}
