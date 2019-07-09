package com.instaclustr.sidecar.operations;

import javax.inject.Inject;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.instaclustr.sidecar.jackson.MapBackedTypeIdResolver;

@JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM, property = "type")
@JsonTypeIdResolver(OperationRequest.TypeIdResolver.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class OperationRequest {

    @JsonIgnore
    public OperationType type;

    static class TypeIdResolver extends MapBackedTypeIdResolver<OperationRequest> {
        @Inject
        public TypeIdResolver(final Map<OperationType, Class<? extends OperationRequest>> typeMappings) {
            super(typeMappings);
        }
    }
}
