# file_pathing_helper.py
#
# Created -- Ted Strombeck -- August 24th, 2023
# Last Updated -- August 24th, 2023
# Version 1.0.0

import os

def get_download_path():
    """Returns the default downloads path for linux or windows"""
    if os.name == 'nt':
        import winreg
        sub_key = r'SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\Shell Folders'
        downloads_guid = '{374DE290-123F-4565-9164-39C4925E467B}'
        with winreg.OpenKey(winreg.HKEY_CURRENT_USER, sub_key) as key:
            location = winreg.QueryValueEx(key, downloads_guid)[0]
        return location
    else:
        return os.path.join(os.path.expanduser('~'), 'downloads')

def get_current_working_directory():
    """Returns the current working directory"""
    return os.getcwd()