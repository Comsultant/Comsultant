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

IMG_PATH="img/vga"
CRAWLING_DATA_CSV_FILE="VGA.csv"
OUTPUT_FILE="vga_info"


STR_URL = 'url'
STR_ID = 'id'
PROD_NAME = 'name'
CHROMEDRIVER_PATH = 'chromedriver.exe'

LOG_FILE = open('vga_log.txt', 'w')

class VGACrawer:
    def __init__(self):
        self.errorList = list()
        self.crawlingCategory = list()
        self.data_dict = {
            'code': '0', 
            'prod_name' : '0',
            'img_cnt' : '0',
            '제조회사' : '0',
            '등록년월' : '0',
            '칩셋 제조사' : '0',
            '제품 시리즈' : '0',
            'GPU 제조 공정' : '0',
            'NVIDIA 칩셋' : '0',
            'AMD 칩셋' : '0',
            '베이스클럭' : '0',
            '부스트클럭' : '0',
            '스트림 프로세서' : '0',
            '쿠다 프로세서' : '0',
            '인터페이스' : '0',
            '기타 칩셋' : '0',
            '메모리 종류' : '0',
            '메모리 클럭' : '0',
            '메모리 용량' : '0',
            '메모리 버스' : '0',
            'HDMI' : '0',
            'DVI' : '0',
            'DisplayPort' : '0',
            '모니터 지원' : '0',
            'HDMI2.1' : '0',
            'HDMI2.0' : '0',
            'DP1.4' : '0',
            'DP' : '0',
            'mini DP1.4' : '0',
            'DVI(듀얼링크)' : '0',
            'D-SUB' : '0',
            'USB Type-C' : '0',
            '썬더볼트3' : '0',
            'USB 3.0' : '0',
            '기가비트LAN' : '0',
            'TV-OUT' : '0',
            '제로팬(0-dB기술)' : '0',
            '8K 해상도 지원' : '0',
            '4K 해상도 지원' : '0',
            'HDR 지원' : '0',
            'Dual BIOS' : '0',
            'HDCP 2.3' : '0',
            '멀티 VGA' : '0',
            'HDCP 지원' : '0',
            '사용전력' : '0',
            '권장 파워용량' : '0',
            '전원 포트' : '0',
            '전원부' : '0',
            '방열판' : '0',
            '히트파이프' : '0',
            '팬쿨러' : '0',
            '베이퍼챔버' : '0',
            '수냉 쿨링' : '0',
            '팬 개수' : '0',
            '가로(길이)' : '0',
            '높이(두께)' : '0',
            '백플레이트' : '0',
            'Dr.MOS 모스펫' : '0',
            'LED 라이트' : '0',
            '오버클럭 물리키' : '0',
            'PWM 커넥터' : '0',
            'LCD 모니터링' : '0',
            '수냉 장착 지원' : '0',
            '전면 LED' : '0',
            '후면 LED' : '0',
            '측면 LED' : '0',
            '팬 LED' : '0',
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
        cDate = self.GetCurrentDate().strftime('%Y-%m-%d_%H:%M:%S')
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
            'code': '0', 
            'prod_name' : '0',
            'img_cnt' : '0',
            '제조회사' : '0',
            '등록년월' : '0',
            '칩셋 제조사' : '0',
            '제품 시리즈' : '0',
            'GPU 제조 공정' : '0',
            'NVIDIA 칩셋' : '0',
            'AMD 칩셋' : '0',
            '베이스클럭' : '0',
            '부스트클럭' : '0',
            '스트림 프로세서' : '0',
            '쿠다 프로세서' : '0',
            '인터페이스' : '0',
            '기타 칩셋' : '0',
            '메모리 종류' : '0',
            '메모리 클럭' : '0',
            '메모리 용량' : '0',
            '메모리 버스' : '0',
            'HDMI' : '0',
            'DVI' : '0',
            'DisplayPort' : '0',
            '모니터 지원' : '0',
            'HDMI2.1' : '0',
            'HDMI2.0' : '0',
            'DP1.4' : '0',
            'DP' : '0',
            'mini DP1.4' : '0',
            'DVI(듀얼링크)' : '0',
            'D-SUB' : '0',
            'USB Type-C' : '0',
            '썬더볼트3' : '0',
            'USB 3.0' : '0',
            '기가비트LAN' : '0',
            'TV-OUT' : '0',
            '제로팬(0-dB기술)' : '0',
            '8K 해상도 지원' : '0',
            '4K 해상도 지원' : '0',
            'HDR 지원' : '0',
            'Dual BIOS' : '0',
            'HDCP 2.3' : '0',
            '멀티 VGA' : '0',
            'HDCP 지원' : '0',
            '사용전력' : '0',
            '권장 파워용량' : '0',
            '전원 포트' : '0',
            '전원부' : '0',
            '방열판' : '0',
            '히트파이프' : '0',
            '팬쿨러' : '0',
            '베이퍼챔버' : '0',
            '수냉 쿨링' : '0',
            '팬 개수' : '0',
            '가로(길이)' : '0',
            '높이(두께)' : '0',
            '백플레이트' : '0',
            'Dr.MOS 모스펫' : '0',
            'LED 라이트' : '0',
            '오버클럭 물리키' : '0',
            'PWM 커넥터' : '0',
            'LCD 모니터링' : '0',
            '수냉 장착 지원' : '0',
            '전면 LED' : '0',
            '후면 LED' : '0',
            '측면 LED' : '0',
            '팬 LED' : '0',
            '유통회사' : '0'
        }

        self.data_dict['code'] = crawlingID
        self.data_dict['prod_name'] = categoryValue[PROD_NAME]

        # print('Crawling Start : ' + crawlingName)
        print('Crawling Start', file= LOG_FILE)
        crawlingFile = open(f'{OUTPUT_FILE}.csv', 'a', newline='', encoding='utf8')
        crawlingData_csvWriter = csv.writer(crawlingFile)

        try:
            print(crawlingURL, file=LOG_FILE)
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
                    # imgName = f'{imgPath}/{imgIdx}.jpg'
                    # opener.retrieve("https://" + img.attrib['src'][2:].split('?')[0], imgName)
                    imgIdx+=1
            #################################
            self.data_dict['img_cnt'] = imgIdx


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
            # self.erroxList.append(crawlingName)

        crawlingFile.close()

        print('Crawling Finish : ', file=LOG_FILE)
    
    def GetCurrentDate(self):
        tz = timezone(TIMEZONE)
        return (datetime.now(tz))

if __name__ == '__main__':
    crawler = VGACrawer()
    crawler.StartCrawling()
    LOG_FILE.close()