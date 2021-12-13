package BrettDanSmith.CharaterCreator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;

public class Config {

	private File configFile;
	private boolean generateDefault;

	private HashMap<String, String> properties = new HashMap<String, String>();

	public Config(File configFile, boolean generateDefault) {
		properties.clear();
		this.configFile = configFile;
		this.generateDefault = generateDefault;
	}

	public Config(File configFile) {
		this(configFile, false);
	}

	public void load() {
		System.out.println("Attempting to load configuration file: " + configFile.getAbsolutePath());
		if (configFile.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] data = line.split(";");
					properties.put(data[0], data[1]);
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Failed to load configuration file: " + configFile.getAbsolutePath());
				return;
			}
		} else if (this.generateDefault) {

			put("WINDOW_laf", "dark");

			save();
		}
		System.out.println("Loaded configuration file: " + configFile.getAbsolutePath());
	}

	public void save() {
		System.out.println("Attempting to save configuration file: " + configFile.getAbsolutePath());
		try (BufferedWriter writer = Files.newBufferedWriter(configFile.toPath(), Charset.forName("UTF-8"))) {
			String configString = "";

			for (String key : properties.keySet()) {
				String newLine = key + ";" + properties.getOrDefault(key, "NULL").strip() + "\n";
				configString += newLine;
			}

			writer.write(configString);
			System.out.println("Saved configuration file: " + configFile.getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("Failed to save configuration file: " + configFile.getAbsolutePath());
			return;
		}
	}

	public String getOrDefault(String key, String defaultValue) {
		return properties.getOrDefault(key, defaultValue);
	}

	public void put(String key, String value) {
		properties.put(key, value);
	}

}
