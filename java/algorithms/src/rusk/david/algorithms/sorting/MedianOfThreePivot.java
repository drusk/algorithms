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

public class MedianOfThreePivot implements PivotRule {

	@Override
	public int choosePivot(int[] array) {
		int firstIndex = 0;
		int middleIndex = getMiddleIndex(array);
		int lastIndex = array.length - 1;

		int first = array[0];
		int middle = array[middleIndex];
		int last = array[array.length - 1];

		if (first < middle && first < last) {
			if (middle < last) {
				return middleIndex;
			} else {
				return lastIndex;
			}
		} else if (middle < first && middle < last) {
			if (first < last) {
				return firstIndex;
			} else {
				return lastIndex;
			}
		} else {
			if (first < middle) {
				return firstIndex;
			} else {
				return middleIndex;
			}
		}
	}

	private int getMiddleIndex(int[] array) {
		if (array.length % 2 == 0) {
			return array.length / 2 - 1;
		} else {
			return (array.length - 1) / 2;
		}

	}
}
