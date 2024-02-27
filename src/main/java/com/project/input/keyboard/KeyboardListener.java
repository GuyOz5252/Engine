package com.project.input.keyboard;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardListener implements IKeyboardListener {

    private final boolean[] _keysPressed;

    public KeyboardListener() {
        _keysPressed = new boolean[350];
    }

    public void keyCallback(long window, int key, int scanCode, int action, int mods) {
        switch (action) {
            case GLFW_PRESS -> {
                _keysPressed[key] = true;
                System.out.println(key);
            }
            case GLFW_REPEAT -> {

            }
            case GLFW_RELEASE -> {
                _keysPressed[key] = false;
            }
        }
    }

    public boolean isKeyPressed(int key) {
        return _keysPressed[key];
    }
}
