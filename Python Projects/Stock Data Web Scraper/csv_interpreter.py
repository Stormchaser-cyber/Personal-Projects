# csv_interpreter.py file
#
# Created -- Ted Strombeck -- July 2021
# Last Updated -- August 23, 2023
# Version 1.0.9
#

import os
from os import listdir
from os.path import isfile
import csv
import moving_files
import download_csv
import download_stock_statistics
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
    new_file_name = 'nasdaq_screener_%s_%s_%s__%s_%s_%s.csv' % (current_time.year, current_time.month, current_time.day, current_time.hour, current_time.minute, current_time.second) 
    os.rename(file_path + file_name, destination_path + new_file_name)

def find_most_recent_nasdaq_screener_file(source_folder_file_path):
    """
    find_most_recent_nasdaq_screener_file function finds the most recently downloaded and cleaned nasdaq screener file based off of the file's naming convention

    Parameters
    ----------
    String     
        source_folder_file_path: the file path of the folder to search

    Returns
    ----------
    String
        file_name: the name of the most recent file
    """
    max_date = None
    max_file_name = ""

    list_of_files = moving_files.scan_folder(source_folder=source_folder_file_path, criteria='nasdaq_screener')

    for item in list_of_files:
        result = item.split("_")

        if max_date == None:
            max_date = datetime.date(int(result[2]), int(result[3]), int(result[4]))
            max_file_name = item
        else:
            current_date = datetime.date(int(result[2]), int(result[3]), int(result[4]))

            if current_date > max_date:
                max_date = current_date
                max_file_name = item

    result = max_file_name.split("_")
    year = int(result[2])
    month = int(result[3])
    day = int(result[4])
    hour = int(result[6])
    minute = int(result[7])
    second = int(result[-1].split('.')[0])

    Latest_file_name = '%s_%s_%s_%s_%s__%s_%s_%s.csv' % (result[0], result[1],year, month, day, hour, minute, second)
    
    return Latest_file_name

def find_oldest_nasdaq_screener_file(source_folder_file_path):
    """
    find_oldest_nasdaq_screener_file function finds the most recently downloaded and cleaned nasdaq screener file based off of the file's naming convention

    Parameters
    ----------
    String     
        source_folder_file_path: the file path of the folder to search

    Returns
    ----------
    String
        file_name: the name of the oldest file on record
    """
    min_date = None
    min_file_name = ""

    list_of_files = moving_files.scan_folder(source_folder=source_folder_file_path, criteria='nasdaq_screener')

    for item in list_of_files:
        result = item.split("_")

        if min_date == None:
            min_date = datetime.date(int(result[2]), int(result[3]), int(result[4]))
            min_file_name = item
        else:
            current_date = datetime.date(int(result[2]), int(result[3]), int(result[4]))

            if current_date < min_date:
                min_date = current_date
                min_file_name = item

    result = min_file_name.split("_")
    year = int(result[2])
    month = int(result[3])
    day = int(result[4])
    hour = int(result[6])
    minute = int(result[7])
    second = int(result[-1].split('.')[0])

    Oldest_file_name = '%s_%s_%s_%s_%s__%s_%s_%s.csv' % (result[0], result[1],year, month, day, hour, minute, second)
    
    return Oldest_file_name

def get_stock_data_from_nadaq_screener_file(file_to_open_file_path, stock_ticker):
    """
    find_oldest_nasdaq_screener_file function finds the most recently downloaded and cleaned nasdaq screener file based off of the file's naming convention

    Parameters
    ----------
    String     
        file_to_open_file_path: the file path of the file to open
        stock_ticker: the string of the stock ticker to search the file for

    Returns
    ----------
    List
        returning_data: a list of the data related to the specific stock ticker
    """
    returning_data = None

    with open(file_to_open_file_path) as file:
        contents_array = file.readlines()
        if stock_ticker in str(contents_array):
            print("\t##\t\tTicker is in file" + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ##")
        else:
            print("WARNING: Ticker can't be found")
            return

        # grab most recent data
        for row in contents_array:
            if row != contents_array[0]:
                items = row.split(',')
                if items[0] == stock_ticker:
                    returning_data = items
    file.close()

    return returning_data


def generate_candidate_report(stock_ticker):
    """
    generate_candidate_report function creates a text file report for a particular stock where it returns key information about the stock

    Parameters
    ----------
    String     
        stock_ticker: the name of the stock ticker to search for
    """

    
    max_date = None
    max_file_name = ""
    current_date = None
    current_Data = None
    oldest_Data = None
    min_date = None
    min_file_name = ""
    past_Data = None

    print(r""" 
        ############################################################################################################################################################################
        ##   ________                                   __           _________                    .___.__    .___       __           __________                             __    ##
        ##  /  _____/  ____   ____   ________________ _/  |_  ____   \_   ___ \_____    ____    __| _/|__| __| _/____ _/  |_  ____   \______   \ ____ ______   ____________/  |_  ##
        ## /   \  ____/ __ \ /    \_/ __ \_  __ \__  \\   __\/ __ \  /    \  \/\__  \  /    \  / __ | |  |/ __ |\__  \\   __\/ __ \   |       _// __ \\____ \ /  _ \_  __ \   __\ ##
        ## \    \_\  \  ___/|   |  \  ___/|  | \// __ \|  | \  ___/  \     \____/ __ \|   |  \/ /_/ | |  / /_/ | / __ \|  | \  ___/   |    |   \  ___/|  |_> >  <_> )  | \/|  |   ##
        ##  \______  /\___  >___|  /\___  >__|  (____  /__|  \___  >  \______  (____  /___|  /\____ | |__\____ |(____  /__|  \___  >  |____|_  /\___  >   __/ \____/|__|   |__|   ##
        ##         \/     \/     \/     \/           \/          \/          \/     \/     \/      \/         \/     \/          \/          \/     \/|__|                        ##
        ##                                                                                                                                                                v 1.0.9 ##
        ############################################################################################################################################################################
    """)

    print("\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Selected Stock: "+ str(stock_ticker), "##" ))
    print("\t############################################################################################################################################################################")
    
    # find most recent file
    Latest_file_name = find_most_recent_nasdaq_screener_file(source_folder_file_path='C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/Sorted Records')
    
    print("\n\t############################################################################################################################################################################")
    print("\t##\tLastest file: " + Latest_file_name + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ##")

    # search to see if stock ticker exists in most recent file
    current_Data = get_stock_data_from_nadaq_screener_file(file_to_open_file_path='C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/Sorted Records/' + Latest_file_name, stock_ticker=stock_ticker)

    # search for oldest file
    Oldest_file_name = find_oldest_nasdaq_screener_file(source_folder_file_path='C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/Sorted Records')

    print("\t##\tOldest file:  " + Oldest_file_name + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ##")

    # search for ticker in oldest file
    oldest_Data = get_stock_data_from_nadaq_screener_file(file_to_open_file_path='C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets/Sorted Records/' + Oldest_file_name, stock_ticker=stock_ticker)
    
    print("\t############################################################################################################################################################################")
    
    ### Calculate P/E (good is anything between 20-25) <- use google finance to grab
    # p/e = (price per share) / (earnings per share)
    eps, pe = download_stock_statistics.download_pe_and_eps_for_stock_ticker(stock_ticker).split(",")
    
    ### calculate EPS
    # earnings per share = (company's net profit) / (outstanding common shares) <- grab from https://www.nasdaq.com/market-activity/stocks/{desired stock ticker}

    print("\n\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "EPS: "+ str(eps), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "P/E Ratio: "+ str(pe), "##" ))
    print("\t############################################################################################################################################################################")


    ### calculate P/B -- high pb ratio means stocks perceived to be overvalued, lower pb ratio means stock is more undervalued. P/b of 1.0 or 2.0 is pretty good to invest in
    current_pb, min_pb, med_pb, max_pb = download_stock_statistics.download_pb_for_stock_ticker(stock_ticker).split(",")

    print("\n\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Current PB: "+ str(current_pb), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Min PB: "+ str(min_pb), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Med PB: "+ str(med_pb), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Max PB: "+ str(max_pb), "##" ))
    print("\t############################################################################################################################################################################")

    ### calculate Dividend Yield
    current_dividend_yield, min_dividend_yield, med_dividend_yield, max_dividend_yield = download_stock_statistics.download_dividend_yield_for_stock_ticker(stock_ticker).split(",")

    print("\n\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Current Dividend Yield: "+ str(current_dividend_yield), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Min Dividend Yield: "+ str(min_dividend_yield), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Med Dividend Yield: "+ str(med_dividend_yield), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Max Dividend Yield: "+ str(max_dividend_yield), "##" ))
    print("\t############################################################################################################################################################################")

    ### calculate Growth Rates (historical and projected earnings)
    current_yoy_ebitda_growth_rate, ebitda_last_updated = download_stock_statistics.download_yoy_ebitda_growth_rate_for_stock_ticker(stock_ticker).split(",")

    print("\n\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Current Ebitda 5 year Growth Rate: "+ str(current_yoy_ebitda_growth_rate), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Last updated: "+ str(ebitda_last_updated), "##" ))
    print("\t############################################################################################################################################################################")

    ### calculate debt-to-equity ratio
    current_d2e, min_d2e, med_d2e, max_d2e = download_stock_statistics.download_debt_to_equity_for_stock_ticker(stock_ticker).split(",")

    print("\n\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Current Debt-to-Equity: "+ str(current_d2e), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Min Debt-to-Equity: "+ str(min_d2e), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Med Debt-to-Equity: "+ str(med_d2e), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Max Debt-to-Equity: "+ str(max_d2e), "##" ))
    print("\t############################################################################################################################################################################")
    
    ### calculate ROE
    current_roe, min_roe, med_roe, max_roe = download_stock_statistics.download_roe_percentage_for_stock_ticker(stock_ticker).split(",")

    print("\n\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Current ROE: "+ str(current_roe), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Min ROE: "+ str(min_roe), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Med ROE: "+ str(med_roe), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Max ROE: "+ str(max_roe), "##" ))
    print("\t############################################################################################################################################################################")

    ### calculate operating margin
    current_operating_margin_percentage, min_operating_margin_percentage, med_operating_margin_percentage, max_operating_margin_percentage = download_stock_statistics.download_operating_margin_percentage_for_stock_ticker(stock_ticker).split(",")

    print("\n\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Current Operating Margin: "+ str(current_operating_margin_percentage)+"%", "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Min Operating Margin: "+ str(min_operating_margin_percentage)+"%", "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Med Operating Margin: "+ str(med_operating_margin_percentage)+"%", "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Max Operating Margin: "+ str(max_operating_margin_percentage)+"%", "##" ))
    print("\t############################################################################################################################################################################")
    
    ### calculate FCF
    current_fcf_margin_percentage, fcf_margin_percentage_last_updated = download_stock_statistics.download_fcf_margin_percentage_for_stock_ticker(stock_ticker).split(",")

    print("\n\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Current FCF Margin: "+ str(current_fcf_margin_percentage), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Last updated: "+ str(fcf_margin_percentage_last_updated), "##" ))
    print("\t############################################################################################################################################################################")
    
    ### Calculate beta for risk factoring
    current_beta, beta_last_updated = download_stock_statistics.download_beta_for_stock_ticker(stock_ticker).split(",")

    if (beta_last_updated == "(As of Today)"):
        beta_last_updated = datetime.datetime.now()

    print("\n\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Current Beta: "+ str(current_beta), "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Last updated: "+ str(beta_last_updated), "##" ))
    print("\t############################################################################################################################################################################")

    ### generate file based on key stats from above


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
