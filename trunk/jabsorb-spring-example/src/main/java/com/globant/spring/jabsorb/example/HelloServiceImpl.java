package com.globant.spring.jabsorb.example;

import org.springframework.stereotype.Service;

import com.globant.jabsorb.JabsorbService;

@JabsorbService(getAlias="pepe", getInterface=HelloService.class)
@Service
public class HelloServiceImpl implements HelloService {

	public HelloServiceImpl() {
		System.out.println("");
	}
	
	public MessageDTO getMessage() {
		return new MessageDTO("KKCK");
	}

	public String getName() {
		return "sasasas";
	}

}
