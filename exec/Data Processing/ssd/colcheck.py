import csv
CSV_FILE = 'detail_ssd.csv'
cpuCategory = list()

#컬럼 수
last_col = 73

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
show_col = 72
for i in cpuCategory[show_col]:
    print(i + " : " + str(cpuCategory[show_col][i]))