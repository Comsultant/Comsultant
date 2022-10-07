import csv
CSV_FILE = 'detail_cpu.csv'
cpuCategory = list()

# 마지막 컬럼 인덱스 + 1
last_col = 39

for i in range(0,last_col):
    cpuCategory.append({})

with open(CSV_FILE, 'r', newline='', encoding='utf8') as file:
    for crawlingValues in csv.reader(file, skipinitialspace=True):
        for i in range(0, last_col):
            if crawlingValues[i] in cpuCategory[i].keys():
                cpuCategory[i][crawlingValues[i]] += 1;
            else:
                cpuCategory[i][crawlingValues[i]]=1;
        

l = list(cpuCategory)
for c in range(0,last_col):
    if len(cpuCategory[c]) == 2:
        print(str(c) + " : " + str(len(cpuCategory[c])))

print("-----------------")

for i in cpuCategory[38]:
    print(i + " : " + str(cpuCategory[38][i]))