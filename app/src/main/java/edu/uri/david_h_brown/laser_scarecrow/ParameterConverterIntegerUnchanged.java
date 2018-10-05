package edu.uri.david_h_brown.laser_scarecrow;

import edu.uri.david_h_brown.laser_scarecrow.ParameterConverter;

class ParameterConverterIntegerUnchanged implements ParameterConverter {

    @Override
    public String convert(String s) {
        return Integer.toString(Integer.parseInt(s));
    }
}
