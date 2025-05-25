package com.flash.user.service;

import com.flash.user.dao.Msg;

import java.util.List;

public interface MsgService {
    List<Msg> getMsgByUid(Long uid);

    void sendMsg(Long uid, String content, String idSend, int type);

    void deleteMsgById(Long id);

    void deleteAllMsg(Long uid, int type);

}
