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

import org.junit.Test;

public class InversionCounterTest {

	@Test
	public void oneElement() {
		assertEquals(0, new InversionCounter().countInversions(new int[] { 0 }));
	}

	@Test
	public void twoElements() {
		assertEquals(1,
				new InversionCounter().countInversions(new int[] { 1, 0 }));
	}

	@Test
	public void firstExampleFromLecture() {
		assertEquals(
				3,
				new InversionCounter().countInversions(new int[] { 1, 3, 5, 2,
						4, 6 }));
	}

	@Test
	public void inOrder() {
		assertEquals(
				0,
				new InversionCounter().countInversions(new int[] { 0, 1, 3, 4,
						6 }));
	}

	@Test
	public void reverseOrder() {
		assertEquals(
				10,
				new InversionCounter().countInversions(new int[] { 5, 4, 3, 2,
						1 }));
	}

	@Test
	public void sixItems() {
		assertEquals(
				5,
				new InversionCounter().countInversions(new int[] { 1, 6, 3, 2,
						4, 5 }));

	}

}
