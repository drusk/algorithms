/******************************************************************************
 * Copyright (C) 2013 David Rusk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *****************************************************************************/
package rusk.david.algorithms.assignments;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import rusk.david.algorithms.sorting.MedianOfThreePivot;
import rusk.david.algorithms.sorting.MergeSort;
import rusk.david.algorithms.sorting.PivotRule;
import rusk.david.algorithms.sorting.QuickSort;
import rusk.david.algorithms.sorting.RandomizedPivot;
import rusk.david.algorithms.utils.IOUtils;

public class Assignment2 {

	private static int[] get_input_array() throws IOException {
		List<String> lines = IOUtils
				.readLines("/home/drusk/Documents/Courses/online/algorithms1/assignments/a2/QuickSort.txt");

		System.out.println("Read " + lines.size() + " lines.");

		int[] array = new int[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			array[i] = Integer.parseInt(lines.get(i));
		}

		return array;
	}

	private static void sanityCheck() throws IOException {
		int[] array1 = get_input_array();
		int[] array2 = Arrays.copyOf(array1, array1.length);

		MergeSort mergeSort = new MergeSort();
		QuickSort quickSort = new QuickSort(new RandomizedPivot());

		mergeSort.sort(array1);
		quickSort.sort(array2);

		assertArrayEquals(array1, array2);
	}

	public static void main(String[] args) throws IOException {
		sanityCheck();

		// Part 1: use first element as pivot
		QuickSort quickSort1 = new QuickSort(new PivotRule() {

			@Override
			public int choosePivot(int[] array) {
				return 0;
			}
		});

		quickSort1.sort(get_input_array());
		System.out.println("Comparisons when pivoting on first element: "
				+ quickSort1.getComparisonCount());

		// Part 2: use last element as pivot
		QuickSort quickSort2 = new QuickSort(new PivotRule() {

			@Override
			public int choosePivot(int[] array) {
				return array.length - 1;
			}
		});

		quickSort2.sort(get_input_array());
		System.out.println("Comparisons when pivoting on last element: "
				+ quickSort2.getComparisonCount());

		// Part 3
		QuickSort quickSort3 = new QuickSort(new MedianOfThreePivot());
		quickSort3.sort(get_input_array());
		System.out.println("Comparisons when pivoting on median of three: "
				+ quickSort3.getComparisonCount());

	}

}
