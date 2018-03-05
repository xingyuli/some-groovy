package groovy_in_action._13_database.crud

@Grab('org.hsqldb:hsqldb:2.4.0')
@GrabConfig(systemClassLoader = true)
import groovy.sql.Sql
import org.hsqldb.jdbc.JDBCDataSource

def dataSource = new JDBCDataSource(
        database: 'jdbc:hsqldb:mem:GinA', user: 'sa', password: '')
def sql = new Sql(dataSource)

sql.close()
