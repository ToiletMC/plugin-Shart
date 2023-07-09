package net.toiletmc.shart.tasks;

import net.toiletmc.shart.Shart;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class ScatterRainbowShartTask extends BukkitRunnable {
    final private Material[] rainbowItemList = {Material.RED_DYE, Material.ORANGE_DYE, Material.YELLOW_DYE, Material.LIME_DYE, Material.MAGENTA_DYE, Material.PURPLE_DYE};
    final private Material[] rainbowBigItemList = {Material.RED_WOOL, Material.ORANGE_WOOL, Material.YELLOW_WOOL, Material.LIME_WOOL, Material.MAGENTA_WOOL, Material.PURPLE_WOOL};
    private int i = 0;
    final private Player player;
    final private String source;
    final private int maxShart;

    public ScatterRainbowShartTask(Player player) {
        this.player = player;
        this.runTaskTimer(Shart.instance, 1, 2);
        this.source = Shart.instance.getConfig().getString("source");
        this.maxShart = Shart.instance.getConfig().getInt("sharts");
    }

    @Override
    public void run() {
        Random random = new Random();
        i++;

        if (i > maxShart) {
            this.cancel();
        }

        var item = player.getLocation().getWorld().dropItem(player.getLocation().add(0.0, 0.3, 0.0).subtract(player.getLocation().getDirection().normalize().multiply(0.3)), new ItemStack(rainbowItemList[random.nextInt(rainbowItemList.length)]));
        if (source.equals("butt")) {
            item.setVelocity(player.getLocation().getDirection().normalize().multiply(-0.5));
        } else {
            item.setVelocity(new Vector(random.nextFloat() * 2 - 1, 0.5, random.nextFloat() * 2 - 1));
        }
        item.setMetadata("shart", new FixedMetadataValue(Shart.instance, true));
        item.setPickupDelay(Integer.MAX_VALUE);
        player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, item.getLocation().add(0.0, 0.3, 0.0), 1, new Particle.DustOptions(Color.fromRGB(102, 51, 0), 1F));
        new DeleteItemTask(item, 40);

        if (i % 5 == 0) {
            var bigitem = player.getLocation().getWorld().dropItem(player.getLocation().add(0.0, 0.3, 0.0).subtract(player.getLocation().getDirection().normalize().multiply(0.3)), new ItemStack(rainbowBigItemList[random.nextInt(rainbowBigItemList.length)], 2));
            if (source.equals("butt")) {
                bigitem.setVelocity(player.getLocation().getDirection().normalize().multiply(-0.1));
            }
            bigitem.setMetadata("shart", new FixedMetadataValue(Shart.instance, true));
            bigitem.setPickupDelay(Integer.MAX_VALUE);
            new DeleteItemTask(bigitem, 120);
        }
    }
}
