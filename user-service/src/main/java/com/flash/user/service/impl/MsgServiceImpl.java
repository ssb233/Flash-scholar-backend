package com.flash.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.user.dao.Msg;
import com.flash.user.mapper.MsgMapper;
import com.flash.user.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author Yury
 */
@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgMapper msgMapper;

    @Override
    public List<Msg> getMsgByUid(Long uid) {
        QueryWrapper<Msg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).orderByDesc("time");
        return msgMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteMsgById(Long id) {
        QueryWrapper<Msg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        msgMapper.delete(queryWrapper);
    }

    @Override
    public void deleteAllMsg(Long uid, int type) {
        QueryWrapper<Msg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("type", type);
        msgMapper.delete(queryWrapper);
    }

    @Override
    public void sendMsg(Long uid, String con, String idSend, int type) {
        String title = "";
        String content = "";

        // 认领学者
        if (type == 0) {

            title = "认领个人门户审核结果";
            content = con;

            Msg msg = new Msg(null, uid, title, content, idSend, new Date(), type);
            msgMapper.insert(msg);
        } else if (type == 1) {

            title = "评论回复";
            content = con;

            Msg msg = new Msg(null, uid, title, content, idSend, new Date(), type);
            msgMapper.insert(msg);

        }
//        else if (type == 1){
//            UserDTO userDTO = userClient.getUserDTOBySid(sid);
//            Zhuanlan zhuanlan = videoClient.getSpecolByZid(idSend);
//            title = userDTO.getName() + "更新了一个专栏，快来看看吧！";
//            content = userDTO.getName() + "更新了专栏《" + zhuanlan.getTitle() + "》，" + "赶快点击打开看看吧！";
//            List<UserDTO> userDTOList = userClient.getFansService(sid);
//            for (UserDTO userDTO1 : userDTOList) {
//                Msg msg = new Msg(null, userDTO1.getSid(), title, content, idSend, new Date(), type);
//                msgMapper.insert(msg);
//            }
//        } else if (type == 2) {
//            title = "创作周报更新";
//            content = "你的创作周报更新了，快来看看吧！";
//            Msg msg = new Msg(null, sid, title, content, 0, new Date(), type);
//            msgMapper.insert(msg);
//        } else if (type == 3) {
//            title = "视频周报更新";
//            content = "你的视频周报更新了，快来看看吧！";
//            Msg msg = new Msg(null, sid, title, content, 0, new Date(), type);
//            msgMapper.insert(msg);
//        } else if (type == 4) {
//            VideoDTO videoDTO = videoClient.getVideoWithDataByVid(idSend);
//            title = "视频删除";
//            content = "你的视频《" + videoDTO.getTitle() + "》涉嫌违规，已被管理员删除，请修改后重新上传。";
//            Msg msg = new Msg(null, sid, title, content, idSend, new Date(), type);
//            msgMapper.insert(msg);
//        } else if (type == 5) {
//            Zhuanlan zhuanlan = videoClient.getSpecolByZid(idSend);
//            title = "专栏隐藏";
//            content = "你的专栏《" + zhuanlan.getTitle() + "》涉嫌违规，已被管理员隐藏，请进行修改。";
//            Msg msg = new Msg(null, sid, title, content, idSend, new Date(), type);
//            msgMapper.insert(msg);
//        }

    }
}
