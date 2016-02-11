

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*******************************************
 *  CPU time limit exceeded!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *  WHYYYYY!????
 ******************************************/
class PrimeNumCounter {
	private static Set<Integer> primeNums = new HashSet<Integer>();

	public static void main(String[] args) throws IOException {
		PrimeNumCounter counter = new PrimeNumCounter();
		int[] uppers = readInput(System.in);
		for (int upper : uppers) {
			System.out.println(counter.count(upper));
		}
	}

	public int count(int upper) {
		int cnt = 0;
		for (int i = 2; i < upper; i++) {
			if (isPrimeNum(i)) {
				cnt++;
			}
		}
		return cnt;
	}

	public boolean isPrimeNum(int num) {
		if (num == 1) {
			return false;
		}
		if (primeNums.contains(num)) {
			return true;
		}

		for (int i = 2; i <= num; i++) {
			if (num != 2 && num % 2 == 0) {
				return false;
			}
			if (num % i == 0) {
				boolean isPrimeNum = (num == i);
				if (isPrimeNum) {
					primeNums.add(num);
				}
				return isPrimeNum;
			}
		}

		return false;
	}

	private static int[] readInput(InputStream is) throws IOException {
		List<Integer> inputNums = new ArrayList<Integer>();

		try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			br.lines().forEach(l -> { inputNums.add(Integer.parseInt(l)); });
		}

		int len = inputNums.size();
		int[] nums = new int[len];
		for (int i = 0; i < len; i++) {
			nums[i] = inputNums.get(i);
		}

		return nums;
	}
}
