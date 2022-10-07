import csv
import os

CSV_FILE = 'cpu_info.csv'
cpuCategory = {}
col_name = {
    'code': '0', 
    'prod_name' : '0',
    '제조회사': '0',
    '등록년월': '0',
    '인텔 CPU종류': '0',
    'AMD CPU종류' : '0',
    '세대 구분': '0',
    '소켓 구분': '0',
    '제조 공정': '0',
    '출시일' : '0',
    '코어 수' : '0',
    '쓰레드 수': '0',
    '기본 클럭': '0',
    '최대 클럭': '0',
    'L2 캐시' : '0',
    'L3 캐시' : '0',
    '연산 체계' : '0',
    '버스 속도' : '0',
    'TDP' : '0',
    'PCIe 버전' : '0',
    '최대 PCIe 레인수' : '0',
    '최대 메모리 크기' : '0',
    '메모리 규격' : '0',
    '메모리 클럭' : '0',
    '메모리 채널' : '0',
    '내장그래픽' : '0',
    'GPU 모델명' : '0',
    'GPU 코어 속도' : '0',
    '하이퍼스레딩' : '0',
    '옵테인' : '0',
    'StoreMI' : '0',
    'SenseMI' : '0',
    'Ryzen Master' : '0',
    'VR Ready 프리미엄' : '0',
    '3D V캐시' : '0',
    '패키지 형태' : '0',
    '쿨러' : '0'
}
datas = []
with open(CSV_FILE, 'r', newline='', encoding='utf8') as file:
    for crawlingValues in csv.reader(file, skipinitialspace=True):
        tmp = []
        for v in crawlingValues:
            tmp.append(v)
        datas.append(tmp)
        
        # if crawlingValues[col_num] in cpuCategory.keys():
        #     cpuCategory[crawlingValues[col_num]] += 1;
        # else:
        #     cpuCategory[crawlingValues[col_num]]=1;

f = open(f'detail_cpu.csv', 'w', newline='', encoding='utf8')
wr = csv.writer(f)
# wr.writerow(list(col_name.keys()))
# if d in col_name.keys():
#     return d

def maker(d):
    if d[0] =='A':
        return 'AMD'
    else:
        return '인텔'


def intelType(d):
    if d == '인텔(제온 실버)':
        return '제온 실버'
    elif d == '인텔(제온 골드)':
        return '제온 골드'
    elif d == '인텔(제온 브론즈)':
        return '제온 브론즈'
    elif d == '인텔(펜티엄 골드)':
        return '펜티엄 골드'
    elif d == '인텔(제온  E)':
        return '제온 E'
    elif d == '인텔(셀러론)':
        return '셀러론'
    elif d == '인텔(제온 E)':
        return '제온 E'
    elif d == '인텔(제온)':
        return '제온'
    elif d == '인텔(코어2듀오)':
        return '코어2듀오'
    elif d == '인텔(코어2쿼드)':
        return '코어2쿼드'
    elif d == '인텔(코어X-시리즈)':
        return '코어X-시리즈'
    elif d == '인텔(코어i3)':
        return '코어i3'
    elif d == '인텔(코어i3-2세대)':
        return '코어i3-2세대'
    elif d == '인텔(코어i3-3세대)':
        return '코어i3-3세대'
    elif d == '인텔(코어i3-4세대)':
        return '코어i3-4세대'
    elif d == '인텔(코어i3-6세대)':
        return '코어i3-6세대'
    elif d == '인텔(코어i3-7세대)':
        return '코어i3-7세대'
    elif d == '인텔(코어i3-8세대)':
        return '코어i3-8세대'
    elif d == '인텔(코어i3-9세대)':
        return '코어i3-9세대'
    elif d == '인텔(코어i5)':
        return '코어i5'
    elif d == '인텔(코어i5-2세대)':
        return '코어i5-2세대'
    elif d == '인텔(코어i5-3세대)':
        return '코어i5-3세대'
    elif d == '인텔(코어i5-4세대)':
        return '코어i5-4세대'
    elif d == '인텔(코어i5-6세대)':
        return '코어i5-6세대'
    elif d == '인텔(코어i5-7세대)':
        return '코어i5-7세대'
    elif d == '인텔(코어i5-8세대)':
        return '코어i5-8세대'
    elif d == '인텔(코어i5-9세대)':
        return '코어i5-9세대'
    elif d == '인텔(코어i7)':
        return '코어 i7'
    elif d == '인텔(코어i7-2세대)':
        return '코어i7-2세대'
    elif d == '인텔(코어i7-3세대)':
        return '코어i7-3세대'
    elif d == '인텔(코어i7-4세대)':
        return '코어i7-4세대'
    elif d == '인텔(코어i7-8세대)':
        return '코어i7-8세대'
    elif d == '인텔(코어i7-9세대)':
        return '코어i7-9세대'
    elif d == '인텔(코어i9-9세대)':
        return '코어i9-9세대'
    elif d == '인텔(펜티엄)':
        return '펜티엄'
    elif d == '인텔(펜티엄 골드)':
        return '펜티엄 골드'
    else:
        return d

def launchDate(d):
    if d == '2014 하반기':
        return '2014 3분기'
    elif d == '2018 1분기 (2018-02-12)':
        return '2018 1분기'
    elif d == '2018년 2분기':
        return '2018 2분기'
    else:
        return d

def cpuCore(d):
    if d == '듀얼 코어':
        return '2코어'
    elif d == '옥타(8) 코어':
        return '8코어'
    elif d == '헥사(6) 코어':
        return '6코어'
    elif d == '트리플 코어':
        return '3코어'
    else:
        return d


def defaultClock(d):
    if d == '2.5 ~ 2.99GHz':
        return 2.5
    else:
        d = d.replace('GHz', '')
        return float(d.strip())

def maxClock(d):
    d = d.replace('GHz', '')
    return float(d.strip())


def TDP(d):
    if d == '60~89W':
        return 60
    elif d == '65~117W':
        return 65
    elif d == '125~150W':
        return 125
    elif d == '65~180W':
        return 65
    elif d == '125~190W':
        return 125
    elif d == '65~202W':
        return 65
    elif d == '125~241W':
        return 125
    elif d == '150~241W':
        return 150
    else:
        d = d.replace('W', '')
        return int(d)

def maxTDP(d):
    if d == '60~89W':
        return 89
    elif d == '65~117W':
        return 117
    elif d == '125~150W':
        return 150
    elif d == '65~180W':
        return 180
    elif d == '125~190W':
        return 190
    elif d == '65~202W':
        return 202
    elif d == '125~241W':
        return 241
    elif d == '150~241W':
        return 241
    else:
        d = d.replace('W', '')
        return int(d)
    

def maxMemorySize(d):
    if d.find('TB') != -1:
        return int(d.replace('TB', '')) * 1024
    elif d.find('GB') != -1:
        return int(d.replace('GB', ''))
    else:
        return d


def memoryClock(d):
    if d == '최대 1866MHz':
        return '1866MHz'
    elif d == '최대 2400MHz':
        return '2400MHz'
    else:
        return d

def gpuCoreSpeed(d):

    if d == '최대 1,100MHz':
        return 1100
    elif d == '최대 1,200MHz':
        return 1200
    elif d == '최대 1,250MHz':
        return 1250
    else:
        d = d.replace(',', '')
        d = d.replace('MHz', '')
        return int(d.strip())



def getImgCnt(path):
    dirListing = os.listdir(path)
    return len(dirListing)



first = True
for r in datas:
    if first:
        r.insert(19, 'maxTDP')
        r.insert(2, 'img_cnt')
        wr.writerow(r)
        first = False
    else:
        
        #제조회사
        r[2] = maker(r[2])

        #인텔 CPU종류
        r[4] = intelType(r[4])

        #출시일
        r[8] = launchDate(r[8])

        #제조공정
        r[9] = '10nm' if r[9] == '10nm(인텔7)' else r[9]

        #코어 수
        r[10] = cpuCore(r[10])

        #기본 클럭
        r[12] = defaultClock(r[12])

        #최대 클럭
        r[13] = maxClock(r[13])

        #TDP
        tmpTdp = r[18]
        r[18] = TDP(r[18])

        
        #최대 메모리 크기
        r[21] = maxMemorySize(r[21])

        #메모리 클럭
        r[23] = memoryClock(r[23])
        
        #메모리 채널
        r[24] = int(r[24])

        #GPU 코어 속도
        r[27] = gpuCoreSpeed(r[27])

        #최대 TDP
        r.insert(19, maxTDP(tmpTdp))
        r.insert(2, getImgCnt('../../img/cpu/' + r[0]))
        

        wr.writerow(r)



f.close()