# moving_downloaded_file.py file
#
# July 2021 -- Created -- Ted Strombeck
#
import shutil

def move_file(file_name, file_type, source_folder, destination_folder):
    source_string = source_folder+ '/' + file_name + file_type # Adding the extra backslash so that it goes into the directory instead of looking above it
    destination_string = destination_folder + '/' + file_name + file_type

    shutil.move(source_string, destination_string) # actually moving the file

def main():
    """
    Main function to run test code and to get initial code working
    """
    file_type = '.csv'
    file_name = 'nasdaq_screener_1626359675325'
    source_folder = 'C:/Users/tedst/Downloads'
    destination_folder = 'C:/Users/tedst/Documents/Augsburg University Files/Programming Files/Bot Projects/Stock Data Web Scraper/Stock Spreadsheets'

    move_file(file_name=file_name, file_type=file_type, source_folder=source_folder, destination_folder=destination_folder)

if __name__ == "__main__":
    main()
