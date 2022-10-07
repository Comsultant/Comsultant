# -*- coding: utf-8 -*-

# danawa_cralwer.py
# sammy310


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

from multiprocessing import Pool


PROCESS_COUNT = 2


CRAWLING_DATA_CSV_FILE = 'CrawlingCategory.csv'
DATA_PATH = 'crawl_data'
DATA_REFRESH_PATH = f'{DATA_PATH}/Last_Data'

TIMEZONE = 'Asia/Seoul'

# CHROMEDRIVER_PATH = 'chromedriver_94.exe'
CHROMEDRIVER_PATH = 'chromedriver.exe'

DATA_DIVIDER = '---'
DATA_REMARK = '//'
DATA_ROW_DIVIDER = '_'
DATA_PRODUCT_DIVIDER = '|'

STR_NAME = 'name'
STR_URL = 'url'
STR_CRAWLING_PAGE_SIZE = 'crawlingPageSize'


class DanawaCrawler:
    def __init__(self):
        self.errorList = list()
        self.crawlingCategory = list()
        with open(CRAWLING_DATA_CSV_FILE, 'r', newline='') as file:
            for crawlingValues in csv.reader(file, skipinitialspace=True):
                if not crawlingValues[0].startswith(DATA_REMARK):
                    self.crawlingCategory.append({STR_NAME: crawlingValues[0], STR_URL: crawlingValues[1], STR_CRAWLING_PAGE_SIZE: int(crawlingValues[2])})

    def StartCrawling(self):
        self.chrome_option = webdriver.ChromeOptions()
        self.chrome_option.add_argument('--headless')
        self.chrome_option.add_argument('--window-size=1920,1080')
        self.chrome_option.add_argument('--start-maximized')
        self.chrome_option.add_argument('--disable-gpu')
        self.chrome_option.add_argument('lang=ko=KR')

        if __name__ == '__main__':
            pool = Pool(processes=PROCESS_COUNT)
            pool.map(self.CrawlingCategory, self.crawlingCategory)
            pool.close()
            pool.join()

            
    
    def CrawlingCategory(self, categoryValue):
        crawlingName = categoryValue[STR_NAME]
        crawlingURL = categoryValue[STR_URL]
        crawlingSize = categoryValue[STR_CRAWLING_PAGE_SIZE]

        print('Crawling Start : ' + crawlingName)

        # data
        crawlingFile = open(f'{crawlingName}.csv', 'w', newline='', encoding='utf8')
        crawlingData_csvWriter = csv.writer(crawlingFile)
        crawlingData_csvWriter.writerow([self.GetCurrentDate().strftime('%Y-%m-%d %H:%M:%S')])
        
        try:
            browser = webdriver.Chrome(CHROMEDRIVER_PATH, options=self.chrome_option)
            browser.implicitly_wait(5)
            browser.get(crawlingURL)

            # browser.find_element_by_xpath('//option[@value="90"]').click()
            browser.find_element(By.XPATH, '//option[@value="90"]').click()
        
            wait = WebDriverWait(browser, 10)
            wait.until(EC.invisibility_of_element((By.CLASS_NAME, 'product_list_cover')))
            
            for i in range(-1, crawlingSize):
                if i == -1:
                    # browser.find_element_by_xpath('//li[@data-sort-method="NEW"]').click()
                    browser.find_element(By.XPATH, '//li[@data-sort-method="NEW"]').click()
                elif i == 0:
                    # browser.find_element_by_xpath('//li[@data-sort-method="BEST"]').click()
                    browser.find_element(By.XPATH, '//li[@data-sort-method="BEST"]').click()
                elif i > 0:
                    if i % 10 == 0:
                        # browser.find_element_by_xpath('//a[@class="edge_nav nav_next"]').click()
                        browser.find_element(By.XPATH, '//a[@class="edge_nav nav_next"]').click()
                    else:
                        browser.find_element(By.XPATH, '//a[@class="num "][%d]'%(i%10)).click()
                wait.until(EC.invisibility_of_element((By.CLASS_NAME, 'product_list_cover')))
                
                html = browser.find_element(By.XPATH, '//div[@class="main_prodlist main_prodlist_list"]').get_attribute('outerHTML')
                selector = Selector(text=html)

                # Get Product List
                products = selector.xpath('//ul[@class="product_list"]/li')[:-1]

                for product in products:
                    # ad
                    if 'prod_ad_item' in product.xpath('@class').get().split(' '):
                        continue

                    productId = product.xpath('@id').get()[11:]
                    productName = product.xpath('./div/div[2]/p/a/text()').get().strip()

                    productItems = product.xpath('./div/div[3]/ul/li')

                    # print(productName)
                    status = True
                    packageName = ""
                    prodId = ""
                    for prod in productItems:
                        prodId = prod.xpath('@id').get()[18:]
                        packageNameList = prod.xpath('./div[@class="over_preview"]/p/text()')
                        for pn in packageNameList:
                            if pn.get() != None and len(pn.get().strip()) != 0:
                                packageName = pn.get().strip()
                                break
                        if packageName != "":
                            crawlingData_csvWriter.writerow([prodId, f'{productName} ({packageName})'])
                            packageName = ""
                            status = False

                    # 1개도 write X
                    if status:
                        crawlingData_csvWriter.writerow([productId, productName])
                        
                print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + str(i))
                # break;


        except Exception as e:
            print('Error - ' + crawlingName + ' ->')
            print(e)
            self.errorList.append(crawlingName)

        crawlingFile.close()

        print('Crawling Finish : ' + crawlingName)

    def DataSort(self):
        print('Data Sort\n')

        for crawlingValue in self.crawlingCategory:
            dataName = crawlingValue[STR_NAME]
            crawlingDataPath = f'{dataName}.csv'

            if not os.path.exists(crawlingDataPath):
                continue

            crawl_dataList = list()
            dataList = list()
            
            with open(crawlingDataPath, 'r', newline='', encoding='utf8') as file:
                csvReader = csv.reader(file)
                for row in csvReader:
                    crawl_dataList.append(row)
            
            if len(crawl_dataList) == 0:
                continue
            
            dataPath = f'{DATA_PATH}/{dataName}.csv'
            if not os.path.exists(dataPath):
                file = open(dataPath, 'w', encoding='utf8')
                file.close()
            with open(dataPath, 'r', newline='', encoding='utf8') as file:
                csvReader = csv.reader(file)
                for row in csvReader:
                    dataList.append(row)
            
            
            if len(dataList) == 0:
                dataList.append(['Id', 'Name'])
                
            # dataList[0].append(crawl_dataList[0][0])
            dataSize = len(dataList[0])
            
            for product in crawl_dataList:
                if not str(product[0]).isdigit():
                    continue
                
                isDataExist = False
                for data in dataList:
                    if data[0] == product[0]:
                        # if len(data) < dataSize:
                        #     data.append(product[2])
                        isDataExist = True
                        break
                
                if not isDataExist:
                    # newDataList = ([product[0], product[1]])
                    # for i in range(2,len(dataList[0])-1):
                    #     newDataList.append(0)
                    # print(newDataList)
                    # print(product)
                    # newDataList.append(product[2])
                
                    # dataList.append(newDataList)
                    dataList.append(product)
                
            # for data in dataList:
            #     if len(data) < dataSize:
            #         for i in range(len(data),dataSize):
            #             data.append(0)
                
            
            productData = dataList.pop(0)
            dataList.sort(key= lambda x: x[1])
            dataList.insert(0, productData)
                
            with open(dataPath, 'w', newline='', encoding='utf8') as file:
                csvWriter = csv.writer(file)
                for data in dataList:
                    csvWriter.writerow(data)
                file.close()
                
            if os.path.isfile(crawlingDataPath):
                os.remove(crawlingDataPath)

    def DataRefresh(self):
        dTime = self.GetCurrentDate()
        if dTime.day == 1:
            print('Data Refresh\n')

            if not os.path.exists(DATA_PATH):
                os.mkdir(DATA_PATH)
            
            dTime -= timedelta(days=1)
            dateStr = dTime.strftime('%Y-%m')

            dataSavePath = f'{DATA_REFRESH_PATH}/{dateStr}'
            if not os.path.exists(dataSavePath):
                os.mkdir(dataSavePath)
            
            for file in os.listdir(DATA_PATH):
                fileName, fileExt = os.path.splitext(file)
                if fileExt == '.csv':
                    filePath = f'{DATA_PATH}/{file}'
                    refreshFilePath = f'{dataSavePath}/{file}'
                    shutil.move(filePath, refreshFilePath)
    
    def GetCurrentDate(self):
        tz = timezone(TIMEZONE)
        return (datetime.now(tz))


if __name__ == '__main__':
    crawler = DanawaCrawler()
    crawler.DataRefresh()
    crawler.StartCrawling()
    crawler.DataSort()