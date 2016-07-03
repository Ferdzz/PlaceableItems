package me.ferdz.placeableitems.proxy;

import me.ferdz.placeableitems.block.tool.BlockSword;
import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		IStateMapper map = new StateMapperBase() {
			
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("placeableitems:tool/block_sword_" + state.getValue(BlockSword.MATERIAL).getName(), "facing=" + state.getValue(BlockSword.FACING).getName() + ",smodel=" + state.getValue(BlockSword.MODEL).getName());
			}
		};

		ModelLoader.setCustomStateMapper(ModBlocks.blockSword, map);
	}

	@Override
	public void checkUpdate() {
	}
}