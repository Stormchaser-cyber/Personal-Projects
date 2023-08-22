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
from selenium.webdriver.common.action_chains import ActionChains
import selenium.common.exceptions as selenium_exceptions
from webdriver_manager.chrome import ChromeDriverManager
import time
import datetime

last_time_downloaded = None

def download_pe_and_eps_for_stock_ticker(stock_ticker):
    """
    download_pe_and_eps_for_stock_ticker looks up that particular stock based on it's ticker and returns the related p/e and the eps for the stock

    Parameters
    ----------
    String:
        stock_ticker: the ticker of the desired stock

    Returns
    ---------
    String:
        A formatted string separated by a comma in order of (Eps, P/E)
    """

    eps = 0.00
    eps_path = "/html/body/div[4]/div/main/div[2]/div[6]/div/div[1]/div/div[2]/table/tbody[2]/tr[4]/td[2]"
    p_e_ratio = 0.00
    p_e_ratio_path = "/html/body/div[4]/div/main/div[2]/div[6]/div/div[1]/div/div[2]/table/tbody[2]/tr[2]/td[2]"
    url = "https://www.google.com/"

    if (len(stock_ticker) == 0):
        raise selenium_exceptions.InvalidArgumentException
    else:
        url = "https://www.nasdaq.com/market-activity/stocks/{:s}".format(stock_ticker)

     ### Works! Just takes a little bit of awhile, not too sure why ###
    options = webdriver.ChromeOptions()

    options.add_experimental_option('excludeSwitches', ['enable-logging'])
    
    #options.add_argument('--headless') # Not showing the webpage pop up
    
    options.add_argument('--log-level=3') # Only showing logs if they are fatal
    
    browser = webdriver.Chrome(options=options) # Creating a chrome browser object

    browser.get(url) # looking up the URL of the website

    ActionChains(browser).scroll_by_amount(0, 1000).perform()

    time.sleep(10) # sleeping for 10 seconds once we get on the webpage so we have time for everything to load

    try:
        p_e_ratio = browser.find_element(By.XPATH, p_e_ratio_path).get_attribute("innerHTML")
        eps = browser.find_element(By.XPATH, eps_path).get_attribute("innerHTML")
    except Exception as ex:
        print('Unsupported Exception caught: ',end='')
        print(type(ex))
        print(ex)
        browser.quit()
    finally:
        browser.quit() # closing all terminals and windows

        last_time_downloaded = datetime.datetime.now()

    #return eps and p/e ratio
    return "{:s},{:s}".format(eps, p_e_ratio)

def download_pb_for_stock_ticker(stock_ticker):
    """
    download_pb_for_stock_ticker downloads the related pb for a selected stock
    """
    results = []
    current_value = 0.00
    min_value = 0.00
    med_value = 0.00
    max_value = 0.00
    result_path = '/html/body/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div/div[1]/strong/text()[1]'
    url = "https://www.google.com/"

    if (len(stock_ticker) == 0):
        raise selenium_exceptions.InvalidArgumentException
    else:
        url = "https://www.gurufocus.com/term/pb/A/PB-Ratio/Agilent%20Technologies"

    ### Works! Just takes a little bit of awhile, not too sure why ###
    options = webdriver.ChromeOptions()

    options.add_experimental_option('excludeSwitches', ['enable-logging'])
    
    #options.add_argument('--headless') # Not showing the webpage pop up
    
    options.add_argument('--log-level=3') # Only showing logs if they are fatal
    
    browser = webdriver.Chrome(options=options) # Creating a chrome browser object

    browser.get(url) # looking up the URL of the website

    #ActionChains(browser).scroll_by_amount(0, 1000).perform()
    browser.maximize_window()

    time.sleep(10) # sleeping for 10 seconds once we get on the webpage so we have time for everything to load

    try:
        browser.find_element(By.ID, "cboxClose").click()
        time.sleep(2)
        search_ticker = browser.find_element(By.ID, "symbol")
        search_ticker.send_keys(stock_ticker)
        time.sleep(2)
        browser.find_element(By.ID, "def_page_ticker_switch").click()
        time.sleep(5)
        results = browser.find_element(By.ID, "pb_tools").get_attribute("outerHTML").split(">")

        for item in results:
            if "Min" in item:
                values = item
            elif "Current" in item:
                current = item
            # can expand to percentage if desired/needed
        

        values=values.replace("&nbsp;&nbsp;", "")
        values=values.replace("\n\n", "")
        values=values.split(" ")

        min_value = values[1]
        med_value = values[3]
        max_value = values[5]

        current=current.replace("Current:", "")
        current_value=current.replace("</strong", "").strip()

    except Exception as ex:
        print('Unsupported Exception caught: ',end='')
        print(type(ex))
        print(ex)
        browser.quit()
    finally:
        browser.quit() # closing all terminals and windows

        last_time_downloaded = datetime.datetime.now()

    #return pb stats
    return "{:s},{:s},{:s},{:s}".format(current_value,min_value,med_value,max_value)

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
    global last_time_downloaded

    if (len(xpath) == 0) or (len(url) == 0):
        raise selenium_exceptions.InvalidArgumentException

    ### Works! Just takes a little bit of awhile, not too sure why ###
    options = webdriver.ChromeOptions()

    options.add_experimental_option('excludeSwitches', ['enable-logging'])
    
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

    last_time_downloaded = datetime.datetime.now()

def main():
    """
    Main function to control and run the functions as you initially would in main
    """

    download_csv_from_url_by_xpath(url="https://www.nasdaq.com/market-activity/stocks/screener",
                                   xpath='/html/body/div[4]/div/main/div[2]/article/div[3]/div[1]/div/div/div[3]/div[2]/div[2]/div/button')

    


if __name__ == "__main__":
    main()