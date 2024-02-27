package com.project.input.keyboard;

public interface IKeyboardListener {
    void keyCallback(long window, int key, int scanCode, int action, int mods);
}
