# download_csv.py

# URL: "https://www.nasdaq.com/market-activity/stocks/screener"
# ID: "nasdaq-screener__form-button--download ns-download-1")

from selenium import webdriver
import time
from selenium.webdriver.common.keys import Keys

def main():
    """
    Main function to control and run the functions as you initially would in main
    """
    browser = webdriver.Chrome()

    browser.get("https://www.nasdaq.com/market-activity/stocks/screener")

    element = browser.find_element_by_xpath('/html/body/div[2]/div/main/div[2]/article/div[3]/div[1]/div/div/div[3]/div[2]/div[2]/div/button').click()
    
    time.sleep(5)

    browser.quit()

if __name__ == "__main__":
    main()
