/*
  main.js — versione ULTRA dettagliata per imparare JavaScript
  - Commenti esplicativi in italiano
  - Correzioni di best practice (let/const, controllo DOM, event delegation)
  - Esempi commentati per ogni concetto importante
*/

'use strict'; // modalità strict per evitare errori silenziosi

// ===================== VARIABILI E TIPI DI DATO =====================
// - Preferisci `const` per valori che non saranno riassegnati, `let` per valori mutabili.
// - Evita `var` salvo per casi legacy (hoisting e scoping diversi).

const nome = 'Alex';            // string
let eta = 25;                   // number (mutabile se necessario)
let attivo = true;              // boolean (usa let, non var)
const valori = [1, 2, 3];       // array (puoi mutare il contenuto anche se const)
const persona = {               // object
  nome: 'Alex',
  eta: 25,
};
let indefinito;                 // undefined
const vuoto = null;             // null

// Nota: `typeof null` è "object" per retrocompatibilità del linguaggio.

// ===================== OPERATORI E VALORI TRUTHY/FALSY =====================
const somma = 10 + 5;          // 15
const confronto = eta > 18;    // true/false
const logico = eta > 18 && nome === 'Alex';

// Truthy/Falsy: '', 0, null, undefined, NaN e false sono "falsy"; tutto il resto è truthy.

// ===================== CONDIZIONALI =====================
if (eta >= 18) {
  console.log('Maggiorenne');
} else if (eta >= 13) {
  console.log('Adolescente');
} else {
  console.log('Bambino');
}

// Esempio con operatore ternario (compatto):
const stato = eta >= 18 ? 'adulto' : 'minore';

// ===================== CICLI =====================
// classico for
for (let i = 0; i < 5; i++) {
  console.log('for i=', i);
}

// for...of (leggibile per array)
for (const n of valori) {
  console.log('for..of', n);
}

// for...in (usa per proprietà di oggetti, non per array)
for (const k in persona) {
  if (Object.prototype.hasOwnProperty.call(persona, k)) {
    console.log('prop', k, '=>', persona[k]);
  }
}

// metodi degli array
const raddoppiati = valori.map(n => n * 2);        // [2,4,6]
const filtrati = valori.filter(n => n > 1);        // [2,3]
const sommaVal = valori.reduce((acc, n) => acc + n, 0); // 6

// ciclo while (usa una variabile separata se non vuoi cambiare `eta` direttamente)
let contatore = 0;
while (contatore < 3) {
  console.log('while', contatore);
  contatore++;
}

// ===================== FUNZIONI =====================
// Function declaration (hoisted — disponibile prima della definizione)
function saluta(nome) {
  return `Ciao, ${nome}!`;
}
console.log(saluta('Alex'));

// Function expression
const sommaNumeri = function (a, b) {
  return a + b;
};

// Arrow function (attenzione al `this` che non viene rebindato)
const sommaArrow = (a, b) => a + b;

// Esempio: differenza di `this`
const obj = {
  x: 10,
  getX() { return this.x; },              // `this` è l'oggetto quando chiamato come metodo
  getXArrow: () => { return undefined; } // arrow usa `this` lessicale (qui non è l'oggetto)
};
console.log('getX', obj.getX());
console.log('getXArrow', obj.getXArrow());

// ===================== OGGETTI E DESTRUCTURING =====================
const { nome: nomePersona, ...rest } = persona; // destructuring + rest
console.log('nomePersona', nomePersona, 'rest', rest);

// aggiungere e cancellare proprietà
persona.email = 'alex@example.com';
delete persona.eta; // elimina la proprietà

// clone/shallow copy
const copiaPersona = { ...persona };

// ===================== CLASSI E PROTOTIPI =====================
class Utente {
  constructor(nome) {
    this.nome = nome;
  }
  saluta() {
    return `Ciao ${this.nome}`;
  }
}
const u = new Utente('Alex');
console.log(u.saluta());

// Prototype puro
function Person(name) {
  this.name = name;
}

Person.prototype.say = function () { return `Hi ${this.name}`; };
const p = new Person('Luigi');
console.log(p.say());

// ===================== SCOPE E CLOSURE =====================
function creatoreContatore() {
  let count = 0; // variabile privata
  return function () {
    count++;
    return count;
  };
}