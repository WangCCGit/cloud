package com.it.cloud.enumdemo;

import lombok.Getter;

public enum eHead {
    nendo(1, 1), date(2, 1), kamokuKbn(4, 1);
    private final int startCol = 1;

    @Getter
    private int       row;

    private int       col;

    private eHead(int row, int col) {
        this.row = row;
        this.col = col;
    }
}


