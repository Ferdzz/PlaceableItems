package com.stuntmania.placeableitems.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.stuntmania.placeableitems.proxy.ClientProxy;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegistryUtil {
	
	public static void addToTERegistry(Class<? extends TileEntity> TEClass, String id) {
		ModBlocks.TERegistry.put(TEClass, id);
	}
	
	public static void addToTESRRegistry(Class <? extends TileEntity> TEClass, TileEntitySpecialRenderer tesr) {
		ClientProxy.TESRRegistry.put(TEClass, tesr);
	}
	
	public static void addToBlockRegistry(Block block, String name) {
		block.setBlockName(name);
		GameRegistry.registerBlock(block, name);
	}
	
	public static Block fullRegister(Block block, String name, Class<? extends TileEntity> TEClass, TileEntitySpecialRenderer tesr) {
		addToBlockRegistry(block, name);
		addToTERegistry(TEClass, name);
		addToTESRRegistry(TEClass, tesr);
		return block;
	}
}
