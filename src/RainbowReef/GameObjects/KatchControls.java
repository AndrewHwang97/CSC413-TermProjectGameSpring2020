package RainbowReef.GameObjects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KatchControls implements KeyListener {

    private Katch katch;

    private final int right;
    private final int left;
    private final int launch;

    public KatchControls(Katch katch, int left, int right, int launch) {
        this.katch = katch;
        this.right = right;
        this.left = left;
        this.launch = launch;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();

        if (keyPressed == left) {
            this.katch.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.katch.toggleRightPressed();
        }
        if (keyPressed == launch) {
            this.katch.toggleLaunchPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == left) {
            this.katch.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.katch.unToggleRightPressed();
        }
        if (keyReleased  == launch) {
            this.katch.unToggleLaunchPressed();
        }
    }
}
