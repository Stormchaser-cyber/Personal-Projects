# python_quick_sort.py file
#
# created by -- Ted Strombeck -- 9/8/2021
#

# python 3 imports
import sys
import math

# custom python file imports
import read_and_write_files as r_and_w
import python_quick_sort as quick_sort

def make_list_copies(list_from_file):
	"""
	Copies the inputted list 5 times and returns the 5 lists for testing purposes
	
	Parameters
	----------
	List
		list_from_file : the list to be copied
	
	Returns
	-------
	List
		copy1 : the first deep copy of the list_from_file parameter
		copy2 : the second deep copy of the list_from_file parameter
		copy3 : the third deep copy of the list_from_file parameter
		copy4 : the fourth deep copy of the list_from_file parameter
		copy5 : the fifth deep copy of the list_from_file parameter
	"""
	copy1 = list_from_file.copy()
	copy2 = list_from_file.copy()
	copy3 = list_from_file.copy()
	copy4 = list_from_file.copy()
	copy5 = list_from_file.copy()
	
	return copy1, copy2, copy3, copy4, copy5

def quarter_sort_test(list_from_file):
	"""
	Runs the quick-sort algorithm on a quarter of the list 5 times and returns the average number 
	of comparisons and the average number of assignments as well. Also runs the randomized 
	quick-sort algorithm over an additional 5 quarter lists and returns the average number of
	comparisons and assignments for those as well.
	
	Parameters
	----------
	List 
		list_from_file : the list in which to copy and run the algorithm on
	
	Returns
	-------
	float
		quarter_num_comparisons : the average number of comparisons that took place when running the test 5 times
	float
		quarter_num_assignments : the average number of assignments that took place when running the test 5 times	
	float
		rand_num_comparisons : the average number of comparisons that took place when running the random tests 5 times
	float
		rand_num_assignments : the average number of assignments that took place when running the random tests 5 times
	"""
	# creating the first five copied lists for testing
	quarter_sort_list_1, quarter_sort_list_2, quarter_sort_list_3, quarter_sort_list_4, quarter_sort_list_5 = make_list_copies(list_from_file) 
	
	quarter_rand_list_1, quarter_rand_list_2, quarter_rand_list_3, quarter_rand_list_4, quarter_rand_list_5 = make_list_copies(list_from_file)
	
	### Sorting a quarter of the file ###
	# getting only a quarter of the values
	quarter_list_number = math.ceil(len(list_from_file) * 0.25)
	
	# running first quick-sort test
	quarter_num_comp_1, quarter_num_assign_1 = quick_sort.quick_sort(quarter_sort_list_1, 0, quarter_list_number-1)
	quarter_rand_num_comp_1, quarter_rand_num_assign_1 = quick_sort.randomized_quick_sort(quarter_rand_list_1, 0, quarter_list_number-1)
	# running second quick-sort test
	quarter_num_comp_2, quarter_num_assign_2 = quick_sort.quick_sort(quarter_sort_list_2, 0, quarter_list_number-1)
	quarter_rand_num_comp_2, quarter_rand_num_assign_2 = quick_sort.randomized_quick_sort(quarter_rand_list_2, 0, quarter_list_number-1)
	# running third quick-sort test
	quarter_num_comp_3, quarter_num_assign_3 = quick_sort.quick_sort(quarter_sort_list_3, 0, quarter_list_number-1)
	quarter_rand_num_comp_3, quarter_rand_num_assign_3 =  quick_sort.randomized_quick_sort(quarter_rand_list_3, 0, quarter_list_number-1)
	# running fourth quick-sort test
	quarter_num_comp_4, quarter_num_assign_4 = quick_sort.quick_sort(quarter_sort_list_4, 0, quarter_list_number-1)
	quarter_rand_num_comp_4, quarter_rand_num_assign_4 =  quick_sort.randomized_quick_sort(quarter_rand_list_4, 0, quarter_list_number-1)
	# running fifth quick-sort test
	quarter_num_comp_5, quarter_num_assign_5 = quick_sort.quick_sort(quarter_sort_list_5, 0, quarter_list_number-1)
	quarter_rand_num_comp_5, quarter_rand_num_assign_5 =  quick_sort.randomized_quick_sort(quarter_rand_list_5, 0, quarter_list_number-1)
	
	# getting the average comparisons
	quarter_num_comparisons = (quarter_num_comp_1 + quarter_num_comp_2 + quarter_num_comp_3 + quarter_num_comp_4 + quarter_num_comp_5) / 5
	quarter_rand_num_comparisons = (quarter_rand_num_comp_1 + quarter_rand_num_comp_2 + quarter_rand_num_comp_3 + quarter_rand_num_comp_4 + quarter_rand_num_comp_5) / 5
	# getting the average assignments
	quarter_num_assignments = (quarter_num_assign_1 + quarter_num_assign_2 + quarter_num_assign_3 + quarter_num_assign_4 + quarter_num_assign_5) / 5
	quarter_rand_num_assignments = (quarter_rand_num_assign_1 + quarter_rand_num_assign_2 + quarter_rand_num_assign_3 + quarter_rand_num_assign_4 + quarter_rand_num_assign_5) / 5
	
	return quarter_num_comparisons, quarter_num_assignments, quarter_rand_num_assignments, quarter_rand_num_comparisons
	
def half_sort_test(list_from_file):
	"""
	Runs the quick-sort algorithm on half of the list 5 times and returns the average number 
	of comparisons and the average number of assignments as well. Also runs the randomized 
	quick-sort algorithm over an additional 5 half lists and returns the average number of
	comparisons and assignments for those as well.
	
	Parameters
	----------
	List 
		list_from_file : the list in which to copy and run the algorithm on
	
	Returns
	-------
	float
		half_num_comparisons : the average number of comparisons that took place when running the test 5 times
	float
		half_num_assignments : the average number of assignments that took place when running the test 5 times
	float
		rand_num_comparisons : the average number of comparisons that took place when running the random tests 5 times
	float
		rand_num_assignments : the average number of assignments that took place when running the random tests 5 times
	"""
	# creating the first five copied lists for testing
	half_sort_list_1, half_sort_list_2, half_sort_list_3, half_sort_list_4, half_sort_list_5 = make_list_copies(list_from_file) 
	half_rand_list_1, half_rand_list_2, half_rand_list_3, half_rand_list_4, half_rand_list_5 = make_list_copies(list_from_file) 
	
	
	### Sorting a quarter of the file ###
	# getting only a half of the values
	half_list_number = math.ceil(len(list_from_file) * 0.5)
	
	# running first quick-sort test
	half_num_comp_1, half_num_assign_1 = quick_sort.quick_sort(half_sort_list_1, 0, half_list_number-1)
	half_rand_num_comp_1, half_rand_num_assign_1 = quick_sort.randomized_quick_sort(half_rand_list_1, 0, half_list_number-1)
	# running second quick-sort test
	half_num_comp_2, half_num_assign_2 = quick_sort.quick_sort(half_sort_list_2, 0, half_list_number-1)
	half_rand_num_comp_2, half_rand_num_assign_2 = quick_sort.randomized_quick_sort(half_rand_list_2, 0, half_list_number-1)
	# running third quick-sort test
	half_num_comp_3, half_num_assign_3 = quick_sort.quick_sort(half_sort_list_3, 0, half_list_number-1)
	half_rand_num_comp_3, half_rand_num_assign_3 = quick_sort.randomized_quick_sort(half_rand_list_3, 0, half_list_number-1)
	# running fourth quick-sort test
	half_num_comp_4, half_num_assign_4 = quick_sort.quick_sort(half_sort_list_4, 0, half_list_number-1)
	half_rand_num_comp_4, half_rand_num_assign_4 = quick_sort.randomized_quick_sort(half_rand_list_4, 0, half_list_number-1)
	# running fifth quick-sort test
	half_num_comp_5, half_num_assign_5 = quick_sort.quick_sort(half_sort_list_5, 0, half_list_number-1)
	half_rand_num_comp_5, half_rand_num_assign_5 = quick_sort.randomized_quick_sort(half_rand_list_5, 0, half_list_number-1)
	
	# getting the average comparisons
	half_num_comparisons = (half_num_comp_1 + half_num_comp_2 + half_num_comp_3 + half_num_comp_4 + half_num_comp_5) / 5
	half_rand_num_comparisons = (half_rand_num_comp_1 + half_rand_num_comp_2 + half_rand_num_comp_3 + half_rand_num_comp_4 + half_rand_num_comp_5) / 5
	# getting the average assignments
	half_num_assignments = (half_num_assign_1 + half_num_assign_2 + half_num_assign_3 + half_num_assign_4 + half_num_assign_5) / 5
	half_rand_num_assignments = (half_rand_num_assign_1 + half_rand_num_assign_2 + half_rand_num_assign_3 + half_rand_num_assign_4 + half_rand_num_assign_5) / 5
	
	return half_num_comparisons, half_num_assignments, half_rand_num_comparisons, half_rand_num_assignments

def full_sort_test(list_from_file):
	"""
	Runs the quick-sort algorithm on the full list 5 times and returns the average number 
	of comparisons and the average number of assignments as well. Also runs the randomized 
	quick-sort algorithm over an additional 5 full lists and returns the average number of
	comparisons and assignments for those as well.
	
	Parameters
	----------
	List 
		list_from_file : the list in which to copy and run the algorithm on
	
	Returns
	-------
	float
		num_comparisons : the average number of comparisons that took place when running the test 5 times
	float
		num_assignments : the average number of assignments that took place when running the test 5 times
	float
		rand_num_comparisons : the average number of comparisons that took place when running the random tests 5 times
	float
		rand_num_assignments : the average number of assignments that took place when running the random tests 5 times
	"""
	# creating the first five copied lists for testing
	full_sort_list_1, full_sort_list_2, full_sort_list_3, full_sort_list_4, full_sort_list_5 = make_list_copies(list_from_file) 
	full_rand_list_1, full_rand_list_2, full_rand_list_3, full_rand_list_4, full_rand_list_5 = make_list_copies(list_from_file) 
	
	### Sorting a quarter of the file ###
	# getting only a half of the values
	list_number = len(list_from_file)
	
	# running first quick-sort test
	num_comp_1, num_assign_1 = quick_sort.quick_sort(full_sort_list_1, 0, list_number-1)
	rand_num_comp_1, rand_num_assign_1 = quick_sort.randomized_quick_sort(full_rand_list_1, 0, list_number-1)
	# running second quick-sort test
	num_comp_2, num_assign_2 = quick_sort.quick_sort(full_sort_list_2, 0, list_number-1)
	rand_num_comp_2, rand_num_assign_2 = quick_sort.randomized_quick_sort(full_rand_list_2, 0, list_number-1)
	# running third quick-sort test
	num_comp_3, num_assign_3 = quick_sort.quick_sort(full_sort_list_3, 0, list_number-1)
	rand_num_comp_3, rand_num_assign_3 = quick_sort.randomized_quick_sort(full_rand_list_3, 0, list_number-1)
	# running fourth quick-sort test
	num_comp_4, num_assign_4 = quick_sort.quick_sort(full_sort_list_4, 0, list_number-1)
	rand_num_comp_4, rand_num_assign_4 = quick_sort.randomized_quick_sort(full_rand_list_4, 0, list_number-1)
	# running fifth quick-sort test
	num_comp_5, num_assign_5 = quick_sort.quick_sort(full_sort_list_5, 0, list_number-1)
	rand_num_comp_5, rand_num_assign_5 = quick_sort.randomized_quick_sort(full_rand_list_5, 0, list_number-1)
	
	# getting the average comparisons
	num_comparisons = (num_comp_1 + num_comp_2 + num_comp_3 + num_comp_4 + num_comp_5) / 5
	rand_num_comparisons = (rand_num_comp_1 + rand_num_comp_2 + rand_num_comp_3 + rand_num_comp_4 + rand_num_comp_5) / 5
	# getting the average assignments
	num_assignments = (num_assign_1 + num_assign_2 + num_assign_3 + num_assign_4 + num_assign_5) / 5
	rand_num_assignments = (rand_num_assign_1 + rand_num_assign_2 + rand_num_assign_3 + rand_num_assign_4 + rand_num_assign_5) / 5
	
	
	return num_comparisons, num_assignments, rand_num_comparisons, rand_num_assignments

def print_results(filename, quarter_num_comparisons, quarter_num_assignments, half_num_comparisons, half_num_assignments, sorted_list_length, num_comparisons, num_assignments):
	"""
	Print_results prints the tests have been conducted.
	
	Parameters
	----------
	String
		filename: the name of the file we conducted the tests on
	Int
		quarter_list_number: the length of the quarter list that we did quick-sort over
	Int
		quarter_num_comparisons: the number of comparisons conducted over the quarter list
	Int
		quarter_num_assignments: the number of assignments conducted over the quarter list
	Int
		half_list_number: the length of the half list that we did quick-sort over
	Int
		half_num_comparisons: the number of comparisons conducted over the half list
	Int
		half_num_assignments: the number of assignments conducted over the half list
	Int
		sorted_list_length: the length of the full list that we did quick-sort over
	Int
		num_comparisons: the number of comparisons conducted over the full list
	Int
		num_assignments: the number of assignments conducted over the full list
	"""
	# printing the results for a quarter of the file
	print("---------------------------------------------------------------------------")
	print("For the %s file, when sorting a quarter of the files:" %filename)
	print("Number of values sorted: %d" % math.ceil(sorted_list_length * 0.25))
	print("Average number of comparisons: %.2f" % quarter_num_comparisons)
	print("Average number of assignments: %.2f" % quarter_num_assignments)
	print("---------------------------------------------------------------------------")
	
	# printing the results for half of the file
	print("---------------------------------------------------------------------------")
	print("For the %s file, when sorting half of the files:" %filename)
	print("Average number of values sorted: %.2f" % math.ceil(sorted_list_length * 0.5))
	print("Average number of comparisons: %.2f" % half_num_comparisons)
	print("Average number of assignments: %.2f" % half_num_assignments)
	print("---------------------------------------------------------------------------")
	
	# printing the results for the whole file
	print("---------------------------------------------------------------------------")
	print("For the %s file, when sorting the whole file:" %filename)
	print("Average number of values sorted: %.2f" % sorted_list_length)
	print("Average number of comparisons: %.2f" % num_comparisons)
	print("Average number of assignments: %.2f" % num_assignments)
	print("---------------------------------------------------------------------------")

def run_one_test(filename):
	"""
	Runs 3 separate tests on one file as per instructions via moodle. It runs quick-sort
	on the first quarter of the list, the first half of the list, and then the entire list.
	Once it has finished running it will call the print_results function to print out the
	results to the screen.
	
	Parameters
	----------
	String
		filename: the name of the file to search for and open
	"""
	# read information from file
	list_from_file = r_and_w.read_file_to_list(filename)
	
	### Sorting quarter of the file ###
	# calling the function to sort a quarter of the file
	quarter_num_comparisons, quarter_num_assignments, rand_quarter_num_comparisons, rand_quarter_num_assignments = quarter_sort_test(list_from_file)
	
	### Sorting half of the file ###
	# calling the function to sort half of the file
	half_num_comparisons, half_num_assignments, rand_half_num_comparisons, rand_half_num_assignments = half_sort_test(list_from_file)
	
	### Sorting the whole file ###
	# calling the function to sort the full file
	num_comparisons, num_assignments, rand_num_comparisons, rand_num_assignments = full_sort_test(list_from_file)
	
	# printing results
	print("Normal Quicksort Tests")
	print_results(filename, quarter_num_comparisons, quarter_num_assignments, half_num_comparisons, half_num_assignments, len(list_from_file), num_comparisons, num_assignments)
	print("Randomized Quicksort Tests")
	print_results(filename, rand_quarter_num_comparisons, rand_quarter_num_assignments, rand_half_num_comparisons, rand_half_num_assignments, len(list_from_file), rand_num_comparisons, rand_num_assignments)
	
def main():
	"""
	Reads user input to open up a .txt file, parses it into a list, runs the quick-sort
 	algorithm on a quarter of the list, half of the list, and the full list and returns 
 	the statistics, all specified by command line arguments.
 	"""
	# if only the filename was called without any file to get information from
	if len(sys.argv) < 2 :
		print( "Usage: python3 run_quick_sort_test.py filename [filename2] [filename3]")
		sys.exit(0) # Exiting without an error code
	# if we have less than 3 items in the console we know we have the python program
	# name and we also have the filename so we have all that we need!
	
	elif len(sys.argv) < 3 :
		# expecting just one file
		run_one_test(sys.argv[1])
		
	elif len(sys.argv) < 4:
		# expecting two files
		# testing the first file
		print("Testing the First File")
		run_one_test(sys.argv[1])
		
		# testing the second file
		print("\nTesting the Second File")
		run_one_test(sys.argv[2])
		
	elif len(sys.argv) < 5:
		# expecting three files
		# testing the first file
		print("Testing the First File")
		run_one_test(sys.argv[1])
		
		# testing the second file
		print("\nTesting the Second File")
		run_one_test(sys.argv[2])
		
		# testing the third file
		print("\nTesting the Third File")
		run_one_test(sys.argv[3])
		
	else:
		# too many arguments were given
		print("Too many arguments")
		sys.exit(0) # Exiting without an error code
	

if __name__ == "__main__":
	main()