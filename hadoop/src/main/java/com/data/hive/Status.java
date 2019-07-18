/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.data.hive;

import org.apache.hive.service.cli.thrift.TGetOperationStatusReq;
import org.apache.hive.service.cli.thrift.TGetOperationStatusResp;
import org.apache.hive.service.cli.thrift.TOperationHandle;
import org.apache.hive.service.cli.thrift.TOperationState;
import org.apache.thrift.TException;

import java.util.concurrent.Callable;

class Status {

    public static class Request extends AbstractRequest<TOperationState> {

        private TOperationHandle tOperationHandle;
        private HiveSession session;

        Request(HiveSession session) {
            this.session = session;
        }

        public Request jobId(TOperationHandle tOperationHandle) {
            this.tOperationHandle = tOperationHandle;
            return this;
        }

        @Override
        protected Callable<TOperationState> callable() {
            return new Callable<TOperationState>() {
                @Override
                public TOperationState call() throws Exception {
                    return execute();
                }
            };
        }

        private TOperationState execute() throws TException {
            TOperationState tOperationState = null;
            if (tOperationHandle != null) {
                TGetOperationStatusReq statusReq = new TGetOperationStatusReq(
                        tOperationHandle);
                TGetOperationStatusResp statusResp = session.getClient()
                        .GetOperationStatus(statusReq);

                tOperationState = statusResp.getOperationState();

            }
            return tOperationState;
        }
    }


}
