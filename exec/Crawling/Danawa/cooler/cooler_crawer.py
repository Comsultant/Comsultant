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

from multiprocessing import Pool
PROCESS_COUNT = 1
TIMEZONE = 'Asia/Seoul'

IMG_PATH="img/cooler"
CRAWLING_DATA_CSV_FILE="Cooler.csv"
OUTPUT_FILE="cooler_info"


STR_URL = 'url'
STR_ID = 'id'
PROD_NAME = 'name'
CHROMEDRIVER_PATH = 'chromedriver.exe'

LOG_FILE = open('cooler_log.txt', 'w')

class VGACrawer:
    def __init__(self):
        self.errorList = list()
        self.crawlingCategory = list()
        self.data_dict = {
            'code' : '0',
            'prod_name' : '0',
            'img_cnt': '0',
            '제조회사' : '0',
            '등록년월' : '0',
            '냉각 방식' : '0',
            '분류' : '0',
            '제품 종류' : '0',
            '써멀 유형' : '0',
            '열전도율' : '0',
            '용량' : '0',
            '전기전도성' : '0',
            '점성' : '0',
            '밀도' : '0',
            'LGA3647' : '0',
            'LGA2066' : '0',
            'LGA2011-V3' : '0',
            'LGA2011' : '0',
            'LGA1700' : '0',
            'LGA1366' : '0',
            'LGA1200' : '0',
            'LGA115x' : '0',
            'LGA775' : '0',
            'LGA771' : '0',
            'LGA4677' : '0',
            'LGA4189-4/5(소켓P4/P5)' : '0',
            '소켓478' : '0',
            '소켓370' : '0',
            'TR4' : '0',
            'AM5' : '0',
            'AM4' : '0',
            'AM3' : '0',
            'AM1' : '0',
            'SP3' : '0',
            'sTRX4' : '0',
            '소켓939' : '0',
            '소켓754' : '0',
            '소켓940' : '0',
            'sWRX8' : '0',
            '소켓A' : '0',
            '소켓F' : '0',
            'FMx/AMx(AM1/4외)' : '0',
            'TDP' : '0',
            '재질' : '0',
            '라디에이터' : '0',
            '히트파이프' : '0',
            '쿨러 높이' : '0',
            '무게' : '0',
            '팬 크기' : '0',
            '팬 두께' : '0',
            '컨넥터' : '0',
            '베어링' : '0',
            '최대 팬속도' : '0',
            '최대 풍량' : '0',
            '최대 풍압' : '0',
            '최대 소음' : '0',
            '팬 개수' : '0',
            '팬수명' : '0',
            'LED 라이트' : '0',
            'LED색상' : '0',
            'PWM 지원' : '0',
            'RGB 컨트롤러' : '0',
            '데이지체인' : '0',
            '제로팬(0-dB기술)' : '0',
            '팬 컨트롤러' : '0',
            '워터블록/로고 회전' : '0',
            'AURA SYNC' : '0',
            'MYSTIC LIGHT' : '0',
            'RGB FUSION' : '0',
            'RGB LED': '0',
            'POLYCHROME' : '0',
            'CHROMA' : '0',
            'VIVID' : '0',
            'BIOSTAR SYNC' : '0',
            '제조사 소프트웨어' : '0',
            'A/S기간' : '0',
            '수랭 커스텀': '0',
            '패키지 상품': '0',
            '리모콘 지원' : '0',
            '인디게이터': '0',
            '펌프속도조절' : '0',
            '유통회사': '0',
            'LCD': '0',
        }

        with open(CRAWLING_DATA_CSV_FILE, 'r', newline='', encoding='utf8') as file:
            for crawlingValues in csv.reader(file, skipinitialspace=True):
                self.crawlingCategory.append({STR_ID: crawlingValues[0],STR_URL: "https://prod.danawa.com/info/?pcode=" + crawlingValues[0], PROD_NAME: crawlingValues[1]})

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
        crawlingFile = open(f'{OUTPUT_FILE}.csv', 'w', newline='', encoding='utf8')
        crawlingData_csvWriter = csv.writer(crawlingFile)
        # crawlingData_csvWriter.writerow([self.GetCurrentDate().strftime('%Y-%m-%d %H:%M:%S')])
        crawlingData_csvWriter.writerow(list(self.data_dict.keys()))
        crawlingFile.close()

    def CrawlingCategory(self, categoryValue):
        # crawlingName = categoryValue[STR_NAME]
        crawlingURL = categoryValue[STR_URL]
        crawlingID = categoryValue[STR_ID]
        imgPath = f'{IMG_PATH}/{crawlingID}'

        if os.path.exists(IMG_PATH) == False:
            os.mkdir(IMG_PATH)
            # shutil.rmtree(IMG_PATH)

        if os.path.exists(imgPath) == False:
            os.mkdir(imgPath)
            

        self.data_dict = {
            'code' : '0',
            'prod_name' : '0',
            'img_cnt': '0',
            '제조회사' : '0',
            '등록년월' : '0',
            '냉각 방식' : '0',
            '분류' : '0',
            '제품 종류' : '0',
            '써멀 유형' : '0',
            '열전도율' : '0',
            '용량' : '0',
            '전기전도성' : '0',
            '점성' : '0',
            '밀도' : '0',
            'LGA3647' : '0',
            'LGA2066' : '0',
            'LGA2011-V3' : '0',
            'LGA2011' : '0',
            'LGA1700' : '0',
            'LGA1366' : '0',
            'LGA1200' : '0',
            'LGA115x' : '0',
            'LGA775' : '0',
            'LGA771' : '0',
            'LGA4677' : '0',
            'LGA4189-4/5(소켓P4/P5)' : '0',
            '소켓478' : '0',
            '소켓370' : '0',
            'TR4' : '0',
            'AM5' : '0',
            'AM4' : '0',
            'AM3' : '0',
            'AM1' : '0',
            'SP3' : '0',
            'sTRX4' : '0',
            '소켓939' : '0',
            '소켓754' : '0',
            '소켓940' : '0',
            'sWRX8' : '0',
            '소켓A' : '0',
            '소켓F' : '0',
            'FMx/AMx(AM1/4외)' : '0',
            'TDP' : '0',
            '재질' : '0',
            '라디에이터' : '0',
            '히트파이프' : '0',
            '쿨러 높이' : '0',
            '무게' : '0',
            '팬 크기' : '0',
            '팬 두께' : '0',
            '컨넥터' : '0',
            '베어링' : '0',
            '최대 팬속도' : '0',
            '최대 풍량' : '0',
            '최대 풍압' : '0',
            '최대 소음' : '0',
            '팬 개수' : '0',
            '팬수명' : '0',
            'LED 라이트' : '0',
            'LED색상' : '0',
            'PWM 지원' : '0',
            'RGB 컨트롤러' : '0',
            '데이지체인' : '0',
            '제로팬(0-dB기술)' : '0',
            '팬 컨트롤러' : '0',
            '워터블록/로고 회전' : '0',
            'AURA SYNC' : '0',
            'MYSTIC LIGHT' : '0',
            'RGB FUSION' : '0',
            'RGB LED': '0',
            'POLYCHROME' : '0',
            'CHROMA' : '0',
            'VIVID' : '0',
            'BIOSTAR SYNC' : '0',
            '제조사 소프트웨어' : '0',
            'A/S기간' : '0',
            '수랭 커스텀': '0',
            '패키지 상품': '0',
            '리모콘 지원' : '0',
            '인디게이터': '0',
            '펌프속도조절' : '0',
            '유통회사': '0',
            'LCD': '0',
        }

        self.data_dict['code'] = crawlingID
        self.data_dict['prod_name'] = categoryValue[PROD_NAME]

        print("Crawling Start : " + self.getCurrentTime(), file= LOG_FILE)
        crawlingFile = open(f'{OUTPUT_FILE}.csv', 'a', newline='', encoding='utf8')
        crawlingData_csvWriter = csv.writer(crawlingFile)

        try:
            print(crawlingURL + " : " + self.getCurrentTime(), file=LOG_FILE)
            browser = webdriver.Chrome(CHROMEDRIVER_PATH, options=self.chrome_option)
            browser.implicitly_wait(5)
            browser.get(crawlingURL)

            # 상세정보 이미지 파일 다운
            # imgHtml = browser.find_element(By.XPATH, '//div[@id="detail_export"]').get_attribute('outerHTML')
            # imgSelector = Selector(text=imgHtml)
            # imgTag = imgSelector.xpath('//div[@class="inner"]')
            # imgList = imgTag.xpath('./table/tbody/tr/td/div/p/img')

            # opener = urllib.request.URLopener()
            # opener.addheader('User-Agent', 'whatever')

            # for idx, img in enumerate(imgList):
            #     print(idx, img.attrib['src'])
            #     imgName = f'{imgPath}/{idx}.jpg'
            #     # imgName = f'{idx}.jpg'
            #     # urllib.request.urlretrieve(img.attrib['src'], imgName)
            #     # os.system("curl " + img.attrib['src'] + f" > {imgName}")
            #     opener.retrieve(img.attrib['src'], imgName)
            #################################

            ## 섬네일 다운
            imgHtml = browser.find_element(By.XPATH, '//div[@id="thumbArea"]').get_attribute('outerHTML')
            imgSelector = Selector(text=imgHtml)
            imgList = imgSelector.xpath('//div[2]/ul[@id="basicThumbSlideArea"]/li')

            opener = urllib.request.URLopener()
            opener.addheader('User-Agent', 'whatever')

            imgIdx = 0
            for imgTag in imgList:
                tagId = imgTag.xpath('./@id').get()
                if tagId != None and tagId.find("imageThumbNail") != -1:
                    img = imgTag.xpath('./a/img[1]')
                    print(imgIdx, "https://" + img.attrib['src'][2:].split('?')[0], file=LOG_FILE)
                    imgName = f'{imgPath}/{imgIdx}.jpg'
                    # opener.retrieve("https://" + img.attrib['src'][2:].split('?')[0], imgName)
                    imgIdx+=1
            self.data_dict['img_cnt'] = imgIdx
            
            #################################


            html = browser.find_element(By.XPATH, '//div[@class="prod_spec"]').get_attribute('outerHTML')
            selector = Selector(text=html)
            # specs = []
            specs = selector.xpath('//table/tbody/tr')
            # specArr = [crawlingID]


            
            for spec in specs:
                # colName 길이로 조건 걸어야 함
                colList = spec.xpath('./th')
                valList = spec.xpath('./td')
                for col, val in zip(colList, valList):
                    # col이 a tag 일 가능성 있음
                    column = ""
                    listSize = len(col.xpath('./a'))
                    if listSize == 0:
                        # print(col.xpath('./text()').get())
                        column = col.xpath('./text()').get()
                    else:
                        # print(col.xpath('./a/text()').get())
                        column = col.xpath('./a/text()').get()
                    
                    atag = len(val.xpath('./a'))
                    value = ""
                    if atag == 0:
                        # print(val.xpath('./text()').get())
                        value = val.xpath('./text()').get()
                    elif atag > 1:
                        # 제조회사 a 태그가 2개임
                        value = val.xpath('./a[1]/text()').get()
                    else:
                        # print(val.xpath('./a/text()').get())
                        # print(val.xpath('./a'))
                        value = val.xpath('./a/text()').get()

                    
                    if value != None:
                        if column in self.data_dict.keys():
                            self.data_dict[column.strip()] = value.strip()
                        else:
                            print(column + " " + value, file=LOG_FILE)
                            print(column + " " + value)
            # crawlingData_csvWriter.writerow(specArr)
            # print(list(self.data_dict.values()))
            crawlingData_csvWriter.writerow(list(self.data_dict.values()))



        except Exception as e:
            print('Error - ', file=LOG_FILE)
            print(e, file=LOG_FILE)
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
    crawler = VGACrawer()
    crawler.StartCrawling()
    LOG_FILE.close()