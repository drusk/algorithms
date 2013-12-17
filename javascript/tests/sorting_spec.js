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

define(["../sorting.js"], function (sorting) {

    describe("bubble sort", function () {

        it("empty array", function () {
            var array = [];
            sorting.bubbleSort(array);

            expect(array).toEqual([]);
        });

        it("one element", function () {
            var array = [1];
            sorting.bubbleSort(array);

            expect(array).toEqual([1]);
        });

        it("two elements", function () {
            var array = [2, 1];
            sorting.bubbleSort(array);

            expect(array).toEqual([1, 2]);
        });

        it("even number of elements", function () {
            var array = [7, 3, 6, 5];
            sorting.bubbleSort(array);

            expect(array).toEqual([3, 5, 6, 7]);
        });

        it("odd number of elements", function () {
            var array = [11, 2, 8, 17, 21];
            sorting.bubbleSort(array);

            expect(array).toEqual([2, 8, 11, 17, 21]);
        });

        it("already sorted", function () {
            var array = [1, 2, 3];
            sorting.bubbleSort(array);

            expect(array).toEqual([1, 2, 3]);
        });

        it("has duplicates", function () {
            var array = [4, 8, 5, 3, 5, 2];
            sorting.bubbleSort(array);

            expect(array).toEqual([2, 3, 4, 5, 5, 8]);
        });

        it("quick sort lecture example", function () {
            var array = [3, 8, 2, 5, 1, 4, 7, 6];
            sorting.bubbleSort(array);

            expect(array).toEqual([1, 2, 3, 4, 5, 6, 7, 8]);
        });

    });
});