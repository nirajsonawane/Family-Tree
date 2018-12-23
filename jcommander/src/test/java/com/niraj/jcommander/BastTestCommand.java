package com.niraj.jcommander;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;

import lombok.EqualsAndHashCode.Exclude;

public class BastTestCommand {
	@Parameter
	@Exclude
	private List<String> parameters = new ArrayList<>();


}
