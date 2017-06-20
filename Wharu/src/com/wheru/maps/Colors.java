package com.wheru.maps;

public enum Colors {
    Red("FF0000"),
    Yellow("FFFF00"),
    Green("008000"),
    Blue("0000FF"),
    Purple("800080"),
    Maroon("800000");

    private String hexValue;

    Colors(String hexValue) {
        this.hexValue = hexValue;
    }

    public String hexValue() {
        return hexValue;
    }
}
