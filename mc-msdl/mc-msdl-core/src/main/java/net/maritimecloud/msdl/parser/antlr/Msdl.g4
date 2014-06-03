grammar Msdl;

@lexer::members {
    public static final int WHITESPACE = 1;
    public static final int COMMENTS = 2;
}

// starting point for parsing a MSDL file
compilationUnit
    :   annotation* namespaceDeclaration? importDeclaration* typeDeclaration* EOF
    ;
  
namespaceDeclaration
    :   'namespace' qualifiedName ';'
    ;
    
importDeclaration
 //  :   'import' '"' qualifiedName ('.')? '"' ';'
  :   'import' StringLiteral ';'
//    :  'import' StringLiteral ';'
    ;  
    
typeDeclaration
    :   annotation* serviceDeclaration
    |   annotation* enumDeclaration
    |   annotation* messageDeclaration
    |   annotation* broadcastDeclaration
    |   annotation* endpointDeclaration
    |   ';'
    ;
    
serviceDeclaration
    :   'service' Identifier
        '{'  (serviceBody)*  '}'
    ;

serviceBody
    :   messageDeclaration
    |   enumDeclaration
    |   broadcastDeclaration
    |   endpointDeclaration
    ;

messageDeclaration
    :   'message' Identifier
        fields
    ;

broadcastDeclaration
    :    'broadcast' Identifier
         fields
       //  '=' Digits ';'
    ;

endpointDeclaration
    :   'endpoint' Identifier
        '{'  (function)*  '}'
    ;

function
    :    returnType Identifier
        '('
        functionArgument? (',' functionArgument)*
         ')' ';'
    ;
    
functionArgument
    : Digits ':' type Identifier
    ;
    
returnType
    :   'void' |
        type
    ;
    
fields
    :   '{' field*  '}'
    ;

field 
    : annotation* Digits ':'  required? type Identifier ';'
    ;

required
    :   'required'
    ;

enumDeclaration
    :   'enum' Identifier
        enumBody
    ;
    
enumBody
    :   '{' (enumTypeDeclaration)*  '}'
    ;


enumTypeDeclaration
    :  Identifier '=' Digits ';'
    ;





// ANNOTATIONS

annotation
    :   '@' annotationName ( '(' ( elementValuePairs | elementValue )? ')' )?
    ;

annotationName : qualifiedName ;

elementValuePairs
    :   elementValuePair (',' elementValuePair)*
    ;

elementValuePair
    :   Identifier '=' elementValue
    ;

elementValue
    :   StringLiteral
    |   BooleanLiteral
    |   elementValueArrayInitializer
    ;

elementValueArrayInitializer
    :   '{' (elementValue (',' elementValue)*)? (',')? '}'
    ;

qualifiedName
    :   Identifier ('.' Identifier)*
    ;


BooleanLiteral
    :   'true'
    |   'false'
    ;
    
Digits
    :   NonZeroDigit (Digit)*
    ;

fragment
Digit
    :   '0'
    |   NonZeroDigit
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;

StringLiteral
    :   '"' StringCharacters? '"'
    ;

fragment
StringCharacters
    :   StringCharacter+
    ;

fragment
StringCharacter
    :   ~["\\]
//    |   EscapeSequence
    ;

type
   : primitiveType
   | complexType
   | qualifiedName
   ;

complexType
   :  'list' '<' type '>'
   |  'set' '<' type '>'
   |  'map' '<' type ',' type '>'
   ;
   
primitiveType
    :   'double'
    |   'float'
    |   'int32'
    |   'int64'
    |   'boolean' // A boolean true or false
    |   'string'  // A string must always contain UTF-8 encoded or 7-bit ASCII text.
    |   'binary'  // May contain any arbitrary sequence of bytes
    ;    


//bigint, bigdecimal <- abitrae laengde    

// Primitive types
DOUBLE    : 'double';
FLOAT     : 'float';
INT32     : 'int32';
INT64     : 'int64';
BOOLEAN   : 'boolean';
STRING    : 'string';
BINARY    : 'binary';

// Complex types
LIST : 'list';
SET : 'set';
MAP : 'map';

NAMESPACE : 'namespace';
SERVICE : 'service';
ENUM : 'enum';
IMPORT : 'import';
MESSAGE : 'message';
REQUIRED : 'required';

ENDPOINT : 'endpoint';
VOID : 'void';

LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
LBRACK          : '[';
RBRACK          : ']';
SEMI            : ';';
COMMA           : ',';
DOT             : '.';


ASSIGN          : '=';
GT              : '>';
LT              : '<';
BANG            : '!';
TILDE           : '~';
QUESTION        : '?';
COLON           : ':';
EQUAL           : '==';
LE              : '<=';
GE              : '>=';
NOTEQUAL        : '!=';
AND             : '&&';
OR              : '||';
INC             : '++';
DEC             : '--';
ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';
BITAND          : '&';
BITOR           : '|';
CARET           : '^';
MOD             : '%';
        
Identifier
    :   JavaLetter JavaLetterOrDigit*
    ;


fragment
JavaLetter
    :   [a-zA-Z$_] // these are the "java letters" below 0xFF
    |   // covers all characters above 0xFF which are not a surrogate
        ~[\u0000-\u00FF\uD800-\uDBFF]
        {Character.isJavaIdentifierStart(_input.LA(-1))}?
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        {Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
    ;

fragment
JavaLetterOrDigit
    :   [a-zA-Z0-9$_] // these are the "java letters or digits" below 0xFF
    |   // covers all characters above 0xFF which are not a surrogate
        ~[\u0000-\u00FF\uD800-\uDBFF]
        {Character.isJavaIdentifierPart(_input.LA(-1))}?
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        {Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
    ;

//
// Whitespace and comments
//

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> channel(COMMENTS)
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> channel(COMMENTS)
    ;