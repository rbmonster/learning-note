# 集合
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/collectionfamily.jpg)
- 集合主要分为两大类，一个实现collection接口的，一个是实现了Map接口的。

## List
- ArrayList： Object[]数组
- Vector：Object[]数组
- CopyOnWriteArrayList：使用了读写分离的思想，在写数据的时候上ReentrantLock锁并新建一个数组，读数据仍从旧数组中读取，而新数据在新增或删除完成之后直接替换旧数组。虽然线程安全，对于频繁写数据的场景效率很低。
- ListIterator： 更强大的Iterator的子类，用于各种List类的访问，并支持双向移动。
- LinkedList：双向链表(JDK1.6 之前为循环链表，JDK1.7 取消了循环)
  - getFirst() 和element() 完全一样，都返回第一个元素。如果为空，抛NoSuchElementException.
  - peek() 方法与上诉类似，只时列表为空返回null
  - removeFirst() 和 remove() 类似，移除并返回列表的头，只是列表为空抛出NoSuchElementException。
  - poll() 同样移除并返回列表头，只是列表为空返回Null
- Stack：pop()、push()、 peek()方法，其中peek()返回栈顶元素，而不将其移除。

### 数据结构及源码分析
#### ArrayList:  Object[]数组
- 默认初始化容量：10
  - ```
    private static final int DEFAULT_CAPACITY = 10;
    ```

#####  扩容
- 添加元素时使用 ensureCapacityInternal() 方法来保证容量足够，如果不够时，需要使用 grow() 方法进行扩容，新容量的大小为 oldCapacity + (oldCapacity >> 1)，也就是旧容量的 1.5 倍。
- 主要一个超精度负数判断，如果经度过长，则默认使用当前长度
- 数据复制使用Arrays.copyOf(elementData, newCapacity);

- 因为为一步操作因此用于快速失败的modCount+1
```
// size为当前的list长度
public boolean add(E e) {
    // 当前list容量size + 1
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}

private void ensureCapacityInternal(int minCapacity) {
    // 判断当前是否为第一次初始化
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    ensureExplicitCapacity(minCapacity);
}

private void ensureExplicitCapacity(int minCapacity) {
    // fast-fail 检测
    modCount++;
    // overflow-conscious code
    // 判断当前添加元素后的容量大小是否需要扩容
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    // 二进制扩容出现负数情况判断
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    // 如果超过最大的负数则默认使用当前需要扩容的范围
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```

思考：arrayList 为啥1.5倍扩容？

##### 删除元素
- 调用 System.arraycopy() 将 index+1 后面的元素都复制到 index 位置上，该操作的时间复杂度为 O(N)，可以看到 ArrayList 删除元素的代价是非常高的。
  -  System.arraycopy(elementData, index+1, elementData, index, numMoved);

##### 快速失败
- 快速失败(fail-fast) 是 Java 集合的一种错误检测机制。在使用迭代器对集合进行遍历的时候，我们在多线程下操作非安全失败(fail-safe)的集合类可能就会触发 fail-fast 机制，导致抛出 ConcurrentModificationException 异常。
   - 另外，在单线程下，如果在遍历过程中对集合对象的内容进行了修改的话也会触发 fail-fast 机制。
- modCount 用来记录 ArrayList 结构发生变化的次数。结构发生变化是指添加或者删除至少一个元素的所有操作，或者是调整内部数组的大小，仅仅只是设置元素的值不算结构发生变化。
  - 使用迭代器遍历时默认会传入当前的数组的modCount，每次操作进行检测
```
    private class Itr implements Iterator<E> {
        int expectedModCount = modCount;
    ····

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
```

- 内存空间占用： ArrayList 的空间浪费主要体现在在 list 列表的结尾会预留一定的容量空间，而 LinkedList 的空间花费则体现在它的每一个元素都需要消耗比 ArrayList 更多的空间（因为要存放直接后继和直接前驱以及数据）。

#### CopyOnWriteArrayList 读写分离list
- 写操作在一个复制的数组上进行，读操作还是在原始数组中进行，读写分离，互不影响。
  - 写操作需要加锁，防止并发写入时导致写入数据丢失。
  - 写操作结束之后需要把原始数组指向新的复制数组
- 适用于读多写少的场景
```
    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    private E get(Object[] a, int index) {
        return (E) a[index];
    }
```

#### LinkedList
- 定义了一个内部的Node 节点，基于双向链表实现，使用 Node 存储链表节点信息。
- 相关操作：
   - getFirst() 和element() 完全一样，都返回第一个元素。如果为空，抛NoSuchElementException.
   - peek() 方法与上诉类似，只时列表为空返回null
   - removeFirst() 和 remove() 类似，移除并返回列表的头，只是列表为空抛出NoSuchElementException。
   - poll() 同样移除并返回列表头，只是列表为空返回Null
```
 private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```


##  Map
- HashMap： JDK1.8 之前 HashMap 由数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）。JDK1.8 以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为 8）（将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树）时，将链表转化为红黑树，以减少搜索时间
- LinkedHashMap： LinkedHashMap 继承自 HashMap，所以它的底层仍然是基于拉链式散列结构即由数组和链表或红黑树组成。另外，LinkedHashMap 在上面结构的基础上，增加了一条双向链表，使得上面的结构可以保持键值对的插入顺序。同时通过对链表进行相应的操作，实现了访问顺序相关逻辑。详细可以查看：《LinkedHashMap 源码详细分析（JDK1.8）》
- Hashtable： 数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的
- ConcurrentHashMap: 线程安全的Map.
- TreeMap: 基于红黑树的实现（自平衡的排序二叉树）。“键”或“键值对”的次序是由Comparable或Comparator决定的。TreeMap是唯一带有subMap()方法的Map，可以返回一个子树。
- WeakHashMap： 弱键映射，允许设释放射所指对象。被垃圾收集器回收。
- ConcurrentHashMap: 线程安全的Map.
- IdentityHashMap：使用==代替equals()对“键”进行比较的散列映射。
- sortedMap: 排序的Map，现阶段TreeMap是其唯一实现。
- EnumMap:要求键必须来自一个Enum。
### HashMap
#### 基本知识
基础的数据节点Node 继承Map.Entry 接口实现的key-value的数据节点
- 基本的存储的结构为Node 节点的数组
  - ```
    transient Node<K,V>[] table;
    ```
threshold:临界值，当实际大小(容量*填充因子)超过临界值时，会进行扩容

TREEIFY_THRESHOLD：树化的最小长度8。
- 为啥设定为8，TreeNodes占用空间是普通Nodes的两倍，建立树是需要时间及空间成本的。因此此处基于时间与空间的权衡定位8,具体可以看源码。

UNTREEIFY_THRESHOLD：树变成链表的阀值6。

MIN_TREEIFY_CAPACITY：hashMap进行树化的最低条件table的大小达到64，否则只是进行扩容。

Map 最大大小：static final int MAXIMUM_CAPACITY = 1 << 30;
```
  static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        // 用于处理哈希冲突的拉链法解决方案
        Node<K,V> next;
```

负载因子：hashMap的负载因子默认为0.75，当hashMap中的元素达到 3/4就进行元素的扩容。
> 负载因子大小的关系，若负载因子为1，那么在出现大量的hash膨胀的情况下，元素会较密集，并且都是用链表或者红黑树的方式连接，导致查询效率较低。
> 若负载因子为0.5 那么就会造成空间的浪费，元素分布较为稀疏。

#### put 操作
1. 首先判断table是否需要扩容，若需要进行扩容操作
2. 计算当前元素hash经过散列后是否有元素存在，若不存在元素直接添加。
3. 若存在元素，分下面两个判断
    1. 替换：若旧元素的hash值与新添加元素一致，且新添加Node 的key调用equals方法一致，则直接替换旧节点。
    2. 拉链法：
        - 普通链表：循环判断链表节点是否为key相同替换情况，若均不是需要替换情况，则定位到链表尾部添加新节点。
        - 红黑树：树形遍历判断是否存在，不存在添加。

#### 扩容
每次扩容的大小为 <<1，表示乘以2。 
1. 计算扩容新的table长度size 与threshold 的长度
2. 遍历旧table，如果节点，无哈希冲突的情况，e.hash&(newCap-1)直接定位到新的位置。
3. 出现哈希冲突的情况，由于每次扩容的大小默认为2的n次方，因此重散列的位置只会为当前位置或者当前位置+旧数组大小两个位置。
4. 如果节点存在哈希冲突，则根据位运算计算最新的位置是否为0，为0表示无需移动节点。为1表示移动到oldCap+j的位置。
5. 针对出现红黑树的哈希冲突，同理。此处针对红黑树冲突的需要判断重散列的节点是否需要重新建立红黑树。

- 如果初始化容量大小部位2的幂次方，那么在初始化的时候，会计算threshold为大于初始化数的最近2的幂次方数，在实际使用的时候声明为table的大小。


#### HashMap红黑树查找
红黑树建立是基于Hash的大小来建立的。这里的hashcode 为hashMap换算过的hash。hash小的为左子树， hash 大的为右子树

针对hash重复的情况：
1. 使用equal的方法进行匹配，相同返回。
2. 若存在左节点或右节点缺失，则直接进入未缺失的节点查找。（left==null ==> findByRight)，均不存在返回null。
3. 左右子节点均存在，判断是否为相同的class，及class是否继承comparable接口，
4. 若为相同的class且都继承则直接通过comparable判断左右节点。
5. 若不同的class、无继承comparable接口或者经过comparable接口比较的结果相等。
6. 递归调用左节点查找，若未找到，递归调用右节点查找。

```
 final TreeNode<K,V> find(int h, Object k, Class<?> kc) {
    TreeNode<K,V> p = this;
    do {
        int ph, dir; K pk;
        TreeNode<K,V> pl = p.left, pr = p.right, q;
        if ((ph = p.hash) > h)
            p = pl;
        else if (ph < h)
            p = pr;
        // hash 相同 使用equal比较
        else if ((pk = p.key) == k || (k != null && k.equals(pk)))
            return p;
        // 左右子树缺失，直接进入存在子树的部分
        else if (pl == null)
            p = pr;
        else if (pr == null)
            p = pl;
        // 基于class的比较，若都继承comparable接口，则使用compareTo比较
        // 若class 均不继承comparable 接口，或者compare接口比较后相同，进入左右子树递归查询。
        else if ((kc != null ||
                  (kc = comparableClassFor(k)) != null) &&
                 (dir = compareComparables(kc, k, pk)) != 0)
            p = (dir < 0) ? pl : pr;
        else if ((q = pr.find(h, k, kc)) != null)
            return q;
        else
            p = pl;
    } while (p != null);
    return null;
}

```

针对建立红黑树或者添加树节点的情况
- 若使用equal及class 的compare 均无法确定添加节点的方向
- 使用对象的类名进行判断，若类名依然相同，则使用System根据对象地址换算的hashcode编码判断添加方向。
```
  static int tieBreakOrder(Object a, Object b) {
        int d;
        if (a == null || b == null ||
            (d = a.getClass().getName().
             compareTo(b.getClass().getName())) == 0)
            d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                 -1 : 1);
        return d;
    }
```

#### hash 方法
一次16位右位移异或混合
- 混合后的低位掺杂了高位的部分特征，这样高位的信息也被变相保留下来。
- 混合原始哈希码的高位和低位，以此来加大低位的随机性。
```
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

#### 并发下循环链表
HashMap扩容是使用类似**头插法**的方式把旧节点转移到新的数组上。假设节点出现哈希冲突以链表的方式连接，且头节点1和节点2 扩容的位置仍然不变。
1. 当线程1与线程2新建完新数组，并且执行到上述链表节点的扩容，执行旧数组的头结点3。举个例子链表为 3->7
2. 假设线程1先执行，扩容完毕后链表变为： 7 -> 3
3. 线程2 继续运行，那么节点3 以头插法的方式接到新的数组头上，接着节点7，但是这时候节点7的next为 -> 3,
4. 当前数组节点的链表顺序为 7->3，重新进行节点3的头插，就会导致一个循环链表的现象

```
 1 void transfer(Entry[] newTable) {
 2     Entry[] src = table;                   //src引用了旧的Entry数组
 3     int newCapacity = newTable.length;
 4     for (int j = 0; j < src.length; j++) { //遍历旧的Entry数组
 5         Entry<K,V> e = src[j];             //取得旧Entry数组的每个元素
 6         if (e != null) {
 7             src[j] = null;//释放旧Entry数组的对象引用（for循环后，旧的Entry数组不再引用任何对象）
 8             do {
 9                 Entry<K,V> next = e.next;
10                 int i = indexFor(e.hash, newCapacity); //！！重新计算每个元素在数组中的位置
11                 e.next = newTable[i]; //标记[1]
12                 newTable[i] = e;      //将元素放在数组上
13                 e = next;             //访问下一个Entry链上的元素
14             } while (e != null);
15         }
16     }
17 } 
```

[美团关于HashMap的讲解](https://tech.meituan.com/2016/06/24/java-hashmap.html)

### LinkedHashMap 
基于HashMap的基础Node的节点做拓展，添加头尾指针，因此支持顺序访问。双链表加数组的实现。
```
static class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before, after;
    Entry(int hash, K key, V value, Node<K,V> next) {
        super(hash, key, value, next);
    }
}
```
accessOrder主要用于LRU的构建 
- 一个基本的LRU队列需要两点：
  - 添加元素添加在队头，
  ``` 
  void afterNodeInsertion(boolean evict) {}
  ```
  - 访问元素后，元素移到队头
  ```
  void afterNodeAccess(Node<K,V> e) {
  ```
- 因此固定大小的LRU可以像这样构建：
```
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_ENTRIES = 3;
    
    // 该方法为afterNodeInsertion执行的一个判断条件
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

    LRUCache() {
        super(MAX_ENTRIES, 0.75f, true);
    }
}
```
### concurrentHashMap实现
数据结构与HashMap一致，并发控制使用 synchronized 和自旋配合 CAS 来操作。 新增了TreeBin和ForwardingNode的概念。

变量 sizeCtl ，它的值决定着当前的初始化状态。
- -1 说明正在初始化
- -N 说明有N-1个线程正在进行扩容
- table为null时，表示 table初始化大小，如果 table 没有初始化
- table不为null时，表示下一次进行扩容的大小，此处与HashMap一致使用0.75的装载因子（n - (n >>> 2);）
- 0 为默认值

ForwardingNode （转移）节点保证扩容时的线程安全。
- 当进行扩容时，要把链表迁移到新的哈希表，在做这个操作时，会在把数组中的头节点替换为ForwardingNode对象。ForwardingNode中不保存key和value，只保存了扩容后哈希表（nextTable）的引用。此时查找相应node时，需要去nextTable中查找。
- 线程put时发现为ForwardingNode会协作进行扩容。完成扩容后，通过for死循环的自旋添加新元素

TreeBin：当链表转为红黑树后，数组中保存的引用为 TreeBin，TreeBin 内部不保存 key/value，他保存了 TreeNode的list以及红黑树 root。充当一颗树的节点锁的概念。

#### get方法
1. 计算出hash位置，通过unsafe的包，保证可见性的获取节点。
2. 若节点的equals方法不匹配，说明存在链表、红黑树或者ForwardingNode。
3. 调用链表节点、红黑树节点或ForwardingNode对应的查找方法。


#### put方法
首先进入一个自旋的for循环
1. 若未初始化，进行初始化操作。设置sizeCtl为-1，表示正在初始化。
2. 若初始化完成，则直接使用可见性获取的操作定位到节点，节点为空则使用CAS设置。
3. 若节点为ForwardingNode，则帮助进行节点转移。
4. 若节点不为空，synchronize锁定节点。若为链表则进行遍历替换或者添加到尾结点。若为红黑树节点，则通过TreeBin进行添加
5. 节点树化判断
6. 检验是否需要扩容
```
final V putVal(K key, V value, boolean onlyIfAbsent) {
    if (key == null || value == null) throw new NullPointerException();
    int hash = spread(key.hashCode());
    int binCount = 0;
    // 自旋
    for (Node<K,V>[] tab = table;;) {
        Node<K,V> f; int n, i, fh;
        // 初始化
        if (tab == null || (n = tab.length) == 0)
            tab = initTable();
        // CAS替换
        else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
       
        }
        // 协助转移
        else if ((fh = f.hash) == MOVED)
            tab = helpTransfer(tab, f);
        else {
            V oldVal = null;
            synchronized (f) {
                if (tabAt(tab, i) == f) {
                    // 链表添加
                    if (fh >= 0) {
                        binCount = 1;
                        ...
                    }
                    // 红黑树添加
                    else if (f instanceof TreeBin) {
                       ...
                    }
                }
            }
            // 节点树化判断
            if (binCount != 0) {
            }
        }
    }
    // 判断是否需要扩容
    addCount(1L, binCount);
    return null;
}
```

#### 扩容 addCount() -> transfer()
1. 建立新table
2. 锁节点，节点转移，旧的table的节点设置为ForwardingNode。便于其他线程识别并帮忙转移。
3. 这里节点转移区分树节点转移和链表转移。



### WeakHashMap
- WeakHashMap 的 Entry 继承自 WeakReference，被 WeakReference 关联的对象在下一次垃圾回收时会被回收。
- WeakHashMap 主要用来实现缓存，通过使用 WeakHashMap 来引用缓存对象，由 JVM 对这部分缓存进行回收。
  - 应用：Tomcat 中的 ConcurrentCache 使用了 WeakHashMap 来实现缓存功能。
  
### TreeMap
定义了一个Entry的节点，基于红黑树的实现
```
  static final class Entry<K,V> implements Map.Entry<K,V> {
        K key;
        V value;
        Entry<K,V> left;
        Entry<K,V> right;
        Entry<K,V> parent;
        boolean color = BLACK;
}
```
## Set
HashSet（无序，唯一）: 基于 HashMap 实现的，底层采用 HashMap 来保存元素

LinkedHashSet：LinkedHashSet 是 HashSet 的子类，并且其内部是通过 LinkedHashMap 来实现的。有点类似于我们之前说的 LinkedHashMap 其内部是基于 HashMap 实现一样，不过还是有一点点区别的

TreeSet（有序，唯一）： 红黑树(自平衡的排序二叉树)

### HashSet
- HashSet是基于HashMap实现的，HashSet中的元素都存放在HashMap的key上面，而value中的值都是统一的一个固定对象private static final Object PRESENT = new Object();
- 为什么没有get方法？ 因为map的get方法是通过Key获取的，而HashSet的应用里面，key都用来存值了。
```
    public HashSet() {
        map = new HashMap<>();
    }
```
### LinkedHashSet实现
- 底层使用LinkedHashMap
```
public LinkedHashSet(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor, true);
}

HashSet(int initialCapacity, float loadFactor, boolean dummy) {
     map = new LinkedHashMap<>(initialCapacity, loadFactor);
 }
```

### TreeSet 实现
- 基于TreeMap实现

## 迭代器
- 迭代器 Iterator 是什么？
```
public interface Iterator<E> {
    //集合中是否还有元素
    boolean hasNext();
    //获得集合中的下一个元素
    E next();
    ......
}
```
Iterator 对象称为迭代器（设计模式的一种），迭代器可以对集合进行遍历，但每一个集合内部的数据结构可能是不尽相同的，所以每一个集合存和取都很可能是不一样的，虽然我们可以人为地在每一个类中定义 hasNext() 和 next() 方法，但这样做会让整个集合体系过于臃肿。于是就有了迭代器。

迭代器是将这样的方法抽取出接口，然后在每个类的内部，定义自己迭代方式，这样做就规定了整个集合体系的遍历方式都是 hasNext()和next()方法

- 迭代器 Iterator 有啥用？
Iterator 主要是用来遍历集合用的，它的特点是更加安全，因为它可以确保，在当前遍历的集合元素被更改的时候，就会抛出 ConcurrentModificationException 异常。


## 其他面试问题
### 如何选用集合?
- 主要根据集合的特点来选用，比如我们需要根据键值获取到元素值时就选用 Map 接口下的集合，需要排序时选择 TreeMap,不需要排序时就选择 HashMap,需要保证线程安全就选用 ConcurrentHashMap。
- 当我们只需要存放元素值时，就选择实现Collection 接口的集合，需要保证元素唯一时选择实现 Set 接口的集合比如 TreeSet 或 HashSet，不需要就选择实现 List 接口的比如 ArrayList 或 LinkedList，然后再根据实现这些接口的集合的特点来选用。

### ArrayList 带参数及不带参数
```
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
```

### Arrays.asList() 方法
Arrays.asList() 方法返回的是Arrays内部的ArrayList，这个ArrayList不支持元素新增及删除，因为未重写抽象父类AbstractList的方法，会抛出UnsupportedOperationException异常。
```
public class Arrays{
 private static class ArrayList<E> extends AbstractList<E>
        implements RandomAccess, java.io.Serializable
    {
        private static final long serialVersionUID = -2764017481108945198L;
        private final E[] a;

        ArrayList(E[] array) {
            a = Objects.requireNonNull(array);
        }
 ...
 }
}
```

### hash 冲突解决方案
1. 链地址法：HashMap中hash冲突节点，使用链表和红黑树解决
2. 线性探测再散列：ThreadLocal中ThreadLocalMap的hash冲突，会线性向后探索直到寻找到向下一个空的节点。
3. 再哈希法。
    > 这种方法是同时构造多个不同的哈希函数：Hi=RH1（key） i=1，2，…，k。当哈希地址Hi=RH1（key）发生冲突时，再计算Hi=RH2（key）……，直到冲突不再产生。

- [Hash冲突的四种解决办法](https://www.cnblogs.com/gongcheng-/p/10894205.html#_label1_0)