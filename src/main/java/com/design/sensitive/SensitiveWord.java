package com.design.sensitive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <pre>
 * @Description:
 * 敏感词汇
 * </pre>
 *
 * @version v1.0
 * @ClassName: SensitiveWord
 * @Author: sanwu
 * @Date: 2020/12/19 15:39
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensitiveWord {

    private char word;
    private boolean endFlag;

    private Map<Character, SensitiveWord> child;

    public SensitiveWord(char word) {
        this.word = word;
    }
}
