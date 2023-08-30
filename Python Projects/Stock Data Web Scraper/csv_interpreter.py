# csv_interpreter.py file
#
# Created -- Ted Strombeck -- July 2021
# Last Updated -- August 30, 2023
# Version 1.0.12
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
import scores

filepath = os.getcwd()

# TODO ----------------------------------------------------------------------
#   - process csv files that haven't been sorted yet --------->     Done    |
#   - move the files once they have been processed ----------->     Done    |
#   - search files for oldest file on record ----------------->     Done    |
#   - create functionality to generate reports for ----------->     Done    |
#     individual stocks based on newest and oldest                          |
#     data that we have. (Searchable by ticker)                             |
#   - revise clean_file logic -------------------------------->     Done    |
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
    with open(filepath+'/Stock Spreadsheets/' + filename,'r') as input_file:
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

    with open(filepath+'/Stock Spreadsheets/' + filename,'w', newline='') as write_file:
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
    local_file_path = filepath+'/Stock Spreadsheets/'
    destination_path = filepath+'/Stock Spreadsheets/Sorted Records/'
    current_time = datetime.datetime.now()
    new_file_name = 'nasdaq_screener_%s_%s_%s__%s_%s_%s.csv' % (current_time.year, current_time.month, current_time.day, current_time.hour, current_time.minute, current_time.second) 
    os.rename(local_file_path + file_name, destination_path + new_file_name)

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
        ##                                                                                                                                                               v 1.0.10 ##
        ############################################################################################################################################################################
    """)

    print("\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Selected Stock: "+ str(stock_ticker), "##" ))
    print("\t############################################################################################################################################################################")
    
    # find most recent file
    Latest_file_name = find_most_recent_nasdaq_screener_file(source_folder_file_path=filepath+'/Stock Spreadsheets/Sorted Records')
    
    print("\n\t############################################################################################################################################################################")
    print("\t##\tLastest file: " + Latest_file_name + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ##")

    # search to see if stock ticker exists in most recent file
    current_Data = get_stock_data_from_nadaq_screener_file(file_to_open_file_path=filepath+'/Stock Spreadsheets/Sorted Records/' + Latest_file_name, stock_ticker=stock_ticker)

    # search for oldest file
    Oldest_file_name = find_oldest_nasdaq_screener_file(source_folder_file_path=filepath+'/Stock Spreadsheets/Sorted Records')

    print("\t##\tOldest file:  " + Oldest_file_name + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ##")

    # search for ticker in oldest file
    oldest_Data = get_stock_data_from_nadaq_screener_file(file_to_open_file_path=filepath+'/Stock Spreadsheets/Sorted Records/' + Oldest_file_name, stock_ticker=stock_ticker)
    
    print("\t############################################################################################################################################################################")
    
    ### Calculate P/E (good is anything between 20-25) <- use google finance to grab
    # p/e = (price per share) / (earnings per share)
    eps, pe = download_stock_statistics.download_pe_and_eps_for_stock_ticker(stock_ticker).split(",")
    
    ### calculate EPS (between 1 - 99)
    # earnings per share = (company's net profit) / (outstanding common shares) <- grab from https://www.nasdaq.com/market-activity/stocks/{desired stock ticker}
    printSubMessage(["EPS: "+ str(eps),"P/E Ratio: "+ str(pe)])

    ### calculate P/B -- high pb ratio means stocks perceived to be overvalued, lower pb ratio means stock is more undervalued. 
    # P/b of 1.0 or 2.0 is pretty good to invest in
    current_pb, min_pb, med_pb, max_pb = download_stock_statistics.download_pb_for_stock_ticker(stock_ticker).split(",")
    printSubMessage(["Current PB: "+ str(current_pb), "Min PB: "+ str(min_pb),"Med PB: "+ str(med_pb),"Max PB: "+ str(max_pb)])

    ### calculate Dividend Yield -> between 2% and 5%
    current_dividend_yield, min_dividend_yield, med_dividend_yield, max_dividend_yield = download_stock_statistics.download_dividend_yield_for_stock_ticker(stock_ticker).split(",")
    printSubMessage(["Current Dividend Yield: "+ str(current_dividend_yield), "Min Dividend Yield: "+ str(min_dividend_yield),"Med Dividend Yield: "+ str(med_dividend_yield),"Max Dividend Yield: "+ str(max_dividend_yield)])

    ### calculate Growth Rates (historical and projected earnings) 60% or higher
    current_yoy_ebitda_growth_rate, ebitda_last_updated = download_stock_statistics.download_yoy_ebitda_growth_rate_for_stock_ticker(stock_ticker).split(",")
    printSubMessage(["Current Ebitda 5 year Growth Rate: "+ str(current_yoy_ebitda_growth_rate),"Last updated: "+ str(ebitda_last_updated)])

    ### calculate debt-to-equity ratio -> good is less than 1
    current_d2e, min_d2e, med_d2e, max_d2e = download_stock_statistics.download_debt_to_equity_for_stock_ticker(stock_ticker).split(",")
    printSubMessage(["Current Debt-to-Equity: "+ str(current_d2e), "Min Debt-to-Equity: "+ str(min_d2e),"Med Debt-to-Equity: "+ str(med_d2e),"Max Debt-to-Equity: "+ str(max_d2e)])
    
    ### calculate ROE 15% - 20%
    current_roe, min_roe, med_roe, max_roe = download_stock_statistics.download_roe_percentage_for_stock_ticker(stock_ticker).split(",")
    printSubMessage(["Current ROE: "+ str(current_roe), "Min ROE: "+ str(min_roe),"Med ROE: "+ str(med_roe),"Max ROE: "+ str(max_roe)])

    ### calculate operating margin 15% or higher
    current_operating_margin_percentage, min_operating_margin_percentage, med_operating_margin_percentage, max_operating_margin_percentage = download_stock_statistics.download_operating_margin_percentage_for_stock_ticker(stock_ticker).split(",")
    printSubMessage(["Current Operating Margin: "+ str(current_operating_margin_percentage)+"%", "Min Operating Margin: "+ str(min_operating_margin_percentage)+"%","Med Operating Margin: "+ str(med_operating_margin_percentage)+"%","Max Operating Margin: "+ str(max_operating_margin_percentage)+"%"])
    
    ### calculate FCF between 20% - 25%
    current_fcf_margin_percentage, fcf_margin_percentage_last_updated = download_stock_statistics.download_fcf_margin_percentage_for_stock_ticker(stock_ticker).split(",")
    printSubMessage(["Current FCF Margin: "+ str(current_fcf_margin_percentage), "Last updated: "+ str(fcf_margin_percentage_last_updated)])
    
    ### Calculate beta for risk factoring -> 1 is neutral or average level of risk
    current_beta, beta_last_updated = download_stock_statistics.download_beta_for_stock_ticker(stock_ticker).split(",")

    if (beta_last_updated == "(As of Today)"):
        beta_last_updated = datetime.datetime.now()
    
    printSubMessage(["Current Beta: " + str(current_beta), "Last updated: " + str(beta_last_updated)])

    ### stock's final score each category is calculated to be in a % range between 1 and 100 
    # with 100 being the highest possible score
    pe_score = scores.calculate_pe_score(float(pe))
    eps_score = scores.calculate_eps_score(float(eps[1:]))
    pb_score = scores.calculate_pb_score(float(current_pb), float(min_pb), float(med_pb), float(max_pb))
    dividend_yield_score = scores.calculate_dividend_yield_score(float(current_dividend_yield), float(min_dividend_yield), float(med_dividend_yield), float(max_dividend_yield))
    ebitda_growth_rate_score = scores.calculate_ebitda_growth_rate_score(float(current_yoy_ebitda_growth_rate[:-1]))
    d2e_score = scores.calculate_debt_to_equity_score(float(current_d2e), float(min_d2e), float(med_d2e), float(max_d2e))
    roe_score = scores.calculate_roe_score(float(current_roe), float(min_roe), float(med_roe), float(max_roe))
    operating_margin_score = scores.calculate_operating_margin_score(float(current_operating_margin_percentage), float(min_operating_margin_percentage), float(med_operating_margin_percentage), float(max_operating_margin_percentage))
    fcf_score = scores.calculate_fcf_score(float(current_fcf_margin_percentage[:-1]))

    score_collection = [pe_score,eps_score,pb_score,dividend_yield_score,ebitda_growth_rate_score,d2e_score,roe_score,operating_margin_score,fcf_score]

    ## stock's risk level recommendation 
    

def printSubMessage(messages):
    print("\n\t############################################################################################################################################################################")
    for message in messages:
        print('\t{:2s}\t{:162s}{:2s}'.format("##", message, "##" ))
    print("\t############################################################################################################################################################################")

def main():
    criteria = 'nasdaq_screener'
    source_folder_file_path = filepath+'/Stock Spreadsheets'
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
