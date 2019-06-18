package com.instaclustr.sidecar.cassandra.operations.scrub;

import static com.instaclustr.operations.OperationBindings.installOperationBindings;

import com.google.inject.AbstractModule;

public class ScrubModule extends AbstractModule {
    @Override
    protected void configure() {
        installOperationBindings(binder(), "scrub", ScrubOperationRequest.class, ScrubOperation.class);
    }
}
