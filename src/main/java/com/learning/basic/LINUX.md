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


## sed
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

###  正则表达式
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
> awk 后面接两个单引号并加上大括号 {} 来设定想要对数据进行的处理动作。 awk 可以处理后续接的文件，也可以读取来自前个指令的 standard output 。 

