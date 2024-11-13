package net.playwolf.minescript.objects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

public class NPC {
    private static Map<UUID, NPC> npcs = new HashMap<>();

    private UUID id;
    private String name;
    private Location location;
    private Predicate<Player> isVisibleToPlayer;

    public NPC(String name, Location location,
            Predicate<Player> isVisibleToPlayer) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.location = location;
        this.isVisibleToPlayer = isVisibleToPlayer;
        npcs.put(id, this);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isVisibleToPlayer(Player player) {
        return isVisibleToPlayer.test(player);
    }

    public void spawn() {
        // Go through all online players, filter to only players that can see this NPC
        // Send packet to spawn the NPC for each of those players
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (isVisibleToPlayer.test(player)) {
                sendSpawnPacket(player);
            }
        }
    }

    private void sendSpawnPacket(Player player) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);
        // TODO: Continue
    }

    public void despawn() {
        npcs.remove(id);
    }
}