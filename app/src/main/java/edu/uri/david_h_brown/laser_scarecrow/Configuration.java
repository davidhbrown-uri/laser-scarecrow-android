package edu.uri.david_h_brown.laser_scarecrow;

public class Configuration {
    private static Configuration instance=null;
    private java.util.HashMap<Parameter.Name, Parameter> parameters;
    private Configuration(){
        parameters=new java.util.HashMap<>();
    }
    public static Configuration getInstance(){
        if(instance==null){
            instance=new Configuration();
        }
        return instance;
    }

    /**
     * @param pName the Parameter.Name being bound to the Configuration
     * @param p the Parameter being bound to the Configuration
     */
    public void bindParameter(Parameter.Name pName, Parameter p)
    {
        if(parameters.containsKey(pName)) {
            throw new RuntimeException();
            /* @todo write a more specific exception to throw */
        }
        parameters.put(pName, p);
    }

    public Parameter getParameter(Parameter.Name pName)
    {
        if(! parameters.containsKey(pName)) {
            throw new RuntimeException();
            /* @todo write a more specific exception to throw */
        }
        return parameters.get(pName);
    }

    public void setParameterValueFromUi(Parameter.Name pName, String value)
    {
        getParameter(pName).setFromUi(value);
    }
}
