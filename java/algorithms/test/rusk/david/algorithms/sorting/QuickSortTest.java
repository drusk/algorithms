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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class QuickSortTest {

	public QuickSort getPivotFirstElementQuickSort() {
		return new QuickSort(new PivotRule() {

			@Override
			public int choosePivot(int[] array) {
				return 0;
			}
		});
	}

	public QuickSort getPivotLastElementQuickSort() {
		return new QuickSort(new PivotRule() {

			@Override
			public int choosePivot(int[] array) {
				return array.length - 1;
			}
		});
	}

	@Test
	public void countComparisions10Numbers() {
		int[] array = new int[] { 3, 9, 8, 4, 6, 10, 2, 5, 7, 1 };

		QuickSort pivotFirstQuickSort = getPivotFirstElementQuickSort();
		pivotFirstQuickSort.sort(Arrays.copyOf(array, array.length));

		assertEquals(25, pivotFirstQuickSort.getComparisonCount());

		QuickSort pivotLastQuickSort = getPivotLastElementQuickSort();
		pivotLastQuickSort.sort(Arrays.copyOf(array, array.length));

		assertEquals(29, pivotLastQuickSort.getComparisonCount());
	}

}
