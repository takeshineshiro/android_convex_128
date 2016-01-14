package com.medical.lepu.wireless_scan_b_mode.entity;

import java.io.Serializable;

/**
 * Created by wong on 16/1/14.
 */
public class Notice  implements Serializable {

    public final static int TYPE_ATME = 1;
    public final static int TYPE_MESSAGE = 2;
    public final static int TYPE_COMMENT = 3;
    public final static int TYPE_NEWFAN = 4;
    public final static int TYPE_NEWLIKE = 5;

    private int atmeCount;


    private int msgCount;


    private int reviewCount;


    private int newFansCount;


    private int newLikeCount;

    public int getAtmeCount() {
        return atmeCount;
    }

    public void setAtmeCount(int atmeCount) {
        this.atmeCount = atmeCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getNewFansCount() {
        return newFansCount;
    }

    public void setNewFansCount(int newFansCount) {
        this.newFansCount = newFansCount;
    }

    public int getNewLikeCount() {
        return newLikeCount;
    }

    public void setNewLikeCount(int newLikeCount) {
        this.newLikeCount = newLikeCount;
    }
}
