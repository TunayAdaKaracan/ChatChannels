package dev.kutuptilkisi.chatchannels.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chat {

    private final String name;
    private final String display;

    private final int distance;

    private final boolean permEnabled;
    private final String perm;

    private final List<UUID> players;

    public Chat(String name, String display, int distance, Boolean permEnabled, String perm){
        this.name = name;
        this.display = display;

        this.distance = distance;

        this.permEnabled = permEnabled;
        this.perm = perm;

        this.players = new ArrayList<>();
    }

    /*
        GETTERS
     */

    public String getName(){return name;}

    public String getDisplay(){return display;}

    public List<UUID> getPlayers(){return players;}

    public boolean getPermEnabled(){return permEnabled;}

    public String  getPerm(){return perm;}


    /*
        PLAYER MANAGEMENT
     */

    public void addPlayer(Player p){
        if(!players.contains(p.getUniqueId())){
            players.add(p.getUniqueId());
            p.sendMessage(ChatColor.GREEN + "You are switched to: " + display);
        }
    }

    public void removePlayer(Player p){
        players.remove(p.getUniqueId());
    }

    /*
        CORE
     */

    public void sendMessage(Player sender, String message){
        for(UUID target : players){
            Player p = Bukkit.getPlayer(target);
            assert p!=null;
            if(distance != 0) {
                if (!p.getWorld().equals(sender.getWorld())) {
                    continue;
                }

                if(p.getLocation().distance(sender.getLocation()) < distance){
                    p.sendMessage(message);
                }
            } else {
                p.sendMessage(message);
            }
        }
    }


}
