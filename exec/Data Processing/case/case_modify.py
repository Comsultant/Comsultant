import csv
import os

CSV_FILE = 'case_info.csv'
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

f = open(f'detail_case.csv', 'w', newline='', encoding='utf8')
wr = csv.writer(f)
# wr.writerow(list(col_name.keys()))
# if d in col_name.keys():
#     return d



def getImgCnt(path):
    dirListing = os.listdir(path)
    return len(dirListing)




def maker(d):
    if d.find('CORSAIR') != -1:
        return 'CORSAIR'
    elif d.find('GIGABYTE') != -1:
        return 'GIGABYTE'
    elif d.find('SilverStone') != -1:
        return 'SilverStone'
    elif d.find('앱코') != -1:
        return '앱코'
    elif d.find('이지넷유비쿼터스') != -1:
        return '이지넷유비쿼터스'
    elif d.find('쿨러마스터') != -1:
        return '쿨러마스터'
    elif d.find('투렉스') != -1:
        return '투렉스'
    else:
        return d


def includePower(d):
    if d == 'DC to DC 120W':
        return 'DC to DC'
    elif d == '500W 이상':
        return '500W'
    else:
        return d

def supportPower(d):
    if d == 'FLEX':
        return 'Flex-ATX 파워'
    elif d == '표준-ATX':
        return 'ATX 파워'
    elif d == 'M-ATX(SFX)':
        return 'M-ATX(SFX) 파워'
    elif d == 'TFX':
        return 'TFX 파워'
    else:
        return d

def bay13dot3(d):
    if d == '3개 이하':
        return '3개'

    if d == '4':
        d = '4개'
    d = d.replace('최대', '').strip()
    return d

def bay8dot9(d):
    d = d.replace('최대', '')
    d = d.replace('개', '')
    d = d.replace('이상', '')


    return int(d.strip())

def pciSlot(d):
    if d == '7+2개':
        return '7개+2개'
    elif d == '7+2':
        return '7개+2개'
    elif d == '10개 +2개':
        return '10개+2개'
    elif d == '8개 + 2개':
        return '8개+2개'
    elif d == '7개 +2개':
        return '7개+2개'
    elif d == '8개 이상':
        return '8개'
    elif d == '8개 +2개':
        return '8개+2개'
    elif d == '4개 이하':
        return '4개'
    elif d.find('+') != -1:
        d = d[0] + '개' + d[1:]
        return d
    else:
        return d


def coolingFanCnt(d):
    if d == '0':
        return 0

    d = d[1:].replace('개', '')
    return int(d.strip())


def ledFanCnt(d):
    if d == '2개 동봉':
        return 2
    else:
        return int(d[0])


def width(d):
    if d == '221mm~':
        return 221.0
    elif d == '141~180mm':
        return 141.0
    elif d == '181~220mm':
        return 181.0
    elif d == '~100mm':
        return 100.0
    elif d == '45mm이하':
        return 45.0
    else:
        d = d.replace('mm', '')
        return float(d.strip())



def depth(d):
    if d == '440mm이하':
        return 440.0
    elif d == '390mm이하':
        return 390.0
    elif d == '473mm 이하':
        return 473.0
    elif d == '360mm이하':
        return 360.0
    elif d == '560mm 이상':
        return 560.0
    else:
        d = d.replace('mm', '')
        return float(d.strip())

def height(d):
    if d == '210mm, 확장 290mm':
        return 210.0
    else:
        d = d.replace('mm', '')
        return float(d.strip()) 


def possiblePower(d):
    d = d.replace('최대', '')
    d = d.replace('이상', '')
    d = d.replace('mm', '')
    d = d.strip()

    if d.find('~') != -1:
        d = d[:d.find('~')]
        return int(d)
    else:
        return int(d)

def caseVga(d):
    if d == '기본 281 mm /  최대 400mm':
            return 400
    elif d == '기본 290 mm / 최대 380mm':
        return 380

    d = d.replace('mm', '')
    d = d.replace('mmmm', '')
    d = d.replace('최대', '').strip()

    if d.find('~') != -1:
        d = d[:d.find('~')]
        return int(d)
    else:
        return int(d)

def caseCpuCooler(d):
    if d == '158.5mm':
        return 158

    d = d.replace('mm', '')
    d = d.replace('m', '')
    d = d.replace('이하', '')
    d = d.replace('최대', '').strip()
    return int(d)


def radiTop(d):
    if d == '최대240mm (우측면)':
        return '240mm'
    elif d == '최대420mm, 360mm (180mm)':
        return '420mm, 360mm, 180mm'
    elif d == '최대480mm x2, 420mm x2':
        return '480mm, 420mm'
    else:
        d = d.replace('최대', '')
        return d.strip()

def radiFront(d):
    if d == '최대480mm x2':
        return '180mm'
    elif d == '최대360mm, 280mm (측면)':
        return '360mm, 280mm'
    elif d == '최대280mm, 240mm (측면)':
        return '280mm, 240mm'
    elif d == '최대240mm (좌측면)':
        return '240mm'
    elif d == '최대420mm, 360mm (좌측면)':
        return '420mm, 360mm'
    elif d == '최대560mm, 480mm (좌/우측면)':
        return '560mm, 480mm'
    else:
        d = d.replace('최대', '')
        return d.strip()

def radiBack(d):
    if d == '최대280mm, 240mm (하단)':
        return '280mm, 240mm'
    elif d == '최대120mm (100/90mm)':
        return '120mm'
    else:
        d = d.replace('최대', '')
        return d.strip()

def powerPosition(d):
    if d == '하단후면':
        return '하단 후면'
    elif d == '하단전면':
        return '하단 전면'
    elif d == '상단전면':
        return '상단 전면'
    elif d == '후면상단':
        return '후면 상단'
    elif d == '우측후면':
        return '우측 후면'
    elif d == '상단후면':
        return '상단 후면'
    else:
        return d
first = True
for r in datas:
    if first:       
        wr.writerow(r)
        first = False
    else:
        
        r[3] = maker(r[3])

        r[8] = includePower(r[8])

        r[9] = supportPower(r[9])

        r[19]= bay13dot3(r[19])

        r[20] = bay8dot9(r[20])

        r[21] = bay8dot9(r[21])
        r[22] = bay8dot9(r[22])
        r[23] = pciSlot(r[23])

        r[25] = coolingFanCnt(r[25])
        r[26] = ledFanCnt(r[26])

        r[27] = '쿨링팬' if r[27] == '쿨링팬 (120mm x2)' else r[27]

        r[45] = width(r[45])
        r[46] = depth(r[46])
        r[47] = height(r[47])

        r[48] = possiblePower(r[48])
        r[49] = powerPosition(r[49])
        r[50] = caseVga(r[50])
        r[51] = caseCpuCooler(r[51])

        r[52] = r[52][2] if r[52] != '0' else r[52]

        r[53] = radiTop(r[53])
        r[54] = radiFront(r[54])
        r[55] = radiBack(r[55])
        
        wr.writerow(r)

f.close()