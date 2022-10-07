import csv
import os

CSV_FILE = 'power_info.csv'
cpuCategory = {}

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

f = open(f'detail_power.csv', 'w', newline='', encoding='utf8')
wr = csv.writer(f)
# wr.writerow(list(col_name.keys()))
# if d in col_name.keys():
#     return d



def getImgCnt(path):
    dirListing = os.listdir(path)
    return len(dirListing)


def maker(d):
    if d == 'CORSAIR PROFESSIONAL SERIES':
        return 'CORSAIR'
    elif d == 'GIGABYTE AORUS':
        return 'GIGABYTE'
    elif d == 'SilverStone Essential':
        return 'SilverStone'
    elif d == 'SilverStone Strider':
        return 'SilverStone'
    elif d == '앱코 SUITMASTER':
        return '앱코'
    elif d == '이도디스플레이 ICE-MAN':
        return '이도디스플레이'
    elif d == '이도디스플레이 YIDO':
        return '이도디스플레이'
    elif d == '제이씨현 UDEA':
        return '제이씨현'
    else:
        return d


def ratedPower(d):
    if d == '표기 1100W':
        return 1100
    elif d == '500W~599W':
        return 500
    elif d == '1000W 이상':
        return 1000
    else:
        d = d.replace('W', '')
        return int(d.strip())


def volt12Output(d):
    if d == '91.2%':
        return 91
    else:
        return int(d.replace('%', '').strip())

def powerFactor(d):
    return int(d.replace('%', '').strip())

def fanSize(d):
    if d == '135mm LED 팬':
        return 135
    elif d == '140mm RGB 팬':
        return 140
    else:
        d = d.replace('mm', '')
        d = d.replace('팬', '')
        return int(d.strip())


def fanCount(d):
    if d == '무팬(fanless)':
        return 0
    else:
        d = d.replace('개(팬)', '')
        return int(d.strip())

def depth(d):
    if d == '49.7~64.7 cm':
        return 64.7
    if d == '715cm':
        return 71.5
    else:
        if d.find('cm') != -1:
            d = d.replace('cm', '')
            return float(d.strip())
        elif d.find('mm') != -1:
            d = d.replace('mm', '')
            d = float(d.strip()) / 10.0
            return d
        else:
            return d

def volt5Output(d):
    d = d.replace('A', '')
    return float(d.strip())

def sata(d):
    if d == "SATA x7":
        return 7
    else:
        d = d.replace('개', '')
        return int(d.strip())


def ide4pin(d):
    if d == '4핀 IDE x5':
        return 5
    else:
        d = d.replace('개', '')
        return int(d.strip())

def outputCap(d):
    d = d.replace('W', '')
    return int(d.strip())


def outputCapVA(d):
    d = d.replace('VA', '')
    return int(d.strip())

def width(d):
    d = d.replace('cm', '')
    return float(d.strip())

def height(d):
    if d == '8.62~13.07 cm':
        return 13.07
    elif d == '21 6. cm':
        return 21.6
    else:
        d = d.replace('cm', '')
        return float(d.strip())

def weight(d):
    if d == '37.33~38.08 kg':
        return 38.08
    elif d == '43.64 cm':
        return 43.64
    else:
        d = d.replace('kg', '')
        return float(d.strip())

def PCIe8(d):
    d = d.replace('개', '')
    return int(d.strip())


def subPower4(d):
    if d == '4핀 1개':
        return 1
    elif d == '8핀(4+4) 1개':
        return 2
    elif d == '8+4핀 1개':
        return 1
    elif d == '8+4+4핀 1개':
        return 2
    elif d == '8핀(4+4) 2개':
        return 4
    elif d == '8핀(4+4) 3개':
        return 6
    elif d == '8+4+4핀 2개':
        return 4
    elif d == '8핀(4+4) 4개':
        return 8
    elif d == '8+4+4+4+4핀 1개':
        return 4
    elif d == '8(4+4)+4핀 1개':
        return 3
    elif d == '8+4+4+4핀 1개':
        return 3
    else:
        return 0

def subPower8(d):
    if d == '8핀(4+4) 1개':
        return 0
    elif d == '8+4핀 1개':
        return 1
    elif d == '8+4+4핀 1개':
        return 1
    elif d == '8핀(4+4) 2개':
        return 0
    elif d == '8핀(4+4) 3개':
        return 0
    elif d == '8+4+4핀 2개':
        return 2
    elif d == '8핀(4+4) 4개':
        return 0
    elif d == '8+4+4+4+4핀 1개':
        return 1
    elif d == '8(4+4)+4핀 1개':
        return 0
    elif d == '8+4+4+4핀 1개':
        return 1
    elif d == '8+8핀 1개':
        return 2
    else:
        return 0

first = True
for r in datas:
    if first:
        # r.insert(2, 'img_cnt')
        r[27] = '보조전원(4핀)'
        r.insert(28, '보조전원(8핀)')
        wr.writerow(r)
        first = False
    else:
        
        # 테스트용으로 컬럼 값 1씩 밀어놓음
        # r.insert(2, 0)
        # r.insert(2, getImgCnt('../../img/power/' + r[0]))

        #제조회사
        r[3] = maker(r[3])

        #정격출력
        r[6] = ratedPower(r[6])

        #+12V 가용률
        r[12] = volt12Output(r[12])

        #PF(역률)
        r[14] = powerFactor(r[14])

        #쿨링팬 크기
        r[15] = fanSize(r[15])

        #쿨링팬 개수
        r[16] = fanCount(r[16])

        #깊이
        r[18] = depth(r[18])

        #5V 출력
        r[21] = volt5Output(r[21])

        #보조전원
        tmpPower = r[27]
        r[27] = subPower4(r[27])

        #PCIe 8핀(6+2)
        r[28] = PCIe8(r[28])

        #SATA
        r[30] = sata(r[30])

        #SATA3.3
        r[31] = sata(r[31])

        #IDE 4핀
        r[32] = ide4pin(r[32])

        #FDD
        r[33] = sata(r[33])

        #출력 용량 (W)
        r[65] = outputCap(r[65])

        r[66] = outputCapVA(r[66])

        #폭
        r[72] = width(r[72])

        #높이
        r[73] = height(r[73])

        #무게
        r[74] = weight(r[74])

        r.insert(28, subPower8(tmpPower))

        wr.writerow(r)



f.close()