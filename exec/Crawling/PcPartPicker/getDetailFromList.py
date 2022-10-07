from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from scrapy.selector import Selector

from datetime import datetime
from datetime import timedelta
from pytz import timezone
import csv
import os
import os.path
import shutil
import urllib.request
import random

from multiprocessing import Pool
PROCESS_COUNT = 1
TIMEZONE = 'Asia/Seoul'

CRAWLING_DATA_CSV_FILE="pcinfo.csv"
OUTPUT_FILE="build_info"


STR_URL = 'url'
STR_ID = 'id'
PROD_NAME = 'name'
CHROMEDRIVER_PATH = 'chromedriver.exe'

LOG_FILE = open('build_log.txt', 'w')

class Crawer:
    def __init__(self):
        self.errorList = list()
        self.crawlingCategory = list()
        self.data_dict = {
            'code': '0',
            'CPU': '0',
            'CPU Cooler': '0',
            'Motherboard': '0',
            'Memory': '0',
            'Storage': '0',
            'Video Card' : '0',
            'Case': '0',
            'Power Supply': '0',
            'Case Fan' :'0',
            'Custom' : '0',
            'Case Accessories' : '0', 
            'Case Fans' : '0', 
            'Fan Controllers' : '0'
        }

        with open(CRAWLING_DATA_CSV_FILE, 'r', newline='', encoding='utf8') as file:
            for crawlingValues in csv.reader(file, skipinitialspace=True):
                self.crawlingCategory.append({STR_URL: crawlingValues[0]})

    def StartCrawling(self):
        self.chrome_option = webdriver.ChromeOptions()
        self.chrome_option.add_argument('--headless')
        self.chrome_option.add_argument('--window-size=1920,1080')
        self.chrome_option.add_argument('--start-maximized')
        self.chrome_option.add_argument('--disable-gpu')
        self.chrome_option.add_argument('lang=ko=KR')

        self.initCrawling()

        if __name__ == '__main__':
            pool = Pool(processes=PROCESS_COUNT)
            pool.map(self.CrawlingCategory, self.crawlingCategory)
            pool.close()
            pool.join()

    def initCrawling(self):
        cDate = self.GetCurrentDate().strftime('%Y-%m-%d_%H:%M:%S')
        crawlingFile = open(f'{OUTPUT_FILE}.csv', 'a', newline='', encoding='utf8')
        crawlingData_csvWriter = csv.writer(crawlingFile)
        crawlingData_csvWriter.writerow(list(self.data_dict.keys()))
        crawlingFile.close()

    def CrawlingCategory(self, categoryValue):
        crawlingURL = categoryValue[STR_URL]
        crawlingID = categoryValue[STR_URL]

        self.data_dict = {
            'code': '0',
            'CPU': '0',
            'CPU Cooler': '0',
            'Motherboard': '0',
            'Memory': '0',
            'Storage': '0',
            'Video Card' : '0',
            'Case': '0',
            'Power Supply': '0',
            'Case Fan' :'0',
            'Custom' : '0',
            'Case Accessories' : '0', 
            'Case Fans' : '0', 
            'Fan Controllers' : '0'
        }

        self.data_dict['code'] = crawlingID.split('/')[-1]

        # print('Crawling Start : ' + crawlingName)
        print('Crawling Start', file= LOG_FILE)
        crawlingFile = open(f'{OUTPUT_FILE}.csv', 'a', newline='', encoding='utf8')
        crawlingData_csvWriter = csv.writer(crawlingFile)

        try:
            print(crawlingURL, file=LOG_FILE)
            browser = webdriver.Chrome(CHROMEDRIVER_PATH, options=self.chrome_option)
            browser.implicitly_wait(random.randrange(4,10))
            browser.get(crawlingURL)
            html = browser.find_element(By.XPATH, '//table[@class="partlist partlist--mini"]').get_attribute('outerHTML')
            selector = Selector(text=html)
            # specs = []
            columns = selector.xpath('//td[@class="td__component"]')
            values = selector.xpath('//td[@class="td__name"]')

            for col, val in zip(columns, values):
                # print(col.xpath('./h4/text()').get() + ":" + val.xpath('./a/text()').get())
                colName = col.xpath('./h4/text()').get()
                valName = val.xpath('./a/text()').get()

                if colName != None and valName != None:
                    colName = colName.strip()
                    valName = valName.strip()

                    if colName in self.data_dict.keys():
                        if self.data_dict[colName] == '0':
                            self.data_dict[colName] = valName
                        else:
                            self.data_dict[colName] += '|'
                            self.data_dict[colName] += valName


            
            # crawlingData_csvWriter.writerow(specArr)
            # print(list(self.data_dict.values()))
            crawlingData_csvWriter.writerow(list(self.data_dict.values()))
            browser.implicitly_wait(random.randrange(4,10))

        except Exception as e:
            print('Error - ', file=LOG_FILE)
            print(e, file=LOG_FILE)
            # self.erroxList.append(crawlingName)

        crawlingFile.close()

        print('Crawling Finish : ', file=LOG_FILE)
    
    def GetCurrentDate(self):
        tz = timezone(TIMEZONE)
        return (datetime.now(tz))

if __name__ == '__main__':
    crawler = Crawer()
    crawler.StartCrawling()
    LOG_FILE.close()