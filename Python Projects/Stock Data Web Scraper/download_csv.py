# download_csv.py
#
# Created -- Ted Strombeck -- July 2021
# Version 1.0
# 

# URL: "https://www.nasdaq.com/market-activity/stocks/screener"
# ID: "nasdaq-screener__form-button--download ns-download-1")

from selenium import webdriver
from selenium.webdriver.common.by import By
import time

def download_csv_from_url_by_xpath(url, xpath):
    """
    download_csv_from_url_by_xpath function looks up a specific url, searches for the element based off of the full xpath
    and if possible, clicks on the element to download the data as a csv file.

    Parameters
    ----------
    String
        url: the string of the url of the website
        xpath: the xpath of the element in the html code
    """

    ### Works! Just takes a little bit of awhile, not too sure why ###
    options = webdriver.ChromeOptions()
    
    #options.add_argument('--headless') # Not showing the webpage pop up
    
    options.add_argument('--log-level=3') # Only showing logs if they are fatal
    
    browser = webdriver.Chrome(options=options) # Creating a chrome browser object

    browser.get(url) # looking up the URL of the website

    time.sleep(10) # sleeping for 10 seconds once we get on the webpage so we have time for everything to load

    try:
        browser.find_element_by_xpath(xpath).click() # navigating through the xpath and clicking on the element
    except:
        print('Didn\'t work')

    time.sleep(5) # sleeping for 5 seconds to ensure that the download is able to go through

    browser.quit() # closing all terminals and windows

def main():
    """
    Main function to control and run the functions as you initially would in main
    """

    download_csv_from_url_by_xpath(url="https://www.nasdaq.com/market-activity/stocks/screener",
                                   xpath='/html/body/div[2]/div/main/div[2]/article/div[3]/div[1]/div/div/div[3]/div[2]/div[2]/div/button')
    
    


if __name__ == "__main__":
    main()
