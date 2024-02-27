package com.project;

import com.project.input.keyboard.KeyboardListener;
import com.project.input.mouse.MouseListener;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(
                1600,
                900,
                "Engine",
                new MouseListener(),
                new KeyboardListener()
        );
        window.run();
    }
}
