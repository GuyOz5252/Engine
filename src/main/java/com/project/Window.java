package com.project;

import com.project.input.mouse.IMouseListener;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private Loop _loop;

    private int _width;
    private int _height;
    private String _title;

    private final IMouseListener _mouseListener;

    private long _glfwWindow;

    public Window(int width, int height, String title, IMouseListener mouseListener) {
        _width = width;
        _height = height;
        _title = title;
        _mouseListener = mouseListener;
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
        GLFWErrorCallback.createPrint(System.out).set();

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

        glfwSetCursorPosCallback(_glfwWindow, _mouseListener::mousePositionCallback);
        glfwSetMouseButtonCallback(_glfwWindow, _mouseListener::mouseButtonCallback);
        glfwSetScrollCallback(_glfwWindow, _mouseListener::mouseScrollCallback);

        glfwMakeContextCurrent(_glfwWindow);

        glfwSwapInterval(1);

        glfwShowWindow(_glfwWindow);

        GL.createCapabilities();

        _loop = new Loop(_glfwWindow);
    }
}
