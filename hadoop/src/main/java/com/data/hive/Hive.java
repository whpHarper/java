package com.data.hive;

import org.apache.hive.service.cli.thrift.*;
import org.apache.thrift.TException;

import java.util.concurrent.Callable;

/**
 * @author whp 19-7-18
 */
public class Hive {
    public static class Request extends AbstractRequest<TOperationHandle>{
        private HiveSession session;
        private String command;

        public Request(HiveSession session) {
            this.session = session;
        }

        public Request command(String command) {
            this.command = command;
            return this;
        }

        @Override
        protected Callable<TOperationHandle> callable() {
            return new Callable<TOperationHandle>() {
                @Override
                public TOperationHandle call() throws Exception {
                    return execute();
                }
            };
        }

        private TOperationHandle execute() throws TException {
            TCLIService.Client client = session.getClient();
            TOpenSessionReq openSessionReq = new TOpenSessionReq();
            TOpenSessionResp osr = client.OpenSession(openSessionReq);
            TSessionHandle sessHandle = osr.getSessionHandle();
            TExecuteStatementReq execReq = new TExecuteStatementReq(sessHandle,
                    command);
            // 异步运行
            execReq.setRunAsync(true);
            TExecuteStatementResp resp = client.ExecuteStatement(execReq);// 执行语句
            resp.getStatus().getInfoMessages();
            TOperationHandle tOperationHandle = resp.getOperationHandle();// 获取执行的handle
            if (tOperationHandle == null) {
                //语句执行异常时，会把异常信息放在resp.getStatus()中。
                throw new TException(resp.getStatus().getErrorMessage());
            }
            return tOperationHandle;
        }
    }
}
