package com.project.utils;

public class Utils {
    public boolean isAnyTrue(boolean[] array) {
        for (boolean b : array) {
            if (b) {
                return true;
            }
        }
        return false;
    }
}
