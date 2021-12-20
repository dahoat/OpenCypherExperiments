# Inconvenience in Open Cypher ANTLRv4 grammar

The open cypher grammar says
```antlrv4
oC_MultiPartQuery
              :  ( ( oC_ReadingClause SP? )* ( oC_UpdatingClause SP? )* oC_With SP? )+ oC_SinglePartQuery ;

```

However, when using a query like 
```cypher
MATCH (a) WITH a MATCH (b) SET a.prop = 'match 2' WITH a, b MATCH (c) WITH a, b, c MATCH (d) RETURN *
```
we cannot distinguish, whether the `SET` part belongs to the seconds `MATCH` or the first or third one.

## Demo
This project contains a Spring application which takes the ANTLRv4 grammar of open cypher 
from https://s3.amazonaws.com/artifacts.opencypher.org/M18/Cypher.g4, generates a visitor 
from it on building of the project and then parses the example from above, displaying 
the content of the resulting `oC_ReadingClause` and `oC_UpdatingClause` contexts. 

### Running
To build and run the project, have Maven installed and run (for Linux): 
```shell
mvn package
java -jar target/OpenCypherExperiments-0.0.1-SNAPSHOT.jar
```

The relevant output should be:
```text
Enter visitOC_MultiPartQuery
Whole context: MATCH (a) WITH a MATCH (b) SET a.prop = 'match 2' WITH a, b MATCH (c) WITH a, b, c MATCH (d) RETURN *
OC_ReadingClause:
        MATCH (a)
        MATCH (b)
        MATCH (c)
OC_UpdatingClause:
        SET a.prop = 'match 2'
To which OC_ReadingClause does this OC_UpdatingClause belong?
Leave visitOC_MultiPartQuery
```
