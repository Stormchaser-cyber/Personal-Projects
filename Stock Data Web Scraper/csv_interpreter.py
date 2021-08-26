# csv_interpreter.py file
#
# July 2021 -- Created -- Ted Strombeck
#

import os
from os import listdir
from os.path import isfile
import csv
import moving_files
import datetime
import time

# TODO -----------------------------------------------------------
#   - process csv files that haven't been sorted yet --------->  |
#   - move the files once they have been processed ----------->  |
#   - make contenders csv file ------------------------------->  |
#   - place contending stocks into contender csv file -------->  |
#           - make sure not to overwrite any stocks ---------->  |
#   - refactor and break down into distinct functions -------->  |
# ----------------------------------------------------------------

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

def clean_file(filename):

    with open('C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Stock Spreadsheets/' + filename,'r') as input_file:
        csv_reader = csv.reader(input_file, delimiter=',')
        line_count = 0

        lines_to_write = []
        
        for item in csv_reader:
            if line_count != 0:
                for i in range(len(item)):
                    if item[i] == '':
                        item[i] = 'n/a'
                
##                print('Ticker: ', item[0])
##                print('Name: ', item[1])
##                print('Share Price: ', item[2])
##                print('Share Price Fluctuation ($): ', item[3])
##                print('Share Price Fluctuation (%): ', item[4])
##                print('Market Cap: ', item[5]) # add value to tell that there was no inputted value
##                print('Country: ', item[6]) # add value to tell that there was no inputted value
##                print('IPO Year: ', item[7]) # add value to tell that there was no inputted value
##                print('Volume: ', item[8])
##                print('Sector: ', item[9])
##                print('Industry: ', item[10])
##                print('\n')

                lines_to_write.append(item)
            line_count += 1

    with open('C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Stock Spreadsheets/' + filename,'w', newline='') as write_file:
        csv_writer = csv.writer(write_file, delimiter=',')

        csv_writer.writerow(['Symbol', 'Name', 'Last Sale', 'Net Change_v2', '% Change', 'Market Cap', 'Country',
                             'IPO Year', 'Volume', 'Sector', 'Industry'])
        for item in lines_to_write:
            csv_writer.writerow(item)
            
    time.sleep(1)
    rename_file(filename)


def rename_file(file_name):
    file_path = 'C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Stock Spreadsheets/'
    destination_path = 'C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Stock Spreadsheets/Sorted Records/'
    current_time = datetime.datetime.now()
    new_file_name = 'nasdaq_screener_%s_%s_%s__%s_%s_%s.csv' % (current_time.day, current_time.month, current_time.year, current_time.hour, current_time.minute, current_time.second) 
    os.rename(file_path + file_name, destination_path + new_file_name)

            
    

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
        for i in range(len(record_files)):
            clean_file(record_files[i])
##        for item in record_files:
##            clean_file(item) 
        

if __name__ == '__main__':
    main()
