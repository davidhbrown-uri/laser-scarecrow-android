package edu.uri.david_h_brown.laser_scarecrow;

import android.support.annotation.NonNull;

/**
 * A list of commands which, when sent over the serial interface to the
 * Laser Scarecrow, will cause the indicated behavior.
 */
public enum CommandString {
    MANUAL_CONTROL_DISABLE("S11 0\n"),
    MANUAL_CONTROL_ENABLE("S11 1\n"),
    STEP_FORWARD_TINY("S121 1\n"),
    STEP_FORWARD_SMALL("S121 10\n"),
    STEP_FORWARD_MEDIUM("S121 50\n"),
    STEP_FORWARD_LARGE("S121 100\n"),
    STEP_BACKWARD_TINY("S122 1\n"),
    STEP_BACKWARD_SMALL("S122 10\n"),
    STEP_BACKWARD_MEDIUM("S122 50\n"),
    STEP_BACKWARD_LARGE("S122 100\n"),
    SERVO_GO_MINIMUM("S133 0\n"),
    SERVO_GO_MIDDLE("S133 50\n"),
    SERVO_GO_MAXIMUM("S133 100\n");
    private final String command;

    CommandString(String command) {
        this.command = command;
    }

    @Override
    @NonNull
    public String toString() {
        return command;
    }
}
