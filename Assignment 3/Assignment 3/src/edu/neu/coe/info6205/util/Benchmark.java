package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.Sort;


import java.util.Random;
import java.util.function.Function;
public class Benchmark<T> {

	    public Benchmark(Function<T, Void> f) {
	        this.f = f;
	    }

	   // static Integer[] temp = new Integer[1000];
	    
	    public double run(T t, int n) {
	    	double avg = 0.0;
	    	for(int i=0;i<n;i++) {
	    		long startTime =System.nanoTime(); 
	        	f.apply(t);
	        	long endTime =System.nanoTime(); 
	        	avg = avg + (endTime - startTime);
	    	}
	    	return avg/n; 
	    }

	    private final Function<T, Void> f;

	    public static void main(String[] args) {
	        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
	        int n = 1000;
	        Integer[] array = new Integer[n];
	        
	        //Randomly generated Array
	        for (int i = 0; i <n ; i++) {
	        	Random r = new Random();
	        	array[i] = r.nextInt(n); 
	        }
	        
	        //Partially Sorted array with 100 inversions
	        /*for (int i = 0; i <n ; i++) {
	        	array[i] = i;
	        }*/
	        
	        //Randomly sorted Array
	        /*for(int i=100;i<200;i++) {
	        	int temp = array[i];
	        	array[i] = array[i+1];
	        	array[i+1] = temp;
	        	i=i+1;
	        }*/
	        
	        //Sorted Array
	        /*for (int i = 0; i <n ; i++) {
	        	array[i] = i;
	    	}*/
	        
	        //Reverse Sorted Array
	        int count = 0;
	        for (int i = n-1; i >=0 ; i--) {
	        	array[count] = i; 
	        	count++;
	        }
	       benchmarkSort(array, n, "SelectionSort", new SelectionSort<>(), m);
	       benchmarkSort(array, n, "InsertionSort", new InsertionSort<>(), m);
	    }

	    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m) {
	        Function<Integer, Void> sortFunction = (x) -> {
	            sorter.sort(xs, 0, x);
	            return null;
	        };
	        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
	        double x = bm.run(n, m);
	        System.out.println(name + ": " + x + " nanoseconds for n=" + n);
	    }
	}
