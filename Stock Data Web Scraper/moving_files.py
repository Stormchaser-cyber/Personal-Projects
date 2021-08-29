# moving_downloaded_file.py file
#
# July 2021 -- Created -- Ted Strombeck
#
import shutil
from os import listdir
from os.path import isfile

def scan_folder(source_folder, criteria):
    """
    scan_folder scans the folder passed into the function and determines which items to select that match the criteria

    Parameters
    ----------
    String
        source_folder: the folder in which we should search for files
        criteria: the string to search for similarities

    Returns
    -------
    List
        files_to_move: a list of strings which are the file names of files to move
    """
    files_to_move = []
    
    for item in listdir(source_folder):
        if item[0:15] == criteria:
            files_to_move.append(item)

    return files_to_move

def custom_scan_folder(source_folder, criteria, number_of_characters):
    files_to_move = []
    
    for item in listdir(source_folder):
        if item[0:number_of_characters] == criteria:
            files_to_move.append(item)

    return files_to_move

def move_file(file_name, source_folder, destination_folder):
    """
    move_file function moves a given file where a file's name, type, source folder, and destination folder are specified

    Parameters
    ----------
    String
        file_name: the full name of the file
        source_folder: the file path of the source folder (NOTE: it should end without a slash or backslash)
        destination_folder: the file path of the desired folder (NOTE: it should end without a slash or backslash)
    """
    
    source_string = source_folder+ '/' + file_name # Adding the extra backslash so that it goes into the directory instead of looking above it
    destination_string = destination_folder + '/' + file_name

    shutil.move(source_string, destination_string) # actually moving the file

def move_files(in_list, source_folder, destination_folder):
    """
    move_files function moves all files passed into it from the in_list parameter, from the source folder parameter
    to the destination_folder parameter

    Parameters
    ----------
    List
        in_list: the list of files to move from the source folder to the destination folder
    String
        source_folder: the file path of the source folder (NOTE: it should end without a slash or backslash)
        destination_folder: the file path of the desired folder (NOTE: it should end without a slash or backslash)
    """

    for item in in_list:
        source_string = source_folder + '/' + item
        destination_string = destination_folder + '/' + item

        shutil.move(source_string, destination_string)

def main():
    """
    Main function to run test code and to get initial code working
    """
    criteria = 'nasdaq_screener'
    file_name = 'nasdaq_screener_1626390668384.csv'
    source_folder = 'C:/Users/tedst/Downloads'
    destination_folder = 'C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Stock Spreadsheets'

    #move_file(file_name=file_name, file_type=file_type, source_folder=source_folder, destination_folder=destination_folder)

    items_to_move = scan_folder(source_folder, criteria)

    move_files(items_to_move, source_folder, destination_folder)
    
if __name__ == "__main__":
    main()
