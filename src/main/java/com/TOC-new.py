import sys
import os
import time

headline = ['#','##','###','####','#####','######']
lines_in_file = []
lineNum = [0,0,0,0,0,0]

"""生成目录列表中的某一项"""
def creat_directory_line(line,headline_mark,i):
    if headline_mark == '#':
        lineNum[0]+=1
        lineNum[1]=0
        prefix = ''
        return '<a href="#' + str(i) + '">' + prefix + line[2:-1] + "</a>  \n"
    elif headline_mark == '##':
        #&emsp;为Markdown中的一种缩进，这里不直接用空格作为缩进是因为多个空格一起出现可能会生成代码块，引发歧义
        lineNum[1]+=1
        lineNum[2]=0
        prefix = str(lineNum[1])+". "
        return '&emsp;<a href="#' + str(i) + '">' + prefix +  line[3:-1] + "</a>  \n"
    elif headline_mark == '###':
        lineNum[2]+=1
        lineNum[3]=0
        prefix = str(lineNum[1])+"."+str(lineNum[2])+'. '
        return '&emsp;&emsp;<a href="#' + str(i) + '">' +prefix +  line[4:-1] + "</a>  \n"
    elif headline_mark == '####':
        lineNum[3]+=1
        lineNum[4]=0
        prefix = str(lineNum[1])+"."+str(lineNum[2])+"."+str(lineNum[3])+'. '
        return '&emsp;&emsp;&emsp;<a href="#' + str(i) + '">' +prefix +  line[5:-1] + "</a>  \n"
    elif headline_mark == '#####':
        lineNum[4]+=1
        lineNum[5]=0
        prefix = str(lineNum[1])+"."+str(lineNum[2])+"."+str(lineNum[3])+"."+str(lineNum[4])+'. '
        return '&emsp;&emsp;&emsp;&emsp;<a href="#' + str(i) + '">' + prefix +  line[6:-1] + "</a>  \n"
    elif headline_mark == '######':
        lineNum[5]+=1
        prefix = str(lineNum[1])+"."+str(lineNum[2])+"."+str(lineNum[3])+"."+str(lineNum[4])+"."+str(lineNum[5])+'. '
        return '&emsp;&emsp;&emsp;&emsp;&emsp;<a href="#' + str(i) + '">' + prefix + line[7:-1] + "</a>  \n"

"""生成目录列表"""
def creat_directory(f):
    i = 0
    directory = []
    directory.append('<a name="index">**Index**</a>\n\n')
    for line in f:
        lines_in_file.append(line)
    f.close()
    length = len(lines_in_file)
    for j in range(length):
        splitedline = lines_in_file[j].lstrip().split(' ')
        if splitedline[0] in headline:
            #如果为最后一行且末尾无换行（防最后一个字被去除）
            if j == length - 1 and lines_in_file[j][-1] != '\n':
                directory.append(creat_directory_line(lines_in_file[j] + '\n',splitedline[0],i) + '\n')
                lines_in_file[j] = lines_in_file[j].replace(splitedline[0] + ' ',splitedline[0] + ' ' + '<a name="' + str(i) + '">')[:] + '</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>' + "\n"
                i = i + 1
            else:
                directory.append(creat_directory_line(lines_in_file[j],splitedline[0],i))
                lines_in_file[j] = lines_in_file[j].replace(splitedline[0] + ' ',splitedline[0] + ' ' + '<a name="' + str(i) + '">')[:-1] + '</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>' + "\n"
                i = i + 1
    lineNum = [0,0,0,0,0,0]
    return directory

"""以目录列表为参数生成添加目录的文件"""
def creat_file_with_toc(f, file_name):
    directory = creat_directory(f)
    file_with_toc = os.getcwd() + os.sep +file_name
    print(file_with_toc)
    if not os.path.exists(file_with_toc):
        with open(file_with_toc, 'w+',encoding='utf-8') as f:
            for directory_line in directory:
                f.write(directory_line)
            for line in lines_in_file:
                f.write(line)
            lines_in_file.clear()
            print('文件已生成')
    else:
        print('文件名重复，请修改文件'+file_with_toc+'.md 的文件名后重试')

result=[]

def search(path="", name=""):
    for item in os.listdir(path):
        item_path = os.path.join(path, item)
        if os.path.isdir(item_path):
            search(item_path, name)
        elif os.path.isfile(item_path):
            if name in item:
                global result
                result.append(item_path)

def generateFile(file_name):
    if os.path.exists(file_name) and os.path.isfile(file_name):
        with open(file_name,'r',encoding='utf-8') as f:
            newFileName = os.path.basename(file_name)
            creat_file_with_toc(f, newFileName)
    else:
        msg = "未找到文件"
        print(msg)

if __name__=='__main__':
    file_name = ''
    #如果未传入文件名
    if len(sys.argv) < 2:
        path = os.getcwd()
        file_and_dir = os.listdir(path)
        md_file = []
        for item in file_and_dir:
            if item.split('.')[-1].lower() in ['md','mdown','markdown'] and os.path.isfile(item):
                md_file.append(item)
        if len(md_file) != 0:
            print('当前目录下的Markdown文件：')
            for file in md_file:
                print(file)
            file_name = input('请输入文件名(含后缀)\n')
        else:
            print('该目录下无Markdown文件，即将退出...')
            time.sleep(2)
            os._exit(0)
    else:
        file_name = sys.argv[1]
    if 'all' == file_name:
        path = os.getcwd()
        search(path,'md')
        print(os.path.exists(str(result[1]).strip()))
        for i in range(len(result)):
            print("序号：%s   值：%s" % (   i  , result[i]  ))
            generateFile(result[i])
    else:
        generateFile(file_name)
