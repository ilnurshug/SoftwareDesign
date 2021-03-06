grammar Shell;

command     : assignment
            | pipeCmd
            | simpleCmd
            | ;

pipeCmd     : simpleCmd ( '|' simpleCmd )+ ;

simpleCmd   : cat
            | echo
            | wc
            | pwd
            | exit
            | grep
            | proc;

assignment  : id '=' literal ;

cat         : 'cat' literal* ;
echo        : 'echo' literal+ ;
wc          : 'wc' literal* ;
pwd         : 'pwd' ;
exit        : 'exit' ;
grep        : 'grep' literal+ ;
proc        : literal+ ;

id          : ID ;
varid       : VAR_ID ;

literal     : fullQuoting
            | weakQuoting
            | varid
            | id ;

fullQuoting : FullQString ;

weakQuoting : WeakQString ;

// Lexer

WS          : [ \t]+ -> channel(HIDDEN) ;
GREP        : 'grep' ;
CAT         : 'cat' ;
ECHO        : 'echo' ;
WC          : 'wc' ;
PWD         : 'pwd' ;
EXIT        : 'exit' ;
ASSIGN      : '=' ;
PIPE        : '|' ;
Q_MARK	    : '\'' ;
Q_MARK_2    : '"' ;
ID          : Id ;
VAR_ID      : Var ;

FullQString : '\'' CharFull* '\'' ;
WeakQString : '"' CharWeak* '"' ;


// fragments

fragment Op
            : Opchar+;
fragment Opchar
            : ~[a-zA-Z0-9()[\]{};, \r\t\n'"$_] ;
fragment Id
            : ([a-zA-Z_0-9] | Op)+ ;
fragment Var
            : '$' Id ;

fragment CharFull
            : ~['] ;
fragment CharWeak
            : ~["] ;

fragment CharEscapeSeq
            : '\\' ('b' | 't' | 'n' | 'f' | 'r' | '"' | '\'' | '\\');
fragment StringElement
            : '\u0020'| '\u0021'|'\u0023' .. '\u007F'
            | CharEscapeSeq;