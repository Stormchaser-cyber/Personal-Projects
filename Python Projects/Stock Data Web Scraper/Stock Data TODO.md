# TODO - Stock Data file #
* Version 1.1.1
* Last updated 8/24/2021 - Ted Strombeck

### Big Ideas ###
------------------------------------------------------------------------------------------------
- [x] Web scraper
- [x] Scraping Stock Information in Real Time
- [x] Data Interpreter
- [ ] Stock Recommendation
- [ ] The Trading logic
- [ ] GUI implemetation/app for bot access/control

Features
------------------------------------------------------------------------------------------------
- [x] Web scraper module
- [x] Gathering data and storing data via csv's
- [x] Information Gathered - (Date, Time, Stock Name, Price, Type -- Stock/Bond/Crypto)
- [ ] Csv Data Interpretter
- [ ] Finding Contenders
- [ ] Combining Data from multipe readings into a single file
- [ ] Contenders to Csv
- [ ] Debugging/Error Identification Process
- [ ] Application Beautification
- [ ] App Wide Unit Testing

TODO List
------------------------------------------------------------------------------------------------
- [x] Downloading the csv file
- [x] Having and implementing file that moves documents from downloads to current directory
- [x] Adding documentation for functions
- [x] Get web scraper to work
- [x] Converting and cleaning up downloaded csv stock files
- [x] Saving it into a csv with the format "nasdaq_screener_DD_MM_YYYY__HH_MM_SS"
- [x] Running anaylsis on stock files for best stocks?
- [ ] Creating a "score" for each stock to determine best stocks
- [ ] Trading logic
- [x] Trying and catching errors so it doesn't crash if internet is slow, etc.
- [ ] Code cleanup/refactoring for clean_file.py
- [ ] Code cleanup/refactoring for csv_interpreter.py
- [ ] Generate reports functionality
- [ ] Read in all csv files related to a given day
- [ ] Write multiple csv files into a singular csv file
- [ ] Functionality for archiving all files that were successfully combined into a new csv file
- [ ] Create daily chart functionality
- [ ] Create initial contenders file
- [ ] Create csv based on Contender (export contender data to csv file)