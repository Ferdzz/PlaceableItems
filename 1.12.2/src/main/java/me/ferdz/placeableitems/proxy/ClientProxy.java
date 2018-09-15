package me.ferdz.placeableitems.proxy;

import org.lwjgl.input.Keyboard;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.tool.BlockSword;
import me.ferdz.placeableitems.event.TextureStichHandler;
import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.init.ModItems;
//import me.ferdz.placeableitems.init.ModItems;
import me.ferdz.placeableitems.state.EnumPreciseFacing;
import me.ferdz.placeableitems.state.tool.EnumSword;
import me.ferdz.placeableitems.state.tool.EnumToolMaterial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	public static KeyBinding keybindingPlaceableItems;
	
	@Override
	public void keybindHandler() {
		keybindingPlaceableItems = new KeyBinding("Place Item Key", Keyboard.KEY_LSHIFT, "Placeable Items");
		ClientRegistry.registerKeyBinding(ClientProxy.keybindingPlaceableItems);
	}
	
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
	}
	
	@Override
	public void registerTESR() {

	}
	
 	@Override
  	public void registerItemRenderers() {
		// Item renderer	
		this.registerModel(ModItems.itemPlate, "placeableitems:item_plate");
		this.registerModel(ModItems.itemSaddleStand, "placeableitems:item_saddle_stand");
		this.registerModel(ModItems.itemHorseArmorStand, "placeableitems:item_horse_armor_stand");
	}
	
	private void registerModel(Item item, String path)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(path, "inventory"));
	}
}