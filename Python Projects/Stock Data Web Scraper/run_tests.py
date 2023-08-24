# run_tests.py
#
# Created -- Ted Strombeck -- August 3, 2023
# Last Updated -- August 24, 2023
# Version 1.0.1
#

import unittest
import download_csv
import moving_files
import os
import file_pathing_helper as path_helper

import selenium.common.exceptions as exceptions

stock_url = "https://www.nasdaq.com/market-activity/stocks/screener"
stock_button_xpath = "/html/body/div[4]/div/main/div[2]/article/div[3]/div[1]/div/div/div[3]/div[2]/div[2]/div/button"

class TestDownloadCsv(unittest.TestCase):
    def test_download_csv_from_url_by_xpath_happy_path(self):
        files_downloaded = None

        # calling function with correct url and xpath
        download_csv.main()

        # check download folder to make sure file was correctly downloaded
        files_downloaded = moving_files.scan_folder(path_helper.get_download_path(), 'nasdaq_screener')

        # checking to make sure that one or more files were downloaded correctly
        self.assertNotEqual(len(files_downloaded), 0)

        # checking to make sure that only one file was downloaded
        self.assertEqual(len(files_downloaded), 1)

    def test_download_csv_from_url_by_xpath_blank_url(self):
        # calling function with empty url
        with self.assertRaises(exceptions.InvalidArgumentException):
            download_csv.download_csv_from_url_by_xpath(url="", xpath=stock_button_xpath)


    def test_download_csv_from_url_by_xpath_blank_xpath(self):
        # calling function with blank xpath
        with self.assertRaises(exceptions.InvalidArgumentException):
            download_csv.download_csv_from_url_by_xpath(url=stock_url, xpath='')

def run_all_tests():
    unittest.main()

def main():
    # remove test files
    test_files_downloaded = moving_files.scan_folder(path_helper.get_download_path(), 'nasdaq_screener')

    for file in test_files_downloaded:
        os.remove(path_helper.get_download_path()+'\\'+file)

    run_all_tests()

if __name__ == '__main__':
    main()
