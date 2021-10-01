# read_and_write_files.py file
#
# created by -- Ted Strombeck -- 9/8/2921
#

# python 3 imports
import sys

def read_file_to_list(file_name):
	"""
	reads a file by the inputted filename and saves the values (separated by commas) into
	a list and then returns said list.
	
	Parameters
	----------
	String
		file_name: the name of the file to open and get the information from
		
	Returns
	-------
	List
		content: the information that was in the file
	
	"""
	# opening the file
	my_file = open(file_name)
	
	# splitting up the content
	content = my_file.read().split("\n")
	
	# the last item in the list usually had a '\n' so this removes that extra couple of characters
	content = content[:-1]
	
	# returning the list of information
	return content
	

def write_list_to_file(list_to_write, file_name, new_file=False):
	"""
	writes a file by the name of "file_name-sorted.txt" determined by the passed in file name
	with the additional "-sorted" information to show that it stores the sorted list passed into it.
	
	Parameters
	----------
	List
		list_to_write: the list of information to write to the file
	String
		file_name: the name of the file to be saved as
	Boolean
		new_file: a true or false value  
	"""
	# creating the new file name to be saved as
	if new_file: # if we are creating a new file we already have the correct file_name
		new_file_name = file_name
	else: # otherwise we need to edit the file_name
		new_file_name = file_name[:-4] + "-sorted" + ".txt"
	
	# opening the file to write to it
	w = open(new_file_name, 'w')
	
	# iterating throughout the list and writing all of the contents
	for i in range(0, len(list_to_write)):
		w.write(str(list_to_write[i]) + "\n")
	# closing the write object as we are done with the file
	w.close()

def main():
	# if only the filename was called without any file to get information from
	if len(sys.argv) < 2 :
		print( "Usage: python3 python_quick_sort.py filename")
		sys.exit(0) # Exiting without an error code
	
	elif len(sys.argv) < 3 :
		# getting the file name
		file_name = sys.argv[1]
		
	else:
		print("Too many arguments")
		sys.exit(0) # Exiting without an error code

if __name__ == "__main__":
	main()