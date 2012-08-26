package com.nicolasmiller.happiness_space;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class HappinessSpace {
	public static final String title = "HappinessSpace";

	public static void main(String[] args) throws Exception {
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				title);
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(6, 3));
		for (int i = 0; i < 20; i++) {
			ParameterSlider slider = new ParameterSlider(JSlider.HORIZONTAL,
					127);
			panel.add(slider);
		}

		JScrollPane scrollPane = new JScrollPane(panel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.setResizable(true);
		frame.setContentPane(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}
}
