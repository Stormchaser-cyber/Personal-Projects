# csv_interpreter.py file
#
# Created -- Ted Strombeck -- July 2021
# Last Updated -- July 31, 2023
# Version 1.0.1
#

import os
from os import listdir
from os.path import isfile
import csv
import moving_files
import datetime
import time

# TODO ----------------------------------------------------------------------
#   - process csv files that haven't been sorted yet --------->     Done    |
#   - move the files once they have been processed ----------->     Done    |
#   - search files for oldest file on record ----------------->  Working on |
#   - create functionality to generate reports for ----------->             |
#     individual stocks based on newest and oldest                          |
#     data that we have. (Searchable by ticker)                             |
#   - revise clean_file logic -------------------------------->             |
#   - make contenders csv file ------------------------------->             |
#   - place contending stocks into contender csv file -------->             |
#           - make sure not to overwrite any stocks ---------->             |
#   - refactor and break down into distinct functions -------->             |
# ---------------------------------------------------------------------------

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

def clean_file(filename):
    """
    clean_file function cleans the specified file by (currently WIP)

    Parameters
    ----------
    String
        filename: the string name of the file to be added to the csv file
    """
    with open('C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/' + filename,'r') as input_file:
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

    with open('C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/' + filename,'w', newline='') as write_file:
        csv_writer = csv.writer(write_file, delimiter=',')

        csv_writer.writerow(['Symbol', 'Name', 'Last Sale', 'Net Change_v2', '% Change', 'Market Cap', 'Country',
                             'IPO Year', 'Volume', 'Sector', 'Industry'])
        for item in lines_to_write:
            csv_writer.writerow(item)
            
    time.sleep(1)
    rename_file(filename)


def rename_file(file_name):
    """
    rename_file function renames the file to nasdaq_screener____ with trailing information for the date and time that it was processed

    Parameters
    ----------
    String
        file_name: the name of the file to be renamed
    """
    file_path = 'C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/'
    destination_path = 'C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/Sorted Records/'
    current_time = datetime.datetime.now()
    new_file_name = 'nasdaq_screener_%s_%s_%s__%s_%s_%s.csv' % (current_time.day, current_time.month, current_time.year, current_time.hour, current_time.minute, current_time.second) 
    os.rename(file_path + file_name, destination_path + new_file_name)

def generate_candidate_report(stock_ticker):
    """
    generate_candidate_report function creates a text file report for a particular stock where it returns key information about the stock

    Parameters
    ----------
    String     
        stock_ticker: the name of the stock ticker to search for
    """

    # find most recent file <-- break down into own function
    max_date = [None, None, -1, -1, -1, None, -1, -1, -1]
    current_Data = None
    min_date = [None, None, 99, 99, 9999, None, 99, 99, 99]
    past_Data = None

    list_of_files = moving_files.scan_folder(source_folder='C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/Sorted Records', criteria='nasdaq_screener')#glob.glob('C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/Sorted Records/*.csv')
    
    for item in list_of_files:
        
        result = item.split("_")
        result[4] = int(result[4])
        result[3] = int(result[3])
        result[2] = int(result[2])
        result[6] = int(result[6])
        result[7] = int(result[7])
        result[-1] = int(result[-1].split('.')[0])


        if result[4] >= max_date[4]:
            if result[3] >= max_date[3]:
                if result[2] >= max_date[2]:
                    if result[6] >= max_date[6]:
                        if result[7] >= max_date[7]:
                            if result[8] > max_date[8]:
                                max_date = result

    Latest_file_name = '%s_%s_%s_%s_%s__%s_%s_%s.csv' % (str(max_date[0]), str(max_date[1]), str(max_date[2]), str(max_date[3]), str(max_date[4]), str(max_date[6]), str(max_date[7]), str(max_date[8]) )

    print("Lastest file: " + Latest_file_name)

    # search to see if stock ticker exists in most recent file
    with open('C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/Sorted Records/' + Latest_file_name) as latest_file:
        contents_array = latest_file.readlines()
        if stock_ticker in str(contents_array):
            print("Ticker is in file")
        else:
            print("Ticker can't be found")
            return

        # grab most recent data
        for row in contents_array:
            if row != contents_array[0]:
                items = row.split(',')
                if items[0] == stock_ticker:
                    current_Data = items
    
    # search for oldest file <-- not currently Working
    # expecting 26-8-2021--15-19-18
    # getting 7-9-2021--15-6-0
    for item in list_of_files:
        
        result = item.split("_")
        result[4] = int(result[4])
        result[3] = int(result[3])
        result[2] = int(result[2])
        result[6] = int(result[6])
        result[7] = int(result[7])
        result[-1] = int(result[-1].split('.')[0])

        if result[4] <= min_date[4]:
            if result[3] <= min_date[3]:
                print('smallest month: ' + str(result[3]))
                if result[2] <= min_date[2]:
                    if result[6] <= min_date[6]:
                        if result[7] <= min_date[7]:
                            if result[8] < min_date[8]:
                                min_date = result
    print("min_date: ", end='')
    print(min_date)

    # search for ticker in oldest file

    # calculate key stats to log

    # generate file based on key stats


def main():
    criteria = 'nasdaq_screener'
    source_folder_file_path = 'C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets'
    record_files = moving_files.scan_folder(source_folder=source_folder_file_path, criteria=criteria)

    record_files.sort()
    if(len(record_files) == 0):
        print('No new files')
    else:
        # Some sort of function to sort through the new files
        for i in range(len(record_files)):
            clean_file(record_files[i])
        

if __name__ == '__main__':
    main()
    #generate_candidate_report("AAPL")
