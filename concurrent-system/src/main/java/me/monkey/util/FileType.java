package me.monkey.util;

/**
 * 文件头对应类型的枚举
 *
 * @author Administrator
 */
public enum FileType {
    JPEG("FFD8FF"), PNG("89504E47"), GIF("47494638"), TIFF("49492A00"), BMP(
            "424d"), DWG("41433130"), PSD("38425053"), XML("3C3F786D6C"), HTML(
            "68746C3E"), PDF("25044462D312E"), ZIP("504B0304"), RAR("52617221"), WAV(
            "57415645"), AVI("41564920");

    private String value = "";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private FileType(String value) {
        this.value = value;
    }
}