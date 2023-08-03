# download_csv.py
#
# Created -- Ted Strombeck -- July 2021 
# Last Updated -- July 31, 2023
# Version 1.0.1
# 

# URL: "https://www.nasdaq.com/market-activity/stocks/screener"
# ID: "nasdaq-screener__form-button--download ns-download-1"

from selenium import webdriver
from selenium.webdriver.common.by import By
import selenium.common.exceptions as selenium_exceptions

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

    if (len(xpath) == 0) or (len(url) == 0):
        raise selenium_exceptions.InvalidArgumentException

    ### Works! Just takes a little bit of awhile, not too sure why ###
    options = webdriver.ChromeOptions()
    
    #options.add_argument('--headless') # Not showing the webpage pop up
    
    options.add_argument('--log-level=3') # Only showing logs if they are fatal
    
    browser = webdriver.Chrome(options=options) # Creating a chrome browser object

    browser.get(url) # looking up the URL of the website

    time.sleep(10) # sleeping for 10 seconds once we get on the webpage so we have time for everything to load

    try:
        browser.find_element("xpath", xpath).click() # navigating through the xpath and clicking on the element
    except AttributeError as ex:
        print("Attribute Error: ", end="")
        print(ex)
        browser.quit()
    except selenium_exceptions.NoSuchElementException as ex:
        print("Element cannot be found: ", end="")
        print(ex)
        browser.quit()
    except selenium_exceptions.TimeoutException as ex:
        print("Program took too long: ", end="")
        print(ex)
        browser.quit()
    except selenium_exceptions.NoSuchAttributeException as ex:
        print("Website does not contain attribute of element: ", end="")
        print(ex)
        browser.quit()
    except selenium_exceptions.ElementNotInteractableException as ex:
        print("Element not currently interactable : ", end="")
        print(ex)
        browser.quit()
    except selenium_exceptions.InvalidSelectorException as ex:
        print("Invalid selector, most likely invalid xpath")
        print(ex)
        browser.quit()
    except Exception as ex:
        print('Unsupported Exception caught: ',end='')
        print(type(ex))
        browser.quit()

    time.sleep(5) # sleeping for 5 seconds to ensure that the download is able to go through

    browser.quit() # closing all terminals and windows

def main():
    """
    Main function to control and run the functions as you initially would in main
    """

    download_csv_from_url_by_xpath(url="https://www.nasdaq.com/market-activity/stocks/screener",
                                   xpath='/html/body/div[4]/div/main/div[2]/article/div[3]/div[1]/div/div/div[3]/div[2]/div[2]/div/button')

    


if __name__ == "__main__":
    main()