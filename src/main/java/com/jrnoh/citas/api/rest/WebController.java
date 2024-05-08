package com.jrnoh.citas.api.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
  
   @RequestMapping(value = "/")
   public String consulta() {
      return "consulta";
   }
   
   @RequestMapping(value = "/alta")
   public String alta() {
      return "alta";
   }
}