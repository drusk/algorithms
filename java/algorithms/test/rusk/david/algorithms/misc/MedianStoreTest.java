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
package rusk.david.algorithms.misc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MedianStoreTest {

	private MedianStore undertest;

	@Before
	public void setUp() {
		undertest = new MedianStore();
	}

	@Test(expected = RuntimeException.class)
	public void medianOfEmptyStore() {
		undertest.getMedian();
	}

	@Test
	public void medianOfOneItem() {
		undertest.insert(1);
		assertEquals(1, undertest.getMedian());
	}

	@Test
	public void medianOfTwo() {
		undertest.insert(1);
		undertest.insert(2);
		assertEquals(1, undertest.getMedian());
	}

	@Test
	public void medianOfThree() {
		undertest.insert(1);
		undertest.insert(2);
		undertest.insert(3);
		assertEquals(2, undertest.getMedian());
	}

	@Test
	public void medianWithDuplicates() {
		undertest.insert(5);
		undertest.insert(2);
		undertest.insert(3);
		undertest.insert(2);
		assertEquals(2, undertest.getMedian());
	}

	@Test
	public void medianLargeEvenNumberOfItems() {
		undertest.insert(5);
		undertest.insert(9);
		undertest.insert(3);
		undertest.insert(2);
		undertest.insert(5);
		undertest.insert(6);
		undertest.insert(7);
		undertest.insert(8);
		undertest.insert(12);
		undertest.insert(13);
		assertEquals(6, undertest.getMedian());
	}

	@Test
	public void medianLargeOddNumberOfItems() {
		undertest.insert(5);
		undertest.insert(9);
		undertest.insert(3);
		undertest.insert(2);
		undertest.insert(5);
		undertest.insert(6);
		undertest.insert(7);
		undertest.insert(8);
		undertest.insert(12);
		undertest.insert(13);
		undertest.insert(14);
		assertEquals(7, undertest.getMedian());
	}

	@Test
	public void medianSmallAtEnd() {
		undertest.insert(4);
		undertest.insert(5);
		undertest.insert(6);
		undertest.insert(7);
		undertest.insert(8);
		undertest.insert(9);
		undertest.insert(10);
		undertest.insert(1);
		undertest.insert(2);
		undertest.insert(3);
		assertEquals(5, undertest.getMedian());
	}
}
