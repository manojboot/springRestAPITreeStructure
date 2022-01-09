package com.example.population.api;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopulationResponse {

	private List<Population> populationList;
	private List<CountryTree> countryTree;
	public List<Population> getPopulationList() {
		return populationList;
	}
	public void setPopulationList(List<Population> populationList) {
		this.populationList = populationList;
	}
	public List<CountryTree> getCountryTree() {
		return countryTree;
	}
	public void setCountryTree(List<CountryTree> countryTree) {
		this.countryTree = countryTree;
	}
	public PopulationResponse(List<Population> populationList, List<CountryTree> countryTree) {
		super();
		this.populationList = populationList;
		this.countryTree = countryTree;
	}
	public PopulationResponse() {
	}
	
}
