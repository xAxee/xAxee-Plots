package pl.xaxee.plots;

import org.bukkit.plugin.java.JavaPlugin;
import pl.xaxee.plots.command.PlotCommand;
import pl.xaxee.plots.completer.PlotCompleter;
import pl.xaxee.plots.config.Settings;
import pl.xaxee.plots.manager.UserManager;

public class PlotPlugin extends JavaPlugin {
    public UserManager userManager;
    public Settings settings;
    @Override
    public void onEnable() {
        loadConfig();
        registerManager();
        registerCommands();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void registerCommands(){
        new PlotCommand(this);
    }
    public void loadConfig(){
        this.settings = new Settings(this);
        this.settings.load();
    }
    public void registerManager(){
        this.userManager = new UserManager();
    }
}
