package com.piu.game;

import com.piu.game.Pius.HumanBrain;
import com.piu.engine.Keyboard.IKeyConcreteHandler;

import java.awt.event.KeyEvent;

public class PlayerMoverConcreteKeyHandler implements IKeyConcreteHandler {
    private HumanBrain player;

    public PlayerMoverConcreteKeyHandler(HumanBrain player){

        this.player = player;
    }
    @Override
    public boolean tryHandleKeyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_A:
                player.startMoveLeftCommand();
                return true;
            case KeyEvent.VK_D:
                player.startMoveRightCommand();
                return true;
            case KeyEvent.VK_W:
                player.startMoveUpCommand();
                return true;
            case KeyEvent.VK_S:
                player.startMoveDownCommand();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean tryHandleKeyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_A:
                player.finishMoveLeftCommand();
                return true;
            case KeyEvent.VK_D:
                player.finishMoveRightCommand();
                return true;
            case KeyEvent.VK_W:
                player.finishMoveUpCommand();
                return true;
            case KeyEvent.VK_S:
                player.finishMoveDownCommand();
                return true;
            default:
                return false;
        }
    }
}
