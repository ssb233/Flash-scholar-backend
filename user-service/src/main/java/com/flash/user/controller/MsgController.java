package com.flash.user.controller;

import com.flash.user.response.CustomResponse;
import com.flash.user.service.MsgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author Yury
 */
@CrossOrigin
@RestController
@Tag(name = "系统消息api")
@RequestMapping("/api/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @GetMapping("/uid/{uid}")
    @Operation(summary = "获取uid的所有消息")
    public CustomResponse getMsgByUid(@PathVariable Long uid) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(msgService.getMsgByUid(uid));
        return customResponse;
    }

    @PostMapping("/send")
    @Operation(summary = "给uid发送消息，type 0个人门户申请通过")
    public CustomResponse sendMsg(@RequestParam("uid") Long uid,
                                  @RequestParam("idSend") String idSend,
                                  @RequestParam("content") String content,
                                  @RequestParam("type") int type) {
        CustomResponse customResponse = new CustomResponse();
        msgService.sendMsg(uid, content, idSend, type);
        return customResponse;
    }

    @DeleteMapping("/del/id/{id}")
    @Operation(summary = "根据id删除消息")
    public CustomResponse deleteMsgById(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        msgService.deleteMsgById(id);
        customResponse.setData(true);
        return customResponse;
    }

    @DeleteMapping("/del/all")
    @Operation(summary = "根据uid用户删除所有为type的消息")
    public CustomResponse deleteAllMsg(@RequestParam Long uid, @RequestParam Integer type) {
        CustomResponse customResponse = new CustomResponse();
        msgService.deleteAllMsg(uid, type);
        customResponse.setData(true);
        return customResponse;
    }

}
