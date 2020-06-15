package com.learning.offer;

/**
 * <pre>
 * @Description: TODO 翻转句子
 * 翻转句子    =>  I am student.
 * </pre>
 *
 * @version v1.0
 * @ClassName: ReverseSentence
 * @Author: 86159
 * @Date: 2020/3/24 21:00
 */
public class ReverseSentence {
    public static void main(String[] args) {
        String sen = "I am student.";
        String result = new ReverseSentence().reverseRight(sen);
        System.out.println(result);
    }

    public String reversePer(String str) {
        String[] strArray = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strArray.length-1 ; i >=0;i--) {
            sb.append(strArray[i]+ " ");
        }
        return sb.toString();
    }

    public String reverseRight( String str) {
        int n = str.length()-1;
        char[] ch = str.toCharArray();
        int i =0,j =0;
        while (j< n) {
            if(j==n ||ch[j] == ' '){
                reverse(i, j-1, ch);
                i = j+1;
            }
            j++;
        }
        reverse(0 , n-1, ch);
        return new String(ch);
    }

    public void reverse(int i, int j, char[] ch) {
        while (i<j) {
            swap(ch, i++,j--);
        }
    }

    private  void swap(char[] ch, int i, int j) {
        char t = ch[i];
        ch[i] = ch[j];
        ch[j] = t;
    }
}
