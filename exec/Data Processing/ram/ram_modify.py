import csv
import os

CSV_FILE = 'ram_info.csv'
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

f = open(f'detail_ram.csv', 'w', newline='', encoding='utf8')
wr = csv.writer(f)
# wr.writerow(list(col_name.keys()))
# if d in col_name.keys():
#     return d



def getImgCnt(path):
    dirListing = os.listdir(path)
    return len(dirListing)

def maker(d):
    if d.find('ADATA') != -1:
        return 'ADATA'
    elif d.find('ESSENCORE') != -1:
        return 'ESSENCORE'
    elif d.find('TeamGroup') != -1:
        return 'TeamGroup'
    elif d.find('Terabyte') != -1:
        return 'Terabyte'
    elif d.find('V-Color') != -1:
        return 'V-Color'
    elif d.find('갤럭시') != -1:
        return '갤럭시'
    elif d.find('마이크론') != -1:
        return '마이크론'
    else:
        return d

def memoryCap(d):
    if d == '512MB':
        return 0.5
    elif d == '256MB':
        return 0.25
    else:
        d = d.replace('GB', '')
        return float(d.strip())


def memoryClock(d):
    if d == '333MHz':
        return 333
    else:
        idx = d.find('MHz')
        d = d[:idx]
        return int(d)

def operatingVoltage(d):
    d = d.replace('V', '')
    return float(d.strip())

first = True
for r in datas:
    if first:       
        wr.writerow(r)
        first = False
    else:
        r[3] = maker(r[3])

        r[8] = memoryCap(r[8])

        r[9] = memoryClock(r[9])

        r[11] = operatingVoltage(r[11])
        r[12] = r[12][0]


        wr.writerow(r)

f.close()