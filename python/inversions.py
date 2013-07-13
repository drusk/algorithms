"""
Count inversions in a list of numbers.
"""

__author__ = "David Rusk <drusk@uvic.ca>"


def main():
    input_file = ("/home/drusk/Documents/Courses/online/algorithms1/"
                  "assignments/a1/IntegerArray.txt")
    numbers = []
    with open(input_file, "rb") as filehandle:
        for line in filehandle.readlines():
            numbers.append(int(line))

    print "Read %d numbers." % len(numbers)

    # Just implementing the naive way to test other code
    inversions = 0
    for index, num in enumerate(numbers):
        for other_num in numbers[index + 1:]:
            if other_num < num:
                inversions += 1

    print "Inversions: %d" % inversions


if __name__ == "__main__":
    main()
