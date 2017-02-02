package controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class SecurityController {

	private Engine engine;

	public SecurityController(Engine engine) {
		this.engine = engine;
		this.verifyAuthentication();
	}

	private void verifyAuthentication() {
		NetworkInterface network = null;
		try {
			network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		} catch (SocketException | UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Error: Internet connection is required for security puposes.\nErrorcode: 1", "Error", JOptionPane.ERROR_MESSAGE);
		}
		byte[] mac = null;
		try {
			mac = network.getHardwareAddress();
		} catch (SocketException e) {
			JOptionPane.showMessageDialog(null, "Error: Internet connection is required for security puposes.\nErrorcode: 2", "Error", JOptionPane.ERROR_MESSAGE);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}

		if (this.engine.getStorageController().isEmpty("Security")) {
			this.engine.getStorageController().write("Security", sb.toString());
		} else if (!this.engine.getStorageController().readLine("Security", 0).equals(sb.toString())) {
			this.engine.getStorageController().empty("Account");
			this.engine.getStorageController().empty("Password");
			this.engine.getStorageController().empty("Security");
			this.engine.getStorageController().write("Security", sb.toString());
			JOptionPane.showMessageDialog(null, "New computer detected: Cleaning cache.", "Security violation", JOptionPane.ERROR_MESSAGE);
		}
	}
}
