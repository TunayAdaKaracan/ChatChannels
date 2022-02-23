package dev.kutuptilkisi.chatchannels.manager;

import dev.kutuptilkisi.chatchannels.ChatChannels;
import dev.kutuptilkisi.chatchannels.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatManager {

    private final List<Chat> chats;

    public ChatManager(ChatChannels cc){
        this.chats = new ArrayList<>();

        FileConfiguration config = cc.getConfig();
        //noinspection ConstantConditions
        for(String str : cc.getConfig().getConfigurationSection("chats").getKeys(false)){
            String path = "chats."+str;
            //noinspection ConstantConditions
            chats.add(new Chat(str, ChatColor.translateAlternateColorCodes('&', config.getString(path+".display")), config.getInt(path+".distance"), config.getBoolean(path+".permission.enabled"), config.getString(path+".permission.enabled")));
        }
    }

    public List<Chat> getChats(){return chats;}

    public Chat getChat(Player p){
        for(Chat chat : chats){
            if(chat.getPlayers().contains(p.getUniqueId())){
                return chat;
            }
        }

        return null;
    }

    public Chat getChat(String name){
        for(Chat chat : chats){
            if(chat.getName().equalsIgnoreCase(name)){
                return chat;
            }
        }
        return null;
    }


    public void switchChat(Player p, String name){
        for(Chat chat : chats){
            if(chat.getPlayers().contains(p.getUniqueId())){
                chat.removePlayer(p);
            } else if(chat.getName().equalsIgnoreCase(name)){
                chat.addPlayer(p);
            }
        }
    }

}
