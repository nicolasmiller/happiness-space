package com.nicolasmiller.happiness_space;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ParameterComboBox extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox combo;
	private Parameter parameter;
	private MidiCommunicator midicomm;

	public ParameterComboBox(Parameter parameter) {
		this.parameter = parameter;
		initCombo();
		this.add(new JLabel(parameter.getName()));
	}

	private void initCombo() {
		combo = new JComboBox(parameter.getNames());
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		});
		combo.setSelectedIndex(0);
		this.add(combo);
	}

	public void setMidiCommunicator(MidiCommunicator midicomm) {
		this.midicomm = midicomm;
	}
}
