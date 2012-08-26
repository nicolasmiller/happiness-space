package com.nicolasmiller.happiness_space;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ParameterSlider extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	private JSlider slider;
	private JTextField field;

	public ParameterSlider(int sliderOrientation, int maximum) {
		initializeSlider(sliderOrientation, maximum);
		initializeField();
	}

	private void initializeSlider(int sliderOrientation, int maximum) {
		slider = new JSlider();
		slider.setOrientation(sliderOrientation);
		slider.setMinimum(0);
		slider.setMaximum(maximum);
		slider.setValue(0);
		slider.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				JSlider slider = (JSlider) e.getComponent();
				if (e.getWheelRotation() > 0)
					slider.setValue(slider.getValue() - 1);
				else
					slider.setValue(slider.getValue() + 1);
			}
		});
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Integer value = ((JSlider) e.getSource()).getValue();
				field.setText(value.toString());
			}
		});
		this.add(slider);
	}

	private void initializeField() {
		field = new JTextField(3);
		field.setText("0");
		field.addKeyListener(this);
		field.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				((JTextField) e.getComponent()).setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {

			}

		});
		this.add(field);
	}

	public void setValue() {

	}

	public void getValue() {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			boolean restore = true;
			try {
				int value = Integer.parseInt(((JTextField) e.getComponent())
						.getText());
				if (value <= slider.getMaximum()
						&& value >= slider.getMinimum()) {
					slider.setValue(value);
					restore = false;
				}
			} catch (NumberFormatException ex) {
				// value restored below
			}
			if (restore) {
				field.setText(Integer.toString(slider.getValue()));
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
