# python_quick_sort.py file
#
# created by -- Ted Strombeck -- 9/8/2021
#

# python 3 imports
import sys
import random

# custom python file imports
import read_and_write_files as r_and_w

def quick_sort(in_list, min_value, max_value, num_comparisons=0, num_assignments=0):
	"""
	the quick_sort function is the python implementation of the quick-sort algorithm that
	takes in an array, the min_value, and the max_value as parameters in order to return
	the sorted list to be written out
	
	Parameters
	----------
	List
		in_list: the list of unsorted values to be sorted
	int
		min_value: the int value of the smallest index to iterate over
	int
		max_value: the int value of the biggest index to iterate over
	
	Returns
	-------
	int
		num_comparisons : the number of comparisons that took place while running the algorithm
	int 
		num_assignments : the number of assignments that took place while running the algorithm
	"""
	# increasing the number of comparisons
	num_comparisons += 1
	
	# initial condition, our min index value should be smaller than the max_value
	# when it is the same, the algorithm is complete
	if min_value < max_value:
		# increasing the number of assignments
		num_assignments += 1
		# partitioning the array and getting our q value
		q, num_comparisons, num_assignments = quick_sort_partition(in_list, min_value, max_value, num_comparisons, num_assignments)
		# recursively calling quick-sort on the left half of the array
		quick_sort(in_list, min_value, q - 1, num_comparisons, num_assignments)
		# recursively calling quick-sort on the right half of the array
		quick_sort(in_list, q + 1, max_value, num_comparisons, num_assignments)
	
	# returning the sorted list so we can write it to a file
	return num_comparisons, num_assignments
	
		
def quick_sort_partition(in_list, min_value, max_value, num_comparisons, num_assignments):
	"""
	the quick_sort_partition function is the python implementation of the partition method of 
	the quick-sort algorithm. It takes in an array, partitions the array, and swaps values
	if they satisfy certain conditions to help sort the list. It returns the index of the next
	partition to be conducted
	
	Parameters
	----------
	List
		in_list: the list of unsorted values to be sorted
	int
		min_value: the int value of the smallest index to iterate over
	int 
		max_value: the int value of the biggest index to iterate over
		
	Returns
	-------
	int
		i + 1: the index of where the next partition is to occur
	int
		num_comparisons : the number of comparisons that took place in partition
	int
		num_assignments : the number of assignments that took place in partition
	"""
	# setting x to be the last item in the list
	x = float(in_list[max_value])
	# setting i to the first value to compare it with
	i = min_value - 1
	
	# increasing the number of assignments
	num_assignments += 2
	
	# increasing the number of comparisons
	num_comparisons += 1
	# iterating until we get to the value before the last item
	for j in range(min_value, max_value):
		# increasing the number of comparisons
		num_comparisons += 1
		# if the current value is less than or equal to the last item...
		if float(in_list[j]) <= x:
			# we increment i by 1
			i = i + 1
			# and we swap places of the two values
			temp = in_list[i]
			in_list[i] = in_list[j]
			in_list[j] = temp
			# increase the number of assignments
			num_assignments += 4
			
	# then we swap the last value with the first value
	temp = in_list[i + 1]
	in_list[i + 1] = in_list[max_value]
	in_list[max_value] = temp
	# increase the number of assignments
	num_assignments += 3
	
	 # and we return the index of the new partition
	return i + 1, num_comparisons, num_assignments
		
def randomized_quick_sort(in_list, min_value, max_value, num_comparisons=0, num_assignments=0):
	"""
	the randomized_quick_sort function is the python implementation of the randomized quick-sort algorithm that
	takes in an array, the min_value, and the max_value as parameters in order to return
	the sorted list to be written out using random number generators!
	
	Parameters
	----------
	List
		in_list: the list of unsorted values to be sorted
	int
		min_value: the int value of the smallest index to iterate over
	int
		max_value: the int value of the biggest index to iterate over
	
	Returns
	-------
	int
		num_comparisons : the number of comparisons that took place while running the algorithm
	int 
		num_assignments : the number of assignments that took place while running the algorithm
	"""
	# increasing the number of comparisons
	num_comparisons += 1
	
	# initial condition, our min index value should be smaller than the max_value
	# when it is the same, the algorithm is complete
	if min_value < max_value:
		# increasing the number of assignments
		num_assignments += 1
		# partitioning the array and getting our q value
		q, num_comparisons, num_assignments = randomized_quick_sort_partition(in_list, min_value, max_value, num_comparisons, num_assignments)
		# recursively calling quick-sort on the left half of the array
		randomized_quick_sort(in_list, min_value, q - 1, num_comparisons, num_assignments)
		# recursively calling quick-sort on the right half of the array
		randomized_quick_sort(in_list, q + 1, max_value, num_comparisons, num_assignments)
	
	# returning the sorted list so we can write it to a file
	return num_comparisons, num_assignments
	
def randomized_quick_sort_partition(in_list, min_value, max_value, num_comparisons, num_assignments):
	"""
	the randomized_quick_sort_partition function is the python implementation of the partition method of 
	the quick-sort algorithm. It takes in an array, partitions the array, and swaps values
	if they satisfy certain conditions to help sort the list. It returns the index of the next
	partition to be conducted
	
	Parameters
	----------
	List
		in_list: the list of unsorted values to be sorted
	int
		min_value: the int value of the smallest index to iterate over
	int 
		max_value: the int value of the biggest index to iterate over
		
	Returns
	-------
	method
		quick_sort_partition: the quick-sort partition method with the randomized values
	"""
	# finding a random value
	i = random.randint(min_value, max_value)
	
	# switching right value with the random value
	temp = in_list[max_value]
	in_list[max_value] = in_list[i]
	in_list[i] = temp
	num_assignments += 4
	
	# calling and returning quick_sort_partition
	return quick_sort_partition(in_list, min_value, max_value, num_comparisons, num_assignments)

def main():
	""" 
	Reads user input to open up a .txt file, parse it into a list, run the quick-sort
	algorithm on the list and returns the statistics all specified by command line arguments.
	"""
	# if only the filename was called without any file to get information from
	if len(sys.argv) < 2 :
		print( "Usage: python3 python_quick_sort.py filename")
		sys.exit(0) # Exiting without an error code
	# if we have less than 3 items in the console we know we have the python program
	# name and we also have the filename so we have all that we need!
	elif len(sys.argv) < 3 :
		# getting the file name
		file_name = sys.argv[1]
	
		# reading the file and saving its' contents into a list
		list_from_file = r_and_w.read_file_to_list(file_name)
	
		# calling quick sort on the unsorted list and saving it into a sorted list
		num_comparisons, num_assignments = quick_sort(list_from_file, 0, (len(list_from_file) - 1))
	
		# writing the contents of the sorted list to a new file
		r_and_w.write_list_to_file(list_from_file, file_name)
	
		# printing the statistics
		print("Number of values sorted: %d" % len(list_from_file))
		print("Number of comparisons: %d" % num_comparisons)
		print("Number of assignments: %d" % num_assignments)
	else:
		print("Too many arguments")
		sys.exit(0) # Exiting without an error code
		

if __name__ == "__main__":
	main()