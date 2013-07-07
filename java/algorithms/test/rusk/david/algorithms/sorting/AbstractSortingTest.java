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

	private SortingAlgorithm undertest;

	@Before
	public void setUp() {
		undertest = getSortingAlgorithm();
	}

	public abstract SortingAlgorithm getSortingAlgorithm();

	@Test
	public void emptyArray() {
		assertArrayEquals(new int[] {}, undertest.sort(new int[] {}));
	}

	@Test
	public void oneElement() {
		assertArrayEquals(new int[] { 1 }, undertest.sort(new int[] { 1 }));
	}

	@Test
	public void twoElements() {
		assertArrayEquals(new int[] { 1, 2 },
				undertest.sort(new int[] { 2, 1 }));
	}

	@Test
	public void evenNumberOfElements() {
		assertArrayEquals(new int[] { 3, 5, 6, 7 },
				undertest.sort(new int[] { 7, 3, 6, 5 }));
	}

	@Test
	public void oddNumberOfElements() {
		assertArrayEquals(new int[] { 2, 8, 11, 17, 21 },
				undertest.sort(new int[] { 11, 2, 8, 17, 21 }));
	}

	@Test
	public void alreadySorted() {
		assertArrayEquals(new int[] { 1, 2, 3 },
				undertest.sort(new int[] { 1, 2, 3 }));
	}

	@Test
	public void originalUnaltered() {
		int[] originalArray = new int[] { 3, 2, 1 };
		assertArrayEquals(new int[] { 1, 2, 3 }, undertest.sort(originalArray));
		assertArrayEquals(new int[] { 3, 2, 1 }, originalArray);
	}

}
