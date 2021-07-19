package com.four.annotation.cotroller;

import com.four.annotation.annotationobj.EnrichUserInfo;
import com.four.annotation.model.Book;
import com.four.annotation.model.BookForm;
import com.four.annotation.model.BookUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private TestService testService;


    @PostMapping("/sell")
    public void sellBook(@RequestBody @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(System.lineSeparator()));
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        // do some logic


    }

    @PostMapping("booking")
    public void bookingBook(@RequestBody BookForm bookForm) {
        log.info("sdfsadf");
        testService.test();
    }
}
