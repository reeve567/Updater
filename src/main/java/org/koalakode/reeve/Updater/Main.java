package org.koalakode.reeve.Updater;
// made by reeve
// on 11:03 PM

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main extends JavaPlugin {

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("update") || label.equalsIgnoreCase("upd")) {
			if (!sender.hasPermission("Xwy.updater") && !sender.getName().equalsIgnoreCase("Xwy")) {
				return false;
			}

			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Either too many args, or not enough.");
				return true;
			}
			if (args[0].equalsIgnoreCase("CustomEnchants")) {
				Thread download = new Thread(){
					public void run(){

						URL url= null;
						try {
							url = new URL("https://www.dropbox.com/s/e48yelx0e0gngcj/CustomEnchants-0.1.jar?dl=1");
						} catch (MalformedURLException e) {
							sender.sendMessage(ChatColor.RED + "Could not get file");
						}
						String localFilename= System.getProperty("user.dir") + "/plugins/CustomEnchants-0.1.jar"; //needs to be replaced with local file path
						try {
							downloadFromUrl(url, localFilename,sender);
						} catch (IOException e) {
							sender.sendMessage(ChatColor.RED + "Could not get file 1");
						}
					}
				};
				download.start();
			}
			else if (args[0].equalsIgnoreCase("SupplyCrates")) {
				Thread download = new Thread(){
					public void run(){

						URL url= null;
						try {
							url = new URL("https://www.dropbox.com/s/hxlazighgx0dtsl/SupplyCrates-0.1.jar?dl=1");
						} catch (MalformedURLException e) {
							sender.sendMessage(ChatColor.RED + "Could not get file");
						}
						String localFilename= System.getProperty("user.dir") + "/plugins/SupplyCrates-0.1.jar"; //needs to be replaced with local file path
						try {
							downloadFromUrl(url, localFilename,sender);
						} catch (IOException e) {
							sender.sendMessage(ChatColor.RED + "Could not get file");
						}
					}
				};
				download.start();
			}
			else if (args[0].equalsIgnoreCase("Updater")) {
				Thread download = new Thread(){
					public void run(){

						URL url= null;
						try {
							url = new URL("https://www.dropbox.com/s/a6dmwi49vm9a4rk/Updater-RELEASE.jar?dl=1");
						} catch (MalformedURLException e) {
							sender.sendMessage(ChatColor.RED + "Could not get file");
						}
						String localFilename= System.getProperty("user.dir") + "/plugins/Updater-RELEASE.jar"; //needs to be replaced with local file path
						try {
							downloadFromUrl(url, localFilename,sender);
						} catch (IOException e) {
							sender.sendMessage(ChatColor.RED + "Could not get file");
						}
					}
				};
				download.start();
			}
			else if (args[0].equalsIgnoreCase("FactionsShop")) {
				Thread download = new Thread(){
					public void run(){

						URL url= null;
						try {
							url = new URL("https://www.dropbox.com/s/b8djc17xndknkcd/FactionsShop-0.1.jar?dl=1");
						} catch (MalformedURLException e) {
							sender.sendMessage(ChatColor.RED + "Could not get file");
						}
						String localFilename= System.getProperty("user.dir") + "/plugins/Updater-RELEASE.jar"; //needs to be replaced with local file path
						try {
							downloadFromUrl(url, localFilename,sender);
						} catch (IOException e) {
							sender.sendMessage(ChatColor.RED + "Could not get file");
						}
					}
				};
				download.start();
			}
			else {
				sender.sendMessage(ChatColor.GRAY + "This plugin was made for updating Xwy's plugins.");
			}

		}
		return true;
	}

	void downloadFromUrl(URL url, String localFilename,CommandSender sender) throws IOException {
		InputStream is = null;
		FileOutputStream fos = null;

		try {
			URLConnection urlConn = url.openConnection();//connect

			is = urlConn.getInputStream();               //get connection inputstream
			fos = new FileOutputStream(localFilename);   //open outputstream to local file

			byte[] buffer = new byte[4096];              //declare 4KB buffer
			int len;

			//while we have availble data, continue downloading and storing to local file
			while ((len = is.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			sender.sendMessage(ChatColor.GOLD + "Plugin has been downloaded.");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} finally {
				if (fos != null) {
					fos.close();
				}
			}
		}
	}
}
