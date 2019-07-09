package com.instaclustr.cassandra.sidecar.operations.decommission;


import com.google.common.base.MoreObjects;
import com.instaclustr.cassandra.sidecar.operations.CassandraOperationType;
import com.instaclustr.sidecar.operations.OperationRequest;

public class DecommissionOperationRequest extends OperationRequest {
    // decommission requests have no parameters

    public DecommissionOperationRequest() {
        this.type = CassandraOperationType.DECOMMISSION;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("type", type).toString();
    }
}
