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

import java.io.IOException;
import java.util.List;

import rusk.david.algorithms.sorting.InversionCounter;
import rusk.david.algorithms.utils.IOUtils;

public class Assignment1 {

	public static void main(String[] args) throws IOException {
		List<String> lines = IOUtils
				.readLines("/home/drusk/Documents/Courses/online/algorithms1/assignments/a1/IntegerArray.txt");

		System.out.println("Read " + lines.size() + " lines.");

		int[] array = new int[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			array[i] = Integer.parseInt(lines.get(i));
		}

		long inversions = new InversionCounter().countInversions(array);
		System.out.println("Counting inversions: " + inversions);

		System.out.println("For reference, max int is: " + Integer.MAX_VALUE);
	}

}
