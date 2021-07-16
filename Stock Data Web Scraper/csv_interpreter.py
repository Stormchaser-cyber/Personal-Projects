# csv_interpreter.py file
#
# July 2021 -- Created -- Ted Strombeck
#

from os import listdir
from os.path import isfile
import csv
import moving_files

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
        writer.writerow([filename + '.csv'])

def get_files_already_sorted():
    """
    get_files_already_sorted function scans the records spreadsheet and
    returns a list of all of the file names already sorted

    Returns
    -------
    List
        files_already_sorted: the list containing the file names as strings that
                              are already sorted.
    """
    files_already_sorted = []
    
    with open('sorted_spreadsheet_records.csv') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for item in csv_reader:
            if line_count != 0:
                files_already_sorted.append("".join(item))
                
            line_count += 1

    return files_already_sorted

def main():
    sorted_files = get_files_already_sorted()
    criteria = 'nasdaq_screener'
    source_folder_file_path = 'C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Stock Spreadsheets'
    record_files = moving_files.scan_folder(source_folder=source_folder_file_path, criteria=criteria)

    sorted_files.sort()
    record_files.sort()
    if(sorted_files == record_files) or (len(record_files) == 0):
        print('No new files')
    else:
        # Some sort of function to sort through the new files

    #moving_files.move_files(record_files, source_folder_file_path, source_folder_file_path + '/Sorted Records')
        
        

if __name__ == '__main__':
    main()
