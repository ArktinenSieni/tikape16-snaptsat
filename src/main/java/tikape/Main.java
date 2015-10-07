package tikape;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Session;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.database.Database;
import tikape.database.UserDao;
import tikape.pojo.User;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("org.sqlite.JDBC", "jdbc:sqlite:snaptsat.db");
        database.setDebugMode(true);

        UserDao userDao = new UserDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        post("/login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");

            User user = userDao.findByUsernameAndPassword(username, password);

            if (user == null) {
                res.redirect("/");
                return "";
            }

            req.session(true).attribute("user", user);
            res.redirect("/s/users/" + user.getId());
            return "";
        });

        // all the requests to /s/* will go through a filter, 
        // that determines whether the user is logged in or not
        before((req, res) -> {
            if(!req.url().contains("/s/")) {
                return;
            }
            
            Session sess = req.session(true);
            if (sess.attribute("user") == null) {
                sess.invalidate();
                res.redirect("/");
            }
        });

        // retrieve view for a single user
        get("/s/users/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("user", userDao.findOne(Integer.parseInt(req.params(":id"))));

            // get 10 chat messages and add them to the map
            // NB! use "tsats" as the name for the messages
            
            
            return new ModelAndView(map, "tsats");
        }, new ThymeleafTemplateEngine());

        
        post("/s/tsats", (req, res) -> {
            HashMap map = new HashMap<>();
            User loggedUser = (User) req.session(true).attribute("user");
            
            // TODO: add a new chat message
            // see from "tsats.html" to figure out what the parameters
            // are called
            
            res.redirect("/s/users/" + loggedUser.getId());
            return "";
        });

    }
}
