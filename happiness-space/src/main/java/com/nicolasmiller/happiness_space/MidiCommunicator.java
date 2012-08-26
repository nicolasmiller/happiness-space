package com.nicolasmiller.happiness_space;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;

public class MidiCommunicator {
	private Receiver r;

	public MidiCommunicator() {
		try {
			printMidiDeviceInfo();
			MidiDevice.Info info = MidiSystem.getMidiDeviceInfo()[3];
			MidiDevice presonus = MidiSystem.getMidiDevice(info);
			presonus.open();
			this.r = presonus.getReceiver();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void printMidiDeviceInfo() throws Exception {
		int j = 0;
		for (MidiDevice.Info i : MidiSystem.getMidiDeviceInfo()) {
			System.out.println("Midi Device " + j);
			System.out.println(i.getName());
			System.out.println(i.getVendor());
			System.out.println(i.getVersion());
			System.out.println(i.getDescription());
			MidiDevice device = MidiSystem.getMidiDevice(i);
			device.open();
			System.out.println("max receivers: " + device.getMaxReceivers());
			System.out.println("max transmitters: "
					+ device.getMaxTransmitters());
			for (Receiver r : device.getReceivers()) {
				System.out.println("receiver: ");
				System.out.println(r);
			}
			for (Transmitter t : device.getTransmitters()) {
				System.out.println("transmitter: ");
				System.out.println(t);
			}
			System.out.println();
			device.close();
			j++;
		}
	}

	public void playNote(int note_num, int duration) {
		try {
			ShortMessage onMessage = new ShortMessage();
			ShortMessage offMessage = new ShortMessage();
			onMessage.setMessage(ShortMessage.NOTE_ON, 0, note_num, 127);
			offMessage.setMessage(ShortMessage.NOTE_OFF, 0, note_num, 0);
			r.send(onMessage, -1);
			Thread.sleep(duration);
			r.send(offMessage, -1);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void sendControllerMessage(int number, int value) {
		try {
			ShortMessage message = new ShortMessage();
			message.setMessage(ShortMessage.CONTROL_CHANGE, 0, number, value);
			r.send(message, -1);
		} catch (InvalidMidiDataException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		/*
		 * Integer[] major = { 0, 2, 4, 5, 7, 9 }; int root = 40;
		 * 
		 * for (int i = 40; i < 60; i += 2) { m.playNote(i, 200); } for (int i =
		 * 60; i > 40; i -= 2) { m.playNote(i, 200); }
		 */

		MidiCommunicator m = new MidiCommunicator();
		while (true) {
			/*
			 * int note = major[(int) Math.floor(Math.random() * major.length)]
			 * + root; int duration = (int) Math.floor(Math.random() * 300) +
			 * 20;
			 */
			int freq = (int) Math.floor(Math.random() * 127);
			m.playNote(40, 200);
			m.sendControllerMessage(freq, 52);
		}
	}
}
