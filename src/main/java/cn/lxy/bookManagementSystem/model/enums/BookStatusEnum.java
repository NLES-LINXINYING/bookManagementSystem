package cn.lxy.bookManagementSystem.model.enums;

public enum BookStatusEnum {

    NORMAL(0),      //正常
    DELETE(1),      //删除
    recommended(2); //推荐

    private int value;

    BookStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
