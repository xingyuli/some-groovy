package groovy_in_action._13_database.crud

@Grab('org.hsqldb:hsqldb:2.4.0')
@GrabConfig(systemClassLoader = true)
import groovy.sql.Sql

import java.sql.ResultSet

//def url = 'jdbc:hsqldb:mem:GinA'
//def user = 'sa'
//def password = ''
//def driver = 'org.hsqldb.jdbcDriver'
//def sql = Sql.newInstance(url, user, password, driver)

def sql = Sql.newInstance(
        url: 'jdbc:hsqldb:mem:GinA',
        user: 'sa',
        password: '',
        driver: 'org.hsqldb.jdbcDriver',
        cacheStatements: true,
        resultSetConcurrency: ResultSet.CONCUR_READ_ONLY
)

//Sql.withInstance(url, user, password, driver) { sql ->
//    // use 'sql' instance ...
//}

sql.close()
