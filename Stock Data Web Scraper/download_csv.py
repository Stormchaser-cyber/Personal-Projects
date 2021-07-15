# download_csv.py

# URL: "https://www.nasdaq.com/market-activity/stocks/screener"
# ID: "nasdaq-screener__form-button--download ns-download-1")

from selenium import webdriver
from selenium.webdriver.common.keys import Keys

browser = webdriver.Chrome()

browser.get("https://www.nasdaq.com/market-activity/stocks/screener")

element = browser.find_element_by_xpath('/html/body/div[2]/div/main/div[2]/article/div[3]/div[1]/div/div/div[3]/div[2]/div[2]/div/button')
#button = browser.find_element_by_id('nasdaq-screener__form-button--download ns-download-1')
print(element.get_attribute('class'))
browser.close()

# Doesn't work -- Not really sure why
