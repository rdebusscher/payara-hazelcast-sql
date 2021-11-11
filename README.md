# payara hazelcast-sql

Examples using Hazelcast SQL with Payara Micro.

Require Payara Micro 5.2021.3 or later as it needs Hazelcast 4.2.

## cache

Read 'fixed' data from a database and perform SQL queries on the cached data.

Requires MySQL (or other database) to store the data.

see `commands.txt` for the commands to get the example up and running.

## sessions

Read user name from logged in users (simulated by putting a name into the HTTP session)

see `commands.txt` for the commands to get the example up and running.


## timings

Compare 4 different ways of reading and filtering data cached with Hazelcast.

. local: retrieve Map from Hazelcast and process it through Java Stream
. slow: retrieve Map from Hazelcast and process it through _for_ loop
. predicate: use Predicate/Criteria API to filter distributed.
. sql: use Hazelcast SQL to _filter_ distributed.

Data are stored from a JSON into cache at application start up.
