package dev.kutuptilkisi.chatchannels.tabcompleter;

import dev.kutuptilkisi.chatchannels.ChatChannels;
import dev.kutuptilkisi.chatchannels.chat.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwitchCommandTabCompleter implements TabCompleter {

    private final List<String> chatNames;

    public SwitchCommandTabCompleter(ChatChannels cc){
        chatNames = new ArrayList<>();
        for(Chat chat : cc.getManager().getChats()){
            chatNames.add(chat.getName());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("switch", "list"), new ArrayList<>());
        } else if(args.length == 2){
            return StringUtil.copyPartialMatches(args[1], chatNames, new ArrayList<>());
        } else {
            return new ArrayList<>();
        }
    }
}
