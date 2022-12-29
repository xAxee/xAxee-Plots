package pl.xaxee.plots.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.xaxee.plots.PlotPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Settings {
    private final PlotPlugin plugin;
    public int max_size;
    public HashMap<String, String> messages = new HashMap<String, String>();
    public HashMap<String, String> aliases = new HashMap<>();
    public List<String> help = new ArrayList<String>();
    public List<String> info = new ArrayList<String>();

    public Settings(PlotPlugin plugin){
        this.plugin = plugin;
    }
    public void load(){
        this.plugin.saveDefaultConfig();
        FileConfiguration config = this.plugin.getConfig();
        // Var
        max_size = config.getInt("plot.max_size");
        
        // Errors
        messages.put("wrongUsage", config.getString("messages.errors.wrongUsage"));
        messages.put("playerNotOnline", config.getString("messages.errors.playerNotOnline"));
        messages.put("notOwner", config.getString("messages.errors.notOwner"));
        messages.put("errorMark", config.getString("messages.errors.errorMark"));
        messages.put("noMarks", config.getString("messages.errors.noMarks"));
        messages.put("playerIsNotMember", config.getString("messages.errors.playerIsNotMember"));
        messages.put("playerIsMember", config.getString("messages.errors.playerIsMember"));
        messages.put("playerIsNotOwner", config.getString("messages.errors.playerIsNotOwner"));
        messages.put("regionIsNotExist", config.getString("messages.errors.regionIsNotExist"));
        messages.put("regionIsExist", config.getString("messages.errors.regionIsExist"));
        messages.put("lastOwner", config.getString("messages.errors.lastOwner"));
        messages.put("intersectRegion", config.getString("messages.errors.intersectRegion"));
        messages.put("regionIsToolarge", config.getString("messages.errors.regionIsToolarge"));

        // Messages
        messages.put("removeMember", config.getString("messages.messages.removeMember"));
        messages.put("addMember", config.getString("messages.messages.addMember"));
        messages.put("deleteRegion", config.getString("messages.messages.deleteRegion"));
        messages.put("createRegion", config.getString("messages.messages.createRegion"));
        messages.put("successMark", config.getString("messages.messages.successMark"));
        messages.put("removeOwner", config.getString("messages.messages.removeOwner"));
        messages.put("addOwner", config.getString("messages.messages.addOwner"));
        messages.put("list", config.getString("messages.messages.list"));

        // Help messages
        help = config.getStringList("messages.help");
        // Info message
        info = config.getStringList("messages.info");

        // Subcommand aliases
        aliases.put("help", config.getString("aliases.help"));
        aliases.put("mark", config.getString("aliases.mark"));
        aliases.put("create", config.getString("aliases.create"));
        aliases.put("delete", config.getString("aliases.delete"));
        aliases.put("info", config.getString("aliases.info"));
        aliases.put("addMember", config.getString("aliases.addMember"));
        aliases.put("removeMember", config.getString("aliases.removeMember"));
        aliases.put("addOwner", config.getString("aliases.addOwner"));
        aliases.put("removeOwner", config.getString("aliases.removeOwner"));
        aliases.put("list", config.getString("aliases.list"));
    }
    public String get(String key){
        return messages.get(key);
    }
    public String getAliases(String key){
        return aliases.get(key);
    }
}
