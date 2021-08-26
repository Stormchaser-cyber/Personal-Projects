# TODO - Stock Data file #

Big Ideas
------------------------------------------------------------------------------------------------
* The Actual Program
* Web scraper - done
* Scraping Stock Information in Real Time
* Gathering any previous data as well?

Things to Implement
------------------------------------------------------------------------------------------------
* Which scraper module to use ------------------------------------------------------------> done
* Gathering data and storing data into lists or some data structure?
* Information Gathered? - (Date, Time, Stock Name, Price, Type -- Stock/Bond/Crypto)

TODO List
------------------------------------------------------------------------------------------------
* Downloading the csv file -------------------------------------------------------------> done 
* Changing the file name of webscraper to excel spreadsheet scanner --------------------> done 
* Having a file that moves documents from downloads to current directory ---------------> done 
* Implementing file that moves documents from downloads to current directory -----------> done 
* Having moving file scan the downloads folder and having it select all records that match our data ---------> done
* Creating a move function that takes in a list of files to move -----------------------> done
* Adding documentation for functions ---------------------------------------------------> done
* Get web scraper working again --------------------------------------------------------> done
* Get web scraper to supress the warnings ----------------------------------------------> done
* Converting and cleaning information from the downloaded csv stock file ---------------> done
	* Breaking up the file into different categories
		* Symbols
		* Name
		* Last Sale
		* Net Change
		* % Change
		* Market Cap
		* Country
		* IPO Year
		* Volume
		* Sector
		* Industry
	* Saving it into a csv file with the format ('nasdaq_screener_DD_MM_YYYY__HH_MM_SS') -----> done
* Running analysis on all files for best stocks? ------------------------------------------------->
	* 2 approaches
		* Iterate through each file and keep the top 250 stocks with the best positive % change
			* compare and find the top stocks and sort them based off of best % change over time
		* Iterate through each file and keep track of all the percentages increased over a certain period of time
			* recommend best stocks in terms of % change and when would be the best time to buy
	* Save the recommendations into a text file
* Having a csv file that stores the filenames of files already sorted through ---------->
* Main bot program --------------------------------------------------------------------->      
* Trading logic ------------------------------------------------------------------------>      
* Try and catch blocks for error management -------------------------------------------->      
* Full implementation ------------------------------------------------------------------>      
------------------------------------------------------------------------------------------------