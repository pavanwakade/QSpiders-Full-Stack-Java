## History of JavaScript (**3-2-25**)

- **1995**: JavaScript was created by **Brendan Eich** while working at **Netscape Corporation**.
  - Initially called **Mocha**, then renamed **LiveScript**, and finally **JavaScript** in the same year.
- **1997**: JavaScript was handed over to **ECMA (European Computer Manufacturers Association)** for standardization.
  - ECMA released the first official specification, **ECMAScript (ES1)**, in 1997.
  - ECMA is a **non-profit organization** responsible for maintaining and evolving the **ECMAScript standard**.

## JavaScript Features

- **High-Level**: portability,readabilitya,writyabiloty,Simplifies low-level operations for ease of use.
- **Object-Oriented & Object-Based**: Supports **prototype-based inheritance**.
- **Single-Threaded**: Executes one Task(operation) at a time.
- **Synchronous (by default)**: Executes code sequentially.
- **Interpreted & Just-in-Time (JIT) Compiled**:
  - Initially, JavaScript was interpreted.
  - Modern JavaScript engines use **JIT compilation** for better performance.

## Additional Concepts

- **Readability & Writability**:
  - JavaScript is easy to **read, write, and understand**.
  - This makes it easier to **find and fix errors** efficiently.
- **Platform Independence**:
  - JavaScript code can **run on any platform** (Windows, macOS, Linux, etc.) with a browser or runtime like Node.js.
- **Productivity**:
  - JavaScript is **highly productive** due to its vast ecosystem of **libraries and frameworks**.
  - Developers can write code faster using tools like **React, Angular, Vue, and Express.js**.

## What is AST (Abstract Syntax Tree)

- After parsing the code, it is converted into an **AST**.
- The AST breaks the code into small parts to identify **syntactical errors and mistakes** efficiently.

## Script Tag

- Used to write **JavaScript** code in an HTML file.

## NoScript

- If the JavaScript engine is disabled, the **NoScript** tag can be used to display alternative content.

## Ways to Add JavaScript

1. **Internal** - Write JavaScript code inside the **script** tag within an HTML file.

2. **External** - Create a separate file with a **.js** extension and link it to the HTML file using the **script** tag.

## Tokens

- Tokens are the smallest unit of a programming language.(;,...)

### Types of Tokens

1. **Keywords** - Reserved words or pre defined words recognized by the JavaScript engine (e.g., `let`, `var`, `const`, `function`).

2. **Identifiers** - Names given by the programmer to JavaScript members like variables, objects, and functions.

3. **Literals** - Fixed values or data in JavaScript.

   - **Primitive Literals**: Numbers, Booleans, Undefined, Null, Strings, NaN, Symbols.
   - **Non-Primitive Literals**: Objects, Arrays, Functions.

4. **Operators** - Symbols that perform operations on operands (e.g., `+`, `-`, `*`, `/`).

5. **Separators** - Symbols used to separate code elements (e.g., `{}`, `()`, `,`, `;`).

6. **Comments** - Used to add explanations or disable code execution (e.g., `// single-line`, `/* multi-line */`).

7. **Punctuators** - Symbols that define structure (e.g., `[]`, `{}`, `()`).

**4-2-25**

## variavles

1.var (eg var=10; var name="pavan";
2.let
3.const

🌟 **JavaScript Variable Behavior Table** 🌟

| Variable Type | Declaration. | Initialization. | Decl & Init. | Updation. | Redecl & Reinit. |
| ------------- | ------------ | --------------- | ------------ | --------- | ---------------- |
| **var**       | ✅           | ✅              | ✅           | ✅        | ✅               |
| **let**       | ✅           | ✅              | ✅           | ✅        | ❌               |
| **const**     | ❌           | ✅              | ✅           | ❌        | ❌               |

## JavaScript Data Types

JavaScript has **two main categories** of data types:

### 🔹 **1. Primitive Data Types** (Immutable, Stored by Value)

1. **Number** – Integers & Floating-point values
2. **String** – Textual data
3. **Boolean** – `true` or `false`
4. **Undefined** – Variable declared but not assigned
5. **Null** – Represents an empty or unknown value
6. **Symbol (ES6)** – Unique identifier
7. **BigInt (ES11)** – Large integers beyond `Number.MAX_SAFE_INTEGER`

### 🔹 **2. Non-Primitive (Reference) Data Types** (Mutable, Stored by Reference)

8. **Object** – Key-value pairs
9. **Array** – Ordered collection of values
10. **Function** – Block of reusable code
11. **Date** – Represents date & time
12. **RegExp** – Regular expressions for pattern matching

## Functions

**Nameed Function** or **Regular Function**

````javascript
function demo(){

}

**Arraow Function**

````javascript
let a=()=>{

}

**Anonymous Function**

````javascript
 let b= (function () {

        console.log("immidiateFunction");
        
      })();
