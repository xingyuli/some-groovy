package groovy_in_action._13_database.crud

import groovy_in_action.util.DbUtil

def sql = DbUtil.create()
DbUtil.populate(sql)

def expected = ['Paul Tergat', 'Khalid Khannouchi', 'Ronaldo da Costa']

/* Reads using query */

def rowNum = 0
sql.query('SELECT firstname, lastname FROM Athlete') { resultSet ->
    // External iteration on the ResultSet
    while (resultSet.next()) {
        // Accesses properties via JDBC API calls
        def first = resultSet.getString(1)
        def last = resultSet.getString('lastname')
        assert expected[rowNum++] == "$first $last"
    }
}

/* Reads using eachRow */

rowNum = 0
// row is of type GroovyRowResult
sql.eachRow('SELECT * FROM Athlete') { row ->
    // Accesses properties via map or list styles
    def first = row[1]
    def last = row.lastname
    assert expected[rowNum++] == "$first $last"
}

/* Reads using firstRow */

def first = sql.firstRow('SELECT lastname, dateOfBirth FROM Athlete')
// Accesses properties via map or list styles
assert first.values().sort().join(',') == 'Tergat,1969-06-17'

/* Reads using rows */

List athletes = sql.rows('SELECT firstname, lastname FROM Athlete')
assert athletes.size() == 3
// Accesses properties via map or list styles
assert athletes.collect { "${it.firstname} ${it[-1]}" } == expected

/* More efficient size calculation */

assert sql.firstRow('SELECT COUNT(*) AS num FROM Athlete').num == 3


sql.close()