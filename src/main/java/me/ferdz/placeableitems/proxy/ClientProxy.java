package me.ferdz.placeableitems.proxy;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.tool.BlockSword;
import me.ferdz.placeableitems.event.TextureStichHandler;
import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.state.EnumPreciseFacing;
import me.ferdz.placeableitems.state.tool.EnumSword;
import me.ferdz.placeableitems.state.tool.EnumToolMaterial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		MinecraftForge.EVENT_BUS.register(new TextureStichHandler());
		
		B3DLoader.INSTANCE.addDomain(PlaceableItems.MODID);
		
		IStateMapper swordMap = new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				EnumPreciseFacing facing = state.getValue(BlockSword.FACING);
				EnumToolMaterial material = state.getValue(BlockSword.MATERIAL);
				EnumSword model = state.getValue(BlockSword.MODEL);
				
				// this is used to remove the missing variant errors. The following models never actually happen in game
				if(facing == EnumPreciseFacing.D135 || facing == EnumPreciseFacing.D225 || facing == EnumPreciseFacing.D315 || facing == EnumPreciseFacing.D45) {
					if(model.getName().contains("side"))
						facing = EnumPreciseFacing.D0;
				}
				
				String location = "facing=" + facing.getName() + ",smodel=" + model.getName();
				return new ModelResourceLocation("placeableitems:tool/block_sword_" + material, location);
			}
		};
		
		ModelLoader.setCustomStateMapper(ModBlocks.blockSword, swordMap);
		
//		ClientRegistry.registerTileEntity(TEPotion.class, "tesr_potion", new TESRPotion());
	}
}