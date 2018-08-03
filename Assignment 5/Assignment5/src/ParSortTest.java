import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParSortTest {

	@Test
	public void sort1() throws Exception {

        int[] xs = {3,4,2,1};
        ParSort.sort(xs, 0, xs.length);
        assertEquals(xs[0], 1);
        assertEquals(xs[1], 2);
        assertEquals(xs[2], 3);
        assertEquals(xs[3], 4);
    }

	@Test
    public void sort2() throws Exception {

        int[] xs = {2,3,5,1,2};
        ParSort.sort(xs, 0, xs.length);
        assertEquals(xs[0], 1);
        assertEquals(xs[1], 2);
        assertEquals(xs[2], 2);
        assertEquals(xs[3], 3);
        assertEquals(xs[4], 5);
    }

	@Test
    public void sort3() throws Exception {

        int[] xs = {1,2,3,4,5,6,7};
        ParSort.sort(xs, 0, xs.length);
        assertEquals(xs[0], 1);
        assertEquals(xs[1], 2);
        assertEquals(xs[2], 3);
        assertEquals(xs[3], 4);
        assertEquals(xs[4], 5);
        assertEquals(xs[5], 6);
        assertEquals(xs[6], 7);
    }
}
