import csv
import os

CSV_FILE = 'mb_info.csv'
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

f = open(f'detail_mb.csv', 'w', newline='', encoding='utf8')
wr = csv.writer(f)
# wr.writerow(list(col_name.keys()))
# if d in col_name.keys():
#     return d



def getImgCnt(path):
    dirListing = os.listdir(path)
    return len(dirListing)



def maker(d):
    if d.find('ASRock') != -1:
        return 'ASRock'
    elif d.find('ASUS') != -1:
        return 'ASUS'
    elif d.find('BIOSTAR') != -1:
        return 'BIOSTAR'
    elif d.find('COLORFUL') != -1:
        return 'COLORFUL'
    elif d.find('ECS') != -1:
        return 'ECS'
    elif d.find('MSI') != -1:
        return 'MSI'
    else:
        return d


def chipset(d):
    if d == '(인텔) G41':
        return '인텔 G41'
    elif d == '(NVIDIA) Carmel':
        return 'NVIDIA Carmel'
    else:
        return d


def formFactor(d):
    if d == '0':
        return '0'
    elif d.find('M-ATX') != -1:
        return 'M-ATX'
    elif d.find('M-iTX') != -1:
        return 'M-ITX'
    elif d.find('Micro-ATX') != -1:
        return 'M-ATX'
    elif d.find('일반-ATX') != -1:
        return 'ATX'
    elif d.find('EEB') != -1:
        return 'EEB'
    elif d =='기타':
        return '기타'
    elif d.find('E-ATX') != -1:
        return 'E-ATX'
    elif d.find('M-DTX') != -1:
        return 'M-DTX'
    elif d.find('Pico-iTX') != -1:
        return 'Pico-iTX'
    elif d == '8.5x5.6cm':
        return '기타'
    elif d.find('CEB') != -1:
        return 'CEB'
    elif d == '표준 ATX':
        return 'ATX'
    elif d.find('XL-ATX') != -1:
        return 'XL-ATX'
    elif d == '11.5x7.8x1.4cm':
        return '기타'
    elif d == '6.96x4.5cm':
        return '기타'
    elif d.find('uATX') != -1:
        return 'M-ATX'
    else:
        return 'ATX'


def internalGraphic(d):
    if d == '인텔 계열':
        return '인텔 HD Graphics 2000/3000'
    elif d == 'nVidia 계열':
        return 'nVidia 2048-core Ampere'
    else:
        return d


def memoryChannel(d):
    if d == '듀얼':
        return '듀얼(2)'
    elif d == '쿼드':
        return '쿼드(4)'
    else:
        return d


def maxMemoryCap(d):
    if d.find('최대') == -1:
        return 0
    else:
        if d == '최대 2TB':
            return 2048
        elif d == '최대 1TB':
            return 1024
        elif d == '최대 1.5TB':
            return 1536
        elif d == '최대 1.5TB (LR-DIMM 3DS)':
            return 1536
        else:
            d = d.replace('최대 ', '')
            d = d.replace('GB', '')
            return int(d)
        



def embededMemory(d):
    if d.find('최대') != -1:
        return 0
    else:
        if d == '512MB 내장':
            return 512
        else:
            d = d.replace('GB 내장', '').strip()
            return 1024 * int(d)

def PCICount(d):
    if d == '○':
        return '0'
    else:
        return d[0]


def PCIex8(d):
    if d == '(3.0) 3개':
        return 3
    else:
        return int(d[0])

def PCIex1(d):
    if d.find('USB') != -1:
        return 0
    else:
        return int(d.replace('개', '').strip())
 
def mdot2(d):
    if d == '3 + 1개':
        return '3+1개'
    else:
        return d


def usb3dot(d):
    d = d.replace('후면', '')
    d = d.replace('개', '')
    return int(d.strip())



def wiredLanChipset(d):
    if d == 'Intel i219V':
        return 'Intel I219V'
    elif d == 'Intel 1219V':
        return 'Intel I219V'
    else:
        return d
    

def wiredLanSpeed(d):
    if d == '최대 100메가':
        return 0.1
    elif d == '최대 기가비트':
        return 1.0
    elif d == '최대 2.5기가비트':
        return 2.5
    elif d == '최대 10기가비트':
        return 10
    elif d == '최대 5기가비트':
        return 5
    else:
        return 0.0




first = True
for r in datas:
    if first:       
        r[17] = '최대 메모리 용량'
        r.pop(74)
        r.insert(18, '임베디드 메모리 용량')
        wr.writerow(r)
        first = False
    else:
        
        

        r[3] = maker(r[3])
        r[7] = chipset(r[7])
        r[8] = r[8][0]
        r[9] = formFactor(r[9])
        r[12] = internalGraphic(r[12])
        r[15] = int(r[15].replace('개', '').strip())
        r[16] = memoryChannel(r[16])

        tmpMem = r[17]
        r[17] = maxMemoryCap(r[17])

        r[26] = PCICount(r[26])
        r[27] = int(r[27][0])
        r[28] = PCIex8(r[28])
        r[29] = int(r[29][0])
        r[30] = PCIex1(r[30])
        r[31] = int(r[31][0])

        r[37] = int(r[37].replace('개', '').strip())
        r[38] = mdot2(r[38])

        r[39] = r[39][0]
        r[40] = r[40][0]
        r[41] = r[41][0]
        r[43] = '○' if r[43] == 'SATA' else r[43]

        r[64] = usb3dot(r[64])
        r[65] = 0 if r[65] == '○' else usb3dot(r[65])
        r[66] = 0 if r[66] == '○' else usb3dot(r[66])
        r[67] = usb3dot(r[67])
        r[68] = '○' if r[68] == '후면 2개' else r[68]

        r[75] = wiredLanChipset(r[75])
        
        r[76] = wiredLanSpeed(r[76])

        r[87] = r[87][0]
        r[88] = r[88][0]
        r[89] = r[89][0]
        r[90] = r[90][0]
        r[91] = r[91][0]
        r[92] = r[92][0]
        r[93] = r[93][0]
        r[94] = r[94][0]
        r[95] = r[95][0]











        r.pop(74)
        
        r.insert(18, embededMemory(tmpMem))
        
        wr.writerow(r)


f.close()