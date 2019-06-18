package com.instaclustr.sidecar.cassandra.operations.cleanup;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;
import com.instaclustr.operations.Operation;
import com.instaclustr.operations.OperationFailureException;
import jmx.org.apache.cassandra.service.StorageServiceMBean;

public class CleanupOperation extends Operation<CleanupOperationRequest> {
    private final StorageServiceMBean storageServiceMBean;

    @Inject
    public CleanupOperation(final StorageServiceMBean storageServiceMBean,
                            @Assisted final CleanupOperationRequest request) {
        super(request);

        this.storageServiceMBean = storageServiceMBean;
    }

    @Override
    protected void run0() throws Exception {
        int result = storageServiceMBean.forceKeyspaceCleanup(request.jobs, request.keyspace, request.tables.toArray(new String[]{}));

        switch (result) {
            case 1:
                throw new OperationFailureException("Aborted cleaning up at least one table in keyspace " + request.keyspace + ", check server logs for more information.");
            case 2:
                throw new OperationFailureException("Failed marking some sstables compacting in keyspace " + request.keyspace + ", check server logs for more information");
        }
    }
}
