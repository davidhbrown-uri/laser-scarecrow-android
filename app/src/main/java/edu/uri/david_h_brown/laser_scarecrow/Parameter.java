package edu.uri.david_h_brown.laser_scarecrow;

/**
 * A Parameter is implemented in the Laser Scarecrow command protocol
 * using a nonnegative code number that is unique to that parameter.
 *
 * @link https://docs.google.com/document/d/1BEQdWz1lOl8SjxaDAI5R47IBIe5X4mZlMuOE-zMSdLE/
 * To read a parameter, for example, wake/sleep via light or RTC (code 201),
 * the string "L201\n" is sent to the Bluetooth serial port. The response is the code, a
 * space, and the btValue. E.g., "L201 0\n" indicating control via light (btValue 0)
 * To set a parameter, e.g., wake via RTC, the string "S201 1\n" would be sent. The
 * response "ok\n" indicates success; "error(#)\n" otherwise; # is an integer.
 */
public class Parameter {

    private ParameterName pName;
    private String btValue; // as represented in the serial command protocol
    private ParameterConverter ui2bt;
    private ParameterConverter bt2ui;

    /**
     * Use the long form to specify non-default parameter converters
     *
     * @param pName the Parameter.Name of this parameter
     * @param ui2bt the ParameterConverter to map from UI widget strings to BT serial strings
     * @param bt2ui the ParameterConverter to map from BT serial strings to UI Widget strings
     */
    public Parameter(ParameterName pName, ParameterConverter ui2bt, ParameterConverter bt2ui) {
        this.pName = pName;
        this.ui2bt = ui2bt;
        this.bt2ui = bt2ui;
    }

    /**
     * Use the short form to use the default ParameterConverterIntegerUnchanged btValue conversions
     *
     * @param pName the Parameter.Name of this parameter
     * @see ParameterConverterIntegerUnchanged
     */
    public Parameter(ParameterName pName) {
        this(pName, new ParameterConverterIntegerUnchanged(), new ParameterConverterIntegerUnchanged());
    }

    public final String convertUi2Bt(String s) {
        return ui2bt.convert(s);
    }

    public final String convertBt2Ui(String s) {
        return bt2ui.convert(s);
    }

    public ParameterName pName() {
        return pName;
    }

    public String getBtValue() {
        return btValue;
    }

    public String getUiValue() {
        return convertBt2Ui(btValue);
    }

    public Parameter setFromBt(String s) {
        btValue = s;
        return this;
    }

    public Parameter setFromUi(String s) {
        btValue = convertUi2Bt(s);
        return ParameterBtAdapter.sendStore(this);
    }

    public int code() {
        return pName.code();
    }

    public void setUi2bt(ParameterConverter ui2bt) {
        this.ui2bt = ui2bt;
    }

    public void setBt2ui(ParameterConverter bt2ui) {
        this.bt2ui = bt2ui;
    }


}
