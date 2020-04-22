package rionlion100.extrashields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.brand.adabraniummod.items.ModItems;

import io.github.cottonmc.resources.BuiltinResources;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExtraShields implements ModInitializer {

	public static Map<ShieldConstructor, CustomShieldItem> shieldMap = new  HashMap<ShieldConstructor, CustomShieldItem>();
	public static ArrayList<ShieldConstructor> shieldList = new ArrayList<ShieldConstructor>();
	private static String MOD_ID = "extrashields";

	private void registerShield() {
		for (int i = 0; i < shieldList.size(); i++){
			ShieldConstructor shield = shieldList.get(i);
			String type = shield.getType();
			int durability = shield.getDurability();
			Item repairItem = shield.getRepairItem();
			shieldMap.put(shield, new CustomShieldItem(new Item.Settings().maxDamage(durability).group(ExtraShields.MAIN_GROUP),repairItem));
			Registry.register(Registry.ITEM, new Identifier(MOD_ID, type + "_shield"), shieldMap.get(shield));
}
	}
	
	public void modIntegrationShields() {
		FabricLoader loader = FabricLoader.getInstance();

		if (loader.isModLoaded("adabraniummod")) {
			shieldList.add(new ShieldConstructor("vibranium" , 786, 50, ModItems.VIBRANIUM_INGOT));
			shieldList.add(new ShieldConstructor("adamantium" , 2218, 50, ModItems.ADAMANTIUM_INGOT));
		}
		if (loader.isModLoaded("cotton-resources")) {
			shieldList.add(new ShieldConstructor("copper", 224, 85, BuiltinResources.COPPER.getIngot().get()));
		}
	}

	public static final ItemGroup MAIN_GROUP = FabricItemGroupBuilder.create(
		new Identifier(MOD_ID, "general"))
		.icon(() -> new ItemStack(shieldMap.get(shieldList.get(0))))
		.appendItems(stacks ->
		{
			for (int i = 0; i < shieldList.size(); i++){
				CustomShieldItem customShield  = shieldMap.get(shieldList.get(i));
				stacks.add(new ItemStack(customShield));
			}
		}).build();

	@Override
	public void onInitialize() {
		shieldList.add(new ShieldConstructor("oak", 128, 200, Items.OAK_PLANKS));
		shieldList.add(new ShieldConstructor("birch", 128, 200, Items.BIRCH_PLANKS));
		shieldList.add(new ShieldConstructor("acacia", 128, 200, Items.BIRCH_PLANKS));
		shieldList.add(new ShieldConstructor("dark_oak", 128, 200, Items.BIRCH_PLANKS));
		shieldList.add(new ShieldConstructor("jungle", 128, 200, Items.BIRCH_PLANKS));
		shieldList.add(new ShieldConstructor("spruce", 128, 200, Items.BIRCH_PLANKS));
		shieldList.add(new ShieldConstructor("gold" , 256, 25, Items.GOLD_INGOT));
		shieldList.add(new ShieldConstructor("diamond" , 512, 50, Items.DIAMOND));
		modIntegrationShields();
		registerShield();
	} 

	public class ShieldConstructor{
		ShieldConstructor(String type, int durablity, int cooldown, Item repairItem) {
			this.type2 = type;
			this.durability2 = durablity;
			this.cooldown2 = cooldown;
			this.repairItem2 = repairItem;
		}

		public String type2;
		public int durability2;
		public int cooldown2;
		public Item repairItem2;

		public String getType() {
			return this.type2;
		}
		public int getDurability() {
			return this.durability2;
		}
		public int getCooldown() {
			return this.cooldown2;
		}
		public Item getRepairItem() {
			return this.repairItem2;
		}
	}
}
