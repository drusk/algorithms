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

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianStore {

	private static final int initialCapacity = 10000;

	/* Stores low values, needs to support extract-max. */
	private PriorityQueue<Integer> lowHeap = new PriorityQueue<Integer>(
			initialCapacity / 2, Collections.reverseOrder());

	/* Stores high values, needs to support extract-min. */
	private PriorityQueue<Integer> highHeap = new PriorityQueue<Integer>(
			initialCapacity / 2);

	/**
	 * 
	 * @return The current median of numbers that have been inserted.
	 */
	public int getMedian() {
		if (lowHeap.isEmpty() && highHeap.isEmpty()) {
			throw new RuntimeException("Store is empty.");
		}

		if (lowHeap.size() == highHeap.size()
				|| lowHeap.size() > highHeap.size()) {
			return lowHeap.peek();
		} else {
			return highHeap.peek();
		}
	}

	public void insert(int number) {
		PriorityQueue<Integer> heap = chooseHeap(number);
		heap.offer(number);
		ensureBalanced();
	}

	private PriorityQueue<Integer> chooseHeap(int number) {
		if (lowHeap.isEmpty()) {
			return lowHeap;
		}

		if (number <= lowHeap.peek()) {
			return lowHeap;
		} else {
			return highHeap;
		}
	}

	private void ensureBalanced() {
		if (lowHeap.size() > highHeap.size() + 1) {
			highHeap.offer(lowHeap.poll());
		} else if (highHeap.size() > lowHeap.size() + 1) {
			lowHeap.offer(highHeap.poll());
		}

	}

}
