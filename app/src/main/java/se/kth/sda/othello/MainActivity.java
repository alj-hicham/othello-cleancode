package se.kth.sda.othello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Toast;

import java.util.Map;

import se.kth.sda.othello.imp.NodeImp;
import se.kth.sda.othello.imp.OthelloFactoryImp;
import se.kth.sda.othello.R;

public class MainActivity extends Activity {
    public static final String GAME_TYPE = "GAME_TYPE";
    public static final String GAME_HUMAN = "HUMAN";
    public static final String GAME_RESULT = "GAME_RESULT";

    OthelloFactory gameFactory = new OthelloFactoryImp();
    Othello game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BoardView boardView = (BoardView) findViewById(R.id.boardView);

        if (this.getIntent().getExtras().getString(GAME_TYPE).equals(GAME_HUMAN)) {
            game = gameFactory.createHumanGame();
        }

        game.start();

        boardView.setModel(game.getBoard());
        boardView.setEventListener(new BoardView.BoardViewListener() {
            @Override
            public void onClick(int x, int y) {
                String nodeId = NodeImp.format(x,y);
                game.move(game.getPlayerInTurn().getId(), nodeId);
                boardView.invalidate();
            }
        });
    }

    public void quitGame(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(GAME_RESULT, "P1");
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
