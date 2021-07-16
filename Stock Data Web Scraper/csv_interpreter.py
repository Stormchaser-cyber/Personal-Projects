# csv_interpreter.py file
#
# July 2021 -- Created -- Ted Strombeck
#

from os import listdir
from os.path import isfile
import csv

def update_sorted_spreadsheet_records(filename):
    """
    update_sorted_spreadsheet_records function updates the current file
    records to add a filename to the end of the csv file

    Parameters
    ----------
    String
        filename: the string name of the file to be added to the csv file
    """
    with open('sorted_spreadsheet_records.csv', mode='a', newline='') as csv_file:
        writer = csv.writer(csv_file, delimiter=',')
        writer.writerow([filename])

def main():
    files_already_sorted = []
    
    with open('sorted_spreadsheet_records') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count != 0:
                files_already_sorted.append(row)
            line_count += 1

    print(files_already_sorted)
        
        

if __name__ == '__main__':
    #main()
