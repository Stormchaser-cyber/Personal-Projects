# download_csv.py

# URL: "https://www.nasdaq.com/market-activity/stocks/screener"
# ID: "nasdaq-screener__form-button--download ns-download-1")

from selenium import webdriver

browser = webdriver.Chrome('C:\ProgramData\Microsoft\Windows\Start Menu\Programs')

browser.get("https://www.nasdaq.com/market-activity/stocks/screener")

button = browser.find_element_by_css_selector('/html/body/div[2]/div/main/div[2]/article/div[3]/div[1]/div/div/div[3]/div[2]/div[2]/div/button')

# Doesn't work -- Not really sure why
