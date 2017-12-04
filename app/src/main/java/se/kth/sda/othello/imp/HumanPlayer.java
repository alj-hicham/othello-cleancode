package se.kth.sda.othello.imp;

import se.kth.sda.othello.player.Player;

/**
 * Created by robertog on 2/7/17.
 */
public class HumanPlayer implements Player {
    private  String id;
    private String name;
    private String pwd;

    public HumanPlayer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public HumanPlayer(String id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd=pwd;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Type getType() {
        return Type.HUMAN;
    }
}
