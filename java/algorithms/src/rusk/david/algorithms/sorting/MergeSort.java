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

import rusk.david.algorithms.utils.ArrayUtils;

public class MergeSort implements SortingAlgorithm {

	@Override
	public void sort(int[] array) {
		// Compute the sorted array
		int[] sortedArray = recursiveMergeSort(array);

		// Then make the changes in-place
		ArrayUtils.copyInto(array, sortedArray);
	}

	private int[] recursiveMergeSort(int[] array) {
		if (array.length <= 1) {
			// The array is already sorted
			return array;
		}

		int middleIndex = array.length / 2;
		int[] leftSublist = ArrayUtils.copySlice(array, 0, middleIndex);
		int[] rightSublist = ArrayUtils.copySlice(array, middleIndex,
				array.length);

		leftSublist = recursiveMergeSort(leftSublist);
		rightSublist = recursiveMergeSort(rightSublist);

		return merge(leftSublist, rightSublist);
	}

	private int[] merge(int[] leftArray, int[] rightArray) {
		int mergedLength = leftArray.length + rightArray.length;

		int[] mergedArray = new int[mergedLength];

		int leftIndex = 0;
		int rightIndex = 0;

		while (leftIndex + rightIndex < mergedLength) {
			// There are still items to be merged

			if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
				// Both sublists have items remaining
				if (leftArray[leftIndex] <= rightArray[rightIndex]) {
					mergedArray[leftIndex + rightIndex] = leftArray[leftIndex];
					leftIndex++;
				} else {
					mergedArray[leftIndex + rightIndex] = rightArray[rightIndex];
					rightIndex++;
				}
			}

			else if (leftIndex < leftArray.length) {
				// Only the left sublist has items remaining
				while (leftIndex < leftArray.length) {
					mergedArray[leftIndex + rightIndex] = leftArray[leftIndex];
					leftIndex++;
				}
			}

			else {
				// Only the right sublist has items remaining
				while (rightIndex < rightArray.length) {
					mergedArray[leftIndex + rightIndex] = rightArray[rightIndex];
					rightIndex++;
				}
			}
		}

		return mergedArray;
	}

}
