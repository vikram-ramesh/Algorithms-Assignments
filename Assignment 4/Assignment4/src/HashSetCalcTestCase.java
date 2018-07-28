import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashSetCalcTestCase {

	@Test
	void hash1() {
		int[] xs = {10,22,34,23,51};
		HashSetCalc.arrHashes = new int[5];
		HashSetCalc.slots = 5;
		HashSetCalc.hashTestCase(xs);
        assertEquals(HashSetCalc.arrHashes[0], 10);
        assertEquals(HashSetCalc.arrHashes[1], 51);
        assertEquals(HashSetCalc.arrHashes[2], 22);
        assertEquals(HashSetCalc.arrHashes[3], 23);
        assertEquals(HashSetCalc.arrHashes[4], 34);
	}
	
	@Test
	void hash2() {
		int[] xs = {53,32,33,26,54};
		HashSetCalc.arrHashes = new int[5];
		HashSetCalc.slots = 5;
		HashSetCalc.hashTestCase(xs);
        assertEquals(HashSetCalc.arrHashes[0], 0);
        assertEquals(HashSetCalc.arrHashes[1], 26);
        assertEquals(HashSetCalc.arrHashes[2], 32);
        assertEquals(HashSetCalc.arrHashes[3], 33);
        assertEquals(HashSetCalc.arrHashes[4], 54);
	}

}
