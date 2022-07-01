
import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;
import com.github.houbb.markdown.toc.vo.TocGen;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * AtxMarkdownToc 单个文件测试
 *
 * @author author
 * @version 1.0
 * @since 2018-01-30 15:11:47.256
 */

@Slf4j
public class TestTOCGeneration {

    @Test
    public void nLevelTest() {
//        String path = "/Users/sanwuhong/private/github-folder/learning-note/src/main/java/com/four/SHARDING_JDBC.md";
        String rootPath = System.getProperty("user.dir") + "/src/main/java/com";

        File rootFile = new File(rootPath);
        Deque<File> searchPath = new LinkedList<>();
        searchPath.offer(rootFile);
        List<File> markdownFileList = new LinkedList<>();
        while (!searchPath.isEmpty()) {
            File currentFile = searchPath.poll();
            if (!currentFile.isDirectory()) {
                log.error("error file:{}", currentFile.getName());
                return;
            }
            File[] files = currentFile.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (File tmpFile : files) {
                if (tmpFile.isDirectory()) {
                    searchPath.offer(tmpFile);
                } else if (tmpFile.isFile() && isMarkDownFile(tmpFile)) {
                    markdownFileList.add(tmpFile);
                } else {
                    log.debug("ignore file, filename:{}", tmpFile.getName());
                }
            }
        }
        log.info("markdown file size:{}", markdownFileList.size());
        log.info("begin generate file toc======>");
        for (File file : markdownFileList) {
            TocGen tocGen = AtxMarkdownToc.newInstance()
                    .genTocFile(file.getAbsolutePath());
            log.info("toc generate filename:{}", file.getName());
        }
    }

    private boolean isMarkDownFile(File file) {
        if (Objects.isNull(file)) {
            return false;
        }
        return file.getName().endsWith(".md");
    }
}