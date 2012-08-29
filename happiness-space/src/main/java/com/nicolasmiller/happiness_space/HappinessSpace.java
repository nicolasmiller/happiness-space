package com.nicolasmiller.happiness_space;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.net.URI;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import com.google.gson.Gson;

public class HappinessSpace {
	public static final String title = "HappinessSpace";
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem loadModel;
	private SynthesizerModel model;
	private MidiCommunicator midicomm;

	public HappinessSpace() {
		midicomm = new MidiCommunicator();
		frame = new JFrame();
		initDefaultView();
		initMenu();
		initFrame();

	}

	private void loadModel(File file) throws Exception {
		Gson gson = new Gson();
		model = gson.fromJson(new FileReader(file), SynthesizerModel.class);
		initView();
	}

	private void initDefaultView() {
		try {
			URI uri = this.getClass().getResource("/EvolverModel.json").toURI();
			loadModel(new File(uri));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void initView() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(14, 3));
		for (Parameter p : model.getParameters()) {
			if (p.hasValueNames()) {
				ParameterComboBox box = new ParameterComboBox(p);
				box.setMidiCommunicator(midicomm);
				panel.add(box);
			} else {
				ParameterSlider slider = new ParameterSlider(
						JSlider.HORIZONTAL, p);
				slider.setMidiCommunicator(midicomm);
				panel.add(slider);
			}
		}
		initContentPane();
	}

	private void initContentPane() {
		JScrollPane scrollPane = new JScrollPane(panel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.setContentPane(scrollPane);
		frame.pack();
		frame.repaint();
	}

	private void initFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.setResizable(true);
		frame.setVisible(true);
	}

	private void initMenu() {
		menu = new JMenu("File");
		loadModel = new JMenuItem("Load Model");
		menuBar = new JMenuBar();

		initLoadModelItem();

		menu.add(loadModel);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
	}

	private void initLoadModelItem() {
		loadModel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						loadModel(chooser.getSelectedFile());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				title);
		HappinessSpace hs = new HappinessSpace();
	}
}