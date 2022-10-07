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

IMG_PATH="img/mb"
CRAWLING_DATA_CSV_FILE="MBoard.csv"
OUTPUT_FILE="mb_info"


STR_URL = 'url'
STR_ID = 'id'
PROD_NAME = 'name'
CHROMEDRIVER_PATH = 'chromedriver.exe'

LOG_FILE = open('mb_log.txt', 'w')

class VGACrawer:
    def __init__(self):
        self.errorList = list()
        self.crawlingCategory = list()
        self.data_dict = {
            'code' : '0',
            'prod_name' : '0',
            'img_cnt' : '0',
            '제조회사' : '0',
            '등록년월' : '0',
            '제품 분류' : '0',
            'CPU 소켓' : '0',
            '세부 칩셋' : '0',
            'CPU 장착수' : '0',
            '폼팩터' : '0',
            '전원부' : '0',
            'Vcore 출력' : '0',
            '내장그래픽' : '0',
            '메모리 종류' : '0',
            '메모리 속도' : '0',
            '메모리 슬롯' : '0',
            '메모리 채널' : '0',
            '메모리 용량' : '0',
            'XMP' : '0',
            '옵테인' : '0',
            'XMP3.0' : '0',
            'VGA 연결' : '0',
            'PCIe5.0' : '0',
            'PCIe4.0' : '0',
            'PCIe3.0' : '0',
            'PCIe' : '0',
            'PCI' : '0',
            'PCIex16' : '0',
            'PCIex8' : '0',
            'PCIex4' : '0',
            'PCIex1' : '0',
            'mini-PCIe(mPCIe)' : '0',
            'CrossFire' : '0',
            'CrossFire X' : '0',
            'SLI' : '0',
            'Hybrid CF X' : '0',
            'Lucid Virtu' : '0',
            'SATA3' : '0',
            'M.2' : '0',
            'SAS' : '0',
            'U.2' : '0',
            'SATA2' : '0',
            'SATA' : '0',
            'NVMe' : '0',
            '2230' : '0',
            '2242' : '0',
            '2260' : '0',
            '2280' : '0',
            '22110' : '0',
            'SATA RAID' : '0',
            'NVMe RAID' : '0',
            'D-SUB' : '0',
            'DVI' : '0',
            'HDMI' : '0',
            'DP' : '0',
            'Type-C' : '0',
            'RJ-45' : '0',
            'S/PDIF' : '0',
            '오디오잭' : '0',
            'PS/2' : '0',
            'Type-C (5Gbps)' : '0',
            'Type-C (10Gbps)' : '0',
            'Type-C (20Gbps)' : '0',
            'Type-A (10Gbps)' : '0',
            'USB 3.2' : '0',
            'USB 3.1' : '0',
            'USB 3.0' : '0',
            'USB 2.0' : '0',
            '썬더볼트4' : '0',
            '썬더볼트3' : '0',
            'Type-C(오디오)' : '0',
            'e-SATA' : '0',
            '시리얼포트' : '0',
            '패러럴포트' : '0',
            'EEE1394' : '0',
            '유선랜 칩셋' : '0',
            '유선랜 속도' : '0',
            '무선랜 칩셋' : '0',
            '무선 LAN' : '0',
            '블루투스' : '0',
            'M.2 Key-E(모듈별매)' : '0',
            '듀얼 LAN' : '0',
            '내장사운드' : '0',
            '아날로그 출력' : '0',
            'RGB 헤더(4핀)' : '0',
            'ARGB 헤더(3핀)' : '0',
            'ARGB 헤더(6핀)' : '0',
            '시스템팬 헤더(4핀)' : '0',
            '썬더볼트4 헤더' : '0',
            '썬더볼트3 헤더' : '0',
            'USB2.0 헤더' : '0',
            'USB3.0 헤더' : '0',
            'USB3.1 헤더' : '0',
            'USB3.0 Type C 헤더' : '0',
            'USB3.1 Type C 헤더' : '0',
            'USB3.2 Type C 헤더' : '0',
            'UEFI' : '0',
            'Dr.MOS' : '0',
            'LED 라이트' : '0',
            'LED 헤더' : '0',
            'M.2 히트싱크' : '0',
            'TPM헤더' : '0',
            'AMD APU 지원' : '0',
            'LED 시스템' : '0',
            '내장형(온보드) LED' : '0',
            '후면 LED' : '0',
            '전면 LED' : '0',
            '로고 LED' : '0',
            'I/O쉴드(아머) LED' : '0',
            '히트싱크 LED' : '0',
            '유통회사' : '0'
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
            '제조회사' : '0',
            '등록년월' : '0',
            '제품 분류' : '0',
            'CPU 소켓' : '0',
            '세부 칩셋' : '0',
            'CPU 장착수' : '0',
            '폼팩터' : '0',
            '전원부' : '0',
            'Vcore 출력' : '0',
            '내장그래픽' : '0',
            '메모리 종류' : '0',
            '메모리 속도' : '0',
            '메모리 슬롯' : '0',
            '메모리 채널' : '0',
            '메모리 용량' : '0',
            'XMP' : '0',
            '옵테인' : '0',
            'XMP3.0' : '0',
            'VGA 연결' : '0',
            'PCIe5.0' : '0',
            'PCIe4.0' : '0',
            'PCIe3.0' : '0',
            'PCIe' : '0',
            'PCI' : '0',
            'PCIex16' : '0',
            'PCIex8' : '0',
            'PCIex4' : '0',
            'PCIex1' : '0',
            'mini-PCIe(mPCIe)' : '0',
            'CrossFire' : '0',
            'CrossFire X' : '0',
            'SLI' : '0',
            'Hybrid CF X' : '0',
            'Lucid Virtu' : '0',
            'SATA3' : '0',
            'M.2' : '0',
            'SAS' : '0',
            'U.2' : '0',
            'SATA2' : '0',
            'SATA' : '0',
            'NVMe' : '0',
            '2230' : '0',
            '2242' : '0',
            '2260' : '0',
            '2280' : '0',
            '22110' : '0',
            'SATA RAID' : '0',
            'NVMe RAID' : '0',
            'D-SUB' : '0',
            'DVI' : '0',
            'HDMI' : '0',
            'DP' : '0',
            'Type-C' : '0',
            'RJ-45' : '0',
            'S/PDIF' : '0',
            '오디오잭' : '0',
            'PS/2' : '0',
            'Type-C (5Gbps)' : '0',
            'Type-C (10Gbps)' : '0',
            'Type-C (20Gbps)' : '0',
            'Type-A (10Gbps)' : '0',
            'USB 3.2' : '0',
            'USB 3.1' : '0',
            'USB 3.0' : '0',
            'USB 2.0' : '0',
            '썬더볼트4' : '0',
            '썬더볼트3' : '0',
            'Type-C(오디오)' : '0',
            'e-SATA' : '0',
            '시리얼포트' : '0',
            '패러럴포트' : '0',
            'EEE1394' : '0',
            '유선랜 칩셋' : '0',
            '유선랜 속도' : '0',
            '무선랜 칩셋' : '0',
            '무선 LAN' : '0',
            '블루투스' : '0',
            'M.2 Key-E(모듈별매)' : '0',
            '듀얼 LAN' : '0',
            '내장사운드' : '0',
            '아날로그 출력' : '0',
            'RGB 헤더(4핀)' : '0',
            'ARGB 헤더(3핀)' : '0',
            'ARGB 헤더(6핀)' : '0',
            '시스템팬 헤더(4핀)' : '0',
            '썬더볼트4 헤더' : '0',
            '썬더볼트3 헤더' : '0',
            'USB2.0 헤더' : '0',
            'USB3.0 헤더' : '0',
            'USB3.1 헤더' : '0',
            'USB3.0 Type C 헤더' : '0',
            'USB3.1 Type C 헤더' : '0',
            'USB3.2 Type C 헤더' : '0',
            'UEFI' : '0',
            'Dr.MOS' : '0',
            'LED 라이트' : '0',
            'LED 헤더' : '0',
            'M.2 히트싱크' : '0',
            'TPM헤더' : '0',
            'AMD APU 지원' : '0',
            'LED 시스템' : '0',
            '내장형(온보드) LED' : '0',
            '후면 LED' : '0',
            '전면 LED' : '0',
            '로고 LED' : '0',
            'I/O쉴드(아머) LED' : '0',
            '히트싱크 LED' : '0',
            '유통회사' : '0'
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