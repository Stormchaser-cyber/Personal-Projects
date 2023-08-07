# stock_data_web_scraper_bot
#
# Created -- Ted Strombeck -- August 2021
# Last Updated -- August 7, 2023
# Version 1.0.2
#

import csv_interpreter as interpreter
import download_csv as download
import moving_files
import datetime
import time

def save_day_statistics(program_start, program_end, num_of_files_downloaded, files_downloaded):
    """
    save_day_statistics function creates a text file that contains the number of files downloaded, the time that it was running, and places that file in the daily reports directory

    Parameters
    ----------
    datetime   
        program_start: the datetime object when the program started running
        program_end: the datetime object when the program stopped running
    int 
        num_of_files_downloaded: the number of files that were Downloaded
    list
        files_downloaded: the list containing the file names of all of the files that were downloaded successfully between the program start and end times
    """

    date = str(program_end.day) + '_' + str(program_end.month) + '_' + str(program_end.year)
    file_name = 'daily_report_' + date + '.txt'
    
    daily_report_items = moving_files.custom_scan_folder('C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Daily reports',
                                                  criteria = 'daily_report', number_of_characters = 12)

    if file_name in daily_report_items:
        file = open('C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Daily reports/' + file_name, "a")
        file.write('\n\nNew Files that were downloaded and cleaned:')

        for item in files_downloaded:
            file.write('\n - ' + item)

        file.close()
    else:
        file = open(file_name, 'w')
        file.write('Daily report for ' + date)
        file.write('\nStarted at: ' + str(program_start))
        file.write('\nFinished at: ' + str(program_end))
        file.write('\nDownloaded: ' + str(num_of_files_downloaded) + ' files')
        file.write('\n')
        file.write('\nFiles that were downloaded and cleaned:')

        for item in files_downloaded:
            file.write('\n - ' + item)
    
        file.close()

        moving_files.move_file(file_name, 'C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper',
                               'C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Daily reports')

def run_one_test():
    """
    run_one_test: runs one iteration where it attempts to download a stock data csv file for troubleshooting purposes
    """
    print(r"""
        ############################################################################################################################################################################
        ##                                               _________ __                 __     __________        __    ___________              __                                  ##
        ##                                              /   _____//  |_  ____   ____ |  | __ \______   \ _____/  |_  \__    ___/___   _______/  |_                                ##
        ##                                              \_____  \\   __\/  _ \_/ ___\|  |/ /  |    |  _//  _ \   __\   |    |_/ __ \ /  ___/\   __\                               ##
        ##                                              /        \|  | (  <_> )  \___|    <   |    |   (  <_> )  |     |    |\  ___/ \___ \  |  |                                 ##
        ##                                             /_______  /|__|  \____/ \___  >__|_ \  |______  /\____/|__|     |____| \___  >____  > |__|                                 ##
        ##                                                     \/                  \/     \/         \/                           \/     \/                                       ##
        ##                                                                                                                                                                v 1.0.3 ##
        ############################################################################################################################################################################
    """)

    try:
        
        program_start = datetime.datetime.now()
        current_time = datetime.datetime.now()
        program_end = None
        number_of_files = 0

        print("\t############################################################################################################################################################################")
        print('\t{:2s}\t{:162s}{:2s}'.format("##", "Program started: "+ str(program_start), "##" ))
        print("\t############################################################################################################################################################################")

        download.main()
        number_of_files += 1
        current_time = datetime.datetime.now()

        moving_files.main()
        files_moved = moving_files.scan_folder('C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets',
                                 criteria = 'nasdaq_screener')
        
        interpreter.main()
    
        program_end = datetime.datetime.now()

        print("\t############################################################################################################################################################################")
        print('\t{:2s}\t{:162s}{:2s}'.format("##", "Downloaded file at: "+ str(download.last_time_downloaded), "##" ))
        print("\t############################################################################################################################################################################")


        print("\t############################################################################################################################################################################")
        print('\t{:2s}\t{:162s}{:2s}'.format("##", "Info", "##" ))
        print('\t{:2s}\t{:162s}{:2s}'.format("##", "Downloaded: " + str(number_of_files) + " files", "##" ))
        print('\t{:2s}\t{:162s}{:2s}'.format("##", "Number of Files moved: " + str(len(files_moved)), "##" ))
        print("\t############################################################################################################################################################################")

        print("\t############################################################################################################################################################################")
        print('\t{:2s}\t{:162s}{:2s}'.format("##", "Program ended: "+ str(program_end), "##" ))
        print("\t############################################################################################################################################################################")

        save_day_statistics(program_start, program_end, number_of_files, files_moved)
    except Exception as ex:
        print("An unforseen exception occurred")
        print(ex)

def run_main_bot():
    """
    run_main_bot function runs the main logic for the stock gathering bot
    """
    ## ascii art done at http://patorjk.com/software/taag/#p=display&f=Graffiti&t=Stock%20Bot
    print(r"""
        ############################################################################################################################################################################
        ##                                                      _________ __                 __     __________        __                                                          ##
        ##                                                     /   _____//  |_  ____   ____ |  | __ \______   \ _____/  |_                                                        ##
        ##                                                     \_____  \\   __\/  _ \_/ ___\|  |/ /  |    |  _//  _ \   __\                                                       ##
        ##                                                     /        \|  | (  <_> )  \___|    <   |    |   (  <_> )  |                                                         ##
        ##                                                    /_______  /|__|  \____/ \___  >__|_ \  |______  /\____/|__|                                                         ##
        ##                                                            \/                  \/     \/         \/                                                                    ##
        ##                                                                                                                                                                v 1.0.2 ##
        ############################################################################################################################################################################
    """)

    program_start = datetime.datetime.now()
    current_time = datetime.datetime.now()
    program_end = None
    time_to_wait = int(60 * 30) # waiting 30 minutes before grabbing more data
    number_of_files = 0

    print("\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Program started: "+ str(program_start), "##" ))
    print("\t############################################################################################################################################################################")

    while current_time.hour < 15: # running the bot until 3pm
        download.main()
        number_of_files += 1
        print("\t############################################################################################################################################################################")
        print('\t{:2s}\t{:162s}{:2s}'.format("##", "Downloaded file at: "+ str(download.last_time_downloaded), "##" ))
        print("\t############################################################################################################################################################################")
        time.sleep(time_to_wait)
        current_time = datetime.datetime.now()

    moving_files.main()
    files_moved = moving_files.scan_folder('C:/Users/tedst/source/repos/Personal-Projects/Python Projects/Stock Data Web Scraper/Stock Spreadsheets',
                             criteria = 'nasdaq_screener')
        
    interpreter.main()
    
    program_end = datetime.datetime.now()

    print("\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Info", "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Downloaded: " + str(number_of_files) + " files", "##" ))
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Number of Files moved: " + str(len(files_moved)), "##" ))
    print("\t############################################################################################################################################################################")

    print("\t############################################################################################################################################################################")
    print('\t{:2s}\t{:162s}{:2s}'.format("##", "Program ended: "+ str(program_end), "##" ))
    print("\t############################################################################################################################################################################")

    save_day_statistics(program_start, program_end, number_of_files, files_moved)

def main():
    #run_one_test()
    run_main_bot()
    
    

if __name__ == '__main__':
    main()
