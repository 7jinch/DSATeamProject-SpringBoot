package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping
@Slf4j
public class HtmlController {

  @GetMapping("/")
  public String member() {
    log.info("접속");
    return "index";
  }
}
