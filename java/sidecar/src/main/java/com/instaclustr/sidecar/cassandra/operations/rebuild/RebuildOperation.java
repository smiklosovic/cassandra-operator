package com.instaclustr.sidecar.cassandra.operations.rebuild;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.instaclustr.operations.Operation;
import com.instaclustr.operations.OperationFailureException;
import jmx.org.apache.cassandra.service.StorageServiceMBean;

public class RebuildOperation extends Operation<RebuildOperationRequest> {

    private final StorageServiceMBean storageServiceMBean;

    @Inject
    public RebuildOperation(final StorageServiceMBean storageServiceMBean,
                            @Assisted final RebuildOperationRequest request) {
        super(request);

        this.storageServiceMBean = storageServiceMBean;
    }

    @Override
    protected void run0() throws Exception {

        // TODO move this to "validation" for request
        if (request.keyspace == null && request.specificTokens != null && !request.specificTokens.isEmpty()) {
            throw new OperationFailureException("Cannot specify tokens without keyspace.");
        }

        final String specificTokens = prepareSpecificTokens(request.specificTokens);

        final String specificSources = prepareSpecificSources(request.specificSources);

        storageServiceMBean.rebuild(request.sourceDC,
                                    request.keyspace,
                                    specificTokens,
                                    specificSources);
    }

    private String prepareSpecificTokens(Set<RebuildOperationRequest.TokenRange> specificTokens) {
        if (specificTokens == null || specificTokens.isEmpty()) {
            return null;
        }

        return specificTokens.stream().map(token -> format("(%s,%s]", token.startToken, token.endToken)).collect(joining(","));
    }

    private String prepareSpecificSources(Set<String> specificSources) {
        if (specificSources == null || specificSources.isEmpty()) {
            return null;
        }

        return String.join(",", specificSources);
    }
}
