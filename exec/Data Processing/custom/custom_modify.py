import csv
import os

CSV_FILE = 'custom_info.csv'
cpuCategory = {}
col_name = {
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

f = open(f'detail_custom.csv', 'w', newline='', encoding='utf8')
wr = csv.writer(f)
# wr.writerow(list(col_name.keys()))
    # if d in col_name.keys():
    #     return d

def maker(d):
    if d == 'CORSAIR HYDRO SERIES':
        return 'CORSAIR'
    elif d == 'DEEPCOOL GAMER STORM':
        return 'DEEPCOOL '
    elif d == 'EVERCOOL COOLMARKER':
        return 'EVERCOOL'
    elif d == '라이트컴 COMS':
        return '라이트컴'
    elif d == '비에스컴퓨터 펀쿨러':
        return '비에스컴퓨터'
    elif d == '앱코 SUITMASTER':
        return '앱코'
    elif d == '엠스톤글로벌 mStone':
        return '엠스톤글로벌'
    else:
        return d


def prodType(c, d):
    if c == '수랭' or c == '공랭':
        return 'CPU쿨러'
    else:
        return d

def thermalConductivity(d):
    d = d.replace('W/mk', '').strip()
    return float(d)

def modifyTDP(d):
    if d == 'TDP 95W ~ TDP 129W':
        return 129
    elif d == 'TDP 130W 이상':
        return 130
    else:
        d = d.replace('TDP', '')
        d = d.replace('W', '')
        return int(d.strip())


def coolerHeight(d):
    d = d.replace('높이', '')
    d = d.replace('mmmm', '')
    d = d.replace('mm', '')
    return float(d.strip())

def fanSize(d):
    if d == '120mm x1':
        return 120
    elif d == '120mm x2':
        return 120
    elif d == '120mm x3':
        return 120
    elif d == '120mm x 2':
        return 120
    elif d == '120mm x 4':
        return 120
    elif d == '120mm x 6':
        return 120
    elif d == '140mm x2':
        return 140
    elif d == '140mm x3':
        return 140
    elif d == '2 x 92mm':
        return 92
    elif d == '135mm x1':
        return 153
    elif d == '2 x 140mm':
        return 140
    elif d == '140mm x 2':
        return 140
    elif d == '130mm x1':
        return 130
    elif d == '120mm x 2, 70mm':
        return 120
    elif d == '140mm x 2, 70mm':
        return 140
    elif d == '140mm, 120mm':
        return 140
    elif d == '120mm x1, 140mm x1':
        return 140
    elif d == '135mm, 120mm':
        return 135
    else:
        d = d.replace('mmmm', '')
        d = d.replace('mm', '')
        return float(d.strip())

def maxFanSpeed(d):
    d = d.replace('RPM', '')
    d = d.replace('최대', '')
    d = d.replace('이하', '')
    d = d.replace('이상', '')
    return int(d)

def maxNoise(d):
    d = d.replace('최대', '')
    d = d.replace('이하', '')
    d = d.replace('이상', '')
    d = d.replace('dBA', '')
    d = d.replace('BA', '')

    return float(d)

def getImgCnt(path):
    dirListing = os.listdir(path)
    return len(dirListing)


first = True
for r in datas:
    if first:
        r.insert(2, 'img_cnt')
        wr.writerow(r)
        first = False
    else:
        
        #제조회사
        r[2] = maker(r[2])

        #제품종류
        r[6] = prodType(r[4], r[6])

        #열 전도율
        r[8] = thermalConductivity(r[8])

        #TDP
        r[41] = modifyTDP(r[41])

        #쿨러높이
        r[45] = coolerHeight(r[45])

        #펜크기
        r[47] = fanSize(r[47])
        
        #최대 팬속도
        r[51] = maxFanSpeed(r[51])

        #최대 소음
        r[54] = maxNoise(r[54])
        r.insert(2, getImgCnt('../../img/cooler/' + r[0]))

        wr.writerow(r)



f.close()