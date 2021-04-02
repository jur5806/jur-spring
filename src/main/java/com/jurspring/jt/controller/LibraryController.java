package com.jurspring.jt.controller;

//import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jurspring.jt.home.Book;
import com.jurspring.jt.result.Result;
import com.jurspring.jt.result.ResultFactory;
import com.jurspring.jt.service.BookService;
//import com.sun.org.apache.xpath.internal.compiler.Keywords;
import com.jurspring.jt.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;

    @GetMapping("/march/books")
    public Result listBooks() {
        return ResultFactory.buildSuccessResult(bookService.list());
    }

    @PostMapping("/march/admin/content/books")
    public Result addOrUpdateBooks(@RequestBody @Valid Book book) {
        bookService.addOrUpdate(book);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    @PostMapping("/march/admin/content/books/delete")
    public Result deleteBook(@RequestBody @Valid Book book) {
        bookService.deleteById(book.getId());
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @GetMapping("/march/search")
    public Result searchResult(@RequestParam("keywords") String keywords) {
        if ("".equals(keywords)) {
            return ResultFactory.buildSuccessResult(bookService.list());
        } else {
            return ResultFactory.buildSuccessResult(bookService.Search(keywords));
        }
    }

    @GetMapping("/march/categories/{cid}/books")
    public Result listByCategory(@PathVariable("cid") int cid) {
        if (0 != cid) {
            return ResultFactory.buildSuccessResult(bookService.listByCategory(cid));
        } else {
            return ResultFactory.buildSuccessResult(bookService.list());
        }
    }

    @PostMapping("/march/admin/content/books/covers")
    public String coversUpload(MultipartFile file) {
        String folder = "D:/workspace/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/march/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
