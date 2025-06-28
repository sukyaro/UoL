import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class IOLabPartOne {

	public IOLabPartOne() {
		//Don't touch, used for testing your code
	}
	
	public IOLabPartOne(String[] args) throws IOException {
		//You can uncomment the code below to test if your function is correct
		//Just run the code and then compare the output in the terminal to programs.txt
		
		File file = new File("programs.txt");
		ArrayList<OurData> data = parseStructuredTextFile(file.toPath());
		for (int i = 0; i < data.size(); i++) {
			System.out.println("Program Description " + (i+1));
			data.get(i).printData();
			System.out.println();
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		new IOLabPartOne(args);
	}

	
	public ArrayList<OurData> parseStructuredTextFile(Path path) throws IOException {
		ArrayList<OurData> ourDataObjects = new ArrayList<>();
		String[] fieldNames = {"problem", "program", "program_length", "passed_tests","total_tests","correct"};

		File file = new File(path.toString());
		ArrayList<String> yourList = (ArrayList<String>) Files.readAllLines(file.toPath());
		OurData currentData = null;

		for (int i = 0; i < yourList.size(); i++) {
			String currentLine = yourList.get(i);
			if (currentLine.isBlank()) {
				if (currentData != null) {
					ourDataObjects.add(currentData);
					currentData = null;
				}
			}

			if (currentLine.startsWith("Program_Description")) {
				currentData = new OurData(fieldNames);
			}

			String[] stringParts = currentLine.split("-", 2);

			if (stringParts.length != 2) {
				continue;
			}

			String currentField = stringParts[0].trim();
			String currentValue = stringParts[1].trim();

			switch (currentField) {
				case "problem":
				case "program":
					currentData.setField(currentField, currentValue);
					break;
				case "program_length":
				case "passed_tests":
				case "total_tests":
					currentData.setField(currentField, Integer.parseInt(currentValue));
					break;
				case "correct":
					currentData.setField(currentField, Boolean.parseBoolean(currentValue));
					break;
			}
		}

		if (currentData != null) {
			ourDataObjects.add(currentData);
		}

		return ourDataObjects;
	}
}
	

