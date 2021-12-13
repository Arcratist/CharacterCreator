package BrettDanSmith.CharaterCreator;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import BrettDanSmith.CharaterCreator.Window.CharacterCreatorWindow;

/**
 * Hello world!
 *
 */
public class App {
	static App INSTANCE;
	Config config;

	public static void main(String[] args) {
		App app = new App(args);
		app.start();
	}

	public App(String[] args) {
		INSTANCE = this;

		String configFileName = "config.cfg";
		if (args.length != 0) {
			configFileName = args[0];
		}
		config = new Config(new File(configFileName), true);
	}

	public void start() {
		Thread shutdownHook = new Thread(() -> exit());
		Runtime.getRuntime().addShutdownHook(shutdownHook);

		config.load();
		try {
			UIManager.setLookAndFeel(
					config.getOrDefault("WINDOW_laf", "dark") == "dark" ? new FlatLightLaf() : new FlatDarkLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CharacterCreatorWindow frame = new CharacterCreatorWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void exit() {
		config.save();
		Runtime.getRuntime().halt(0);
		Runtime.getRuntime().exit(0);
		System.exit(0);
	}

	public static App getApp() {
		return INSTANCE;
	}
}
