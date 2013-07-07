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
package rusk.david.algorithms.sorting;

/**
 * A classic sorting algorithm with O(n^2) runtime.
 * 
 * @author drusk
 * 
 */
public class BubbleSort implements SortingAlgorithm {

	@Override
	public int[] sort(int[] array) {
		// Don't modify the original input
		int[] sortedArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			sortedArray[i] = array[i];
		}

		int swaps;
		int temp;

		do {
			swaps = 0;
			for (int i = 0; i < sortedArray.length - 1; i++) {
				if (sortedArray[i] > sortedArray[i + 1]) {
					temp = sortedArray[i + 1];
					sortedArray[i + 1] = sortedArray[i];
					sortedArray[i] = temp;
					swaps++;
				}
			}
		} while (swaps > 0);

		return sortedArray;
	}

}
