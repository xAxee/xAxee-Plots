package pl.xaxee.plots.manager;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlotManager {

    public static List<String> getPlayerRegions(Player player){
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(player.getWorld()));
        List<String> results = new ArrayList<String>();
        for (ProtectedRegion region : regionManager.getRegions().values()) {
            if(!region.isOwner(player.getName())) continue;
            if(!region.getId().contains("plot_")) continue;
            results.add(region.getId().replace("plot_", ""));
        }
        return results;
    }
    public static ProtectedRegion getRegionAtPlayer(Player player) {
        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        List<String> tmp = new ArrayList<>();
        Location playerLocation = player.getLocation();
        ApplicableRegionSet regions = query.getApplicableRegions(BukkitAdapter.adapt(playerLocation));
        for (ProtectedRegion region : regions) {
            if (!region.getId().contains("plot_")) continue;
            return region;
        }
        return null;
    }

}
