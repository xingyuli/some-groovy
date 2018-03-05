package groovy_in_action._13_database.dataset

import groovy_in_action.util.DbUtil

def sql = DbUtil.create()
DbUtil.populate(sql)

def athletes = sql.dataSet('Athlete')

def result = []
// Treats an SQL table like a list of maplike rows
athletes.each { result << it.firstname }
// Initially we have our three sample athletes
assert result == ['Paul', 'Khalid', 'Ronaldo']

athletes.add(
    firstname: 'Paula',
    lastname: 'Radcliffe',
    dateOfBirth: '1973-12-17'
)
result = athletes.rows()*.firstname
// Confirm we now have four athletes
assert result == ['Paul', 'Khalid', 'Ronaldo', 'Paula']

sql.close()