package baubles.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import baubles.api.IBauble;
import baubles.common.Baubles;
import baubles.common.container.InventoryBaubles;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerEntity  {

	@SubscribeEvent
	public void playerTick(PlayerEvent.LivingUpdateEvent event) {
		
		//player events
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entity;
			
			InventoryBaubles baubles = Baubles.instance.playerHandler.getPlayerBaubles(player);
			for (int a=0;a<baubles.getSizeInventory();a++) {
				if (baubles.getStackInSlot(a)!=null && baubles.getStackInSlot(a).getItem() instanceof IBauble) {
					((IBauble)baubles.getStackInSlot(a).getItem()).onWornTick(baubles.getStackInSlot(a), player);
				}
			}
			
		}
		
	}
	
	@SubscribeEvent
	public void playerDeath(LivingDeathEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entity;
			Baubles.instance.playerHandler.getPlayerBaubles(player).dropItems(player);
		}
	}
		
		
}
