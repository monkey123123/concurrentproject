
package com.hachismart.rule.engine.scheduler;

import com.github.kfcfans.powerjob.client.OhMyClient;
import com.github.kfcfans.powerjob.common.response.ResultDTO;
import com.google.common.util.concurrent.ListenableFuture;
import com.hachismart.rule.engine.api.*;
import com.hachismart.rule.engine.api.util.TbNodeUtils;
import com.hachismart.server.common.msg.TbMsg;
import com.hachismart.server.common.msg.TbMsgMetaData;
import com.hachismart.server.data.plugin.ComponentType;
import com.rabbitmq.client.AMQP;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.ssl.SslContextBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import javax.net.ssl.SSLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.hachismart.server.common.util.DonAsynchron.withCallback;

@Slf4j
@RuleNode(
        type = ComponentType.EXTERNAL,
        name = "POWERJOB API调用",
        configClazz = TbRunJobCallNodeConfiguration.class,
        nodeDescription = "发起POWERJOB API调用到第三方法POWERJOB server",
        nodeDetails = "将发起POWERJOB API调用到第三方POWERJOB server. " +
                "消息payload将添加到OhMyClient发起的REST Request body中. 配置属性将从消息元数据添加到Headers中." +
                "输出消息将包含响应的字段 " +
                "(<code>status</code>, <code>statusCode</code>, <code>statusReason</code>和<code>headers</code>)在消息元数据中." +
                " Response body保存在输出的消息Payload中. " +
                "例如 <b>statusCode</b> 字段可以用如下方式获取: <code>metadata.statusCode</code>.",
        uiResources = {"static/rulenode/rulenode-core-config.js"},
        configDirective = "tbActionNodeRestApiCallConfig",
        iconUrl = "data:image/svg+xml;base64,PHN2ZyBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIgNTEyIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbDpzcGFjZT0icHJlc2VydmUiIHZpZXdCb3g9IjAgMCA1MTIgNTEyIiB2ZXJzaW9uPSIxLjEiIHk9IjBweCIgeD0iMHB4Ij48ZyB0cmFuc2Zvcm09Im1hdHJpeCguOTQ5NzUgMCAwIC45NDk3NSAxNy4xMiAyNi40OTIpIj48cGF0aCBkPSJtMTY5LjExIDEwOC41NGMtOS45MDY2IDAuMDczNC0xOS4wMTQgNi41NzI0LTIyLjAxNCAxNi40NjlsLTY5Ljk5MyAyMzEuMDhjLTMuNjkwNCAxMi4xODEgMy4yODkyIDI1LjIyIDE1LjQ2OSAyOC45MSAyLjIyNTkgMC42NzQ4MSA0LjQ5NjkgMSA2LjcyODUgMSA5Ljk3MjEgMCAxOS4xNjUtNi41MTUzIDIyLjE4Mi0xNi40NjdhNi41MjI0IDYuNTIyNCAwIDAgMCAwLjAwMiAtMC4wMDJsNjkuOTktMjMxLjA3YTYuNTIyNCA2LjUyMjQgMCAwIDAgMCAtMC4wMDJjMy42ODU1LTEyLjE4MS0zLjI4Ny0yNS4yMjUtMTUuNDcxLTI4LjkxMi0yLjI4MjUtMC42OTE0NS00LjYxMTYtMS4wMTY5LTYuODk4NC0xem04NC45ODggMGMtOS45MDQ4IDAuMDczNC0xOS4wMTggNi41Njc1LTIyLjAxOCAxNi40NjlsLTY5Ljk4NiAyMzEuMDhjLTMuNjg5OCAxMi4xNzkgMy4yODUzIDI1LjIxNyAxNS40NjUgMjguOTA4IDIuMjI5NyAwLjY3NjQ3IDQuNTAwOCAxLjAwMiA2LjczMjQgMS4wMDIgOS45NzIxIDAgMTkuMTY1LTYuNTE1MyAyMi4xODItMTYuNDY3YTYuNTIyNCA2LjUyMjQgMCAwIDAgMC4wMDIgLTAuMDAybDY5Ljk4OC0yMzEuMDdjMy42OTA4LTEyLjE4MS0zLjI4NTItMjUuMjIzLTE1LjQ2Ny0yOC45MTItMi4yODE0LTAuNjkyMzEtNC42MTA4LTEuMDE4OS02Ljg5ODQtMS4wMDJ6bS0yMTcuMjkgNDIuMjNjLTEyLjcyOS0wLjAwMDg3LTIzLjE4OCAxMC40NTYtMjMuMTg4IDIzLjE4NiAwLjAwMSAxMi43MjggMTAuNDU5IDIzLjE4NiAyMy4xODggMjMuMTg2IDEyLjcyNy0wLjAwMSAyMy4xODMtMTAuNDU5IDIzLjE4NC0yMy4xODYgMC4wMDA4NzYtMTIuNzI4LTEwLjQ1Ni0yMy4xODUtMjMuMTg0LTIzLjE4NnptMCAxNDYuNjRjLTEyLjcyNy0wLjAwMDg3LTIzLjE4NiAxMC40NTUtMjMuMTg4IDIzLjE4NC0wLjAwMDg3MyAxMi43MjkgMTAuNDU4IDIzLjE4OCAyMy4xODggMjMuMTg4IDEyLjcyOC0wLjAwMSAyMy4xODQtMTAuNDYgMjMuMTg0LTIzLjE4OC0wLjAwMS0xMi43MjYtMTAuNDU3LTIzLjE4My0yMy4xODQtMjMuMTg0em0yNzAuNzkgNDIuMjExYy0xMi43MjcgMC0yMy4xODQgMTAuNDU3LTIzLjE4NCAyMy4xODRzMTAuNDU1IDIzLjE4OCAyMy4xODQgMjMuMTg4aDE1NC45OGMxMi43MjkgMCAyMy4xODYtMTAuNDYgMjMuMTg2LTIzLjE4OCAwLjAwMS0xMi43MjgtMTAuNDU4LTIzLjE4NC0yMy4xODYtMjMuMTg0eiIgdHJhbnNmb3JtPSJtYXRyaXgoMS4wMzc2IDAgMCAxLjAzNzYgLTcuNTY3NiAtMTQuOTI1KSIgc3Ryb2tlLXdpZHRoPSIxLjI2OTMiLz48L2c+PC9zdmc+"
)
public class TbRunJobCallNode implements TbNode {

    private static final String STATUS = "status";
    private static final String STATUS_CODE = "statusCode";
    private static final String STATUS_REASON = "statusReason";
    private static final String ERROR = "error";
    private static final String ERROR_BODY = "error_body";

    private TbRunJobCallNodeConfiguration config;
    private OhMyClient ohMyClient;

    @Override
    public void init(TbContext ctx, TbNodeConfiguration configuration) throws TbNodeException {
        this.config = TbNodeUtils.convert(configuration, TbRunJobCallNodeConfiguration.class);
        List<String> list = new ArrayList();
        list.add("127.0.0.1:7700");
        list.add("127.0.0.1:7701");
        ohMyClient = new OhMyClient(list, "powerjob-agent-test", "123");
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
        // 以下三个参数从msg里面解析出来
        Long jobId = 1L;
        String instanceParams = "instanceParams";
        long delayMS = 0L;
        ResultDTO<Long> future = ohMyClient.runJob(jobId, instanceParams, delayMS);
        return msg;
    }

    @Override
    public void destroy() {
        this.ohMyClient = null;
    }

    private TbMsg processException(TbContext ctx, TbMsg origMsg, Throwable t) {
        TbMsgMetaData metaData = origMsg.getMetaData().copy();
        metaData.putValue(ERROR, t.getClass() + ": " + t.getMessage());
        return ctx.transformMsg(origMsg, origMsg.getType(), origMsg.getOriginator(), metaData, origMsg.getData());
    }

}
