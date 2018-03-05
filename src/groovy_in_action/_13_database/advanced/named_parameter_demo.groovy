package groovy_in_action._13_database.advanced

import groovy.transform.Canonical
import groovy_in_action.util.DbUtil

def sql = DbUtil.create()
DbUtil.populate(sql)

def insertPrefix = '''
INSERT INTO Athlete (firstname, lastname, dateOfBirth) VALUES 
'''

sql.execute insertPrefix + '(:firstname, :lastname, :dob)', first: 'Ingrid',
        last: 'Kristiansen', dob: '1956-03-21'

def loroupe = [first: 'Telga', last: 'Loroupe', dob: '1973-05-09']
sql.execute insertPrefix + '(:firstname, :lastname, :dob)', loroupe

@Canonical class Athlete {
    String first, last, dob
}
def ndereba = new Athlete('Catherine', 'Ndereba', '1972-07-21')
sql.execute insertPrefix + '(?.first, ?.last, ?.dob)', ndereba

def takahashi = new Athlete('Naoko', 'Takahashi')
def takahaShiExtra = [dob: '1972-05-06']
def namedOrdinalSuffix = '(?1.first, ?.last, ?2.dob)'
sql.execute insertPrefix + namedOrdinalSuffix, takahashi, takahaShiExtra

assert sql.firstRow('SELECT COUNT(*) AS num FROM Athlete').num == 7

sql.close()
