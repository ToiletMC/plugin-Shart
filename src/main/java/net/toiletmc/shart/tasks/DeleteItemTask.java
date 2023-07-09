package net.toiletmc.shart.tasks;

import net.toiletmc.shart.Shart;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

public class DeleteItemTask extends BukkitRunnable {
    final private Item item;

    public DeleteItemTask(Item item, int time) {
        this.item = item;
        this.runTaskLater(Shart.instance, time);
    }

    @Override
    public void run() {
        item.remove();
        item.removeMetadata("shart", Shart.instance);
    }
}
