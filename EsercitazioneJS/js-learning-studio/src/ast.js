// ast.js — parse code using Acorn (via ESM import from unpkg)
export async function parse(code) {
  // dynamic import of acorn.mjs from unpkg
  const acorn = await import('https://unpkg.com/acorn@8.8.2/dist/acorn.mjs');
  try {
    const ast = acorn.parse(code, { ecmaVersion: 2020, sourceType: 'module' });
    return ast;
  } catch (err) {
    throw err;
  }
}

export function prettyAst(ast) {
  return JSON.stringify(ast, null, 2);
}