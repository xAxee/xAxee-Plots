package pl.xaxee.plots.command;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.xaxee.plots.PlotPlugin;
import pl.xaxee.plots.completer.PlotCompleter;
import pl.xaxee.plots.config.Settings;
import pl.xaxee.plots.constructor.User;
import pl.xaxee.plots.manager.PlotManager;

import java.util.HashMap;
import static pl.xaxee.plots.util.ChatUtil.sendMessage;


public class PlotCommand implements CommandExecutor {
    private final Settings settings;
    private PlotPlugin plotPlugin;

    public PlotCommand(PlotPlugin plugin) {
        this.plotPlugin = plugin;
        this.settings = plugin.settings;
        this.plotPlugin.getCommand("plot").setExecutor(this);
        this.plotPlugin.getCommand("plot").setTabCompleter(new PlotCompleter(plotPlugin));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;

        if(args.length == 0) {
            return help(player);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("help"))){
            return help(player);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("list"))){
            return list(player);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("info"))){
            return info(player, args);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("create"))){
            return create(player, args);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("delete"))){
            return delete(player, args);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("mark"))){
            return mark(player, args);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("addMember"))){
            return addMember(player, args);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("removeMember"))){
            return removeMember(player, args);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("addOwner"))){
            return addOwner(player, args);
        }
        if(args[0].equalsIgnoreCase(settings.getAliases("removeOwner"))){
            return removeOwner(player, args);
        }
        return help(player);
    }
    private boolean info(Player player, String[] args){
        String name;
        ProtectedRegion region;
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(player.getWorld()));
        if(args.length == 2){
            name = args[1];
            region = regionManager.getRegion("plot_" + name);
            if(region == null){
                sendMessage(player, settings.get("regionIsNotExist"));
                return true;
            }
        } else {
            region = PlotManager.getRegionAtPlayer(player);
            if(region == null){
                sendMessage(player, settings.get("regionIsNotExist"));
                return true;
            }
            name = region.getId().replace("plot_", "");
        }
        HashMap<String, String> str_var = new HashMap<String, String>();
        str_var.put("{region_id}", region.getId());
        str_var.put("{region_name}", region.getId().replace("plot_", ""));
        str_var.put("{region_owners}", String.join(", ", region.getOwners().getPlayers().stream().toList()));
        str_var.put("{region_members}", String.join(", ", region.getMembers().getPlayers().stream().toList()));
        str_var.put("{region_max}", "(" + region.getMaximumPoint().getX() + ", " + region.getMaximumPoint().getY() + ", " + region.getMaximumPoint().getZ() + ")");
        str_var.put("{region_min}", "(" + region.getMinimumPoint().getX() + ", " + region.getMinimumPoint().getY() + ", " + region.getMinimumPoint().getZ() + ")");
        str_var.put("{region_size}", mod(region.getMinimumPoint().getX() - region.getMaximumPoint().getX()) + " x "+mod(region.getMinimumPoint().getZ() - region.getMaximumPoint().getZ())+" x "+mod(region.getMinimumPoint().getY() - region.getMaximumPoint().getY()));
        str_var.put("{region_area}", mod(region.getMinimumPoint().getX() - region.getMaximumPoint().getX())*mod(region.getMinimumPoint().getZ() - region.getMaximumPoint().getZ()) + "");
        str_var.put("{region_length}", mod(region.getMinimumPoint().getX() - region.getMaximumPoint().getX()) + "");
        str_var.put("{region_width}", mod(region.getMinimumPoint().getZ() - region.getMaximumPoint().getZ()) + "");
        str_var.put("{region_height}", mod(region.getMinimumPoint().getY() - region.getMaximumPoint().getY()) + "");
        sendMessage(player, settings.info, str_var);
        return true;
    }
    private boolean list(Player player){
        String list = String.join(", ", PlotManager.getPlayerRegions(player));
        sendMessage(player, settings.get("list").replace("{list}", list));
        return true;
    }
    private boolean mark(Player player, String[] args){
        if(args.length == 1){
            if(plotPlugin.userManager.getUser(player).sel_1 == null){
                plotPlugin.userManager.getUser(player).sel_1 = player.getLocation();
                sendMessage(player, settings.get("successMark").replace("{mark}", "1"));
                return true;
            }
            if(plotPlugin.userManager.getUser(player).sel_2 == null){
                plotPlugin.userManager.getUser(player).sel_2 = player.getLocation();
                sendMessage(player, settings.get("successMark").replace("{mark}", "2"));
                return true;
            }
            sendMessage(player, settings.get("errorMark"));
            return true;
        }
        if(args[1].equalsIgnoreCase("first") || args[1].equals("1")){
            plotPlugin.userManager.getUser(player).sel_1 = player.getLocation();
            sendMessage(player, settings.get("successMark").replace("{mark}", "1"));
            return true;
        }
        if(args[1].equalsIgnoreCase("second") || args[1].equals("2")){
            plotPlugin.userManager.getUser(player).sel_2 = player.getLocation();
            sendMessage(player, settings.get("successMark").replace("{mark}", "2"));
            return true;
        }
        sendMessage(player, settings.get("errorMark"));
        return true;
    }
    private boolean removeMember(Player player, String[] args){
        if(args.length != 3){
            sendMessage(player, settings.get("wrongUsage"));
            return true;
        }
        String name = args[1];
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(player.getWorld()));
        if(regionManager.getRegion("plot_" + name) == null){
            sendMessage(player, settings.get("regionIsNotExist"));
            return true;
        }
        if(!regionManager.getRegion("plot_" + name).isOwner(player.getName())){
            sendMessage(player, settings.get("notOwner"));
            return true;
        }
        if(!regionManager.getRegion("plot_" + name).getMembers().contains(args[2])){
            sendMessage(player, settings.get("playerIsNotMember"));
            return true;
        }
        if(Bukkit.getPlayer(args[2]) == null){
            sendMessage(player, settings.get("playerNotOnline"));
            return true;
        }
        regionManager.getRegion("plot_" + name).getMembers().removePlayer(args[2]);
        sendMessage(player, settings.get("removeMember").replace("{player}", args[2]).replace("{region}", name));
        return true;
    }
    private boolean addMember(Player player, String[] args){
        if(args.length != 3){
            sendMessage(player, settings.get("wrongUsage"));
            return true;
        }
        String name = args[1];
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(player.getWorld()));
        if(regionManager.getRegion("plot_" + name) == null){
            sendMessage(player, settings.get("regionIsNotExist"));
            return true;
        }
        if(!regionManager.getRegion("plot_" + name).isOwner(player.getName())){
            sendMessage(player, settings.get("notOwner"));
            return true;
        }
        if(regionManager.getRegion("plot_" + name).getMembers().contains(args[2])){
            sendMessage(player, settings.get("playerIsMember"));
            return true;
        }
        if(Bukkit.getPlayer(args[2]) == null){
            sendMessage(player, settings.get("playerNotOnline"));
            return true;
        }
        regionManager.getRegion("plot_" + name).getMembers().addPlayer(args[2]);
        sendMessage(player, settings.get("addMember").replace("{player}", args[2]).replace("{region}", name));
        return true;
    }
    private boolean removeOwner(Player player, String[] args){
        if(args.length != 3){
            sendMessage(player, settings.get("wrongUsage"));
            return true;
        }
        String name = args[1];
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(player.getWorld()));
        if(regionManager.getRegion("plot_" + name) == null){
            sendMessage(player, settings.get("regionIsNotExist"));
            return true;
        }
        if(!regionManager.getRegion("plot_" + name).isOwner(player.getName())){
            sendMessage(player, settings.get("notOwner"));
            return true;
        }
        if(Bukkit.getPlayer(args[2]) == null){
            sendMessage(player, settings.get("playerNotOnline"));
            return true;
        }
        if(!regionManager.getRegion("plot_" + name).isOwner(args[2])){
            sendMessage(player, settings.get("playerIsNotOwner"));
            return true;
        }
        if(regionManager.getRegion("plot_" + name).getOwners().size() == 1){
            sendMessage(player, settings.get("lastOwner"));
            return true;
        }
        regionManager.getRegion("plot_" + name).getOwners().removePlayer(args[2]);
        sendMessage(player, settings.get("removeOwner").replace("{player}", args[2]).replace("{region}", name));
        return true;
    }
    private boolean addOwner(Player player, String[] args){
        if(args.length != 3){
            sendMessage(player, settings.get("wrongUsage"));
            return true;
        }
        String name = args[1];
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(player.getWorld()));
        if(regionManager.getRegion("plot_" + name) == null){
            sendMessage(player, settings.get("regionIsNotExist"));
            return true;
        }
        if(!regionManager.getRegion("plot_" + name).isOwner(player.getName())){
            sendMessage(player, settings.get("notOwner"));
            return true;
        }
        if(Bukkit.getPlayer(args[2]) == null){
            sendMessage(player, settings.get("playerNotOnline"));
            return true;
        }
        regionManager.getRegion("plot_" + name).getOwners().addPlayer(args[2]);
        sendMessage(player, settings.get("addOwner").replace("{player}", args[2]).replace("{region}", name));
        return true;
    }
    private boolean delete(Player player, String[] args){
        if(args.length != 2){
            sendMessage(player, settings.get("wrongUsage"));
            return true;
        }
        String name = args[1];
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(player.getWorld()));
        if(regionManager.getRegion("plot_" + name) == null){
            sendMessage(player, settings.get("regionIsNotExist"));
            return true;
        }
        if(!regionManager.getRegion("plot_" + name).isOwner(player.getName())){
            sendMessage(player, settings.get("notOwner"));
            return true;
        }
        regionManager.removeRegion("plot_" + name);
        sendMessage(player, settings.get("deleteRegion").replace("{region}", name));
        return true;
    }
    private boolean create(Player player, String[] args){
        if(args.length != 2){
            sendMessage(player, settings.get("wrongUsage"));
            return true;
        }
        String name = args[1];
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(player.getWorld()));
        if(regionManager.getRegion("plot_" + name) != null){
            sendMessage(player, settings.get("regionIsExist"));
            return true;
        }
        //todo
        // warunek sprawdzajacy kase
        User user = plotPlugin.userManager.getUser(player);
        if(user.sel_1 == null || user.sel_2 == null){
            sendMessage(player, settings.get("noMarks"));
            return true;
        }
        BlockVector3 min = BlockVector3.at(user.sel_1.getX(),user.sel_1.getY(),user.sel_1.getZ());
        BlockVector3 max = BlockVector3.at(user.sel_2.getX(),user.sel_2.getY(),user.sel_2.getZ());
        ProtectedRegion rg = new ProtectedCuboidRegion("plot_" + name, min, max);

        int size = mod(max.getX() - min.getX())*mod(max.getZ() - min.getZ());
        if(size > settings.max_size){
            sendMessage(player, settings.get("regionIsToolarge"));
            return true;
        }

        if(rg.getIntersectingRegions(regionManager.getRegions().values()).size() > 0){
            sendMessage(player, settings.get("intersectRegion"));
            return true;
        }
        rg.getOwners().addPlayer(user.name);
        user.sel_2 = null;
        user.sel_1 = null;
        regionManager.addRegion(rg);
        sendMessage(player, settings.get("createRegion").replace("{region}", name));
        return true;
    }
    private boolean help(Player player){
        sendMessage(player, settings.help);
        return true;
    }

    private int mod(double number){
        return (int) Math.floor(number > 0 ? number : -number);
    }


}
