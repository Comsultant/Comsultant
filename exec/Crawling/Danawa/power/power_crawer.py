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

IMG_PATH="img/power"
CRAWLING_DATA_CSV_FILE="POWER.csv"
OUTPUT_FILE="power_info"


STR_URL = 'url'
STR_ID = 'id'
PROD_NAME = 'name'
CHROMEDRIVER_PATH = 'chromedriver.exe'

LOG_FILE = open('power_log.txt', 'w')

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
            '정격출력' : '0',
            '80PLUS인증' : '0',
            'ETA인증' : '0',
            'LAMBDA인증' : '0',
            '전압변동' : '0',
            '+12V 출력방식' : '0',
            '+12V 가용률' : '0',
            'PFC회로' : '0',
            'PF(역률)' : '0',
            '쿨링팬 크기' : '0',
            '쿨링팬 개수' : '0',
            '베어링' : '0',
            '깊이' : '0',
            'A/S 보증기간' : '0',
            '+3.3V 출력' : '0',
            '+5V 출력' : '0',
            '+12V 출력' : '0',
            '-12V 출력' : '0',
            '+5Vsb 출력' : '0',
            '케이블연결' : '0',
            '메인전원' : '0',
            '보조전원' : '0',
            'PCIe 8핀(6+2)' : '0',
            'PCIe 6핀' : '0',
            'SATA' : '0',
            'SATA3.3' : '0',
            'IDE 4핀' : '0',
            'FDD' : '0',
            '팬리스모드' : '0',
            '자동 팬 조절' : '0',
            '수동 팬 조절' : '0',
            '대기전력 1W 미만' : '0',
            '플랫케이블' : '0',
            '프리볼트' : '0',
            'LED 라이트' : '0',
            '디지털제어' : '0',
            '85도 콘덴서' : '0',
            '105도 콘덴서' : '0',
            'DC to DC 설계' : '0',
            'LLC공진형컨버터' : '0',
            '과전압(OVP)' : '0',
            '저전압(UVP)' : '0',
            '과전류(OCP)' : '0',
            '과부하(OLP)' : '0',
            '과전력(OPP)' : '0',
            '과열(OTP/OHP)' : '0',
            '단락(SCP)' : '0',
            '서지+인러쉬(SIP)' : '0',
            '무부하(NLP)' : '0',
            '공회전(NLO)' : '0',
            'AURA SYNC' : '0',
            'MYSTIC LIGHT' : '0',
            'RGB FUSION' : '0',
            'POLYCHROME' : '0',
            'RAZER CHROMA' : '0',
            'TT RGB PLUS' : '0',
            '경보음' : '0',
            'LED' : '0',
            '출력 전압' : '0',
            '출력 용량 (W)' : '0',
            '출력 용량(VA)' : '0',
            '허용 입력 전압' : '0',
            '입력 주파수' : '0',
            '일반적 충전시간' : '0',
            '100%부하' : '0',
            '50% 부하' : '0',
            '폭' : '0',
            '높이' : '0',
            '무게' : '0'
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
            '정격출력' : '0',
            '80PLUS인증' : '0',
            'ETA인증' : '0',
            'LAMBDA인증' : '0',
            '전압변동' : '0',
            '+12V 출력방식' : '0',
            '+12V 가용률' : '0',
            'PFC회로' : '0',
            'PF(역률)' : '0',
            '쿨링팬 크기' : '0',
            '쿨링팬 개수' : '0',
            '베어링' : '0',
            '깊이' : '0',
            'A/S 보증기간' : '0',
            '+3.3V 출력' : '0',
            '+5V 출력' : '0',
            '+12V 출력' : '0',
            '-12V 출력' : '0',
            '+5Vsb 출력' : '0',
            '케이블연결' : '0',
            '메인전원' : '0',
            '보조전원' : '0',
            'PCIe 8핀(6+2)' : '0',
            'PCIe 6핀' : '0',
            'SATA' : '0',
            'SATA3.3' : '0',
            'IDE 4핀' : '0',
            'FDD' : '0',
            '팬리스모드' : '0',
            '자동 팬 조절' : '0',
            '수동 팬 조절' : '0',
            '대기전력 1W 미만' : '0',
            '플랫케이블' : '0',
            '프리볼트' : '0',
            'LED 라이트' : '0',
            '디지털제어' : '0',
            '85도 콘덴서' : '0',
            '105도 콘덴서' : '0',
            'DC to DC 설계' : '0',
            'LLC공진형컨버터' : '0',
            '과전압(OVP)' : '0',
            '저전압(UVP)' : '0',
            '과전류(OCP)' : '0',
            '과부하(OLP)' : '0',
            '과전력(OPP)' : '0',
            '과열(OTP/OHP)' : '0',
            '단락(SCP)' : '0',
            '서지+인러쉬(SIP)' : '0',
            '무부하(NLP)' : '0',
            '공회전(NLO)' : '0',
            'AURA SYNC' : '0',
            'MYSTIC LIGHT' : '0',
            'RGB FUSION' : '0',
            'POLYCHROME' : '0',
            'RAZER CHROMA' : '0',
            'TT RGB PLUS' : '0',
            '경보음' : '0',
            'LED' : '0',
            '출력 전압' : '0',
            '출력 용량 (W)' : '0',
            '출력 용량(VA)' : '0',
            '허용 입력 전압' : '0',
            '입력 주파수' : '0',
            '일반적 충전시간' : '0',
            '100%부하' : '0',
            '50% 부하' : '0',
            '폭' : '0',
            '높이' : '0',
            '무게' : '0'
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