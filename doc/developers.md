## Developers Guide
The Cassandra operator is primarily written in Java and uses Maven as the build and dependency management system.

### Operator
The main logic for the controller portion of the Cassandra operator can in found in the operator/ module. The Cassandra
sidecar also sits within this module as ends up being built in the same jar (with just a different main entry point), 
this reduces the number of docker image layers that get pulled. 

### Model
Defined kubernetes CRD objects can be found in the model/ module. These are defined by a json schema and there Java
classes are generated when the model/ module is built by maven. If you are having trouble with resolving model classes
in your IDE you made need to configure the target directory as a source/generated sources directory

### Backup
The somewhat stand-alone backup agent for Cassandra. It can be used as a separate stand alone backup process (controlled via
command line parameters) or embedded in the operator sidecar. 

### k8s-addons
This module contains add-on components that extend Cassandra and make it easier to work and operate in a Kubernetes environment.
The k8s-addon jar gets included Cassandra docker image and is dropped into the Cassandra classpath. 

## Building
To build the project and generate relevant jars, from the project root, run: 
```bash
make
```
This will build all the submodules defined in the parent pom.xml of java/ and it will also build all required docker images.

To define a registry for the docker containers, set the `DOCKER_REPO` environment variable. E.g. `DOCKER_REPO=gcr.io/foo/bar/`
