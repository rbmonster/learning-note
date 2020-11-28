<a name="index">**Index**</a>
&emsp;<a href="#0">集合容器</a>  
&emsp;&emsp;<a href="#1">list相关</a>  
&emsp;&emsp;<a href="#2">Set相关：</a>  
&emsp;&emsp;<a href="#3">Map 相关：</a>  
&emsp;&emsp;<a href="#4">快速报错机制</a>  
&emsp;&emsp;<a href="#5">散列</a>  
&emsp;&emsp;<a href="#6">持有引用(java.lang.ref)</a>  
&emsp;&emsp;<a href="#7">性能测试</a>  
&emsp;<a href="#8">面试相关</a>  
-  记录一下在Thinking in java 里面的一些方法
## <a name="0">集合容器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="1">list相关</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- CopyOnWriteArrayList：使用了读写分离的思想，在写数据的时候上ReentrantLock锁并新建一个数组，读数据仍从旧数组中读取，而新数据在新增或删除完成之后直接替换旧数组。虽然线程安全，对于频繁写数据的场景效率很低。
- ListIterator： 更强大的Iterator的子类，用于各种List类的访问，并支持双向移动。
- LinkedList：
  - getFirst() 和element() 完全一样，都返回第一个元素。如果为空，抛NoSuchElementException.
  - peek() 方法与上诉类似，只时列表为空返回null
  - removeFirst() 和 remove() 类似，移除并返回列表的头，只是列表为空抛出NoSuchElementException。
  - poll() 同样移除并返回列表头，只是列表为空返回Null
- Stack：pop()、push()、 peek()方法，其中peek()返回栈顶元素，而不将其移除。
### <a name="2">Set相关：</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - TreeSet:将元素存储在红-黑树的数据结构中，而HashSet使用的是散列函数。
  - LinkedHashSet：具有HashSet的查询速度，且内部使用链表维护元素的顺序。
  - HashSet: 存入的元素必须实现hashSet()方法。
  - TreeSet: 保持次序的Set，底层为树结构。元素必须实现Comparable接口。
  - SortedSet: 元素可以保证处于排序状态，必须实现Comparable接口. SortedSet<> so = new TreeSet<>()
  - EnumSet：元素必须来自一个Enum。
- Queue为LinkedList的子类：
  - offer()方法：将一个元素插入队尾并返回false
  - peek() 和element() 在被移除的情况下返回队头，element()遇到队列为空时返回null
  - poll() 在队列为空时返回null,而remove()会抛出NoSuchElementException()。
  - PriorityQueue：优先级队列弹出的元素具有最高的优先级。

### <a name="3">Map 相关：</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - HashMap: 给予散列表实现。可以通过构造器设置容量和负载因子，以调整容器的性能
  - LinkedHashMap：类似HashMap，但是迭代访问时，取得“键值对”的顺序是按其插入对的顺序，或者是最近最少使用(LRU)的次序。
    - 在构造器可以指定参数为new LinkedHashMap<>(initialCapacity, loadFactor, true),initialCapacity为初始容量，loadFactor为加载因子，true表示使用LRU访问。
    - 初始容量是创建哈希表时的容量，加载因子是哈希表在其容量自动增加之前可以达到多满的一种尺度，它衡量的是一个散列表的空间的使用程度，负载因子越大表示散列表的装填程度越高，反之愈小。
  - TreeMap: 基于红黑树的实现。“键”或“键值对”的次序是由Comparable或Comparator决定的。TreeMap是唯一带有subMap()方法的Map，可以返回一个子树。
  - WeakHashMap： 弱键映射，允许设释放射所指对象。被垃圾收集器回收。
  - ConcurrentHashMap: 线程安全的Map.
  - IdentityHashMap：使用==代替equals()对“键”进行比较的散列映射。
  - sortedMap: 排序的Map，现阶段TreeMap是其唯一实现。
  - EnumMap:要求键必须来自一个Enum。
  - 散列陷阱：hashCode的生成应该保持在不同环境下生成的hashcode是不变的，否则就会造成放入HashMap中后，无法正常取出。
  - HashMap的性能因子
    - 容量：表中的桶位数。
    - 初始容量：表在创建时拥有的桶位数。允许在初始化时指定。
    - 尺寸：表中当前存储的项数。
    - 负载因子： 尺寸/容量。空表时因子值为0，半满时值为0.5。负载轻的表产生的冲突的可能性最小，因此HashMap和HashSet都具有允许你再指定负载因子的构造器，表示达到该负载因子水平时，容器将自动增加容量，使容器的容量大致加倍，并重新分布到新的桶位集中。
    - 再散列：达到该负载因子水平时，容器将自动增加容量，使容器的容量大致加倍，并重新分布到新的桶位集中（再散列）。
    - HashMap中的默认负载因子为0.75，这个因子在时间和空间代价之间达到了平衡。

### <a name="4">快速报错机制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 - 定义探查容器上的任何除了你的进程所进行的操作之外的所有变化，一旦发现立即抛出ConCurrentModificationException的异常。

### <a name="5">散列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - 散列的价值在于速度，散列是的查询得以快速进行。数组并不保存键本身，而是通过键对象生成一个对象，将其作为数组的下表，这个数字就是散列码。
  - 不同的键可以产生相同的下标，为了解决数组容量被固定的问题，相当于换算的键可能冲突。冲突通过外部链接处理，即链表。然后对链表中的值调用equals的方法进行线性查询。
 
### <a name="6">持有引用(java.lang.ref)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - SoftReference用以实现内存敏感的高速缓存。
  - WeakReference是为实现“规范映射”而设计的，它不妨碍垃圾回收器回收映射的键和值。
  - PhantomReference用以调度回收前的清理工作，它比java机制更成熟。
  - 引用的强弱顺序，为SoftReference、WeakReference、PhantomReference

### <a name="7">性能测试</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - List 的结果如下
```
--------------------- ArrayList ---------------------
 size     add     get     set iteradd  insert  remove
   10     121     139     191     435    3952     446
  100      72     141     191     247    3934     296
 1000      98     141     194     839    2202     923
10000     122     144     190    6880   14042    7333
--------------------- LinkedList ---------------------
 size     add     get     set iteradd  insert  remove
   10     182     164     198     658     366     262
  100     106     202     230     457     108     201
 1000     133    1289    1353     430     136     239
10000     172   13648   13187     435     255     239
----------------------- Vector -----------------------
 size     add     get     set iteradd  insert  remove
   10     129     145     187     290    3635     253
  100      72     144     190     263    3691     292
 1000      99     145     193     846    2162     927
10000     108     145     186    6871   14730    7135
-------------------- Queue tests --------------------
 size    addFirst     addLast     rmFirst      rmLast
   10         199         163         251         253
  100          98          92         180         179
 1000          99          93         216         212
10000         111         109         262         384
```
- Set性能测试: LinkedHashSet 比 HashSet代价更高，这是维护链表带来的额外开销
```
------------- TreeSet -------------
 size       add  contains   iterate
   10       746       173        89
  100       501       264        68
 1000       714       410        69
10000      1975       552        69
------------- HashSet -------------
 size       add  contains   iterate
   10       308        91        94
  100       178        75        73
 1000       216       110        72
10000       711       215       100
---------- LinkedHashSet ----------
 size       add  contains   iterate
   10       350        65        83
  100       270        74        55
 1000       303       111        54
10000      1615       256        58
```
- Map的性能表现：
  - 除了IdentityHashMap，其他的都会随着Map的尺寸变大而明显变慢。
```
---------- TreeMap ----------
 size     put     get iterate
   10     748     168     100
  100     506     264      76
 1000     771     450      78
10000    2962     561      83
---------- HashMap ----------
 size     put     get iterate
   10     281      76      93
  100     179      70      73
 1000     267     102      72
10000    1305     265      97
------- LinkedHashMap -------
 size     put     get iterate
   10     354     100      72
  100     273      89      50
 1000     385     222      56
10000    2787     341      56
------ IdentityHashMap ------
 size     put     get iterate
   10     290     144     101
  100     204     287     132
 1000     508     336      77
10000     767     266      56
-------- WeakHashMap --------
 size     put     get iterate
   10     484     146     151
  100     292     126     117
 1000     411     136     152
10000    2165     138     555
--------- Hashtable ---------
 size     put     get iterate
   10     264     113     113
  100     181     105      76
 1000     260     201      80
10000    1245     134      77
```

## <a name="8">面试相关</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- HashMap 数据结构：数组+(链表或红黑树)  
  - 数组中的元素为Node<K, V>,Node是一个内部类，这里的key为键，value为值

- 初始化100个元素大小如何设置 ：
  - initialCapacity = (需要存储的元素个数 / 负载因子)。注意负载因子（即loader factor）默认为0.75， 如果暂时无法确定初始值大小，请设置为16（即默认值）。

- resize会出现的问题:
  - 可能导致环形链表的出现。在resize的时候，如果一个线程已经resize完成，而另一个线程才开始resize就可能出现环形链表的情况。
  - https://www.cnblogs.com/wang-meng/p/7582532.html
