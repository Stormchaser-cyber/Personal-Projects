# Quick-sort README file #
----

### Intro Information ###
- This directory contains python3 scripts that implement the quick-sort algorithm to sort .txt files that contain float values separated by commas.
- author: Ted Strombeck, strombet@augsburg.edu
- iteration 1 : Started: 9/8/2021 Finished: 9/15/2021
- iteration 2 : Started: 9/17/2021 Finished: 9/20/2021
- iteration 3 : Started: 9/27/2021 Finished: 9/28/2021
----

### Main files ###
* .gitignore -- the .gitignore file for this repo
* README.md -- this file!
* TODO.md -- a md file that contains the requirements for the project to be used like a checklist
* create_test_file.py -- main python 3 script for creating a new test file
* numgen.py -- main supplied python3 script for creating test files
* python_quick_sort.py -- main python 3 script for running the quick sort algorithm implementation
* read_and_write_files.py -- main python 3 script for writing and reading .txt files
* run_quick_sort_test.py -- main python3 script for testing files with quick-sort running on a quarter of the list, half of the list, and then on the full list
* sample_test_1.txt -- .txt test file with 100 values
* sample_test_2.txt -- .txt test file with 1500 values
* sample_test_3.txt -- .txt test file with 5000 values
----

### How to use section ###
* In order to create a file you just run either create_test_file.py or numgen.py and usage messages will
pop up in the command line on how to properly use them
* The way to call the quick sort program is by running the following command on the command line where 
the python3 module is located as "python3 (or py if you are on windows) python_quick_sort.py filename",
where the filename is the name of the desired file that you want to sort
* The way to call the run quick sort test program is by running the following command on the command line
where the python3 module is located as "python3 (or py if you are on windows) run_quick_sort_test.py filename
[filename2] [filename3]", where the first filename is required but the second two (filenames 2 and 3) are optional.
----

### Individual File Break-Down ###
##### Create_test_file.py function list #####
* main() -- gets the user input from the command line and creates a test file with the
user specified parameters
* create_test_file(smallest_number, biggest_number, number_of_items, filename="test_file") --
this function creates a test file in between the smallest and biggest numbers with a specified
amount of items to be in the file, as well as having an optional parameter for a custom file name.
If a filename isn't passed into the function it will default to "test_file" otherwise you could 
make your own test file names!

##### numgen.py function list #####
* write_array( filename, data_array) -- writes the given data array out to a file with the given filename
* create_array( how_many, range_size) -- creates an int array with the given number of spots. The array is filled
with int values within the range size, half negative.

##### python_quick_sort.py function list #####
* quick_sort(in_list, min_value, max_value) -- python implementation of the quick-sort algorithm
that takes in an array, the min_value, and the max_value as parameters to return the sorted list to be written out.
* quick_sort_partition(in_list, min_value, max_value) -- python implementation of the quick-sort algorithm
partition step that takes in an array, partitions it, and begins to sort the list and returns the index of the next
partition to be conducted.
* randomized_quick_sort(in_list, min_value, max_value) -- python implementation of the randomized version of the
quick-sort algorithm similar to quick_sort, just uses random values instead.
* randomized_quick_sort_partition(in_list, min_value, max_value) -- python implementation of the randomized version
of the quick-sort algorithm partition step, quick_sort partition, just uses random values instead.
* main() -- gets the user input and calls quick_sort on the appropriate file and user specifications

##### read_and_write_files.py function list #####
* write_list_to_file( list_to_write, file_name, new_file=False) -- writes the given array out to a file whether
it is a new file or an already sorted file (which is what the new_file boolean parameter is for)
* read_file_to_list( file_name) -- reads the file with the associated filename to a list and returns it
* main() -- gets the user input from the command line and runs the appropriate actions according to the 
user specifications

##### run_quick_sort_test.py function list #####
* make_list_copies(in_list) -- This function makes 5 copies of the list (in separate memory locations so they are not pointing to the same list) and returns them for testing purposes
* quarter_sort_test(in_list) -- This function runs 5 tests on a quarter of the list and an additional 5 random tests and returns the average statistics
* half_sort_test(in_list) -- This function runs 5 tests on half of the list and an additional 5 random tests and returns the average statistics
* full_sort_test(in_list) -- This function runs 5 test on the full list and an additional 5 random tests and returns the average statistics
* run_one_test(filename) -- This test function runs 3 separate test on one file for both regular and randomized quick-sort. These tests include, 
running quick-sort on a quarter of the list, running quick-sort on half the list, and the last test is running quick-sort on the full list
* print_results(filename, quarter_list_number, quarter_num_comparisons, quarter_num_assignments, half_list_number, half_num_comparisons, half_num_assignments, sorted_list_length, num_comparisons, num_assignments) -- this function
prints the results that have been tested in the run_one_test function and prints those results to the screen
* main() -- gets the user input from the command line and runs the appropriate actions according to the user specifications. 
It can run the 3 different tests on up to 3 files but 1 file is required for the file to function as intended
