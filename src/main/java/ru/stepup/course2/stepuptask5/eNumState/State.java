package ru.stepup.course2.stepuptask5.eNumState;

public enum State {
    CLOSE("Закрыт"),
    OPEN("Открыт"),
    RESERVE("Зарезервирован"),
    DELETE("Удален");

    private String code;

    State(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
