//package me.ferdz.placeableitems.network;
//
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.network.codec.ByteBufCodecs;
//import net.minecraft.network.codec.StreamCodec;
//import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//import net.minecraft.resources.ResourceLocation;
//
//public record PlaceKeyPayload(boolean pressed) implements CustomPacketPayload {
//    public static final Type<PlaceKeyPayload> TYPE =
//            new Type<>(ResourceLocation.fromNamespaceAndPath("placeableitems", "place_key_state"));
//
//    public static final StreamCodec<FriendlyByteBuf, PlaceKeyPayload> CODEC =
//            StreamCodec.composite(
//                    ByteBufCodecs.BOOL,
//                    PlaceKeyPayload::pressed,
//                    PlaceKeyPayload::new
//            );
//
//    @Override
//    public Type<? extends CustomPacketPayload> type() {
//        return TYPE;
//    }
//}
