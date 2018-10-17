package edu.uri.david_h_brown.laser_scarecrow;

public enum ParameterName {
    ROTATION_SPEED(101),
    SERVO_PULSE_MINIMUM(131),
    SERVO_PULSE_RANGE(132),
    WAKE_SLEEP_CYCLE_CONTROL(201),
    LIGHT_SENSOR_THRESHOLD(221),
    RTC_WAKE_TIME(261),
    RTC_SLEEP_TIME(262),
    RTC_CURRENT_TIME(251),
    RTC_CURRENT_DATE(252);
    private int code;

    ParameterName(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

}
