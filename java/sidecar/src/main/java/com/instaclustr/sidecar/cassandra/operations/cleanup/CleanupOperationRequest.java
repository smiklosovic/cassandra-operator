package com.instaclustr.sidecar.cassandra.operations.cleanup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.instaclustr.operations.OperationRequest;

/**
 * $ nodetool help cleanup
 * NAME
 *         nodetool cleanup - Triggers the immediate cleanup of keys no longer
 *         belonging to a node. By default, clean all keyspaces
 *
 * SYNOPSIS
 *         nodetool [(-h <host> | --host <host>)] [(-p <port> | --port <port>)]
 *                 [(-pw <password> | --password <password>)]
 *                 [(-pwf <passwordFilePath> | --password-file <passwordFilePath>)]
 *                 [(-u <username> | --username <username>)] cleanup
 *                 [(-j <jobs> | --jobs <jobs>)] [--] [<keyspace> <tables>...]
 *
 * OPTIONS
 *         -h <host>, --host <host>
 *             Node hostname or ip address
 *
 *         -j <jobs>, --jobs <jobs>
 *             Number of sstables to cleanup simultanously, set to 0 to use all
 *             available compaction threads
 *
 *         -p <port>, --port <port>
 *             Remote jmx agent port number
 *
 *         -pw <password>, --password <password>
 *             Remote jmx agent password
 *
 *         -pwf <passwordFilePath>, --password-file <passwordFilePath>
 *             Path to the JMX password file
 *
 *         -u <username>, --username <username>
 *             Remote jmx agent username
 *
 *         --
 *             This option can be used to separate command-line options from the
 *             list of argument, (useful when arguments might be mistaken for
 *             command-line options
 *
 *         [<keyspace> <tables>...]
 *             The keyspace followed by one or many tables
 */
public class CleanupOperationRequest extends OperationRequest {

    @NotNull
    public final String keyspace;

    @NotEmpty
    public final Set<String> tables;

    @Min(0)
    public final int jobs;

    @JsonCreator
    public CleanupOperationRequest(@JsonProperty("keyspace") final String keyspace,
                                   @JsonProperty("tables") final Set<String> tables,
                                   @JsonProperty("jobs") final int jobs) {
        this.keyspace = keyspace;
        this.tables = tables;
        this.jobs = jobs;
    }
}