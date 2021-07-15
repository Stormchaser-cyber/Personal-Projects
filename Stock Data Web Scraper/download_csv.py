# download_csv.py

# URL: "https://www.nasdaq.com/market-activity/stocks/screener"
# ID: "nasdaq-screener__form-button--download ns-download-1")

from selenium import webdriver
import time

def download_csv_from_url_by_xpath(url, xpath):
    browser = webdriver.Chrome()

    browser.get(url)

    browser.find_element_by_xpath(xpath).click()

    time.sleep(5)

    browser.quit()

def main():
    """
    Main function to control and run the functions as you initially would in main
    """

    download_csv_from_url_by_xpath(url="https://www.nasdaq.com/market-activity/stocks/screener",
                                   xpath='/html/body/div[2]/div/main/div[2]/article/div[3]/div[1]/div/div/div[3]/div[2]/div[2]/div/button')

if __name__ == "__main__":
    main()
