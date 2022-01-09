package com.example.population.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StateTree {

	private String stateCode;
	private String stateDesc;
	private List<DistrictTree> children;	
}
