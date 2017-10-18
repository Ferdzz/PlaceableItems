package me.ferdz.placeableitems.block;

import net.minecraft.util.BlockRenderLayer;

public class BlockDragonBreath extends BlockBiPosition {

	public BlockDragonBreath(String name) {
		super(name);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
