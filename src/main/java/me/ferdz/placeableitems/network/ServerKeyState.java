//package me.ferdz.placeableitems.network;
//
//import net.minecraft.server.level.ServerPlayer;
//
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class ServerKeyState {
//
//    private static final ConcurrentHashMap<UUID, Boolean> holding = new ConcurrentHashMap<>();
//
//    public static void setHolding(UUID player, boolean held) {
//        holding.put(player, held);
//    }
//
//    public static boolean isHolding(UUID player) {
//        return holding.getOrDefault(player, false);
//    }
//}
