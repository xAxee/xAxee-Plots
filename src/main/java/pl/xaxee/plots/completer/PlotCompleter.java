package pl.xaxee.plots.completer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.xaxee.plots.PlotPlugin;
import pl.xaxee.plots.manager.PlotManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlotCompleter implements TabCompleter {
    PlotPlugin plotPlugin;
    public PlotCompleter(PlotPlugin plugin) {
        this.plotPlugin = plugin;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if(args.length == 1){
            return plotPlugin.settings.aliases.values().stream().sorted().toList();
        }
        if(args[0].equalsIgnoreCase(plotPlugin.settings.getAliases("mark"))){
            return Arrays.asList("1", "2");
        }
        if(args[0].equalsIgnoreCase(plotPlugin.settings.getAliases("help"))){
            return new ArrayList<String>();
        }
        if(args[0].equalsIgnoreCase(plotPlugin.settings.getAliases("list"))){
            return new ArrayList<String>();
        }
        if(args.length == 2){
            return PlotManager.getPlayerRegions(player);
        }
        if(args.length == 3){
            List<String> players = new ArrayList<String>();
            for(Player p : Bukkit.getOnlinePlayers()){
                players.add(p.getName());
            }
            return players;
        }
        return new ArrayList<String>();
    }
}
