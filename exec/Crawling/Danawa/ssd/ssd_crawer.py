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

IMG_PATH="img/ssd"
CRAWLING_DATA_CSV_FILE="SSD.csv"
OUTPUT_FILE="ssd_info"


STR_URL = 'url'
STR_ID = 'id'
PROD_NAME = 'name'
CHROMEDRIVER_PATH = 'chromedriver.exe'

LOG_FILE = open('ssd_log.txt', 'w')

class VGACrawer:
    def __init__(self):
        self.errorList = list()
        self.crawlingCategory = list()
        self.data_dict = {
            'code' : '0',
            'prod_name' : '0',
            'img_cnt' : '0',
            '등록년월' : '0',
            '제조회사' : '0',
            '제품분류' : '0',
            '상세분류' : '0',
            '폼팩터' : '0',
            '인터페이스' : '0',
            '프로토콜' : '0',
            '용량' : '0',
            '메모리 타입' : '0',
            '낸드 구조' : '0',
            'RAM 유무' : '0',
            'RAM 타입' : '0',
            '컨트롤러' : '0',
            '콘솔게임기 호환' : '0',
            '공정' : '0',
            '크기 변환' : '0',
            '인터페이스 변환' : '0',
            '순차읽기' : '0',
            '순차쓰기' : '0',
            '읽기IOPS' : '0',
            '쓰기IOPS' : '0',
            'TRIM' : '0',
            'GC' : '0',
            'SLC캐싱' : '0',
            'S.M.A.R.T' : '0',
            'ECC' : '0',
            'DEVSLP' : '0',
            'AES 암호화' : '0',
            '전용 S/W' : '0',
            'TBW' : '0',
            '맥북 업그레이드용' : '0',
            '마이그레이션' : '0',
            '관리기능' : '0',
            'MTBF' : '0',
            '5년' : '0',
            '3년' : '0',
            '데이터 복구 3년' : '0',
            '데이터 복구 1년' : '0',
            '제한보증' : '0',
            'NVMe 방열판' : '0',
            '가로' : '0',
            '세로' : '0',
            '두께' : '0',
            '무게' : '0',
            '유통회사' : '0',
            '2.5형(6.4cm)' : '0',
            '3.5형(8.9cm)' : '0',
            'Mini SATA(mSATA)' : '0',
            '보관(장착) 개수' : '0',
            'M.2 (2230)' : '0',
            'M.2 (2242)' : '0',
            'M.2 (2260)' : '0',
            'M.2 (2280)' : '0',
            'M.2 (22110)' : '0',
            '지원 두께' : '0',
            '용도 구분' : '0',
            '전원공급' : '0',
            '충격보호' : '0',
            '화이트' : '0',
            '옐로우' : '0',
            '그린' : '0',
            '퍼플' : '0',
            '그레이' : '0',
            '블루' : '0',
            '블랙' : '0',
            '실버' : '0',
            '방수' : '0',
            '제품재질' : '0',
            '잠금장치' : '0',
            '보조전원' : '0'
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
            'img_cnt' : '0',
            '등록년월' : '0',
            '제조회사' : '0',
            '제품분류' : '0',
            '상세분류' : '0',
            '폼팩터' : '0',
            '인터페이스' : '0',
            '프로토콜' : '0',
            '용량' : '0',
            '메모리 타입' : '0',
            '낸드 구조' : '0',
            'RAM 유무' : '0',
            'RAM 타입' : '0',
            '컨트롤러' : '0',
            '콘솔게임기 호환' : '0',
            '공정' : '0',
            '크기 변환' : '0',
            '인터페이스 변환' : '0',
            '순차읽기' : '0',
            '순차쓰기' : '0',
            '읽기IOPS' : '0',
            '쓰기IOPS' : '0',
            'TRIM' : '0',
            'GC' : '0',
            'SLC캐싱' : '0',
            'S.M.A.R.T' : '0',
            'ECC' : '0',
            'DEVSLP' : '0',
            'AES 암호화' : '0',
            '전용 S/W' : '0',
            'TBW' : '0',
            '맥북 업그레이드용' : '0',
            '마이그레이션' : '0',
            '관리기능' : '0',
            'MTBF' : '0',
            '5년' : '0',
            '3년' : '0',
            '데이터 복구 3년' : '0',
            '데이터 복구 1년' : '0',
            '제한보증' : '0',
            'NVMe 방열판' : '0',
            '가로' : '0',
            '세로' : '0',
            '두께' : '0',
            '무게' : '0',
            '유통회사' : '0',
            '2.5형(6.4cm)' : '0',
            '3.5형(8.9cm)' : '0',
            'Mini SATA(mSATA)' : '0',
            '보관(장착) 개수' : '0',
            'M.2 (2230)' : '0',
            'M.2 (2242)' : '0',
            'M.2 (2260)' : '0',
            'M.2 (2280)' : '0',
            'M.2 (22110)' : '0',
            '지원 두께' : '0',
            '용도 구분' : '0',
            '전원공급' : '0',
            '충격보호' : '0',
            '화이트' : '0',
            '옐로우' : '0',
            '그린' : '0',
            '퍼플' : '0',
            '그레이' : '0',
            '블루' : '0',
            '블랙' : '0',
            '실버' : '0',
            '방수' : '0',
            '제품재질' : '0',
            '잠금장치' : '0',
            '보조전원' : '0'
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
                    opener.retrieve("https://" + img.attrib['src'][2:].split('?')[0], imgName)
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
                            # print(column + " : " + value.strip())
                            # print('------------------------')
                            self.data_dict[column.strip()] = value.strip()
                            # specArr.append(value.strip())
            
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