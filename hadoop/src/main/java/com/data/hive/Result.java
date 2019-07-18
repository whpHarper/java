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

import org.apache.hive.service.cli.thrift.TFetchResultsReq;
import org.apache.hive.service.cli.thrift.TFetchResultsResp;
import org.apache.hive.service.cli.thrift.TOperationHandle;
import org.apache.thrift.TException;

import java.util.concurrent.Callable;

class Result {

    public static class Request extends AbstractRequest<TFetchResultsResp> {

        private TOperationHandle tOperationHandle;
        private long maxRows;
        private HiveSession session;

        Request(HiveSession session) {
            this.session = session;
        }

        public Request jobId(TOperationHandle tOperationHandle) {
            this.tOperationHandle = tOperationHandle;
            return this;
        }

        public Request maxRows(long maxRows) {
            this.maxRows = maxRows;
            return this;
        }

        @Override
        protected Callable<TFetchResultsResp> callable() {
            return new Callable<TFetchResultsResp>() {
                @Override
                public TFetchResultsResp call() throws Exception {
                    return execute();
                }
            };
        }

        private TFetchResultsResp execute() throws TException {

          //  TFetchResultsReq fetchReq = new TFetchResultsReq(tOperationHandle, TFetchOrientation.FETCH_NEXT ,maxRows);
            TFetchResultsReq fetchReq = new TFetchResultsReq();
            fetchReq.setOperationHandle(tOperationHandle);
            // fetchReq.setFetchType((short)1);
            TFetchResultsResp re = null;
            fetchReq.setMaxRows(maxRows);
            if (tOperationHandle != null) {
                re = session.getClient().FetchResults(fetchReq);
            }
            return re;
        }
    }


}
