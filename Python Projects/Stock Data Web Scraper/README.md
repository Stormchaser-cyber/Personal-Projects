# This is the README.md for the Stock Data Web Scraper Project#
* Version 1.0.1
* Last updated 8/24/2021
----

### What is this Code for? ###
----
* The python3 scripts contained in this directory runs the Stock Data Web scraper bot project that has the computer
grab a stock data spreadsheet from online, fill in any missing information, write a small report to a text file,
and print a little report to the screen as well.

### How to use ###
----
* I have updated the file-pathing methods to be more widely used. It now supports windows and linux.
* In order to run the application you would need to navigate to the directory (in the command line) that 
the stock_data_web_scraper_bot.py file is located in and run the following command via the command line
` python stock_data_web_scraper_bot.py `
* If done correctly, you'll see the stock bot graphic appear and it automatically open up a chrome window and start working

### What is here? ###
----
* Daily Reports Folder: Folder that contains all the daily reports for downloaded csv files
* Stock Spreadsheets Folder: Folder that contains all of the stock data spreadsheets
* chromedriver.exe: the main executable that the python3 scripts uses to download the stock spreadsheets
* README.md: this file!
* Stock Data TODO.md: TODO file containing next steps to do
* csv_interpretter.py: Parses and fills out missing information in the downloaded stock data files
* download_csv.py: Downloads a stock data spreadsheet
* download_stock_statisctics.py: Downloads certain stats for stocks to use in the candidate reporting
* file_pathing_helper.py: helps grab current directories needed to run this bot
* moving_files.py: Moves files from one directory to another
* run_tests.py: primary test file
* stock_data_web_scraper_bot.py: Main bot logic that runs other python3 scripts in this directory
* stock_data_web_scraper_bot_laptop_version.py: currently nonfunctional - same script as stock_data_web_scraper_bot.py

### Who Do I talk to? ###
----
* Ted Strombeck - <ted.strombeck1@gmail.com>