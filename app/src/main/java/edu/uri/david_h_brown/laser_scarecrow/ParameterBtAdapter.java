package edu.uri.david_h_brown.laser_scarecrow;

public class ParameterBtAdapter {
    static final char EOL = '\n';
    static final char SPACE = ' ';

    public static Parameter sendLook(Parameter p) {
        StringBuilder message = new StringBuilder("L");
        message.append(p.code());
        message.append(EOL);
        /* @todo send message via BT serial */
        return p;
    }

    public static Parameter sendStore(Parameter p) {
        StringBuilder message = new StringBuilder("S");
        message.append(p.code());
        message.append(SPACE);
        message.append(p.getBtValue());
        message.append(EOL);
        /* @todo send message via BT serial */
        return p;
    }
}
