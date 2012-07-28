package com.nicolasmiller.happiness_space;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;

public class MidiCommunicator {
	public static void printMidiDeviceInfo() throws Exception {
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

	public static void main(String[] args) throws Exception {
		MidiDevice.Info info = MidiSystem.getMidiDeviceInfo()[3];
		try {
			MidiDevice presonus = MidiSystem.getMidiDevice(info);
			presonus.open();
			Receiver r = presonus.getReceiver();
			ShortMessage onMessage = new ShortMessage();
			ShortMessage offMessage = new ShortMessage();
			onMessage.setMessage(ShortMessage.NOTE_ON, 0, 64, 127);
			offMessage.setMessage(ShortMessage.NOTE_OFF, 0, 64, 0);
			// for (int i = 0; i < 10; i++) {
			Integer[] major = { 0, 2, 4, 5, 7, 9 };
			int root = 40;
			while (true) {
				r.send(onMessage, -1);
				Thread.sleep(200);
				r.send(offMessage, -1);
				int key = major[(int) Math.floor(Math.random() * major.length)]
						+ root;
				System.out.println(key);
				onMessage.setMessage(ShortMessage.NOTE_ON, 0, key, 127);
				offMessage.setMessage(ShortMessage.NOTE_OFF, 0, key, 0);
			}
			// presonus.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
