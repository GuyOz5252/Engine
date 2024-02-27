package com.project.input.mouse;

import com.project.utils.Utils;
import lombok.Data;
import lombok.Getter;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.system.windows.MOUSEINPUT;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener implements IMouseListener {

    private double _scrollX;
    private double _scrollY;
    private double _xPosition;
    private double _yPosition;
    private double _lastXPosition;
    private double _lastYPosition;
    private boolean[] _mouseButtonsPressed;
    private boolean _isDragging;

    private Utils _utils;

    public MouseListener() {
        _scrollX = 0;
        _scrollY = 0;
        _xPosition = 0;
        _yPosition = 0;
        _lastXPosition = 0;
        _lastYPosition = 0;
        _mouseButtonsPressed = new boolean[8];

        _utils = new Utils();
    }

     public void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            _mouseButtonsPressed[button] = true;
        } else if (action == GLFW_RELEASE) {
            _mouseButtonsPressed[button] = false;
            _isDragging = false;
        }
    }

    public void mouseScrollCallback(long window, double xOffset, double yOffset) {
        _scrollX = xOffset;
        _scrollY = yOffset;
    }

    public void mousePositionCallback(long window, double xPosition, double yPosition) {
        _lastXPosition = _xPosition;
        _lastYPosition = _yPosition;
        _xPosition = xPosition;
        _yPosition = yPosition;

        if (_utils.isAnyTrue(_mouseButtonsPressed)) {
            _isDragging = true;
        }
    }

    private void endFrame() {
        _scrollX = 0;
        _scrollY = 0;
        _lastXPosition = _xPosition;
        _lastYPosition = _yPosition;
    }

    public float getDx() {
        return (float)(_lastXPosition - _xPosition);
    }

    public float getDy() {
        return (float)(_lastYPosition - _yPosition);
    }

    public boolean mouseButtonDown(int button) {
        return _mouseButtonsPressed[button];
    }
}
