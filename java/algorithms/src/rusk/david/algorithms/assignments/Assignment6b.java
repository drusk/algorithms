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
import java.util.ArrayList;
import java.util.List;

import rusk.david.algorithms.misc.MedianStore;
import rusk.david.algorithms.utils.IOUtils;

public class Assignment6b {

	private static final String INPUT_FILE = "/home/drusk/Documents/Courses/online/algorithms1/assignments/a6/Median.txt";

	public static void main(String[] args) throws IOException {
		List<Integer> integers = new ArrayList<Integer>();
		for (String line : IOUtils.readLines(INPUT_FILE)) {
			integers.add(Integer.parseInt(line));
		}

		System.out.println("Parsed data.");

		MedianStore medianStore = new MedianStore();

		int sum = 0;
		for (Integer integer : integers) {
			medianStore.insert(integer);
			sum += medianStore.getMedian();
		}

		int result = sum % 10000;

		System.out.println("Result: " + result);
	}

}
