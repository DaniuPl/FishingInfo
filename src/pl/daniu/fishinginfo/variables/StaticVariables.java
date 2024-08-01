package pl.daniu.fishinginfo.variables;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class StaticVariables {

    public static HashMap<UUID, BukkitTask> shedulers = new HashMap<>();
    public static HashMap<UUID, Integer> timers = new HashMap<>();
}
