package dev.kutuptilkisi.chatchannels.listener;

import dev.kutuptilkisi.chatchannels.ChatChannels;
import dev.kutuptilkisi.chatchannels.chat.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final ChatChannels cc;

    public ChatListener(ChatChannels cc){
        this.cc = cc;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e){
        e.setCancelled(true);
        Chat chat = cc.getManager().getChat(e.getPlayer());
        chat.sendMessage(e.getPlayer(), e.getFormat());
    }

}
