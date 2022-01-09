package com.example.population.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryTree {

	private String countryCode;
	private String countryDesc;
	private List<StateTree> children;
}
