package groovy_in_action._13_database.dataset

import groovy_in_action.util.DbUtil

def sql = DbUtil.create()
DbUtil.populate(sql)

sql.execute '''
  DROP   VIEW AthleteRecord IF EXISTS;
  CREATE VIEW AthleteRecord AS
    SELECT * FROM Athlete LEFT OUTER JOIN Record
      ON fkAthlete=athleteId;
'''

def records = sql.dataSet('AthleteRecord').findAll {
    it.firstname == 'Khalid'
}
def result = records.rows().collect { "$it.lastname $it.venue" }
assert ['Khannouchi London', 'Khannouchi Chicago'] == result

sql.close()
