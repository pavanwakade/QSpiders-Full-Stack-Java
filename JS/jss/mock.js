//! Variables in JS

//! var
//? Stored in global scope/window object (if declared outside functions)
/* We can:
- declare only: var a;
- declare and initialize: var a = 10;
- update: a = 20;
- redeclare: var a = 50;
*/

//! let
//? Stored in block scope/Temporal Dead Zone (TDZ) before initialization
/* We can:
- declare only: let a;
- declare and initialize: let a = 10;
- update: a = 20;
//?Cannot:
- redeclare: let a = 50; // SyntaxError in same scope
*/

//! const
//? Stored in block scope/Temporal Dead Zone (TDZ) before initialization
/* We can:
- declare and initialize: const a = 10;
//?Cannot:
- declare only: const a; // SyntaxError
- update: a = 20; // TypeError
- redeclare: const a = 50; // SyntaxError in same scope
*/


//! datatypes (NNBBSSU)
//? Primitive types
//Number
let a = 10; // decimal
let b =10.10; // decimal

//BigInt
let 