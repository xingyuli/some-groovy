package groovy_in_action.simple_datatype

def myFairString = 'The rain in Spain stays mainly in the plain!'

// words that end with 'ain': \b\w*ain\b
def wordEnding = /\w*ain/
def rhyme = /\b$wordEnding\b/
def found = ''
myFairString.eachMatch(rhyme) { match -> // 1. String.eachMatch(regex){}
    found += match + ' '
}
assert found == 'rain Spain plain '

found = ''
(myFairString =~ rhyme).each { match -> // 2. Matcher.each{}
    found += match + ' '
}
assert found == 'rain Spain plain '

def cloze = myFairString.replaceAll(rhyme) { it - 'ain' + '___' } // 3. String.replaceAll(regex){}
assert cloze == 'The r___ in Sp___ stays mainly in the pl___!'
