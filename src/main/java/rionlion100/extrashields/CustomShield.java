package rionlion100.extrashields;

import me.crimsondawn45.fabricshieldlib.object.AbstractShield;
import net.minecraft.item.Item;

public class CustomShield extends AbstractShield {

    public CustomShield(Settings settings, int cooldownTicks, int durability, Item... repairItems) {
        super(settings, cooldownTicks, durability, repairItems);
    }
    
}