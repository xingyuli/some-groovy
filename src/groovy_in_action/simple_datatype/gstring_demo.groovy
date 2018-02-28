package groovy_in_action.simple_datatype

import static java.util.Calendar.*

def me = 'Tarzan'
def you = 'Jane'
def line = "me $me - you $you" // 1. Abbreviated dollar syntax
assert line == 'me Tarzan - you Jane'
assert line instanceof GString
assert line.strings[0] == 'me '
assert line.strings[1] == ' - you '
assert line.values[0] == 'Tarzan'
assert line.values[1] == 'Jane'

println "abc".class // still java.lang.String
println "abc $me".class // placeholder present, is groovy.lang.GString

TimeZone.default = TimeZone.getTimeZone('GMT')
def date = new Date(0)
def dateMap = [
        y:date[YEAR] - 1900,
        m:date[MONTH],
        d:date[DAY_OF_MONTH]
]
def out = "Year $dateMap.y Month $dateMap.m Day $dateMap.d" // 2. Extended dot syntax
assert out == 'Year 70 Month 0 Day 1'

def tz = TimeZone.getTimeZone('GMT')
def format = 'd MMM YYYY HH:mm:SS z'
out = "Date is ${date.format(format, tz)} !" // 3. Full syntax with braces
assert out == 'Date is 1 Jan 1970 00:00:00 GMT !'

// 4. Multiline GStrings
def sql = """
SELECT FROM MyTable
  WHERE Year = $dateMap.y
"""
println "start" + sql + "end"
assert sql == """
SELECT FROM MyTable
  WHERE Year = 70
"""

out = "my 0.02\$" // Escaped dollar sign
assert out == 'my 0.02$' // Literal dollar sign


/* From Java to Groovy */

System.out.print("Hello Groovy!");
System.out.print('Hello Groovy!');
print('Hello Groovy!');
print 'Hello Groovy!'

String greeting = 'Hello Groovy!'

assert greeting.startsWith('Hello')

assert greeting.getAt(0) == 'H'
assert greeting[0] == 'H'

assert greeting.indexOf('Groovy') >= 0
assert greeting.contains('Groovy')

assert greeting[6..11] == 'Groovy'
assert 'Hi' + greeting - 'Hello' == 'Hi Groovy!'

assert greeting.count('o') == 3

assert 'x'.padLeft(3) == '  x'
assert 'x'.padRight(3, '_') == 'x__'

assert 'x'.center(3) == ' x '
assert 'x' * 3 == 'xxx'