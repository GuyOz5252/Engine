package com.project;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Loop {

    private long _glfwWindow;

    public Loop(long glfwWindow) {
        _glfwWindow = glfwWindow;
    }

    public void run() {
        while (!glfwWindowShouldClose(_glfwWindow)) {
            glfwPollEvents();

            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(_glfwWindow);
        }
    }
}
