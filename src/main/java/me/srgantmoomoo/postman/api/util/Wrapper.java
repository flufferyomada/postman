package me.srgantmoomoo.postman.api.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

//Created by 086 on 11/11/2017.

//got this from game sense, im guessing they got it from kami or smthn... but it was written by 086 - srgantmoomoo

public class Wrapper{

	private static FontRenderer fontRenderer;

	public static Minecraft mc = Minecraft.getMinecraft();

	public static Minecraft getMinecraft(){
		return Minecraft.getMinecraft();
	}

	public static EntityPlayerSP getPlayer(){
		return getMinecraft().player;
	}

	public static World getWorld(){
		return getMinecraft().world;
	}

	public static int getKey(String keyname){
		return Keyboard.getKeyIndex(keyname.toUpperCase());
	}

	public static FontRenderer getFontRenderer(){
		return fontRenderer;
	}
}