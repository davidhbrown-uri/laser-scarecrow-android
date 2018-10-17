package edu.uri.david_h_brown.laser_scarecrow;

import android.util.SparseArray;

/**
 * The Configuration is a composite of Parameters.
 */
public class Configuration {
    private static Configuration instance = null;
    private java.util.HashMap<ParameterName, Parameter> parametersByName;
    private SparseArray<Parameter> parametersByCode;

    private Configuration() {
        parametersByName = new java.util.HashMap<>();
        parametersByCode = new SparseArray<>();
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    /**
     * @param p the Parameter being bound to the Configuration
     */
    public void bindParameter(Parameter p) {
        // check for duplicates
        if (parametersByName.containsKey(p.pName()) || parametersByCode.indexOfKey(p.code()) >= 0) {
            throw new RuntimeException();
            /* @todo write a more specific exception to throw */
        }
        parametersByName.put(p.pName(), p);
        parametersByCode.put(p.code(), p);
    }

    public Parameter getParameter(ParameterName pName) {
        if (!parametersByName.containsKey(pName)) {
            throw new RuntimeException();
            /* @todo write a more specific exception to throw */
        }
        return parametersByName.get(pName);
    }

    public Parameter getParameter(int code) {
        int index = parametersByCode.indexOfKey(code);
        if (index < 0) {
            throw new RuntimeException();
            /* @todo write a more specific exception to throw */
        }
        return parametersByCode.valueAt(index);
    }

    public void setParameterValueFromUi(ParameterName pName, String value) {
        getParameter(pName).setFromUi(value);
    }
}
