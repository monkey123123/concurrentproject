/**
 * Copyright © 2016-2020 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.rule.engine.scheduler;

//import com.github.kfcfans.powerjob.client.OhMyClient;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.thingsboard.rule.engine.api.*;
import org.thingsboard.rule.engine.api.util.TbNodeUtils;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.plugin.ComponentType;
import org.thingsboard.server.common.msg.TbMsg;
import org.thingsboard.server.common.msg.TbMsgMetaData;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

import static org.thingsboard.common.util.DonAsynchron.withCallback;

@Slf4j
@RuleNode(
        type = ComponentType.EXTERNAL,
        name = "powerjob",
        configClazz = TbRunJobNodeConfiguration.class,
        nodeDescription = "Invoke REST API calls to external REST server",
        nodeDetails = "Will invoke REST API call <code>GET | POST | PUT | DELETE</code> to external REST server. " +
                "Message payload added into Request body. Configured attributes can be added into Headers from Message Metadata." +
                " Outbound message will contain response fields " +
                "(<code>status</code>, <code>statusCode</code>, <code>statusReason</code> and response <code>headers</code>) in the Message Metadata." +
                " Response body saved in outbound Message payload. " +
                "For example <b>statusCode</b> field can be accessed with <code>metadata.statusCode</code>.",
        uiResources = {"static/rulenode/rulenode-core-config.js"},
        configDirective = "tbActionNodeRestApiCallConfig",
        iconUrl = "data:image/svg+xml;base64,PHN2ZyBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIgNTEyIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbDpzcGFjZT0icHJlc2VydmUiIHZpZXdCb3g9IjAgMCA1MTIgNTEyIiB2ZXJzaW9uPSIxLjEiIHk9IjBweCIgeD0iMHB4Ij48ZyB0cmFuc2Zvcm09Im1hdHJpeCguOTQ5NzUgMCAwIC45NDk3NSAxNy4xMiAyNi40OTIpIj48cGF0aCBkPSJtMTY5LjExIDEwOC41NGMtOS45MDY2IDAuMDczNC0xOS4wMTQgNi41NzI0LTIyLjAxNCAxNi40NjlsLTY5Ljk5MyAyMzEuMDhjLTMuNjkwNCAxMi4xODEgMy4yODkyIDI1LjIyIDE1LjQ2OSAyOC45MSAyLjIyNTkgMC42NzQ4MSA0LjQ5NjkgMSA2LjcyODUgMSA5Ljk3MjEgMCAxOS4xNjUtNi41MTUzIDIyLjE4Mi0xNi40NjdhNi41MjI0IDYuNTIyNCAwIDAgMCAwLjAwMiAtMC4wMDJsNjkuOTktMjMxLjA3YTYuNTIyNCA2LjUyMjQgMCAwIDAgMCAtMC4wMDJjMy42ODU1LTEyLjE4MS0zLjI4Ny0yNS4yMjUtMTUuNDcxLTI4LjkxMi0yLjI4MjUtMC42OTE0NS00LjYxMTYtMS4wMTY5LTYuODk4NC0xem04NC45ODggMGMtOS45MDQ4IDAuMDczNC0xOS4wMTggNi41Njc1LTIyLjAxOCAxNi40NjlsLTY5Ljk4NiAyMzEuMDhjLTMuNjg5OCAxMi4xNzkgMy4yODUzIDI1LjIxNyAxNS40NjUgMjguOTA4IDIuMjI5NyAwLjY3NjQ3IDQuNTAwOCAxLjAwMiA2LjczMjQgMS4wMDIgOS45NzIxIDAgMTkuMTY1LTYuNTE1MyAyMi4xODItMTYuNDY3YTYuNTIyNCA2LjUyMjQgMCAwIDAgMC4wMDIgLTAuMDAybDY5Ljk4OC0yMzEuMDdjMy42OTA4LTEyLjE4MS0zLjI4NTItMjUuMjIzLTE1LjQ2Ny0yOC45MTItMi4yODE0LTAuNjkyMzEtNC42MTA4LTEuMDE4OS02Ljg5ODQtMS4wMDJ6bS0yMTcuMjkgNDIuMjNjLTEyLjcyOS0wLjAwMDg3LTIzLjE4OCAxMC40NTYtMjMuMTg4IDIzLjE4NiAwLjAwMSAxMi43MjggMTAuNDU5IDIzLjE4NiAyMy4xODggMjMuMTg2IDEyLjcyNy0wLjAwMSAyMy4xODMtMTAuNDU5IDIzLjE4NC0yMy4xODYgMC4wMDA4NzYtMTIuNzI4LTEwLjQ1Ni0yMy4xODUtMjMuMTg0LTIzLjE4NnptMCAxNDYuNjRjLTEyLjcyNy0wLjAwMDg3LTIzLjE4NiAxMC40NTUtMjMuMTg4IDIzLjE4NC0wLjAwMDg3MyAxMi43MjkgMTAuNDU4IDIzLjE4OCAyMy4xODggMjMuMTg4IDEyLjcyOC0wLjAwMSAyMy4xODQtMTAuNDYgMjMuMTg0LTIzLjE4OC0wLjAwMS0xMi43MjYtMTAuNDU3LTIzLjE4My0yMy4xODQtMjMuMTg0em0yNzAuNzkgNDIuMjExYy0xMi43MjcgMC0yMy4xODQgMTAuNDU3LTIzLjE4NCAyMy4xODRzMTAuNDU1IDIzLjE4OCAyMy4xODQgMjMuMTg4aDE1NC45OGMxMi43MjkgMCAyMy4xODYtMTAuNDYgMjMuMTg2LTIzLjE4OCAwLjAwMS0xMi43MjgtMTAuNDU4LTIzLjE4NC0yMy4xODYtMjMuMTg0eiIgdHJhbnNmb3JtPSJtYXRyaXgoMS4wMzc2IDAgMCAxLjAzNzYgLTcuNTY3NiAtMTQuOTI1KSIgc3Ryb2tlLXdpZHRoPSIxLjI2OTMiLz48L2c+PC9zdmc+"
)
public class TbRunJobNode implements TbNode {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private static final String ERROR = "error";

    private TbRunJobNodeConfiguration config;
//    private OhMyClient ohMyClient;


    private String str = "";
    @Override
    public void init(TbContext ctx, TbNodeConfiguration configuration) throws TbNodeException {
        this.config = TbNodeUtils.convert(configuration, TbRunJobNodeConfiguration.class);

//        this.config.getClientProperties().forEach((k,v) -> factory.getClientProperties().put(k,v));



        try {
            str = this.config.getRestEndpointUrlPattern()
                    +"--------------"
                    +this.config.getRequestMethod()
                    +"--------------"
                    +this.config.getHeaders();
            System.out.println("str--------------="+str);
//            ohMyClient = new OhMyClient(this.config.getServerAddress(), this.config.getAppName(), this.config.getAppPwd());
        } catch (Exception e) {
            throw new TbNodeException(e);
        }
    }

    @Override
    public void onMsg(TbContext ctx, TbMsg msg) throws ExecutionException, InterruptedException, TbNodeException {
        withCallback(publishMessageAsync(ctx, msg),
                m -> ctx.tellNext(m, TbRelationTypes.SUCCESS),
                t -> {
                    TbMsg next = processException(ctx, msg, t);
                    ctx.tellFailure(next, t);
                });
    }

    private ListenableFuture<TbMsg> publishMessageAsync(TbContext ctx, TbMsg msg) {
        return ctx.getExternalCallExecutor().executeAsync(() -> publishMessage(ctx, msg));
    }

    private TbMsg publishMessage(TbContext ctx, TbMsg msg) throws Exception {
        /*
        String exchangeName = "";
        if (!StringUtils.isEmpty(this.config.getExchangeNamePattern())) {
            exchangeName = TbNodeUtils.processPattern(this.config.getExchangeNamePattern(), msg.getMetaData());
        }
        String routingKey = "";
        if (!StringUtils.isEmpty(this.config.getRoutingKeyPattern())) {
            routingKey = TbNodeUtils.processPattern(this.config.getRoutingKeyPattern(), msg.getMetaData());
        }
        AMQP.BasicProperties properties = null;
        if (!StringUtils.isEmpty(this.config.getMessageProperties())) {
            properties = convert(this.config.getMessageProperties());
        }
        channel.basicPublish(
                exchangeName,
                routingKey,
                properties,
                msg.getData().getBytes(UTF8));
        */
//        ctx.getUserService().
//        originator就是设备id
        Device device = ctx.getDeviceService().findDeviceById(ctx.getTenantId(), (DeviceId) msg.getOriginator());
        //接下来就是根据设备，查到所属用户/或者查到当前用户（不会），再查到所属JOB即可。然后把jobId和参数喂给ohMyClient
        System.out.println("接收到消息ctx------------------:"+ctx.toString());
        System.out.println("接收到消息msg.toString()------------------:"+msg.toString());
        System.out.println("TODO调用OhMyClient.runJob------------------:");
//        ohMyClient.runJob(1L, "jobParams is xxxx", 1000);
        return msg;
    }

    private TbMsg processException(TbContext ctx, TbMsg origMsg, Throwable t) {
        TbMsgMetaData metaData = origMsg.getMetaData().copy();
        metaData.putValue(ERROR, t.getClass() + ": " + t.getMessage());
        return ctx.transformMsg(origMsg, origMsg.getType(), origMsg.getOriginator(), metaData, origMsg.getData());
    }

    @Override
    public void destroy() {

    }

}

