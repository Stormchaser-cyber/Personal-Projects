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
* Interpretting the downloaded csv stock file ------------------------------------------>
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
	* Storing information?
		* General List (for now) -------> done
		* Each "section" is 11 items
		* Only add it into a contender section if it is on an uptick (for right now that means on a positive % change)
		* Ends by Saving it into a csv file
* Having a csv file that stores the filenames of files already sorted through ---------->
* Main bot program --------------------------------------------------------------------->      
* Trading logic ------------------------------------------------------------------------>      
* Try and catch blocks for error management -------------------------------------------->      
* Full implementation ------------------------------------------------------------------>      
------------------------------------------------------------------------------------------------