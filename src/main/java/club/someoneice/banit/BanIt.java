package club.someoneice.banit;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class BanIt implements ModInitializer {
	File file = new File(System.getProperty("user.dir") + "\\config\\BanIt", "BanItList.json");
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static Data data;

	@Override
	public void onInitialize() {
		try {
			if (!file.getParentFile().isDirectory()) file.getParentFile().mkdirs();
			if (!file.isFile()) createFile();
			else readFile();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void createFile() throws IOException {
		file.createNewFile();
		List<String> sandman = new ArrayList<>();
		FileOutputStream output = new FileOutputStream(file);
		output.write(gson.toJson(new Data(sandman, sandman)).getBytes());
		output.close();
		data = new Data(sandman, sandman);
	}

	public void readFile() throws IOException {
		FileInputStream input = new FileInputStream(file);
		byte[] buffered = new byte[((Long) file.length()).intValue()];
		input.read(buffered);
		input.close();
		data = gson.fromJson(new String(buffered), Data.class);
	}

	public static String getIDByStack(ItemStack stack) {
		return Registry.ITEM.getId(stack.getItem()).toString();
	}
}
