# download_stock_statistics.py
#
# Created -- Ted Strombeck -- August 23, 2023
# Last Updated -- August 23, 2023
# Version 1.0.1
# 

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

def download_dividend_yield_for_stock_ticker(stock_ticker):
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
        url = "https://www.gurufocus.com/term/yield/A/Dividend-Yield-Percentage/Agilent%20Technologies"

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
        results = browser.find_element(By.ID, "yield_tools").get_attribute("outerHTML").split(">")

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

    #return Dividend yield stats
    return "{:s},{:s},{:s},{:s}".format(current_value,min_value,med_value,max_value)

def download_yoy_ebitda_growth_rate_for_stock_ticker(stock_ticker):
    """
    download_pb_for_stock_ticker downloads the related pb for a selected stock
    """
    results = []
    current_value = 0.00
    last_updated = "n/a"
    result_path = '/html/body/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div/div[1]/strong/text()[1]'
    url = "https://www.google.com/"

    if (len(stock_ticker) == 0):
        raise selenium_exceptions.InvalidArgumentException
    else:
        url = "https://www.gurufocus.com/term/editda_5y_growth/A/5-Year-EBITDA-Growth-Rate/Agilent%20Technologies"

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
        results = browser.find_element(By.XPATH, "/html/body/div[2]/div[2]/div/div/div/div[2]/font[1]").get_attribute("innerHTML")
        
        results=results.replace(":", "")
        results=results.strip()
        results = results.split()

        current_value = results[0]
        last_updated = " ".join(results[1:len(results)])
        

    except Exception as ex:
        print('Unsupported Exception caught: ',end='')
        print(type(ex))
        print(ex)
        browser.quit()
    finally:
        browser.quit() # closing all terminals and windows

        last_time_downloaded = datetime.datetime.now()

    #return Dividend yield stats
    return "{:s},{:s}".format(current_value,last_updated)

def download_debt_to_equity_for_stock_ticker(stock_ticker):
    """
    
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
        url = "https://www.gurufocus.com/term/deb2equity/A/Debt-to-Equity/Agilent%20Technologies"

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
        results = browser.find_element(By.ID, "deb2equity_tools").get_attribute("outerHTML").split(">")

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

    #return debt-to-equity stats
    return "{:s},{:s},{:s},{:s}".format(current_value,min_value,med_value,max_value)

def download_roe_percentage_for_stock_ticker(stock_ticker):
    """
    
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
        url = "https://www.gurufocus.com/term/ROE/A/ROE-Percentage"

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
        results = browser.find_element(By.ID, "ROE_tools").get_attribute("outerHTML").split(">")

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

    #return roe stats
    return "{:s},{:s},{:s},{:s}".format(current_value,min_value,med_value,max_value)

def download_operating_margin_percentage_for_stock_ticker(stock_ticker):
    """
    
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
        url = "https://www.gurufocus.com/term/operatingmargin/A/Operating-Margin-Percentage"

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
        results = browser.find_element(By.ID, "operatingmargin_tools").get_attribute("outerHTML").split(">")

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

    #return roe stats
    return "{:s},{:s},{:s},{:s}".format(current_value,min_value,med_value,max_value)

def download_fcf_margin_percentage_for_stock_ticker(stock_ticker):
    """
    
    """
    results = []
    current_value = 0.00
    last_updated = "n/a"
    result_path = '/html/body/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div/div[1]/strong/text()[1]'
    url = "https://www.google.com/"

    if (len(stock_ticker) == 0):
        raise selenium_exceptions.InvalidArgumentException
    else:
        url = "https://www.gurufocus.com/term/FCFmargin/A/FCF-Margin-Percentage"

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
        results = browser.find_element(By.XPATH, "/html/body/div[2]/div[2]/div/div/div/div[2]/font[1]").get_attribute("innerHTML")
        
        results=results.replace(":", "")
        results=results.strip()
        results = results.split()

        current_value = results[0]
        last_updated = " ".join(results[1:len(results)])
        

    except Exception as ex:
        print('Unsupported Exception caught: ',end='')
        print(type(ex))
        print(ex)
        browser.quit()
    finally:
        browser.quit() # closing all terminals and windows

        last_time_downloaded = datetime.datetime.now()

    #return fcf margin % stats
    return "{:s},{:s}".format(current_value,last_updated)

def download_beta_for_stock_ticker(stock_ticker):
    """
    
    """
    results = []
    current_value = 0.00
    last_updated = "n/a"
    result_path = '/html/body/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div/div[1]/strong/text()[1]'
    url = "https://www.google.com/"

    if (len(stock_ticker) == 0):
        raise selenium_exceptions.InvalidArgumentException
    else:
        url = "https://www.gurufocus.com/term/beta/A/Beta/Agilent%20Technologies"

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
        results = browser.find_element(By.XPATH, "/html/body/div[2]/div[2]/div/div/div/div[2]/font[1]").get_attribute("innerHTML")
        
        results=results.replace(":", "")
        results=results.strip()
        results = results.split()

        current_value = results[0]
        last_updated = " ".join(results[1:len(results)])
        

    except Exception as ex:
        print('Unsupported Exception caught: ',end='')
        print(type(ex))
        print(ex)
        browser.quit()
    finally:
        browser.quit() # closing all terminals and windows

        last_time_downloaded = datetime.datetime.now()

    #return beta stats
    return "{:s},{:s}".format(current_value,last_updated)

if __name__ == '__main__':
    download_beta_for_stock_ticker("AAPL")