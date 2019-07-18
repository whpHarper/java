package com.data.hive;

import com.data.hive.HiveSession;
import com.data.hive.Job;
import org.apache.hive.service.cli.thrift.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HiveTest {

    @Test
    public void jobSubmit() {
        HiveSession session = HiveSession.login("hive", "123", "docker2", 10000);
        TOperationHandle th = Job.submitHive(session).command("select * from employee").now();
        System.out.println("--------提交任务完成-------");
        TOperationState state = Job.queryStatus(session).jobId(th).now();
        System.out.println("---------查询状态-------");
        System.out.println(state.name());
        System.out.println("---------查询结果-------");
        TGetResultSetMetadataResp rest = Job.fetchResultsSetMetadata(session).jobId(th).now();
        TTableSchema tableSchema = rest.getSchema();
        if (tableSchema != null) {
            List<TColumnDesc> columnDescs = tableSchema.getColumns();
            List<String> columns = new ArrayList<String>();
            System.out.println("---------获取查询字段名-------");
            for (TColumnDesc tColumnDesc : columnDescs) {
                columns.add(tColumnDesc.getColumnName());
                System.out.println(tColumnDesc.getColumnName());
            }
        }
        TFetchResultsResp re = Job.fetchResults(session).jobId(th).maxRows(1000).now();

        List<TColumn> list = re.getResults().getColumns();
        List<Object> list_row = getList(list);
        for (Object obj : list_row) {
            System.out.println(obj);
        }

    }

    private List<Object> getList(List<TColumn> list) {
        List<String> list1=null;
        List<Object> list_row = new ArrayList<Object>();
        for (TColumn field : list) {
            if (field.isSetStringVal()) {
                list_row.add(field.getStringVal().getValues());
            } else if (field.isSetDoubleVal()) {
                list_row.add(field.getDoubleVal().getValues());
            } else if (field.isSetI16Val()) {
                list_row.add(field.getI16Val().getValues());
            } else if (field.isSetI32Val()) {
                list_row.add(field.getI32Val().getValues());
            } else if (field.isSetI64Val()) {
                list_row.add(field.getI64Val().getValues());
            } else if (field.isSetBoolVal()) {
                list_row.add(field.getBoolVal().getValues());
            } else if (field.isSetByteVal()) {
                list_row.add(field.getByteVal().getValues());
            }
        }
        return list_row;
    }
}
