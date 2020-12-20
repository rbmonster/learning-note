package com.design.sensitive;

import java.util.*;

/**
 * <pre>
 * @Description:
 * 敏感词过滤设置
 * </pre>
 *
 * @version v1.0
 * @ClassName: SensitiveFiler
 * @Author: sanwu
 * @Date: 2020/12/19 15:31
 */
public class SensitiveFilter {

    private static final String END_FLAG = "end";

    public static final SensitiveWord endWord = new SensitiveWord('*', true, null);

    public static void main(String[] args) {
        Set<String> sensitiveWords=new HashSet<>();
        sensitiveWords.add("你是傻逼");
        sensitiveWords.add("你是傻逼啊");
        sensitiveWords.add("你是坏蛋");
        sensitiveWords.add("你个大笨蛋");
        sensitiveWords.add("我去年买了个表");
        sensitiveWords.add("shit");

        SensitiveFilter textFilter=new SensitiveFilter();
        textFilter.initSensitiveWordsMap(sensitiveWords);
        String text="你你你你是傻逼你，说你呢，你个大笨蛋。";
        System.out.println(textFilter.getSensitiveWords(text,MatchType.MAX_MATCH));
    }

    private Map<Character, SensitiveWord> sensitiveWordsMap;

    private void initSensitiveWordsMap(Set<String> sensitiveWords) {
        if (sensitiveWords == null || sensitiveWords.isEmpty()) {
            throw new IllegalArgumentException("Senditive words must not be empty!");
        }
        sensitiveWordsMap = new HashMap<>(sensitiveWords.size());
        String currentWord;
        Map<Character, SensitiveWord>  currentMap;
        Map<Character, SensitiveWord>  subMap;
        for (String sensitiveWord : sensitiveWords) {
            currentWord = sensitiveWord;
            if (currentWord == null || currentWord.trim().length() < 2) {  //敏感词长度必须大于等于2
                continue;
            }
            currentMap = sensitiveWordsMap;
            for (int i = 0; i < currentWord.length(); i++) {
                char c = currentWord.charAt(i);
                SensitiveWord curSen = currentMap.get(c);
                if (curSen == null) {
                    curSen = new SensitiveWord(c, false, new HashMap<>());
                    subMap = curSen.getChild();
                    currentMap.put(c, curSen);
                    currentMap = subMap;
                } else {
                    currentMap = curSen.getChild();
                }
                if (i == currentWord.length() - 1) {
                    //如果是最后一个字符，则put一个结束标志，这里只需要保存key就行了，value为null可以节省空间。
                    //如果不是最后一个字符，则不需要存这个结束标志，同样也是为了节省空间。
                    curSen.setEndFlag(true);
                }
            }
        }
    }


    public Set<String> getSensitiveWords(String text, MatchType matchType) {
        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException("The input text must not be empty.");
        }
        Set<String> sensitiveWords = new HashSet<>();
        for (int i = 0; i < text.length(); i++) {
            int sensitiveWordLength = getSensitiveWordLength(text, i, matchType);
            if (sensitiveWordLength > 0) {
                String sensitiveWord = text.substring(i, i + sensitiveWordLength);
                sensitiveWords.add(sensitiveWord);
                if (matchType == MatchType.MIN_MATCH) {
                    break;
                }
                i = i + sensitiveWordLength - 1;
            }
        }
        return sensitiveWords;
    }

    public int getSensitiveWordLength(String text, int startIndex, MatchType matchType) {
        char currentChar;
        Map<Character, SensitiveWord> currentMap = sensitiveWordsMap;
        int wordLength = 0;
        boolean endFlag = false;
        for (int i = startIndex; i < text.length(); i++) {
            currentChar = text.charAt(i);
            SensitiveWord curSen = currentMap.get(currentChar);
            if (curSen == null) {
                break;
            } else {
                wordLength++;
                if (curSen.isEndFlag()) {
                    endFlag = true;
                    if (matchType == MatchType.MIN_MATCH) {
                        break;
                    } else {
                        currentMap = curSen.getChild();
                    }
                } else {
                    currentMap =  curSen.getChild();
                }
            }
        }
        if (!endFlag) {
            wordLength = 0;
        }
        return wordLength;
    }

    public enum MatchType {

        MIN_MATCH("最小匹配规则"), MAX_MATCH("最大匹配规则");

        String desc;

        MatchType(String desc) {
            this.desc = desc;
        }
    }

}
