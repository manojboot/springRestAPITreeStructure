package com.example.population.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Population {

	private String countryCode;
	private String countryDesc;
	private String stateCode;
	private String stateDesc;
	private String districtCode;
	private String districtDesc;
	private String thesilCode;
	private String thesilDesc;
	private String population;
	private String malePopulation;
	private String femalePopulation;
	private String houseHold;
	private String year;

}
