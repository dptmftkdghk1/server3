package com.example.problem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

	@GetMapping("/admin")
	public void get_admin(){
	}

    @GetMapping("/")
    public String get_login(){
        return "login";
    }

    @GetMapping("/info")
    public String get_info(
			@RequestParam String id,
			Model model
	){
		if(id.equals("관리자")){
			return "redirect:/admin";
		}else{
			String url = "https://api.odcloud.kr/api/15109950/v1/uddi:1f78fe49-78b4-4784-a5f0-e2c8a60515b4?page=2&perPage=10&serviceKey=WX/qIsVj4inhHOXPUgOGfxI7MQh1LIHyhfli2ch1DGUWsG37xoDDh724K5F05MzVG2OQJuigRzUq63r/nTMfRg==";
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
			Map responseMap = response.getBody();
			List dataList = (List)responseMap.get("data");

			for(Object data : dataList){
				Map d = (Map)data;
				System.out.println(d);
				String a = (String)d.get("가맹점명");
				System.out.println(a);
			}
			System.out.println(dataList);

			model.addAttribute("dataList", dataList);

			return "info";
		}


    }
}
