# Copyright (C) 2013 David Rusk
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to
# deal in the Software without restriction, including without limitation the
# rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
# sell copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
# FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
# IN THE SOFTWARE.

__author__ = "David Rusk <drusk@uvic.ca>"

import unittest

from hamcrest import assert_that, contains

import sorting


class BubbleSortTest(unittest.TestCase):
    def test_empty_array(self):
        array = []
        sorting.bubble_sort(array)
        assert_that(array, contains())

    def test_one_element(self):
        array = [1]
        sorting.bubble_sort(array)
        assert_that(array, contains(1))

    def test_two_elements(self):
        array = [2, 1]
        sorting.bubble_sort(array)
        assert_that(array, contains(1, 2))

    def test_even_number_of_elements(self):
        array = [7, 3, 6, 5]
        sorting.bubble_sort(array)
        assert_that(array, contains(3, 5, 6, 7))

    def test_odd_number_of_elements(self):
        array = [11, 2, 8, 17, 21]
        sorting.bubble_sort(array)
        assert_that(array, contains(2, 8, 11, 17, 21))

    def test_already_sorted(self):
        array = [1, 2, 3]
        sorting.bubble_sort(array)
        assert_that(array, contains(1, 2, 3))

    def test_duplicate_items(self):
        array = [4, 8, 5, 3, 5, 2]
        sorting.bubble_sort(array)
        assert_that(array, contains(2, 3, 4, 5, 5, 8))


if __name__ == "__main__":
    unittest.main()
