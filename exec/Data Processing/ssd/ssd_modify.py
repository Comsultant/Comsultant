import csv
CSV_FILE = 'ssd_info.csv'
cpuCategory = {}
col_name = {
    'code' : '0',
    'prod_name' : '0',
    'img_cnt' : '0',
    '등록년월' : '0',
    '제조회사' : '0',
    '제품분류' : '0',
    '상세분류' : '0',
    '폼팩터' : '0',
    '인터페이스' : '0',
    '프로토콜' : '0',
    '용량' : '0',
    '메모리 타입' : '0',
    '낸드 구조' : '0',
    'RAM 유무' : '0',
    'RAM 타입' : '0',
    '컨트롤러' : '0',
    '콘솔게임기 호환' : '0',
    '공정' : '0',
    '크기 변환' : '0',
    '인터페이스 변환' : '0',
    '순차읽기' : '0',
    '순차쓰기' : '0',
    '읽기IOPS' : '0',
    '쓰기IOPS' : '0',
    'TRIM' : '0',
    'GC' : '0',
    'SLC캐싱' : '0',
    'S.M.A.R.T' : '0',
    'ECC' : '0',
    'DEVSLP' : '0',
    'AES 암호화' : '0',
    '전용 S/W' : '0',
    'TBW' : '0',
    '맥북 업그레이드용' : '0',
    '마이그레이션' : '0',
    '관리기능' : '0',
    'MTBF' : '0',
    '5년' : '0',
    '3년' : '0',
    '데이터 복구 3년' : '0',
    '데이터 복구 1년' : '0',
    '제한보증' : '0',
    'NVMe 방열판' : '0',
    '가로' : '0',
    '세로' : '0',
    '두께' : '0',
    '무게' : '0',
    '유통회사' : '0',
    '2.5형(6.4cm)' : '0',
    '3.5형(8.9cm)' : '0',
    'Mini SATA(mSATA)' : '0',
    '보관(장착) 개수' : '0',
    'M.2 (2230)' : '0',
    'M.2 (2242)' : '0',
    'M.2 (2260)' : '0',
    'M.2 (2280)' : '0',
    'M.2 (22110)' : '0',
    '지원 두께' : '0',
    '용도 구분' : '0',
    '전원공급' : '0',
    '충격보호' : '0',
    '화이트' : '0',
    '옐로우' : '0',
    '그린' : '0',
    '퍼플' : '0',
    '그레이' : '0',
    '블루' : '0',
    '블랙' : '0',
    '실버' : '0',
    '방수' : '0',
    '제품재질' : '0',
    '잠금장치' : '0',
    '보조전원' : '0'
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

f = open(f'detail_ssd.csv', 'w', newline='', encoding='utf8')
wr = csv.writer(f)
# wr.writerow(list(col_name.keys()))

def maker(d):
    if d in col_name.keys():
        return d

    if d == 'ADATA XPG':
        d = 'ADATA'
    elif d == 'ESSENCORE KLEVV':
        d = 'ESSENCORE'
    elif d == 'EVERCOOL COOLMARKER':
        d = 'EVERCOOL'
    elif d == 'STCOM ALPHA':
        d = 'STCOM'
    elif d == 'TeamGroup T-Force':
        d = 'TeamGroup'
    elif d == 'Western Digital WD':
        d = 'Western Digital'
    elif d == '강원전자 NETmate':
        d = '강원전자'
    elif d == '강원전자 ineo':
        d = '강원전자'
    elif d == '노트옵션 NOTEKING':
        d = '노트옵션'
    elif d == '뉴젠테크 NAMAN':
        d = '뉴젠테크'
    elif d == '디지탈그리고나 e-STAR':
        d = '디지탈그리고나'
    elif d == '디지탈그리고나 이코모':
        d = '디지탈그리고나'
    elif d == '라이트컴 COMS':
        d = '라이트컴'
    elif d == '라인업시스템 LANSTAR':
        d = '라인업시스템'
    elif d == '리버네트워크 NEXI':
        d = '리버네트워크'
    elif d == '마이크론 Crucial':
        d = '마이크론'
    elif d == '위즈플랫 새로텍':
        d = '위즈플랫'
    elif d == '케이엘시스템 KLcom':
        d = '케이엘시스템'
    return d

def interface(d):
    if d in col_name.keys():
        return d

    if d == 'SATA3(6Gb/s)':
        return 'SATA3 (6Gb/s)'
    else:
        return d

def storageSize(d):
    if d in col_name.keys():
        return d

    if d == '1TB(1024GB)':
        return 1024
    
    if d.find('TB') != -1:
        if d == '1TB':
            return 1024
        elif d == '1.92TB':
            return 1970
        elif d == '2TB':
            return 2048
        elif d == '4TB':
            return 4096
        elif d == '8TB':
            return 8192
        elif d == '3.84TB':
            return 3940
        elif d == '1.6TB':
            return 1640
        elif d == '1.2TB':
            return 1230
        elif d == '16TB':
            return 16384
        elif d == '6TB':
            return 6144
        elif d == '30TB':
            return 30720
        elif d == '60TB':
            return 61440
        elif d == '15.36TB':
            return 15730
        elif d == '32TB':
            return 32768
        elif d == '1TB(1024GB)':
            return 1024
        elif d == '6.4TB':
            return 6550
        elif d == '3.2TB':
            return 3275
        elif d == '7.68TB':
            return 7860
        elif d == '30.72TB':
            return 31460
        elif d == '51.2TB':
            return 52430
        elif d == '61.44TB':
            return 62910
        elif d == '12.8TB':
            return 13110
        else:
            return d
    elif d.find('GB') != -1:
        dd = int(d.replace('GB', ''))
        return dd
    else:
        return d

def seqRead(d):
    if d in col_name.keys():
        return d

    if d =='최대 2,500MB/s':
        return 2500
    elif d =='최대 1,835MB/sMB/s':
        return 1835
    elif d =='최대 549MB/sMB/s':
        return 549
    elif d =='최대 540MB/sMB/s':
        return 540
    elif d =='최대 900MB/s':
        return 900
    else:
        d = d.replace('MB/s', '')
        d = int(d.replace(',' , ''))
        return d

def seqWrite(d):
    
    if d in col_name.keys():
        return d
    elif d == '1,000~1,499MB/s':
        return 1499
    else:
        d = d.replace('MB/s', '')
        d = d.replace('최대', '')
        d = d.replace(',', '')
        d.strip()

        return int(d)
    

def readwriteIOPS(d):
    if d in col_name.keys():
        return d
    else:
        d = d.replace('K', '')
        d = d.replace('k', '')
        d = d.replace('최대', '')
        d = d.replace(',', '')
        d = d.strip()
        return float(d)

def tbw(d):
    if d in col_name.keys():
        return d
    else:
        if d == '~199TB':
            return 199
        elif d == '10,0000TB':
            return 10000
        else:
            d = d.replace('TB', '')
            d = d.replace(',', '')
            d = d.strip()
            return float(d)

for r in datas:
        
    #제조회사
    
    r[4] = maker(r[4])

    #인터페이스
    r[9] = interface(r[9])
    
    #용량
    r[11] = storageSize(r[11])

    #순차읽기
    r[21] = seqRead(r[21])

    # #순차쓰기
    r[22] = seqWrite(r[22])

    #읽기IOPS
    r[23] = readwriteIOPS(r[23])

    #쓰기IOPS
    r[24] = readwriteIOPS(r[24])

    #TBW
    r[33] = tbw(r[33])

    r.pop(5)

    wr.writerow(r)



f.close()