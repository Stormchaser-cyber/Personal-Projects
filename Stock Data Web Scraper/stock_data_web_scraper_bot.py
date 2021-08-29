# stock_data_web_scraper_bot
#
# Created -- Ted Strombeck -- August 2021
#

import csv_interpreter as interpreter
import download_csv as download
import moving_files

import datetime
import time

def save_day_statistics(program_start, program_end, num_of_files_downloaded, files_downloaded):
    date = str(program_end.day) + '_' + str(program_end.month) + '_' + str(program_end.year)
    file_name = 'daily_report_' + date + '.txt'
    
    daily_report_items = moving_files.custom_scan_folder('C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Daily reports',
                                                  criteria = 'daily_report', number_of_characters = 12)

    if file_name in daily_report_items:
        file = open('C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Daily reports/' + file_name, "a")
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

        moving_files.move_file(file_name, 'C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper',
                               'C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Daily reports')
def run_one_test():
    program_start = datetime.datetime.now()
    current_time = datetime.datetime.now()
    program_end = None
    number_of_files = 0

    download.main()
    number_of_files += 1
    current_time = datetime.datetime.now()

    moving_files.main()
    files_moved = moving_files.scan_folder('C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Stock Spreadsheets',
                             criteria = 'nasdaq_screener')
        
    interpreter.main()
    
    program_end = datetime.datetime.now()
    

    print('Started at: ', program_start)
    print('Ended at: ', program_end)
    print('Downloaded: ', number_of_files, ' files')
    print('Number of Files moved: ', len(files_moved))

    save_day_statistics(program_start, program_end, number_of_files, files_moved)

def run_main_bot():
    program_start = datetime.datetime.now()
    current_time = datetime.datetime.now()
    program_end = None
    time_to_wait = int(60 * 25) # waiting 25 minutes before grabbing more data
    number_of_files = 0

    while current_time.hour < 17: # running the bot until 5pm
        download.main()
        number_of_files += 1
        time.sleep(time_to_wait)
        current_time = datetime.datetime.now()

    moving_files.main()
    files_moved = moving_files.scan_folder('C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Stock Spreadsheets',
                             criteria = 'nasdaq_screener')
        
    interpreter.main()
    
    program_end = datetime.datetime.now()
    

    print('Started at: ', program_start)
    print('Ended at: ', program_end)
    print('Downloaded: ', number_of_files, ' files')
    print('Number of Files moved: ', len(files_moved))

    save_day_statistics(program_start, program_end, number_of_files, files_moved)

def main():
    run_one_test()
    
    

if __name__ == '__main__':
    main()
