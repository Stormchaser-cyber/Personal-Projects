# create_test_file.py file
#
# created by -- Ted Strombeck -- 9/8/2921
#

# python 3 imports
import sys
import random

# custom python file imports
import python_quick_sort as quick_sort
import read_and_write_files as r_and_w

def create_test_file(smallest_number, biggest_number, number_of_items, filename="test_file"): #TODO - Finish method
	""""
	create_test_file function creates a float .txt comma separated file filled with random float values
	where a user can set the range, number of items, and if they want a custom filename as well
	
	Parameters
	----------
	float
		smallest_number: the smallest float value that can be present in the list
	float
		biggest_number: the biggest float value that can be present in the list
	int
		number_of_items: the desired size of the list
	str
		filename: the desired name of the file that will be created
	"""
	
	# creating a list to store elements
	list_of_numbers = []
	
	# iterating through the number of desired items
	for i in range(number_of_items):
		# appending the random numbers to the list between the smallest and biggest numbers
		x = random.uniform(smallest_number, biggest_number)
		list_of_numbers.append('{:.2f}'.format(x))
	
	# calling the write_list_to_file to write the list to a new file
	r_and_w.write_list_to_file(list_of_numbers, filename+'.txt', new_file=True)

def main():
	# if only the filename was called without any file to get information from
	if len(sys.argv) < 4 :
		# the brackets imply an optional parameter
		print( "Usage: python3 create_test_file.py smallest_num biggest_num num_items [file_name]") 
		sys.exit(0) # Exiting without an error code
		
	elif len(sys.argv) < 5:
		# we have the input of "create_test_file.py smallest_num biggest_num num_items"
		smallest_num = float(sys.argv[1]) # grabbing the smallest number from the user
		biggest_num = float(sys.argv[2]) # grabbing the biggest number from the user
		num_items = int(sys.argv[3]) # grabbing the number of items to be had in the array
		
		# calling the create test function with the user values
		create_test_file(smallest_num, biggest_num, num_items)
		
	elif len(sys.argv) < 6:
		# we have the input of "create_test_file.py smallest_num biggest_num num_items filename"
		smallest_num = float(sys.argv[1]) # grabbing the smallest number from the user
		biggest_num = float(sys.argv[2]) # grabbing the biggest number from the user
		num_items = int(sys.argv[3]) # grabbing the number of items to be had in the array
		file_name = str(sys.argv[4]) # grabbing the custom file name from the user 
		
		# calling the create test function with the user values
		create_test_file(smallest_num, biggest_num, num_items, file_name)
		
	else:
		# otherwise we have too many arguments
		print("Too many arguments")
		sys.exit(0) # Exiting without an error code

if __name__ == "__main__":
	main()