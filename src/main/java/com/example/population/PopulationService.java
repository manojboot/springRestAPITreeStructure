package com.example.population;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.population.api.CountryTree;
import com.example.population.api.DistrictTree;
import com.example.population.api.Population;
import com.example.population.api.PopulationResponse;
import com.example.population.api.StateTree;
import com.example.population.api.ThehsilTree;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PopulationService {

	@Autowired
	ResourceLoader resourceLoader;
	
	public PopulationResponse getPopulationTree() {
		Resource resource = resourceLoader.getResource("classpath:PopulationTree.json");
		ObjectMapper objectMapper = new ObjectMapper();
		PopulationResponse response = new PopulationResponse();
		try {
			List<PopulationResponse> listCar = objectMapper.readValue(resource.getInputStream(),
					new TypeReference<List<PopulationResponse>>() {
					});
			response.setCountryTree(getCountryTree(listCar));
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (PopulationResponse) Collections.EMPTY_LIST;
	}
	
	private List<CountryTree> getCountryTree(List<PopulationResponse> list) {

		List<Population> populationList = list.get(0).getPopulationList();
		List<CountryTree> countryTree = new ArrayList<>();
		Map<String, List<Population>> countryGroupMap = populationList.stream()
				.sorted(Comparator.comparing(Population::getCountryCode))
				.collect(Collectors.groupingBy(Population::getCountryCode, LinkedHashMap::new, Collectors.toList()));
		if (!CollectionUtils.isEmpty(countryGroupMap)) {
			for (String countryCode : countryGroupMap.keySet()) {
				List<Population> p = countryGroupMap.get(countryCode.trim());
				CountryTree country = new CountryTree();
				country.setCountryCode(countryCode);
				country.setCountryDesc(p.get(0).getCountryDesc());
				country.setChildren(getStateTree(p));
				countryTree.add(country);
			}
		}
		return countryTree;
	}

	private List<StateTree> getStateTree(List<Population> gridList){
		log.info("inside the method");
		List<StateTree> stateTree = new ArrayList<>();
		Map<String,List<Population>> stateGroupMap = gridList.stream().sorted(Comparator.comparing(Population::getStateCode))
				.collect(Collectors.groupingBy(Population::getStateCode,LinkedHashMap::new,Collectors.toList()));
		for(Entry<String,List<Population>> stateCd : stateGroupMap.entrySet()) 
		{
			List<Population> list = stateGroupMap.get(stateCd.getKey());
			Map<String,List<Population>> districtGroupMap = gridList.stream().sorted(Comparator.comparing(Population::getDistrictCode))
					.collect(Collectors.groupingBy(Population::getDistrictCode,LinkedHashMap::new,Collectors.toList()));
			StateTree state = new StateTree();
			state.setStateCode(stateCd.getKey());
			state.setStateDesc(list.get(0).getStateDesc());
			List<DistrictTree> districtTree = new ArrayList<>();
			for(Entry<String,List<Population>> districtCd : districtGroupMap.entrySet()) {
				List<Population> districtDetails = districtGroupMap.get(districtCd.getKey());
				List<ThehsilTree> thesilGroupMap = districtDetails.stream()
						.map(x-> new ThehsilTree(x.getThesilDesc(),x.getPopulation(),
						x.getMalePopulation(),x.getFemalePopulation(),x.getHouseHold(),x.getYear())).
						collect(Collectors.toList());
				DistrictTree district = new DistrictTree(districtCd.getValue().get(0).getDistrictCode(),
						districtDetails.get(0).getDistrictDesc(),thesilGroupMap);
				districtTree.add(district);
				state.setChildren(districtTree);
			}
			stateTree.add(state);
		}
		log.info(stateTree.toString());
		return stateTree;
	}

}
