package gameSpecific;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ItemIDMap {
	
	public static Map<String, String> itemIDs = new HashMap<String, String>();
	public static String path = "src/main/resources/ItemIds";
	
	public static void generateList() {
		
		StringBuilder string = new StringBuilder();
		try {
			FileReader file = new FileReader(new File(path));
			BufferedReader reader = new BufferedReader(file);
			String line;
			while((line=reader.readLine())!=null) {
				string.append(line+"\n");
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] itemsAndIds = string.toString().split("\n");
		for(String item: itemsAndIds) {
			int index = item.indexOf(": ");
			String noSpaceItem = item.substring(index+2);
			Pattern pattern = Pattern.compile("(\\s)+:\\s");
			String[] splitItemId = pattern.split(noSpaceItem);
			if(splitItemId.length==2) {
				itemIDs.put(splitItemId[1], splitItemId[0]);
			}

		}
	}
	
	
	
}
