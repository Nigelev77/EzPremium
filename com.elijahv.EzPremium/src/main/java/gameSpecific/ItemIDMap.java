package gameSpecific;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ItemIDMap {
	
	public static Map<String, String> itemIDs = new HashMap<String, String>();
	public static final String path = "../ItemIds";

	
	public static void generateList() {
		
		StringBuilder string = new StringBuilder();
		try {
			InputStream file = ItemIDMap.class.getResourceAsStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(file));
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
