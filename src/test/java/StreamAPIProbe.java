import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class StreamAPIProbe {
	@Test
	public  void sum(){
		List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 11, 13, 17, 19);
		int sumOfNumbers = numbers.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum);
		System.out.println(sumOfNumbers);
	}
}
