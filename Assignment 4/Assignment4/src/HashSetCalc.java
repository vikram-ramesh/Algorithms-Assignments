import java.util.Arrays;
import java.util.Random;

public class HashSetCalc {

	static int slots; // number of bins or slots
	static int[] arrHashes; // array of Hashes
	static int result[]; // to store count of C1 and B0
	static double iteration = 50.0; //Number of times where program runs to get accurate Value

	public static void main(String[] args) {
		double avgCoupon = 0.0;
		double avgBirthday = 0.0;
		slots = 300;
		
		for (int i = 0; i < iteration; i++) {
			arrHashes = new int[slots];
			Arrays.fill(arrHashes, -1);
			result = new int[2];
			hashFunction();
			avgCoupon = avgCoupon + result[0];
			avgBirthday = avgBirthday + result[1];
		}
		System.out.println("Collector Coupon program value:"+avgCoupon/iteration);
		System.out.println("Collector Coupon theoretical value :"+Math.sqrt(Math.PI * slots / 2));
		System.out.println("Birthday Problem program value:"+avgBirthday/iteration);
		System.out.println("Birthday Problem theoretical value :"+slots*Math.log(slots));
	}

	//Hash function
	static void hashFunction() {
		boolean checkFirstIteration = true;
		boolean checkForAllElements = false;
		while(true) {
			if(checkFirstIteration) {
				initializeArray1(true);
				checkFirstIteration = false;
				continue;
			}else {
				checkForAllElements = false;
				for(int i=0;i<slots;i++) {
					if(arrHashes[i]==-1) {
						initializeArray2();
						checkForAllElements = true;
					}
				}
			}
				if(checkForAllElements) {
					continue;
				}else {
					break;
				}
		}
	}
	
	//First array initialization to get C1(m)
	static void initializeArray1(boolean first) {
		int c = 0;
		for (int i = 0; i < slots; i++) {
			Random rand = new Random();
			int val = rand.nextInt(slots);
			int mod = val % slots;
			if (arrHashes[mod] == -1) {
				arrHashes[mod] = val;
				result[1]++;
				c++;
			} else if(first){
				result[0] = c;
				break;
			}
		}
	}
	
	//Continue the initialization if the value is not filled in the array
	static void initializeArray2() {
		for (int i = 0; i < slots; i++) {
			Random rand = new Random();
			int value = rand.nextInt(slots);
			//Hash implementation
			int mod = value % slots;
			arrHashes[mod] = value;
			result[1]++;
		}
	}
	
	//For unit test case using same hash function
	static void hashTestCase(int givenArray[]) {
		for (int i = 0; i < slots; i++) {
			int mod = givenArray[i] % slots;
			arrHashes[mod] = givenArray[i];
		}
	}

}
