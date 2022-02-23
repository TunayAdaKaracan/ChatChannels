package dev.kutuptilkisi.chatchannels;

import dev.kutuptilkisi.chatchannels.command.SwitchCommand;
import dev.kutuptilkisi.chatchannels.listener.ChatListener;
import dev.kutuptilkisi.chatchannels.listener.ConnectionListener;
import dev.kutuptilkisi.chatchannels.manager.ChatManager;
import dev.kutuptilkisi.chatchannels.tabcompleter.SwitchCommandTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatChannels extends JavaPlugin {

    private ChatManager manager;

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        if(!getConfig().getBoolean("enabled")){
            System.out.println("[ChatChannels] Plugin Not Enabled, Shutdowning Plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        try {
            manager = new ChatManager(this);
        }catch(NullPointerException e){
            System.out.println("[ChatChannels] Something is wrong in config");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        if(manager.getChat(getConfig().getString("default-chat")) == null){
            System.out.println("[ChatChannels] General Chat Not Set! Shutdowning Plugin");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);

        //noinspection ConstantConditions
        getCommand("channel").setExecutor(new SwitchCommand(this));
        //noinspection ConstantConditions
        getCommand("channel").setTabCompleter(new SwitchCommandTabCompleter(this));
    }

    public ChatManager getManager(){return manager;}
}
