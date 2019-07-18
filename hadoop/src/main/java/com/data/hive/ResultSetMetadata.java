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

import org.apache.hive.service.cli.thrift.TGetResultSetMetadataReq;
import org.apache.hive.service.cli.thrift.TGetResultSetMetadataResp;
import org.apache.hive.service.cli.thrift.TOperationHandle;
import org.apache.thrift.TException;

import java.util.concurrent.Callable;

class ResultSetMetadata {

    public static class Request extends AbstractRequest<TGetResultSetMetadataResp> {

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
        protected Callable<TGetResultSetMetadataResp> callable() {
            return new Callable<TGetResultSetMetadataResp>() {
                @Override
                public TGetResultSetMetadataResp call() throws Exception {
                    return execute();
                }
            };
        }

        private TGetResultSetMetadataResp execute() throws TException {
            TGetResultSetMetadataReq req = new TGetResultSetMetadataReq(tOperationHandle);
            TGetResultSetMetadataResp re = null;
            if (tOperationHandle != null) {

                re = session.getClient().GetResultSetMetadata(req);
            }
            return re;
        }
    }


}
