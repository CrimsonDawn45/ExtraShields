package rionlion100.extrashields;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExtraShields implements ModInitializer {

	public Item registerShield(String type,int durability,Item repairItem) {
		Identifier identifier = new Identifier("extrashields", type + "_shield");
		Registry.register(Registry.ITEM, identifier, new CustomShieldItem(new Item.Settings().maxDamage(durability).group(ItemGroup.COMBAT),repairItem));
		return null;
	}	

	@Override
	public void onInitialize() {
		registerShield("oak",128,Items.OAK_PLANKS);
		registerShield("birch",128,Items.BIRCH_PLANKS);
		registerShield("gold",424,Items.GOLD_INGOT);
		registerShield("diamond",612,Items.DIAMOND);
	}
}
