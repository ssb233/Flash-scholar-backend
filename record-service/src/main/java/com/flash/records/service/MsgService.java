package com.flash.records.service;


import com.flash.records.dao.Msg;

import java.util.List;

public interface MsgService {
    List<Msg> getMsgByUid(Integer uid);

    void sendMsg(Integer uid, String con, String idSend, int type);

    void deleteMsgById(Integer id);

}
