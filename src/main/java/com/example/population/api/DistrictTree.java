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
public class DistrictTree {

	private String districtCode;
	private String districtDesc;
	private List<ThehsilTree> children;
}
