package groovy_in_action._13_database.advanced

import groovy.sql.GroovyResultSet
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy_in_action.util.DbUtil

import java.sql.ResultSetMetaData

def sql = DbUtil.create()
DbUtil.populate(sql)

def dump(Sql sql, tablename) {
    println " CONTENT OF TABLE ${tablename} ".center(32, '-')
    sql.eachRow('SELECT * FROM ' + tablename) { GroovyResultSet rs ->
        def meta = rs.getMetaData()
        if (meta.columnCount <= 0) return
        for (i in 0..<meta.columnCount) {
            // Counts from 1
            print "${i}: ${meta.getColumnLabel(i + 1)}".padRight(20)
            // Counts from 0 and possibly null
            println rs[i]?.toString()
        }
        println '-' * 32
    }
}

def dump2(Sql sql, tablename) {
    def printColNames = { ResultSetMetaData meta ->
        def width = meta.columnCount * 12
        println " CONTENT OF TABLE ${tablename} ".center(width, '-')
        (1..meta.columnCount).each {
            print meta.getColumnLabel(it).padRight(12)
        }
        println()
        println '-' * width
    }
    def printRow = { GroovyResultSet rs ->
        rs.toRowResult().values().each {
            print it.toString().padRight(12)
        }
        println()
    }
    sql.eachRow('SELECT * FROM ' + tablename, printColNames, printRow)
}

// Captures standard out
def baos = new ByteArrayOutputStream()
System.setOut(new PrintStream(baos))

dump(sql, 'Athlete')
assert baos.toString().readLines()*.trim().join('\n') == '''\
--- CONTENT OF TABLE Athlete ---
0: ATHLETEID        0
1: FIRSTNAME        Paul
2: LASTNAME         Tergat
3: DATEOFBIRTH      1969-06-17
--------------------------------
0: ATHLETEID        1
1: FIRSTNAME        Khalid
2: LASTNAME         Khannouchi
3: DATEOFBIRTH      1971-12-22
--------------------------------
0: ATHLETEID        2
1: FIRSTNAME        Ronaldo
2: LASTNAME         da Costa
3: DATEOFBIRTH      1970-06-07
--------------------------------\
'''

baos.reset()
dump2(sql, 'Athlete')

assert baos.toString().readLines()*.trim().join('\n') == '''\
----------- CONTENT OF TABLE Athlete -----------
ATHLETEID   FIRSTNAME   LASTNAME    DATEOFBIRTH
------------------------------------------------
0           Paul        Tergat      1969-06-17
1           Khalid      Khannouchi  1971-12-22
2           Ronaldo     da Costa    1970-06-07\
'''

sql.close()