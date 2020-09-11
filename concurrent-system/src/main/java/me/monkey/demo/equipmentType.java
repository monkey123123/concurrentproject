package me.monkey.demo;
public enum equipmentType {

        CPE("SDW-CPE"),

        GW("SDW-GW");

    private String value;

    equipmentType(String value) {

        this.value = value;

    }



    public String getValue() {

        return value;

    }

/*

    public void setValue(String value) {
        this.value = value;
    }

*/



    public static void main(String[] args) {
        System.out.println(equipmentType.GW.value);
    }
}