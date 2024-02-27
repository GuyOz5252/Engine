package com.project;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private Loop _loop;

    private int _width;
    private int _height;
    private String _title;

    private long _glfwWindow;

    public Window(int width, int height, String title) {
        _width = width;
        _height = height;
        _title = title;
    }

    public void run() {
        initialize();
        _loop.run();

        glfwFreeCallbacks(_glfwWindow);
        glfwDestroyWindow(_glfwWindow);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void initialize() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);

        _glfwWindow = glfwCreateWindow(_width, _height, _title, NULL, NULL);
        if (_glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create GLFW window");
        }

        glfwMakeContextCurrent(_glfwWindow);

        glfwSwapInterval(1);

        glfwShowWindow(_glfwWindow);

        GL.createCapabilities();

        _loop = new Loop(_glfwWindow);
    }
}
