package com.data.hive;

/**
 * @author whp 19-7-18
 */
public class Job {
    public static Hive.Request submitHive(HiveSession session) {
        return new Hive.Request(session);
    }
    public static Result.Request fetchResults(HiveSession session) {
        return new Result.Request(session);
    }

    public static ResultSetMetadata.Request fetchResultsSetMetadata(HiveSession session) {
        return new ResultSetMetadata.Request(session);
    }
    public static Status.Request queryStatus(HiveSession session) {
        return new Status.Request(session);
    }
}
