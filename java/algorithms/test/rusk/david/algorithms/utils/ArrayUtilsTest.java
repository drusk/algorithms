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
package rusk.david.algorithms.utils;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ArrayUtilsTest {

	@Test
	public void copySlice() {
		int[] array = new int[] { 1, 2, 3, 4 };

		int middle = array.length / 2;
		assertArrayEquals(new int[] { 1, 2 },
				ArrayUtils.copySlice(array, 0, middle));
		assertArrayEquals(new int[] { 3, 4 },
				ArrayUtils.copySlice(array, middle, array.length));
	}

	@Test
	public void copyInto() {
		int[] source = new int[] { 1, 2, 3, 4 };
		int[] target = new int[] { 0, 0, 0, 0 };

		ArrayUtils.copyInto(target, source);

		assertArrayEquals(source, target);
	}
	
	@Test
	public void reverse() {
		int[] array = new int[] { 1, 2, 3 };
		
		int[] reversed = ArrayUtils.reverse(array);
		assertArrayEquals(new int[] { 3, 2, 1 }, reversed);
	}

}
