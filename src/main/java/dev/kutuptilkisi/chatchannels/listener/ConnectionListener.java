package dev.kutuptilkisi.chatchannels.listener;

import dev.kutuptilkisi.chatchannels.ChatChannels;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private final ChatChannels cc;

    public ConnectionListener(ChatChannels cc){
        this.cc = cc;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        cc.getManager().switchChat(e.getPlayer(), cc.getConfig().getString("default-chat"));
    }

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent e){
        cc.getManager().switchChat(e.getPlayer(), "");
    }

}
