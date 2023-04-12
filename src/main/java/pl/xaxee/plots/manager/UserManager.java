package pl.xaxee.plots.manager;

import org.bukkit.entity.Player;
import pl.xaxee.plots.PlotPlugin;
import pl.xaxee.plots.constructor.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    PlotPlugin plotPlugin;
    public UserManager(PlotPlugin plotPlugin){
        this.plotPlugin = plotPlugin;
    }
    public static List<User> userList;
    public UserManager(){
        this.userList = new ArrayList<User>();
    }
    public User getUser(Player player){
        User user = userList.stream().filter(u -> u.name.equals(player.getName())).findFirst().orElse(addUser(player));

        return user;
    }
    public User addUser(Player player){
        User user = new User(player.getName());
        userList.add(user);
        return user;
    }
}
