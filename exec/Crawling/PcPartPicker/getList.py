# https://pcpartpicker.com/builds/#page=1&sort=recent

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
import time
import shutil
import urllib.request

from multiprocessing import Pool
PROCESS_COUNT = 1
TIMEZONE = 'Asia/Seoul'

OUTPUT_FILE="pcinfo"


STR_URL = 'url'
STR_ID = 'id'
PROD_NAME = 'name'
CHROMEDRIVER_PATH = 'chromedriver.exe'
START_PAGE = 50
PAGE_SIZE = 50
# PAGE_SIZE = 1

# START_PAGE = 37
# PAGE_SIZE = 37


LOG_FILE = open('pc_part_list.txt', 'w')

class Crawer:
    def __init__(self):
        self.errorList = list()
        self.crawlingCategory = list()

        for i in range(START_PAGE, PAGE_SIZE+1):
            self.crawlingCategory.append({
                STR_URL: self.getUrl(i)
            })

    def getUrl(self, page):
        return f'https://pcpartpicker.com/builds/#sort=recent&period=1y&page={page}'

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
        # crawlingName = categoryValue[STR_NAME]
        crawlingURL = categoryValue[STR_URL]

        print("Crawling Start : " + self.getCurrentTime(), file= LOG_FILE)
        crawlingFile = open(f'{OUTPUT_FILE}.csv', 'a', newline='', encoding='utf8')
        crawlingData_csvWriter = csv.writer(crawlingFile)

        try:
            print(crawlingURL + " : " + self.getCurrentTime(), file=LOG_FILE)
            browser = webdriver.Chrome(CHROMEDRIVER_PATH, options=self.chrome_option)
            browser.implicitly_wait(3)
            browser.get(crawlingURL)

            try:
                cpaTest = browser.find_element(By.XPATH, '//div[@class="main-content"]')
            except:
                print("here")
                # print(browser.find_element(By.XPATH,'//div[@id="simple-single-message"]').get_attribute('outerHTML'))
                # b = browser.find_element(By.XPATH,'//div[@id="simple-single-message"]')
                browser.implicitly_wait(1)
                c = browser.find_element(By.CSS_SELECTOR,"iframe[name^='a-'][src^='https://www.google.com/recaptcha/api2/anchor?']")
                print(c.get_attribute('outerHTML'))
                browser.switch_to.frame(c) 

                # WebDriverWait(browser, 10).until(EC.frame_to_be_available_and_switch_to_it(c))
                a = browser.find_element(By.XPATH, "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked rc-anchor-checkbox']")
                print(a.get_attribute('outerHTML'))
                a.click()
                browser.implicitly_wait(1)
                browser.switch_to.default_content()


                d = browser.find_element(By.XPATH, '//div[@class="g-recaptcha"]')
                # d = browser.find_element(By.XPATH, '//div')
                print(d.get_attribute('outerHtml'))
                d.click()
                
                # WebDriverWait(browser, 10).until(EC.element_to_be_clickable((By.XPATH, "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked rc-anchor-checkbox']/div[@class='recaptcha-checkbox-checkmark']"))).click()
                browser.implicitly_wait(3)
                # print(browser.find_element(By.XPATH, "//div[1]").get_attribute('outerHtml'))
                # raise "test"


            wait = WebDriverWait(browser, 10)
            wait.until(EC.visibility_of_element_located((By.CLASS_NAME, 'logGroup')))

            html = browser.find_element(By.XPATH, '//div[@class="main-content"]').get_attribute('outerHTML')
            selector = Selector(text=html)
            build_list = selector.xpath('//div[1]/ul[@id="userbuild_list"]/li')
            # print(build_list.get())
            for build in build_list:
                # print(build.xpath('./a[1]').attrib["href"])
                u = build.xpath('./a[1]').attrib["href"].strip()
                crawlingData_csvWriter.writerow(["https://pcpartpicker.com" + u])




        except Exception as e:
            print('Error - ', file=LOG_FILE)
            print(e, file=LOG_FILE)
            print(e)
            print(self.getCurrentTime())
            # self.erroxList.append(crawlingName)

        crawlingFile.close()

        print('Crawling Finish : ' + self.getCurrentTime(), file=LOG_FILE)
    
    def GetCurrentDate(self):
        tz = timezone(TIMEZONE)
        return (datetime.now(tz))

    def getCurrentTime(self):
        cDate = self.GetCurrentDate().strftime('%Y-%m-%d_%H:%M:%S')
        return cDate


if __name__ == '__main__':
    crawler = Crawer()
    crawler.StartCrawling()
    LOG_FILE.close()