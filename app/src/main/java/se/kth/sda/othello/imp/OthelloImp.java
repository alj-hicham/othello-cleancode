package se.kth.sda.othello.imp;

import android.widget.Toast;

import java.util.List;
import java.util.Vector;

import se.kth.sda.othello.Othello;
import se.kth.sda.othello.board.Board;
import se.kth.sda.othello.board.Node;
import se.kth.sda.othello.player.Player;

/**
 * Created by robertog on 2/7/17.
 */
public class OthelloImp implements Othello {
    private final Player playerOne;
    private final Player playerTwo;
    private final BoardImp board;
    private Player currentPlayer;
    List<Node> nodeRes ;

    public OthelloImp(Player one, Player two) {
        this.playerOne = one;
        this.playerTwo = two;
        this.board = new BoardImp();
        nodeRes = new Vector<Node>();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    //start: new method by Aleksandar 11.27
    @Override
    public void setNode(Node node) {
        board.setNode(node);
    }

    //start: modified by Aleksandar 11.26
    @Override
    public List<Node> getNodesToSwap(String playerId, String nodeId) {

        Node nodes[][] = getBoard().getBoardNodes();
        Node node = new NodeImp(nodeId, playerId);
        List<Node> res = new Vector<Node>();
        //res.add(node);
        boolean foundPlayer = false;



        if(!nodes[node.getXCoordinate()][node.getYCoordinate()].isMarked()) {
            // It is going to be used to collect Nodes to flip
            // for every direction seperatly.
            // Once they are transferd in res, dirRes is cleared
            // for the next direction.
            List<Node> dirRes = new Vector<Node>();

            //check NORTH

            int k = node.getXCoordinate();
            int l = node.getYCoordinate()-1;

            if(nodes[k][l].isMarked() &&
                    !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                dirRes.add(nodes[k][l]);
                l--;
                while(l >= 0) {
                    if (nodes[k][l].isMarked() &&
                            !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                        dirRes.add(nodes[k][l]);
                        l--;
                    }
                    else if(!nodes[k][l].isMarked()) {
                        dirRes.clear();
                        break;
                    }
                    else {
                        foundPlayer = true;
                        break;
                    }

                }
            }
            if(foundPlayer && dirRes.size() > 0) {
                for(Node node1 : dirRes) {
                    res.add(node1);
                }
            }
            dirRes.clear();
            foundPlayer = false;


            //check SOUTH

            k = node.getXCoordinate();
            l = node.getYCoordinate()+1;

            if(nodes[k][l].isMarked() &&
                    !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                dirRes.add(nodes[k][l]);
                l++;
                while(l < 8) {
                    if (nodes[k][l].isMarked() &&
                            !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                        dirRes.add(nodes[k][l]);
                        l++;
                    }
                    else if(!nodes[k][l].isMarked()) {
                        dirRes.clear();
                        break;
                    }
                    else {
                        foundPlayer = true;
                        break;
                    }

                }
            }
            if(foundPlayer && dirRes.size() > 0) {
                for(Node node1 : dirRes) {
                    res.add(node1);
                }
            }
            dirRes.clear();
            foundPlayer = false;



            //check EAST

            k = node.getXCoordinate()+1;
            l = node.getYCoordinate();

            if(nodes[k][l].isMarked() &&
                    !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                dirRes.add(nodes[k][l]);
                k++;
                while(k < 8) {
                    if (nodes[k][l].isMarked() &&
                            !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                        dirRes.add(nodes[k][l]);
                        k++;
                    }
                    else if(!nodes[k][l].isMarked()) {
                        dirRes.clear();
                        break;
                    }
                    else {
                        foundPlayer = true;
                        break;
                    }

                }
            }
            if(foundPlayer && dirRes.size() > 0) {
                for(Node node1 : dirRes) {
                    res.add(node1);
                }
            }
            dirRes.clear();
            foundPlayer = false;



            //check WEST

            k = node.getXCoordinate()-1;
            l = node.getYCoordinate();

            if(nodes[k][l].isMarked() &&
                    !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                dirRes.add(nodes[k][l]);
                k--;
                while(k >= 0) {
                    if (nodes[k][l].isMarked() &&
                            !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                        dirRes.add(nodes[k][l]);
                        k--;
                    }
                    else if(!nodes[k][l].isMarked()) {
                        dirRes.clear();
                        break;
                    }
                    else {
                        foundPlayer = true;
                        break;
                    }

                }
            }
            if(foundPlayer && dirRes.size() > 0) {
                for(Node node1 : dirRes) {
                    res.add(node1);
                }
            }
            dirRes.clear();
            foundPlayer = false;


            //check NORTHWEST

            k = node.getXCoordinate()-1;
            l = node.getYCoordinate()-1;

            if(nodes[k][l].isMarked() &&
                    !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                dirRes.add(nodes[k][l]);
                k--;
                l--;
                while(k >= 0 || l >= 0) {
                    if (nodes[k][l].isMarked() &&
                            !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                        dirRes.add(nodes[k][l]);
                        k--;
                        l--;
                    }
                    else if(!nodes[k][l].isMarked()) {
                        dirRes.clear();
                        break;
                    }
                    else {
                        foundPlayer = true;
                        break;
                    }

                }
            }
            if(foundPlayer && dirRes.size() > 0) {
                for(Node node1 : dirRes) {
                    res.add(node1);
                }
            }
            dirRes.clear();
            foundPlayer = false;



            //check SOUTHEAST

            k = node.getXCoordinate()+1;
            l = node.getYCoordinate()+1;

            if(nodes[k][l].isMarked() &&
                    !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                dirRes.add(nodes[k][l]);
                k++;
                l++;
                while(k < 8 || l < 8) {
                    if (nodes[k][l].isMarked() &&
                            !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                        dirRes.add(nodes[k][l]);
                        k++;
                        l++;
                    }
                    else if(!nodes[k][l].isMarked()) {
                        dirRes.clear();
                        break;
                    }
                    else {
                        foundPlayer = true;
                        break;
                    }

                }
            }
            if(foundPlayer && dirRes.size() > 0) {
                for(Node node1 : dirRes) {
                    res.add(node1);
                }
            }
            dirRes.clear();
            foundPlayer = false;


            //check NORTHEAST

            k = node.getXCoordinate()+1;
            l = node.getYCoordinate()-1;

            if(nodes[k][l].isMarked() &&
                    !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                dirRes.add(nodes[k][l]);
                k++;
                l--;
                while(k < 8 || l >= 0) {
                    if (nodes[k][l].isMarked() &&
                            !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                        dirRes.add(nodes[k][l]);
                        k++;
                        l--;
                    }
                    else if(!nodes[k][l].isMarked()) {
                        dirRes.clear();
                        break;
                    }
                    else {
                        foundPlayer = true;
                        break;
                    }

                }
            }
            if(foundPlayer && dirRes.size() > 0) {
                for(Node node1 : dirRes) {
                    res.add(node1);
                }
            }
            dirRes.clear();
            foundPlayer = false;


            //check SOUTHWEST

            k = node.getXCoordinate()-1;
            l = node.getYCoordinate()+1;

            if(nodes[k][l].isMarked() &&
                    !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                dirRes.add(nodes[k][l]);
                k--;
                l++;
                while(k >= 0 || l < 8) {
                    if (nodes[k][l].isMarked() &&
                            !nodes[k][l].getOccupantPlayerId().equals(playerId)) {
                        dirRes.add(nodes[k][l]);
                        k--;
                        l++;
                    }
                    else if(!nodes[k][l].isMarked()) {
                        dirRes.clear();
                        break;
                    }
                    else {
                        foundPlayer = true;
                        break;
                    }

                }
            }
            if(foundPlayer && dirRes.size() > 0) {
                for(Node node1 : dirRes) {
                    res.add(node1);
                }
            }
            dirRes.clear();
            foundPlayer = false;
        }

        if (res.size() > 0) {
            res.add(node);
            return res;
        }
        else {
            return res;
        }

    }

    //start: new method by Aleksandar 11.25
    @Override
    public List<Node> getPossibleMoves() {
        Node nodes[][] = getBoard().getBoardNodes();
        List<Node> res = new Vector<Node>();

        //Test if the nodes array if full
        /*for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                System.out.print(nodes[i][j].getOccupantPlayerId() + "  ");
            }
            System.out.println();
        }*/

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {

                //check NORTH
                if(getPlayerInTurn().getId().equals(nodes[i][j].getOccupantPlayerId()) &&
                        nodes[i][j].getYCoordinate() > 1) {
                    int k = j-1;
                    if(nodes[i][k].isMarked() &&
                            !getPlayerInTurn().getId().equals(nodes[i][k].getOccupantPlayerId())) {
                        k--;
                        while(k >= 0) {
                            if (nodes[i][k].isMarked() &&
                                    !getPlayerInTurn().getId().equals(nodes[i][k].getOccupantPlayerId())) {
                                k--;
                            }
                            else if(!nodes[i][k].isMarked()) {
                                res.add(nodes[i][k]);
                                break;
                            }
                            else break;

                        }
                    }

                }

                //check SOUTH
                if(getPlayerInTurn().getId().equals(nodes[i][j].getOccupantPlayerId()) &&
                        nodes[i][j].getYCoordinate() < 6) {
                    int k = j+1;
                    if(nodes[i][k].isMarked() &&
                            !getPlayerInTurn().getId().equals(nodes[i][k].getOccupantPlayerId())) {
                        k++;
                        while(k < 8) {
                            if (nodes[i][k].isMarked() &&
                                    !getPlayerInTurn().getId().equals(nodes[i][k].getOccupantPlayerId())) {
                                k++;
                            }
                            else if(!nodes[i][k].isMarked()) {
                                res.add(nodes[i][k]);
                                break;
                            }
                            else break;

                        }
                    }

                }

                //check EAST
                if(getPlayerInTurn().getId().equals(nodes[i][j].getOccupantPlayerId()) &&
                        nodes[i][j].getXCoordinate() < 6) {
                    int k = i+1;
                    if(nodes[k][j].isMarked() &&
                            !getPlayerInTurn().getId().equals(nodes[k][j].getOccupantPlayerId())) {
                        k++;
                        while(k < 8) {
                            if (nodes[k][j].isMarked() &&
                                    !getPlayerInTurn().getId().equals(nodes[k][j].getOccupantPlayerId())) {
                                k++;
                            }
                            else if(!nodes[k][j].isMarked()) {
                                res.add(nodes[k][j]);
                                break;
                            }
                            else break;

                        }
                    }

                }

                //check WEST
                if(getPlayerInTurn().getId().equals(nodes[i][j].getOccupantPlayerId()) &&
                        nodes[i][j].getXCoordinate() > 1) {
                    int k = i-1;
                    if(nodes[k][j].isMarked() &&
                            !getPlayerInTurn().getId().equals(nodes[k][j].getOccupantPlayerId())) {
                        k--;
                        while(k >= 0) {
                            if (nodes[k][j].isMarked() &&
                                    !getPlayerInTurn().getId().equals(nodes[k][j].getOccupantPlayerId())) {
                                k--;
                            }
                            else if(!nodes[k][j].isMarked()) {
                                res.add(nodes[k][j]);
                                break;
                            }
                            else break;

                        }
                    }
                }

                //check NORTHWEST
                if(getPlayerInTurn().getId().equals(nodes[i][j].getOccupantPlayerId()) &&
                        (nodes[i][j].getXCoordinate() > 1 && nodes[i][j].getYCoordinate() > 1)) {
                    int k = i-1;
                    int l = j-1;
                    if(nodes[k][l].isMarked() &&
                            !getPlayerInTurn().getId().equals(nodes[k][l].getOccupantPlayerId())) {
                        k--;
                        l--;
                        while(k >= 0 || l >= 0) {
                            if (nodes[k][l].isMarked() &&
                                    !getPlayerInTurn().getId().equals(nodes[k][l].getOccupantPlayerId())) {
                                k--;
                                l--;
                            }
                            else if(!nodes[k][l].isMarked()) {
                                res.add(nodes[k][l]);
                                break;
                            }
                            else break;

                        }
                    }

                }

                //check SOUTHEAST
                if(getPlayerInTurn().getId().equals(nodes[i][j].getOccupantPlayerId()) &&
                        (nodes[i][j].getXCoordinate() > 1 && nodes[i][j].getYCoordinate() > 1)) {
                    int k = i+1;
                    int l = j+1;
                    if(nodes[k][l].isMarked() &&
                            !getPlayerInTurn().getId().equals(nodes[k][l].getOccupantPlayerId())) {
                        k++;
                        l++;
                        while(k < 8 || l < 8) {
                            if (nodes[k][l].isMarked() &&
                                    !getPlayerInTurn().getId().equals(nodes[k][l].getOccupantPlayerId())) {
                                k++;
                                l++;
                            }
                            else if(!nodes[k][l].isMarked()) {
                                res.add(nodes[k][l]);
                                break;
                            }
                            else break;

                        }
                    }
                }

                //check NORTHEAST
                if(getPlayerInTurn().getId().equals(nodes[i][j].getOccupantPlayerId()) &&
                        (nodes[i][j].getXCoordinate() < 6 && nodes[i][j].getYCoordinate() > 1)) {
                    int k = i+1;
                    int l = j-1;
                    if(nodes[k][l].isMarked() &&
                            !getPlayerInTurn().getId().equals(nodes[k][l].getOccupantPlayerId())) {
                        k++;
                        l--;
                        while(k < 8 || l >= 0) {
                            if (nodes[k][l].isMarked() &&
                                    !getPlayerInTurn().getId().equals(nodes[k][l].getOccupantPlayerId())) {
                                k++;
                                l--;
                            }
                            else if(!nodes[k][l].isMarked()) {
                                res.add(nodes[k][l]);
                                break;
                            }
                            else break;

                        }
                    }
                }

                //check SOUTHWEST
                if(getPlayerInTurn().getId().equals(nodes[i][j].getOccupantPlayerId()) &&
                        (nodes[i][j].getXCoordinate() > 1 && nodes[i][j].getYCoordinate() < 6)) {
                    int k = i-1;
                    int l = j+1;
                    if(nodes[k][l].isMarked() &&
                            !getPlayerInTurn().getId().equals(nodes[k][l].getOccupantPlayerId())) {
                        k--;
                        l++;
                        while(k >= 0 || l < 8) {
                            if (nodes[k][l].isMarked() &&
                                    !getPlayerInTurn().getId().equals(nodes[k][l].getOccupantPlayerId())) {
                                k--;
                                l++;
                            }
                            else if(!nodes[k][l].isMarked()) {
                                res.add(nodes[k][l]);
                                break;
                            }
                            else break;

                        }
                    }
                }
            }
        }
        return res;

    }

    @Override
    public Player getPlayerInTurn() {
        return this.currentPlayer;
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> res = new Vector<Player>();
        res.add(playerOne);
        res.add(playerTwo);
        return res;
    }

    @Override
    public boolean hasValidMove(String playerId) {
        return true;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isMoveValid(String playerId, String nodeId) {

        for(Node node:nodeRes){
            if(node.getId().equals(nodeId)){
                return false;
            }
        }

        return true;
    }

    private void swapPlayer() {
        if (currentPlayer == playerOne)
            currentPlayer = playerTwo;
        else
            currentPlayer = playerOne;
    }

    @Override
    public List<Node> move() {
        if (currentPlayer.getType() != Player.Type.COMPUTER)
            throw new IllegalStateException("Current player is not a computer");

        swapPlayer();

        return new Vector<Node>();
    }

    @Override
    public List<Node> move(String playerId, String nodeId) throws IllegalStateException {
        if (currentPlayer.getType() != Player.Type.HUMAN)
            throw new IllegalStateException("Current player is not a human");
        if (! isMoveValid(playerId, nodeId))
            throw new IllegalStateException("Invalid move");
        if (playerId != currentPlayer.getId())
            throw new IllegalStateException("Invalid player ID");

        Node newNode = new NodeImp(nodeId, playerId);
        board.setNode(newNode);
        nodeRes.add(newNode);

        swapPlayer();

        return nodeRes;
    }

    @Override
    /**
     * Make initial 2 moves for each player.
     * Initial nodes are defined in the BoardImp class.
     * @return the list of nodes where the moves are made
     * @author petrych
     */
    public List<Node> moveInitialNodes() {
        List<Node> initialNodes = board.getInitialNodes();
        for (Node node : initialNodes) {
            move(currentPlayer.getId(), node.getId());
        }
        return initialNodes;
    }

    @Override
    public void start() {
        // TODO: choose a random player
        currentPlayer = playerOne;
    }

    @Override
    public void start(String playerId) {
        // TODO: choose the player with the right ID
        currentPlayer = playerOne;
    }

    /**
     * Get the score of the current player by Xin
     * @param playerId current player
     * @return the score of the current player
     */
    public int getPlayerScore(String playerId){
        int score =0;

        for(Node node: nodeRes){
            if(playerId.equals(node.getOccupantPlayerId())){

                score++;
            }
        }
        return score;
    }
}
