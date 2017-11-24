package se.kth.sda.othello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import se.kth.sda.othello.imp.NodeImp;
import se.kth.sda.othello.imp.OthelloFactoryImp;
import se.kth.sda.othello.R;
import se.kth.sda.othello.player.Player;

public class MainActivity extends Activity {
    public static final String GAME_TYPE = "GAME_TYPE";
    public static final String GAME_HUMAN = "HUMAN";
    public static final String GAME_RESULT = "GAME_RESULT";
    public static final String GAME_PLAYERONE = "P1";
    public static final String GAME_PLAYERTWO = "P2";

    OthelloFactory gameFactory = new OthelloFactoryImp();
    Othello game;
    //start: modify by Xin 11.23
    private ImageView turnImage;
    private TextView totlePlayone;
    private TextView totlePlaytow;
    //end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BoardView boardView = (BoardView) findViewById(R.id.boardView);
        //start: modify by Xin 11.23
        turnImage = (ImageView) findViewById(R.id.ture_image);
        totlePlayone=(TextView) findViewById(R.id.totle_playone);
        totlePlaytow=(TextView) findViewById(R.id.totle_playtwo);
        //end
        if (this.getIntent().getExtras().getString(GAME_TYPE).equals(GAME_HUMAN)) {
            game = gameFactory.createHumanGame();
        }

        game.start();

        boardView.setModel(game.getBoard());
        game.moveInitialNodes();                 // add initial coins on the board
        boardView.setEventListener(new BoardView.BoardViewListener() {
            @Override
            public void onClick(int x, int y) {
                String nodeId = NodeImp.format(x, y);
                Player currentPlay = game.getPlayerInTurn();
                try {
                    game.move(currentPlay.getId(), nodeId);
                } catch (IllegalStateException e) {
                    if (e.getMessage().equals("Invalid move")) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                //start: modify by Xin 11.23

                swapPlayerTurnImage(currentPlay);

                totlePlayone.setText(String.valueOf(game.getPlayerScore(GAME_PLAYERONE)));
                totlePlaytow.setText(String.valueOf(game.getPlayerScore(GAME_PLAYERTWO)));

                //end
                boardView.invalidate();
            }
        });
    }

    private void swapPlayerTurnImage(Player currentPlay){
        if (GAME_PLAYERONE.equals(currentPlay.getId())) {
            turnImage.setImageResource(R.mipmap.ic_arrow_right);
        } else if (GAME_PLAYERTWO.equals(currentPlay.getId())) {
            turnImage.setImageResource(R.mipmap.ic_arrow_left);
        }
    }
    public void quitGame(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(GAME_RESULT, "P1");
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
