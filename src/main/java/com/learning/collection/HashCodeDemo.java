package com.learning.collection;//: containers/CountedString.java
// Creating a good hashCode().

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashCode 的demo
 */
public class HashCodeDemo {
  private static List<String> created =
    new ArrayList<String>();
  private String s;
  private int id = 0;
  public HashCodeDemo(String str) {
    s = str;
    created.add(s);
    // id is the total number of instances
    // of this string in use by CountedString:
    for(String s2 : created)
      if(s2.equals(s))
        id++;
  }
  public String toString() {
    return "String: " + s + " id: " + id +
      " hashCode(): " + hashCode();
  }
  public int hashCode() {
    // The very simple approach:
    // return s.hashCode() * id;
    // Using Joshua Bloch's recipe:
    int result = 17;
    result = 37 * result + s.hashCode();
    result = 37 * result + id;
    return result;
  }
  public boolean equals(Object o) {
    return o instanceof HashCodeDemo &&
      s.equals(((HashCodeDemo)o).s) &&
      id == ((HashCodeDemo)o).id;
  }
  public static void main(String[] args) {
    Map<HashCodeDemo,Integer> map =
      new HashMap<HashCodeDemo,Integer>();
    HashCodeDemo[] cs = new HashCodeDemo[5];
    for(int i = 0; i < cs.length; i++) {
      cs[i] = new HashCodeDemo("hi");
      map.put(cs[i], i); // Autobox int -> Integer
    }
    System.out.println(map);
    for(HashCodeDemo cstring : cs) {
      System.out.println("Looking up " + cstring);
      System.out.println(map.get(cstring));
    }
  }
} /* Output: (Sample)
{String: hi id: 4 hashCode(): 146450=3, String: hi id: 1 hashCode(): 146447=0, String: hi id: 3 hashCode(): 146449=2, String: hi id: 5 hashCode(): 146451=4, String: hi id: 2 hashCode(): 146448=1}
Looking up String: hi id: 1 hashCode(): 146447
0
Looking up String: hi id: 2 hashCode(): 146448
1
Looking up String: hi id: 3 hashCode(): 146449
2
Looking up String: hi id: 4 hashCode(): 146450
3
Looking up String: hi id: 5 hashCode(): 146451
4
*///:~
