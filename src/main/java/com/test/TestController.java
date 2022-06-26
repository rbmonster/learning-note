package com.test;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping(value = "/byte-resource/{testId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ByteArrayResource byteArrayResource(@PathVariable("testId") String testId) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/sanwu/private/es-demo/src/main/resources/2021062503821.pdf"));
        ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
        return byteArrayResource;
    }


    @GetMapping("/stream-response/{testId}")
    public void responseTest(@PathVariable("testId") String testId, HttpServletResponse response) throws IOException {
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            byte[] bytes = Files.readAllBytes(Paths.get("/Users/sanwu/private/es-demo/src/main/resources/2021062503821.pdf"));
            outputStream.write(bytes);
            outputStream.flush();
        }catch (Exception e){
            throw new RuntimeException("");
        }
    }

    @GetMapping("/stream-response-name/{testId}")
    public void responseTest11(@PathVariable("testId") String testId, HttpServletResponse response) throws IOException {
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            byte[] bytes = Files.readAllBytes(Paths.get("/Users/sanwu/private/es-demo/src/main/resources/2021062503821.pdf"));
            outputStream.write(bytes);
            outputStream.flush();
        }catch (Exception e){
            throw new RuntimeException("");
        }
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename("sadfasdf.pdf")
                .build();
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
    }

    @GetMapping("/responseEntity/{testId}")
    public ResponseEntity<byte[]> responseEntity(@PathVariable("testId") String testId, HttpServletResponse response) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/sanwu/private/es-demo/src/main/resources/2021062503821.pdf"));
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename("sadfasdf.pdf")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @GetMapping("/bill/{testId}")
    public ResponseEntity<byte[]> bill(@PathVariable("testId") String testId, HttpServletResponse response) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/sanwu/private/learning-note/src/main/resources/bill.xls"));
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
//                .filename("bill.xls")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }
}
