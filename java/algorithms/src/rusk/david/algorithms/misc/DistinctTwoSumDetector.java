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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implements a variant of the 2-sum problem. The goal of the general 2-sum
 * problem is to determine whether or not there are two numbers in an unsorted
 * array of integers which add to a specified target values.
 * 
 * This class implements a variant where the two integers must be distinct, or
 * they don't count.
 * 
 * @author drusk
 * 
 */
public class DistinctTwoSumDetector {

	/* A HashSet implements the Set interface, but is backed by a hash table. */
	private Set<Integer> hashtable = new HashSet<Integer>();

	public DistinctTwoSumDetector(int[] integers) {
		for (int integer : integers) {
			hashtable.add(integer);
		}
	}

	public DistinctTwoSumDetector(List<Integer> integers) {
		for (Integer integer : integers) {
			hashtable.add(integer);
		}
	}

	public boolean hasTwoSum(int targetValue) {
		for (Integer x : hashtable) {
			int y = targetValue - x;
			if (hashtable.contains(y) && x != y) {
				return true;
			}
		}
		return false;
	}
}
