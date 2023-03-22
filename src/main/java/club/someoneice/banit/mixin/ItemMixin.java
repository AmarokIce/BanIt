package club.someoneice.banit.mixin;

import club.someoneice.banit.BanIt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(at = @At("TAIL"), method = "inventoryTick()V")
    private void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo info) {
        if (!world.isClient) {
            PlayerManager playerManager = world.getServer().getPlayerManager();
            if (playerManager != null && entity instanceof PlayerEntity && !playerManager.isOperator(((PlayerEntity) entity).getGameProfile()) &&
                    BanIt.data.getDefaultList().contains(BanIt.getIDByStack(stack)) && !BanIt.data.getwhitelist().contains(entity.getDisplayName().getString())) {
                ((PlayerEntity) entity).sendMessage(new LiteralText("The item is ban in this server : " + stack.getName().getString()), false);
                stack.setCount(0);
            }
        }
    }
}
