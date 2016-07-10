package com.skrymer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by skrymer on 7/07/16.
 */
@Controller
public class WebController {

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public ModelAndView index() {

    ModelAndView model = new ModelAndView();
    model.setViewName("index");

    return model;

  }
}
