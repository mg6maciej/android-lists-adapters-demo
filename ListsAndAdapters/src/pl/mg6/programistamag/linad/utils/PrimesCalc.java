package pl.mg6.programistamag.linad.utils;

import java.util.ArrayList;
import java.util.List;

import pl.mg6.programistamag.linad.model.Composite;
import pl.mg6.programistamag.linad.model.Prime;

public class PrimesCalc {

	private PrimesCalc() {
	}
	
	public static List<Object> generatePrimes(int max) {
		List<Object> ret = new ArrayList<Object>();
		List<Integer> divisors = new ArrayList<Integer>();
		for (int i = 2; i <= max; i++) {
			for (int div = 2; div <= i / 2; div++) {
				if (i % div == 0) {
					divisors.add(div);
				}
			}
			if (divisors.size() > 0) {
				Composite composite = new Composite();
				composite.value = i;
				composite.divisors = divisors.toArray(new Integer[divisors.size()]);
				ret.add(composite);
				divisors.clear();
			} else {
				Prime prime = new Prime();
				prime.value = i;
				ret.add(prime);
			}
		}
		return ret;
	}
}
