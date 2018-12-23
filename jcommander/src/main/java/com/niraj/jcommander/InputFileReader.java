package com.niraj.jcommander;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class InputFileReader {

	private String fileName;

	public InputFileReader(String fileName) {
		this.fileName = fileName;
	}

	public Stream<String> readFile() throws IOException, URISyntaxException {
		Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		return Files.lines(path);

	}

}
