package groovy_in_action.simple_datatype

import java.util.regex.Matcher

assert "abc" == /abc/
assert "\\d" == /\d/

def reference = 'hello'
assert reference == /$reference/

def twister = 'she sells sea shells at the sea shore of seychelles'

// twister must contain a substring of size 3 that starts with s and ends with a
assert twister =~ /s.a/ // 1. Regex find operator =~, usable in an if statement

def finder = (twister =~ /s.a/) // 2. Find expression evaluates to a Matcher object
assert finder instanceof Matcher

// twister must contain only words delimited by single spaces
assert twister ==~ /(\w+ \w+)*/ // Regex match operator

def WORD = /\w+/
matches = (twister ==~ /($WORD $WORD)*/) // Match expression evaluates to a Boolean
assert matches instanceof Boolean

assert !(twister ==~ /s.e/) // Match is full unlike find

def wordsByX = twister.replaceAll(WORD, 'x')
assert wordsByX == 'x x x x x x x x x x'

def words = twister.split(/ /) // Split returns a list of words
assert words.size() == 10
assert words[0] == 'she'
