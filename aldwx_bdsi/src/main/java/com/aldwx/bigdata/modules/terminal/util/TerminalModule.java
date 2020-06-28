package com.aldwx.bigdata.modules.terminal.util;

/**
 * 终端模块
 */
public enum TerminalModule {

    WVV("wvv","1"), NT("nt","2"), WW_WH("ww_wh","3"), LANG("lang","4"),
    WSDK("wsdk","5"), SV("sv","6"), WV("wv","7"), WH("wh","8"), WW("ww","9");

    private final String name;
    private final String code;

    TerminalModule(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(String code) {
        for(TerminalModule module : TerminalModule.values()) {
            if(module.getCode().equals(code)) {
                return module.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
