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

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractSortingTest {

	protected SortingAlgorithm undertest;

	@Before
	public void setUp() {
		undertest = getSortingAlgorithm();
	}

	public abstract SortingAlgorithm getSortingAlgorithm();

	@Test
	public void emptyArray() {
		int[] array = new int[] {};
		undertest.sort(array);
		assertArrayEquals(new int[] {}, array);
	}

	@Test
	public void oneElement() {
		int[] array = new int[] { 1 };
		undertest.sort(array);
		assertArrayEquals(new int[] { 1 }, array);
	}

	@Test
	public void twoElements() {
		int[] array = new int[] { 2, 1 };
		undertest.sort(array);
		assertArrayEquals(new int[] { 1, 2 }, array);
	}

	@Test
	public void evenNumberOfElements() {
		int[] array = new int[] { 7, 3, 6, 5 };
		undertest.sort(array);
		assertArrayEquals(new int[] { 3, 5, 6, 7 }, array);
	}

	@Test
	public void oddNumberOfElements() {
		int[] array = new int[] { 11, 2, 8, 17, 21 };
		undertest.sort(array);
		assertArrayEquals(new int[] { 2, 8, 11, 17, 21 }, array);
	}

	@Test
	public void alreadySorted() {
		int[] array = new int[] { 1, 2, 3 };
		undertest.sort(array);
		assertArrayEquals(new int[] { 1, 2, 3 }, array);
	}

	@Test
	public void duplicateItems() {
		int[] array = new int[] { 4, 8, 5, 3, 5, 2 };
		undertest.sort(array);
		assertArrayEquals(new int[] { 2, 3, 4, 5, 5, 8 }, array);
	}

	@Test
	public void quickSortLectureExample() {
		int[] array = new int[] { 3, 8, 2, 5, 1, 4, 7, 6 };
		undertest.sort(array);
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, array);
	}

}
