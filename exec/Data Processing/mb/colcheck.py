import csv
CSV_FILE = 'detail_mb.csv'
cpuCategory = list()

#컬럼 수
last_col = 111

for i in range(0,last_col):
    cpuCategory.append({})

with open(CSV_FILE, 'r', newline='', encoding='utf8') as file:
    for crawlingValues in csv.reader(file, skipinitialspace=True):
        for i in range(0, last_col):
            if crawlingValues[i] in cpuCategory[i].keys():
                cpuCategory[i][crawlingValues[i]] += 1;
            else:
                cpuCategory[i][crawlingValues[i]]=1;
        

# l = list(cpuCategory)
for c in range(0,last_col):
    if len(cpuCategory[c]) == 2:
        print(cpuCategory[c])
        print(str(c) + " : " + str(len(cpuCategory[c])))
print("-----------------")
show_col = 110
for i in cpuCategory[show_col]:
    print(i + " : " + str(cpuCategory[show_col][i]))
