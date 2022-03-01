package com.example.population;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.population.api.PopulationResponse;


@RequestMapping("/v1/population/district/census")
@RestController
public class PopulationController {

	@Autowired
	PopulationService populationService;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to this API";
	}

	@GetMapping("/load")
	public PopulationResponse getPopulationTree() {
		
		return populationService.getPopulationTree();
		
	}
}
