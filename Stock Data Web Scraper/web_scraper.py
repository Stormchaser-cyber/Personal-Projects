# web_scaper.py file
#
# July 2021 -- Created -- Ted Strombeck
#

# URL: https://www.google.com/finance/

import requests
from bs4 import BeautifulSoup
import sys

URL = "https://www.nasdaq.com/market-activity/stocks/screener"
response = requests.get(URL)

#print(response.content)

soup = BeautifulSoup(response.content, "html.parser")

results = soup.find(id="main-content") # div.sbnBtf

if (results != None):
    print(results.prettify())
else:
    print("Results didn't have any values")
