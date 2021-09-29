# This is the README.md for the Stock Data Web Scraper Project #
----

### What is this Code for? ###
----
* The python3 scripts contained in this directory runs the Stock Data Web scraper bot project that has the computer
grab a stock data spreadsheet from online, fill in any missing information, write a small report to a text file,
and print a little report to the screen as well.

### How to use ###
----
* This project uses my own filepathing methods and since it isn't refactored to handle inputted file pathing,
an individual will have to go in and manually change the filepaths for each python3 file
* Once that is complete, you can run the python3 script either from the command line or from an editor and the 
rest will be taken care of by the program.

### What is here? ###
----
* Daily Reports Folder: Folder that contains all the daily reports for downloaded csv files
* Stock Spreadsheets Folder: Folder that contains all of the stock data spreadsheets
* chromedriver.exe: the main executable that the python3 scripts uses to download the stock spreadsheets
* README.md: this file!
* Stock Data TODO.md: TODO file containing next steps to do
* csv_interpretter.py: Parses and fills out missing information in the downloaded stock data files
* download_csv.py: Downloads a stock data spreadsheet
* moving_files.py: Moves files from one directory to another
* stock_data_web_scraper_bot.py: Main bot logic that runs other python3 scripts in this directory
* stock_data_web_scraper_bot_laptop_version.py: currently nonfunctional - same script as stock_data_web_scraper_bot.py

### Who Do I talk to? ###
----
* Ted Strombeck - <ted.strombeck1@gmail.com>