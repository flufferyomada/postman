package me.srgantmoomoo.postman.mixins;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.event.Type;
import me.srgantmoomoo.postman.event.events.EventPacket;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Shadow
    private Channel channel;

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true)
    public void send(Packet<?> packet, CallbackInfo info) {
        EventPacket.Send e = new EventPacket.Send(packet);
        e.setType(Type.PRE);
        Main.INSTANCE.moduleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();

        if(packet instanceof ChatMessageC2SPacket packet1) {
            if (packet1.chatMessage().startsWith(Main.INSTANCE.commandManager.getPrefix())) {
                Main.INSTANCE.commandManager.onClientChat(packet1.chatMessage());
                info.cancel();
            }
        }
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void receive(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo info) {
        EventPacket.Receive e = new EventPacket.Receive(packet);
        e.setType(Type.PRE);
        Main.INSTANCE.moduleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();
    }
}
