package dev.kutuptilkisi.chatchannels.command;

import dev.kutuptilkisi.chatchannels.ChatChannels;
import dev.kutuptilkisi.chatchannels.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SwitchCommand implements CommandExecutor {

    private final ChatChannels cc;

    public SwitchCommand(ChatChannels cc){
        this.cc = cc;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(args.length == 1 && args[0].equalsIgnoreCase("list")){
                p.sendMessage(ChatColor.AQUA + "Available Chats");
                for(Chat chat : cc.getManager().getChats()){
                    p.sendMessage(chat.getDisplay());
                }
            } else if(args.length == 2 && args[0].equalsIgnoreCase("switch")){
                Chat chat = cc.getManager().getChat(args[1]);
                if(chat.getPermEnabled()){
                    if(p.hasPermission(chat.getPerm())){
                        cc.getManager().switchChat(p, args[1]);
                    } else {
                        p.sendMessage(ChatColor.RED + "You dont have perm to switch to this channel!");
                    }
                } else {
                    cc.getManager().switchChat(p, args[1]);
                }


            } else {
                p.sendMessage(ChatColor.RED + "Wrong usage");
                p.sendMessage(ChatColor.RED + " -/channel list");
                p.sendMessage(ChatColor.RED + " -/channel switch <channel>");
            }

        } else {
            sender.sendMessage("You cant use this command!");
        }

        return false;
    }
}
