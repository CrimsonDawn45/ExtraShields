package rionlion100.extrashields.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.UseAction;

@Mixin(PlayerEntity.class)
class PlayerEntityMixin{
    public ItemStack self;
    @Redirect(method = "damageShield(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private Item shieldDamageMixin(ItemStack self){
        Item item = self.getItem();
        if (item.getUseAction(self) == UseAction.BLOCK){
            return Items.SHIELD;
        }
        else
            return Items.AIR;
    }
}