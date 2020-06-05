package hdfs; /**
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


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.net.URI;
import java.security.PrivilegedExceptionAction;

/** Utilities for HDFS tests */
public class DFSTestUtil {
    /**
     * Get a FileSystem instance as specified user in a doAs block.
     */
    static public FileSystem getFileSystemAs(UserGroupInformation ugi,URI uri ,
                                             final Configuration conf) throws IOException,
            InterruptedException {
        return ugi.doAs(new PrivilegedExceptionAction<FileSystem>() {
            @Override
            public FileSystem run() throws Exception {
                return FileSystem.get(uri,conf);
            }
        });
    }

}
