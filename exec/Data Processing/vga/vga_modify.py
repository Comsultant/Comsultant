import csv
import os

CSV_FILE = 'vga_info.csv'
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

f = open(f'detail_vga.csv', 'w', newline='', encoding='utf8')
wr = csv.writer(f)
# wr.writerow(list(col_name.keys()))
# if d in col_name.keys():
#     return d



def getImgCnt(path):
    dirListing = os.listdir(path)
    return len(dirListing)

def maker(d):
    if d == 'ASUS PH':
        return 'ASUS'
    elif d == 'GIGABYTE AORUS':
        return 'GIGABYTE'
    elif d.find('갤럭시') != -1:
        return '갤럭시'
    elif d.find('이엠텍') != -1:
        return '이엠텍'
    else:
        return d

def baseClock(d):
    if d == '1151 MHz , 1379MHz':
        return 1151
    else:
        d = d.replace('MHz', '')
        return int(d.strip())

def boostClock(d):
    d = d.replace('MHz', '')
    return int(d.strip())


def cudaProcessor(d):
    d = d.replace('개', '')
    return int(d.strip())

def memoryClock(d):
    d = d.replace('MHz', '')
    return int(d.strip())

def memoryCap(d):
    if d == '512MB':
        return 0.5
    elif d == '32MB':
        return 0.03125
    else:
        d = d.replace('GB', '')
        return float(d.strip())


def memoryBus(d):
    d = d.replace('-bit', '')
    return int(d.strip())

def displayPort(d):
    if d == 'Mini DP 2개 + DP 1개':
        return 1
    elif d == '1개':
        return 1
    elif d == '2개':
        return 2
    elif d == '3개':
        return 3
    elif d == '3개':
        return 3
    elif d == '4개':
        return 4
    elif d == '6개':
        return 6
    else:
        return 0


def miniDp(d):
    if d == 'Mini DP 2개 + DP 1개':
        return 2
    elif d == 'Mini DP 6개':
        return 6
    elif d == 'Mini DP 4개':
        return 4
    elif d == 'mini DP 4개':
        return 4
    elif d == '4개 (MINI DP)':
        return 4
    elif d == '6개(Mini DP)':
        return 6
    elif d == 'miniDP 4개':
        return 4
    elif d == 'miniDP 3개':
        return 3
    elif d == '4개(mini)':
        return 4
    elif d == 'mini DP 6개':
        return 6
    else:
        return 0

def usedPower(d):
    d = d.replace('최대', '')
    d = d.replace('W', '')
    return float(d.strip())

def requiredUsedPower(d):
    d = d.replace('정격파워 ', '')
    d = d.replace('W 이상', '')
    return int(d.strip())

def powerPort4(d):
    if d == '16(12+4)핀 x1개':
        return 1
    elif d == '16(12+4)핀 x2개':
        return 2
    else:
        return 0

def powerPort6(d):
    if d == '6핀 x1개':
        return 1
    elif d == '6핀 x1 + 8핀 x1개':
        return 1
    elif d == '8핀 x2 + 6핀 x1개':
        return 1
    else:
        return 0

def powerPort8(d):
    if d == '8핀 x1개':
        return 1
    elif d == '8핀 x2개':
        return 2
    elif d == '8핀 x3개':
        return 3
    elif d == '6핀 x1 + 8핀 x1개':
        return 1
    elif d == '8핀 x2 + 6핀 x1개':
        return 2
    else:
        return 0

def powerPort12(d):
    if d == '16(12+4)핀 x1개':
        return 1
    elif d == '16(12+4)핀 x2개':
        return 2
    else:
        return 0

def horizontalLen(d):
    d = d.replace('mm', '')
    return float(d.strip())

def verticalLen(d):
    d = d.replace('mm', '')
    return float(d.strip())

first = True
for r in datas:
    if first:
        # r.insert(2, 'img_cnt')
        r[46] = '전원 포트(4핀)'
        r.pop(35)
        r.pop(31)
        r.pop(30)
        r.pop(29)
        r.pop(28)
        r.pop(27)
        r.pop(25)
        r.pop(15)

        r.insert(22, 'miniDP')
        r.insert(40, '전원 포트(12핀)')
        r.insert(40, '전원 포트(8핀)')
        r.insert(40, '전원 포트(6핀)')

        
        
        wr.writerow(r)
        first = False
    else:
        
        # for i in range(0,68):
        #     print(r[0])
        #     r[i] = r[i]

        r[3] = maker(r[3])

        if r[10] == '1151 MHz , 1379MHz':
            r[11] = '1379MHz'

        r[10] = baseClock(r[10])
        r[11] = boostClock(r[11])

        r[13] = cudaProcessor(r[13])

        r[17] = memoryClock(r[17])

        r[18] = memoryCap(r[18])

        r[19] = memoryBus(r[19])

        r[20] = r[20][0]
        r[21] = r[21][0]

        tmpDp = r[22]
        r[22] = displayPort(r[22])

        r[23] = r[23][-2] if r[23] != '0' else 0

        r[44] = usedPower(r[44])
        r[45] = requiredUsedPower(r[45])

        tmpPower = r[46]
        r[46] = powerPort4(r[46])

        r[53] = r[53][0]

        r[54] = horizontalLen(r[54])
        r[55] = verticalLen(r[55])

        r.pop(35)
        r.pop(31)
        r.pop(30)
        r.pop(29)
        r.pop(28)
        r.pop(27)
        r.pop(25)
        r.pop(15)

        r.insert(22, miniDp(tmpDp))
        r.insert(40, powerPort12(tmpPower))
        r.insert(40, powerPort8(tmpPower))
        r.insert(40, powerPort6(tmpPower))




        wr.writerow(r)



f.close()