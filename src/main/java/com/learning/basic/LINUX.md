# Linux 基本知识

## 基本概念
### 标准输⼊和命令参数

标准输⼊就是编程语⾔中诸如 scanf 或者 readline 这种命令； ⽽参数是指程序的 main 函数传⼊的 args 字符数组。

以cat命令为例，cat命令的功能是从命令行给出的文件中读取数据，并将这些数据直接送到标准输出。若使用如下命令：
```
$ cat file 
Hello world
Hello world
Bye
```

> 使用标准输入/输出文件存在以下问题：
> - 输入数据从终端输入时，用户费了半天劲输入的数据只能用一次。下次再想用这些数据时就得重新输入。而且在终端上输入时，若输入有误修改起来不是很方便。
> - 为了解决上述问题，Linux系统为输入、输出的传送引入了另外两种机制，即输入/输出重定向和管道。

### 输入重定向
输入重定向是指把命令（或可执行程序）的标准输入重定向到指定的文件中。也就是说，输入可以不来自键盘，而来自一个指定的文件。所以说，输入重定向主要用于改变一个命令的输入源，特别是改变那些需要大量输入的输入源。

命令wc统计指定文件包含的行数、单词数和字符数
> 直接输入wc，wc将等待用户告诉它统计什么，这时shell就好象死了一样，从键盘键入的所有文本都出现在屏幕上，但并没有什么结果，直至按下ctrl+d，wc才将命令结果写在屏幕上。
```
[root@VM-0-16-centos ~]# wc
dsf
sadf123
qwe
      3       3      16


[root@VM-0-16-centos ~]# wc < file 
1 1 7
[root@VM-0-16-centos ~]# wc << file 
> delim
> sdfasdf
> sadff
> delim
> file
 4  4 26
```

### 输出重定向
输出重定向是指把命令（或可执行程序）的标准输出或标准错误输出重新定向到指定文件中。这样，该命令的输出就不显示在屏幕上，而是写入到指定文件中。

```
[root@VM-0-16-centos ~]# echo "sdfadf" > file


```

错误重定向： 和程序的标准输出重定向一样，程序的错误输出也可以重新定向。使用符号2>（或追加符号2>>）表示对错误输出设备重定向。例如下面的命令：
```
$ ls /usr/tmp 2> err.file
```

标准输入输出均重定向：可以使用另一个输出重定向操作符（&>）将标准输出和错误输出同时送到同一文件中。例如：
```
$ ls /usr/tmp &> output.file
```


### 管道
> 将一个程序或命令的输出作为另一个程序或命令的输入，有两种方法，一种是通过一个临时文件将两个命令或程序结合在一起，例如上个例子中的/tmp/dir文件将ls和wc命令联在一起；另一种是Linux所提供的管道功能。

管道可以把一系列命令连接起来，这意味着第一个命令的输出会作为第二个命令的输入通过管道传给第二个命令，第二个命令的输出又会作为第三个命令的输入，以此类推。显示在屏幕上的是管道行中最后一个命令的输出,通过使用管道符“|”来建立一个管道行。

```
[root@VM-0-16-centos ~]# cat file|grep adf
sdfadf
[root@VM-0-16-centos ~]# cat file|grep adf|wc -l
1
```


### 相关资料
[百度百科](https://baike.baidu.com/item/%E6%A0%87%E5%87%86%E8%BE%93%E5%85%A5%E8%BE%93%E5%87%BA/4714867?fr=aladdin#3)

### 磁盘分区及目录

挂载：利用一个目录当成进入点，将磁盘分区槽的数据放置在该目录下。也就是说，进入该目录就可以读取该分区槽的意思。
挂载点：那个进入点的目录我们称为『挂载点』。 


### 权限修改
- chgrp ：改变文件所属群组
- chown ：改变文件拥有者
- chmod ：改变文件的权限, SUID, SGID, SBIT 等等的特性

## 其他资料

### 单引号与双引号
1. 单引号、双引号用于用户把带有空格的字符串赋值给变量的分界符。
```
[root@VM-0-16-centos ~]# str="sdf111 sdf"
[root@VM-0-16-centos ~]# echo $str
sdf111 sdf
// 如果没有单引号或双引号，shell会把空格后的字符串解释为命令。
[root@localhost sh]# str=Today is Monday
bash: is: command not found
```
2. 单引号和双引号的区别。单引号告诉shell忽略所有特殊字符，而双引号忽略大多数，但不包括 ` $ \ `

双引号中的`'$'`（参数替换）和``'`'``（命令替换）是例外，所以，两者基本上没有什么区别，除非在内容中遇到了参数替换符`$`和命令替换符```。

3. 反引号 (```) 位于键盘的Tab键的上方，1键的左方。注意与单引号(')位于Enter键的左方的区别。在Linux中起着命令替换的作用。命令替换是指shell能够将一个命令的标准输出插在一个命令行中任何位置。
> 如，shell会执行反引号中的date命令，把结果插入到echo命令显示的内容中。
```
[root@localhost sh]# echo The date is `date`
The date is 2011年 03月 14日 星期一 21:15:43 CST
```

### 脚本中#!/bin/sh与#!/bin/bash的区别
第一句的#!是对脚本的解释器程序路径，脚本的内容是由解释器解释的，我们可以用各种各样的解释器来写对应的脚本。

比如说/bin/csh脚本，/bin/perl脚本，/bin/awk脚本，/bin/sed脚本，甚至/bin/echo等等。

> Ubuntu中可以认为/bin/sh就是/bin/dash, 如果打算使用bash, 可直接将/bin/sh软链接到/bin/bash.


### 指令组合 ||,&&
```
范例三：我不清楚 /tmp/abc 是否存在，但就是要建立 /tmp/abc/hehe 文件
[dmtsai@study ~]$ ls /tmp/abc || mkdir /tmp/abc && touch /tmp/abc/hehe
```

## 管道命令
### cut 指令
```
选项与参数：
-d ：后面接分隔字符。与 -f 一起使用；
-f ：依据 -d 的分隔字符将一段讯息分区成为数段，用 -f 取出第几段的意思；
-c ：以字符 (characters) 的单位取出固定字符区间；


[root@VM-0-16-centos ~]# echo ${PATH}
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin
[root@VM-0-16-centos ~]# echo ${PATH}| cut -d ":" -f 3,4
/usr/sbin:/usr/bin

// 输出4 之后的字符
[root@VM-0-16-centos ~]# echo ${PATH}| cut -c 4-
r/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin

```

### grep指令
#### 选项及参数
```
选项与参数：
-a ：将 binary 文件以 text 文件的方式搜寻数据
-i ：忽略大小写的不同，所以大小写视为相同
-c ：计算找到 '搜寻字符串' 的次数
-n ：顺便输出行号
-v ：反向选择，亦即显示出没有 '搜寻字符串' 内容的那一行！
-r ：递归查找指定文件夹下所有匹配的文本
-e : 使用正则表达式匹配内容
-l : 只输出匹配到文件的文件名
-L : 只输出未匹配到文件的文件名
-H : 查询文件时显示文件名。
-h : 查询文件时不显示文件名。
-w ：整字匹配
-A : 显示匹配行和后n行
-B : 显示匹配行和前n行
-C : 显示匹配行、前n行和后n行
--color=auto ：可以将找到的关键词部分加上颜色的显示喔！


```


#### 指令实例
-c指令说明
```
[root@VM-0-16-centos ~]# last |cut -f 1 -d " "
root
root
root
root
root
root
root
root
root
root
root
root
root
root
root
root
root
root
root
root
reboot

wtmp
[root@VM-0-16-centos ~]# last |cut -f 1 -d " "| wc -l
23
[root@VM-0-16-centos ~]# last |cut -f 1 -d " "|grep -c "root"
20

```

-v反向匹配过滤
```
[root@VM-0-16-centos ~]# last |cut -f 1 -d " "|grep -v "root"
reboot

wtmp
```


grep "匹配内容" filename 
```
[root@VM-0-16-centos ~]# last -n 5 |cut -f 1 -d " "|grep root --color -n
1:root
2:root
3:root
4:root
5:root

```

-r递归查找
```
[root@VM-0-16-centos ~]# grep -r 23000 ./test
./test/temp:VBird 23000 24000 25000
./test/temp:DMTsai 21000 20000 23000
./test/tmp/record:VBird 23000 24000 25000
./test/tmp/record:DMTsai 21000 20000 23000

```

-e正则表达式
```

```

-l 与 -L 输出文件名
```
[root@VM-0-16-centos test]# ls ./*
./file  ./temp

./tmp:
record

// 输出匹配到文本的文件名
[root@VM-0-16-centos test]# grep -r 23000 ./ -l
./temp
./tmp/record

// 输出未匹配到文本的文件名
[root@VM-0-16-centos test]# grep -r 23000 ./ -L
./file

```

-h匹配不显示文件名-H匹配显示文件名
```
[root@VM-0-16-centos test]# grep -r 23000 ./ -h
VBird 23000 24000 25000
DMTsai 21000 20000 23000
VBird 23000 24000 25000
DMTsai 21000 20000 23000
[root@VM-0-16-centos test]# grep "2300" temp -H
temp:VBird 23000 24000 25000
temp:DMTsai 21000 20000 23000

```

-A -B -C 行数显示说明
```
[root@VM-0-16-centos test]# grep "20000" temp -C 1
VBird 23000 24000 25000
DMTsai 21000 20000 23000
Bird2 43000 42000 41000
[root@VM-0-16-centos test]# grep "20000" temp -A 1 -B 1 
VBird 23000 24000 25000
DMTsai 21000 20000 23000
Bird2 43000 42000 41000

```

-w 整字匹配
```
[root@VM-0-16-centos test]# grep "VB" temp 
VBird 23000 24000 25000
[root@VM-0-16-centos test]# grep -w "VB" temp 
```

## sed
### 选项与参数说明
```
[dmtsai@study ~]$ sed [-nefr] [动作]
选项与参数：
-n ：使用安静(silent)模式。在一般 sed 的用法中，所有来自 STDIN 的数据一般都会被列出到屏幕上。但如果加上 -n 参数后，则只有经过 sed 特殊处理的那一行(或者动作)才会被列出来。
-e ：直接在指令列模式上进行 sed 的动作编辑
-f ：直接将 sed 的动作写在一个文件内， -f filename 则可以执行 filename 内的 sed 动作；
-r ：sed 的动作支持的是延伸型正规表示法的语法。(预设是基础正规表示法语法)
-i ：直接修改读取的文件内容，而不是由屏幕输出。

动作说明： [n1[,n2]]function
n1, n2 ：不见得会存在，一般代表『选择进行动作的行数』，举例来说，如果我的动作
是需要在 10 到 20 行之间进行的，则『 10,20[动作行为] 』
function 有底下这些咚咚：
a ：新增， a 的后面可以接字符串，而这些字符串会在新的一行出现(目前的下一行)～
c ：取代， c 的后面可以接字符串，这些字符串可以取代 n1,n2 之间的行！
d ：删除，因为是删除啊，所以 d 后面通常不接任何咚咚；
i ：插入， i 的后面可以接字符串，而这些字符串会在新的一行出现(目前的上一行)；
p ：打印，亦即将某个选择的数据印出。通常 p 会与参数 sed -n 一起运作～
s ：取代，可以直接进行取代的工作哩！通常这个 s 的动作可以搭配正规表示法！
例如 1,20s/old/new/g 就是
```




- `function：d` demo
```
[root@VM-0-16-centos ~]# nl file 
     1	sdfadf
     2	123
     3	123
     4	123
     5	12
     6	3123
     7	123123
     8	qwe
     9	123
    10	qwe
    11	qwe
    12	qwe
    13	asd
    14	qwea
    15	sd
    16	xzcZXc
    17	qwweqwe12
       
[root@VM-0-16-centos ~]# nl file |sed '2,5d'
     1	sdfadf
     6	3123
     7	123123
     8	qwe
     9	123
    10	qwe
    11	qwe
    12	qwe
    13	asd
    14	qwea
    15	sd
    16	xzcZXc
    17	qwweqwe12
```

- `function：a` 新增demo 会新增到下一行
```
[root@VM-0-16-centos ~]# nl file |sed '2a|sadf'
     1	sdfadf
     2	123
|sadf
     3	123
     4	123
     5	12
     6	3123
     7	123123
     8	qwe
     9	123
    10	qwe
    11	qwe
    12	qwe
    13	asd
    14	qwea
    15	sd
    16	xzcZXc
    17	qwweqwe12

```

```
// 新增多行以反斜杠结尾
[root@VM-0-16-centos ~]# nl file |sed '2a|sadf............\
dsfasdff ? dfsd\
dsfasdf'
     1	sdfadf
     2	123
|sadf............
dsfasdff ? dfsd
dsfasdf
     3	123
     4	123
     5	12
     6	3123
     7	123123
     8	qwe
     9	123
    10	qwe
    11	qwe
    12	qwe
    13	asd
    14	qwea
    15	sd
    16	xzcZXc
    17	qwweqwe12

```

- `function：c` 行替换例子
```
[root@VM-0-16-centos ~]# nl file|sed '2,6c go down town'
     1	sdfadf
go down town
     7	123123
     8	qwe
     9	123
    10	qwe
    11	qwe
    12	qwe
    13	asd
    14	qwea
    15	sd
    16	xzcZXc
    17	qwweqwe12

```

- `function：p` 打印修改行数  
> `-n` 输出sed修改的行数
```
[root@VM-0-16-centos ~]# nl file | sed -n '2,10p' 
     2	123
     3	123
     4	123
     5	12
     6	3123
     7	123123
     8	qwe
     9	123
    10	qwe

```

- `function：s` 替换内容 `sed 's/要被取代的字符串/新的字符串/g'`
> 被取代的字符串部分可以使用正则表达式
```
[root@VM-0-16-centos ~]# nl file 
     1	sdfadf
     2	123
     3	123
     4	123
     5	12
     6	3123
     7	123123
     8	qwe
     9	123
    10	qwe
    11	qwe
    12	qwe
    13	asd
    14	qwea
    15	sd
    16	xzcZXc
    17	qwweqwe12
       
[root@VM-0-16-centos ~]# nl file |sed 's/^.*123/456/g'
     1	sdfadf
456
456
456
     5	12
456
456
     8	qwe
456
    10	qwe
    11	qwe
    12	qwe
    13	asd
    14	qwea
    15	sd
    16	xzcZXc
    17	qwweqwe12

```

- `sed -i ` 修改文件内容

- sed 在每行头部或者尾部添加固定字符
```
[root@VM-0-16-centos ~]# cat file |sed 's/^/yep/g'
yepsdfadf
yep123
yep123
yep123
yep12
yep3123
yep123123
yepqwe
yep123
yepqwe
yepqwe
yepqwe
yepasd
yepqwea
yepsd
yepxzcZXc
yepqwweqwe12

[root@VM-0-16-centos ~]# cat file |sed 's/$/~yep/g'
sdfadf~yep
123~yep
123~yep
123~yep
12~yep
3123~yep
123123~yep
qwe~yep
123~yep
qwe~yep
qwe~yep
qwe~yep
asd~yep
qwea~yep
sd~yep
xzcZXc~yep
qwweqwe12~yep

```

-i忽略大小写匹配
```
[root@VM-0-16-centos test]# cat temp 
Name 1st 2nd 3th
VBird 23000 24000 25000
DMTsai 21000 20000 23000
Bird2 43000 42000 41000
[root@VM-0-16-centos test]# grep  "name"  temp 
[root@VM-0-16-centos test]# grep -i "name"  temp 
Name 1st 2nd 3th
```

##  正则表达式
| 正则表达式|范例|  匹配结果|
| --------------- | -----------------------| ----------------------------------- |
| + | 意义：重复『一个或一个以上』的前一个 RE 字符| `egrep -n 'go+d' regular_express.txt` 搜寻 (god) (good) (goood)... 等等的字符串 |
| ? |意义：『零个或一个』的前一个 RE 字符 | ` egrep -n 'go+d' regular_express.txt` 范例：搜寻 (gd) (god) 这两个字符串。|
| 竖线| 意义：用或( or )的方式找出数个字符串| `egrep -n 'gd/good' regular_express.txt` 范例：搜寻 gd 或 good 这两个字符串|
| () | 意义：找出『群组』字符串 | `egrep 'A(xyz)+C'`  范例：将『AxyzxyzxyzxyzC』用 echo 叫出|
| ^ | 匹配输入字符串的开始位置 | ` egrep '^ABC' ` 范例：匹配以ABC开头的|
| $| 匹配输入字符串的结尾位置| `egrep 'CBA$'` 范例：匹配以CBA结尾的|

## awk
awk 主要是处理『每一行的字段内的数据』，而默认的『字段的分隔符为 "空格键" 或 "[tab]键" 』！

### 选项与参数说明
```
选项参数：
-F ： 指定输入文件的分隔符
-v： 赋值一个用户自定义遍历
-f： 从脚本文件中读取awk命令。

```

-F选项例子： 当未指定分割符号的情况，默认使用空格或者tab作为分割
```
[root@VM-0-16-centos ~]# cat /etc/passwd
root:x:0:0:root:/root:/bin/bash
bin:x:1:1:bin:/bin:/sbin/nologin
daemon:x:2:2:daemon:/sbin:/sbin/nologin
adm:x:3:4:adm:/var/adm:/sbin/nologin
lp:x:4:7:lp:/var/spool/lpd:/sbin/nologin
sync:x:5:0:sync:/sbin:/bin/sync
shutdown:x:6:0:shutdown:/sbin:/sbin/shutdow

// 按 ： 分割 输出1, 2 字段
[root@VM-0-16-centos ~]# cat /etc/passwd | awk -F ":" '{print $1 "|" $2}'
root|x
bin|x
daemon|x
adm|x
lp|x
sync|x


[root@VM-0-16-centos ~]# last -n 5
root     pts/1        140.206.51.97    Sun Jan 31 12:22   still logged in   
root     pts/1        59.61.69.209     Tue Jan 12 10:09 - 17:34  (07:25)    
root     pts/2        223.104.49.216   Mon Jan 11 13:40 - 20:31  (06:50)    
root     pts/1        223.104.49.216   Mon Jan 11 13:38 - 20:31  (06:52)    
root     pts/1        59.61.69.209     Mon Jan 11 09:52 - 13:24  (03:31)    

wtmp begins Wed Dec 23 00:06:07 2020

//  
[root@VM-0-16-centos ~]# last -n 5 |awk  '{print $3"\t"$1}'
140.206.51.97	root
59.61.69.209	root
223.104.49.216	root
223.104.49.216	root
59.61.69.209	root
	
Wed	wtmp

```

-v：自定义变量
```
[root@VM-0-16-centos ~]# last -n 5 |awk -v bk=aisibi  '{print $3"\t"$1"\t"bk}'
140.206.51.97	root	aisibi
59.61.69.209	root	aisibi
223.104.49.216	root	aisibi
223.104.49.216	root	aisibi
59.61.69.209	root	aisibi
		aisibi
Wed	wtmp	aisibi

```

### awk 动作说明
awk 后面接两个单引号并加上大括号 {} 来设定想要对数据进行的处理动作。 awk 可以处理后续接的文件，也可以读取来自前个指令的 standard output 。 
```
[dmtsai@study ~]$ awk '条件类型 1{动作 1} 条件类型 2{动作 2} ...' filename
```

动作中变量说明：

| 变量名称 | 代表意义 |
| --- | --- |
|$0 | 代表整行数据 |
|$1, $2 | 代表第一个参数，第二个参数...|
|NF  | 每一行 ($0) 拥有的字段总数|
|NR  | 目前 awk 所处理的是『第几行』数据|
|FS  | 目前的分隔字符，默认是空格键|


BEGIN：用来预先设定 awk 的变量。`BEGIN{ 这里面放的是执行前的语句 }`如果未使用预设，可能会导致第一行数据处理未生效的情况。
END：END {这里面放的是处理完所有的行后要执行的语句 }
print：打印指定输出内容
```
[root@VM-0-16-centos ~]# last -n 5
root     pts/1        140.206.51.97    Sun Jan 31 12:22   still logged in   
root     pts/1        59.61.69.209     Tue Jan 12 10:09 - 17:34  (07:25)    
root     pts/2        223.104.49.216   Mon Jan 11 13:40 - 20:31  (06:50)    
root     pts/1        223.104.49.216   Mon Jan 11 13:38 - 20:31  (06:52)    
root     pts/1        59.61.69.209     Mon Jan 11 09:52 - 13:24  (03:31)    

wtmp begins Wed Dec 23 00:06:07 2020

[root@VM-0-16-centos ~]# last -n 5| awk '{print $1"\t"$3}'
root	140.206.51.97
root	59.61.69.209
root	223.104.49.216
root	223.104.49.216
root	59.61.69.209
	
wtmp	Wed


[root@VM-0-16-centos ~]# last -n 5| awk '{print $1 "\t lines: " NR "\t columns: " NF}'
root	 lines: 1	 columns: 10
root	 lines: 2	 columns: 10
root	 lines: 3	 columns: 10
root	 lines: 4	 columns: 10
root	 lines: 5	 columns: 10
	 lines: 6	 columns: 0
wtmp	 lines: 7	 columns: 7
```


```
[root@VM-0-16-centos ~]# cat /etc/passwd
root:x:0:0:root:/root:/bin/bash
bin:x:1:1:bin:/bin:/sbin/nologin
daemon:x:2:2:daemon:/sbin:/sbin/nologin
adm:x:3:4:adm:/var/adm:/sbin/nologin
lp:x:4:7:lp:/var/spool/lpd:/sbin/nologin
sync:x:5:0:sync:/sbin:/bin/sync
shutdown:x:6:0:shutdown:/sbin:/sbin/shutdown
halt:x:7:0:halt:/sbin:/sbin/halt
mail:x:8:12:mail:/var/spool/mail:/sbin/nologin
operator:x:11:0:operator:/root:/sbin/nologin


[root@VM-0-16-centos ~]# cat /etc/passwd |awk 'BEGIN {FS=":"} $3<10 {print $1 "\t" $3}'
root	0
bin	1
daemon	2
adm	3
lp	4
sync	5
shutdown	6
halt	7
mail	8

[root@VM-0-16-centos ~]# cat /etc/passwd |awk -F ":" ' $3<10 {print $1 "\t" $3}'
root	0
bin	1
daemon	2
adm	3
lp	4
sync	5
shutdown	6
halt	7
mail	8


```

### 实例说明
汇总求值：
```
$ ls -l *.txt | awk '{sum+=$5} END {print sum}' 
--------------------------------------------------
666581
```

字段最后拼接：首行特殊处理，其他行最后拼接字符串
```
[root@VM-0-16-centos ~]# cat temp 
Name 1st 2nd 3th
VBird 23000 24000 25000
DMTsai 21000 20000 23000
Bird2 43000 42000 41000
[root@VM-0-16-centos ~]# cat temp |awk 'NR==1 {print $0" ""Totual"} NR>=2 {all=$2+$3+$4;print $0" "all}' 
Name 1st 2nd 3th Totual
VBird 23000 24000 25000 72000
DMTsai 21000 20000 23000 64000
Bird2 43000 42000 41000 126000

```
