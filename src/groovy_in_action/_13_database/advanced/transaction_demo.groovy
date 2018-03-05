package groovy_in_action._13_database.advanced

import groovy_in_action.util.DbUtil

def sql = DbUtil.create()
DbUtil.populate(sql)

sql.withTransaction {
    DbUtil.insertAthlete(sql, 'Haile', 'Gebrselassie', '1973-04-18')
    DbUtil.insertAthlete(sql, 'Patrick', 'Makau', '1985-03-02')
}

assert sql.firstRow('SELECT COUNT(*) as num FROM Athlete').num == 5

sql.close()