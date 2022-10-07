import csv
import os

CSV_FILE = 'hdd_info.csv'
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

f = open(f'detail_hdd.csv', 'w', newline='', encoding='utf8')
wr = csv.writer(f)
# wr.writerow(list(col_name.keys()))
# if d in col_name.keys():
#     return d



def getImgCnt(path):
    dirListing = os.listdir(path)
    return len(dirListing)


def maker(d):
    if d == 'Western Digital WD':
        return 'Western Digital'
    else:
        return d

def diskStorage(d):
    if d.find('TB') != -1:
        if d == '1.2TB':
            return 1129
        elif d == '1.5TB':
            return 1536
        elif d == '2TB 이상':
            return 2048
        elif d == '1.8TB':
            return 1843
        elif d == '2.4TB':
            return 2458
        else:
            d = d.replace('TB', '')
            return int(d.strip()) * 1024

    else:
        d = d.replace('GB', '')
        return int(d.strip())

def interF(d):
    if d == 'SATA3 (6Gb/s)':
        return 'SATA3(6Gb/s)'
    else:
        return d


def rpmCnt(d):
    if d == '5,400 가변RPM':
        return 5400
    else:
        d = d.replace(',', '')
        d = d.replace('RPM', '')
        return int(d.strip())


def bufferSize(d):
    d = d.replace('메모리', '')
    d = d.replace('MB', '').strip()

    return int(d)

def transferSpeed(d):
    if d == '최대 261,249MB/s':
        return 249
    else:
        d = d.replace('최대', '')
        d = d.replace('MB/s', '')
        return int(d.strip())

        
def distCnt(d):
    if d == '플래터 2~3장':
        return 2
    elif d == '플래터 4~5장':
        return 4
    else:
        d = d.replace('플래터', '').replace('장', '').strip()
        return int(d)


def thick(d):
    return float(d.replace('mm', '').strip())

def workload(d):
    return int(d.replace('TB/Y', '').strip())

def warrantyUse(d):
    if d == '100만 이하시간':
        return 100 * 10000
    else:
        return int(d.replace('만시간', '').strip()) * 10000

def noise(d):
    if d == '/dB':
        return '0'
    else:
        return d


first = True
for r in datas:
    if first:       
        wr.writerow(r)
        first = False
    else:
        
        r[3] = maker(r[3])

        r[7] = diskStorage(r[7])
        r[8] = interF(r[8])
        r[9] = rpmCnt(r[9])
        r[10] = bufferSize(r[10])
        r[11] = transferSpeed(r[11])

        r[13] = distCnt(r[13])
        r[14] = thick(r[14])

        r[22] = workload(r[22])
        r[23] = warrantyUse(r[23])
        r[24] = noise(r[24])
        wr.writerow(r)

f.close()