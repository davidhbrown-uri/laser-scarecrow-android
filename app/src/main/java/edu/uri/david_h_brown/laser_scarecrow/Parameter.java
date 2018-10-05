package edu.uri.david_h_brown.laser_scarecrow;

/**
 * A Parameter is implemented in the Laser Scarecrow command protocol
 * using a nonnegative code number that is unique to that parameter.
 * @link https://docs.google.com/document/d/1BEQdWz1lOl8SjxaDAI5R47IBIe5X4mZlMuOE-zMSdLE/
 * To read a parameter, for example, wake/sleep via light or RTC (code 201),
 * the string "L201\n" is sent to the Bluetooth serial port. The response is the code, a
 * space, and the btValue. E.g., "L201 0\n" indicating control via light (btValue 0)
 * To set a parameter, e.g., wake via RTC, the string "S201 1\n" would be sent. The
 * response "ok\n" indicates success; "error(#)\n" otherwise; # is an integer.
 */
public class Parameter {
    private static final char EOL='\n';
    private static final char SPACE=' ';
    public enum Name {
        ROTATION_SPEED,
        SERVO_PULSE_MINIMUM,
        SERVO_PULSE_RANGE,
        WAKE_SLEEP_CYCLE_CONTROL,
        LIGHT_SENSOR_THRESHOLD,
        RTC_WAKE_TIME,
        RTC_SLEEP_TIME,
        RTC_CURRENT_TIME,
        RTC_CURRENT_DATE,
    }
    private Parameter.Name pName;
    private String btValue; // as represented in the serial command protocol
    private int code;
    private ParameterConverter ui2bt;
    private ParameterConverter bt2ui;

    /**
     * Use the long form to specify non-default parameter converters
     * @param pName the Parameter.Name of this parameter
     * @param code the BT protocol code of this parameter
     * @param ui2bt the ParameterConverter to map from UI widget strings to BT serial strings
     * @param bt2ui the ParameterConverter to map from BT serial strings to UI Widget strings
     */
    public Parameter(Parameter.Name pName, int code, ParameterConverter ui2bt, ParameterConverter bt2ui)
    {
        this.pName=pName;
        this.code=code;
        this.ui2bt=ui2bt;
        this.bt2ui=bt2ui;
    }

    /**
     * Use the short form to use the default ParameterConverterIntegerUnchanged btValue conversions
     * @see ParameterConverterIntegerUnchanged
     * @param pName the Parameter.Name of this parameter
     * @param code the command protocol code of this parameter
     */
    public Parameter(Parameter.Name pName, int code)
    {
        this(pName, code, new ParameterConverterIntegerUnchanged(), new ParameterConverterIntegerUnchanged());
    }
    public final String convertUi2Bt(String s){
        return ui2bt.convert(s);
    }
    public final String convertBt2Ui(String s) {
        return bt2ui.convert(s);
    }

    public Name pName() {
        return pName;
    }

    public String getBtValue() {
        return btValue;
    }
    public String getUiValue() {
        return convertBt2Ui(btValue);
    }

    public int getCode() {
        return code;
    }

    public void setUi2bt(ParameterConverter ui2bt) {
        this.ui2bt = ui2bt;
    }

    public void setBt2ui(ParameterConverter bt2ui) {
        this.bt2ui = bt2ui;
    }

    /**
     * Reads the btValue from serial, saves in btValue and returns same
     * @return btValue
     */
    public Parameter readFromBt(){
        StringBuilder message = new StringBuilder("L");
        message.append(code);
        message.append(EOL);

        /* @todo send message via BT serial */
        StringBuilder response = new StringBuilder();
        /* @todo read BT serial into response */
        response.append("");
        btValue = response.toString();
        return this;
    }
    public Parameter setFromUi(String s){
        btValue=convertUi2Bt(s);
        return sendToBt();
    }

    public Parameter sendToBt(){
        StringBuilder message = new StringBuilder("S");
        message.append(code);
        message.append(SPACE);
        message.append(btValue);
        message.append(EOL);
        /* @todo send message via BT serial */
        StringBuilder response = new StringBuilder();
        /* @todo read BT serial into response */
        response.append("");
        /* @todo confirm ok or throw runtime error */
        return this;
    }
}
