//package me.ferdz.placeableitems.network;
//
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.entity.player.Player;
//import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
//import net.neoforged.neoforge.network.registration.PayloadRegistrar;
//
//public class NetworkHandler {
//
//    public static void register(RegisterPayloadHandlersEvent event) {
//        PayloadRegistrar registrar = event.registrar("placeableitems");
//
//        registrar.playToServer(
//                PlaceKeyPayload.TYPE,
//                PlaceKeyPayload.CODEC,
//                (payload, ctx) -> {
//                    ctx.enqueueWork(() -> {
//                        Player sender = ctx.player();
//                        if (sender == null) return;
//                        ServerKeyState.setHolding(sender.getUUID(), payload.pressed());
//                    });
//                }
//        );
//    }
//}
