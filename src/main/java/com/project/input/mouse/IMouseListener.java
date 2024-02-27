package com.project.input.mouse;

public interface IMouseListener {
    void mouseButtonCallback(long window, int button, int action, int mods);
    void mouseScrollCallback(long window, double xOffset, double yOffset);
    void mousePositionCallback(long window, double xPosition, double yPosition);
}
