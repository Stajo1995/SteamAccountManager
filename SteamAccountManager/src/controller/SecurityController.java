package controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import model.ErrorHandler;

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
			ErrorHandler.crash(1, 'a');
		}
		byte[] mac = null;
		StringBuilder sb = new StringBuilder();
		try {
			mac = network.getHardwareAddress();

			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
		} catch (SocketException e) {
			ErrorHandler.crash(1, 'b');
		}

		if (this.engine.getStorageController().isEmpty("Security")) {
			this.engine.getStorageController().write("Security", sb.toString());
		} else if (!this.engine.getStorageController().readLine("Security", 0).equals(sb.toString())) {
			this.engine.getStorageController().empty("Account");
			this.engine.getStorageController().empty("Password");
			this.engine.getStorageController().empty("Security");
			this.engine.getStorageController().write("Security", sb.toString());
			JOptionPane.showMessageDialog(null, "New computer detected: Cleaning cache.", "Security violation",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
