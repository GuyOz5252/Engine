package com.project;

import com.project.input.keyboard.IKeyboardListener;
import com.project.input.mouse.IMouseListener;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private Loop _loop;

    private final int _width;
    private final int _height;
    private final String _title;

    private final IMouseListener _mouseListener;
    private final IKeyboardListener _keyboardListener;

    private long _glfwWindow;

    public Window(int width, int height, String title, IMouseListener mouseListener, IKeyboardListener keyboardListener) {
        _width = width;
        _height = height;
        _title = title;
        _mouseListener = mouseListener;
        _keyboardListener = keyboardListener;
    }

    public void run() {
        initialize();
        _loop.run();

        glfwFreeCallbacks(_glfwWindow);
        glfwDestroyWindow(_glfwWindow);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
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
        glfwSetKeyCallback(_glfwWindow, _keyboardListener::keyCallback);

        glfwMakeContextCurrent(_glfwWindow);

        glfwSwapInterval(1);

        glfwShowWindow(_glfwWindow);

        GL.createCapabilities();

        _loop = new Loop(_glfwWindow);
    }
}
